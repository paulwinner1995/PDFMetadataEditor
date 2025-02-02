package org.ppadalka.pdfmetadataeditor.controller;

import org.ppadalka.pdfmetadataeditor.dialog.DialogWindowService;
import org.ppadalka.pdfmetadataeditor.model.PDFDocumentModel;
import org.ppadalka.pdfmetadataeditor.time.CalendarService;
import org.ppadalka.pdfmetadataeditor.view.PDFMetadataView;

import java.io.IOException;

public class PDFMetadataController {
  private final DialogWindowService dialogWindowService;
  private final CalendarService calendarService;

  private PDFDocumentModel model;

  public PDFMetadataController(final DialogWindowService dialogWindowService,
                               final CalendarService calendarService) {
    this.dialogWindowService = dialogWindowService;
    this.calendarService = calendarService;
  }

  public void openDocument(PDFMetadataView view) {
    final var file = dialogWindowService.openPdfFile();

    if (file.isPresent()) {
      try {
        model = new PDFDocumentModel(file.get());
      } catch (IOException e) {
        // TODO: Handle it properly
        throw new RuntimeException(e);
      }

      view.updateState(model.getMetadata());
      view.notifyFileOpened();
    }
  }

  public void saveDocument(PDFMetadataView view) {
    final var metadata = view.getCurrentState();
    final var fileName = metadata.getFileName();

    try {
      model.setMetadata(view.getCurrentState(), calendarService.getCurrentMoment());
      model.save();

      dialogWindowService.showSaveFileNotification(fileName);

      view.updateState(model.getMetadata());
    } catch (IOException e) {
      // TODO: Replace with logger
      System.err.println(e.getMessage());
      // TODO: Show error notification
    }
  }

  public void toggleReadonlyMode(PDFMetadataView view) {
    final boolean readonly = view.isReadonlyModeActive();

    if (readonly && view.hasChanges()) {
      // Discard changes if enter to readonly mode
      view.updateState(view.getBackup());
    }

    view.setControlsDisable(readonly);
  }

  public void close(PDFMetadataView view) {
    if (!view.hasChanges() || dialogWindowService.discardChanges()) {
      // TODO: Show we close model here also?
      view.close();
    }
  }
}
