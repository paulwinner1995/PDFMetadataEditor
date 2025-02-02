package org.ppadalka.pdfmetadataeditor.controller;

import org.ppadalka.pdfmetadataeditor.dialog.DialogWindowService;
import org.ppadalka.pdfmetadataeditor.model.PDFDocumentModel;
import org.ppadalka.pdfmetadataeditor.time.CalendarService;
import org.ppadalka.pdfmetadataeditor.view.PDFMetadataView;

import java.io.IOException;

public class PDFMetadataController {
  private final DialogWindowService dialog;
  private final CalendarService calendar;

  private PDFDocumentModel model;

  public PDFMetadataController(final DialogWindowService dialog,
                               final CalendarService calendar) {
    this.dialog = dialog;
    this.calendar = calendar;
  }

  public void openDocument(PDFMetadataView view) {
    final var file = dialog.openPdfFile();

    if (file.isPresent()) {
      try {
        model = new PDFDocumentModel(file.get());
      } catch (IOException e) {
        // TODO: Show alert that document is protected by password
        // TODO: Show alert that document is corrupted
        // TODO: Handle it properly
        throw new RuntimeException(e);
      }

      view.notifyStateUpdated(model.getMetadata(calendar::toLocalDateTime));
      view.notifyFileOpened();
    }
  }

  public void saveDocument(PDFMetadataView view) {
    final var metadata = view.getCurrentState();
    final var fileName = metadata.getFileName();

    try {
      model.setMetadata(view.getCurrentState(), calendar::getCurrentMoment);
      model.saveWithReplace();

      view.notifyStateUpdated(model.getMetadata(calendar::toLocalDateTime));

      dialog.showFileSaveSuccessAlert(fileName);
    } catch (IOException e) {
      dialog.showFileSaveFailedAlert(metadata.getFileName());
      // TODO: Replace with logger
      System.err.println(e.getMessage());
    }
  }

  public void toggleReadonlyMode(PDFMetadataView view) {
    final boolean readonly = view.isReadonlyModeActive();

    if (readonly && view.hasChanges()) {
      // Discard changes if enter to readonly mode
      view.notifyStateUpdated(view.getBackup());
    }

    view.setControlsDisable(readonly);
  }

  public void close(PDFMetadataView view) {
    if (!view.hasChanges() || dialog.discardChanges()) {
      // TODO: Show we close model here also?
      view.close();
    }
  }
}
