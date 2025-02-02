package org.ppadalka.pdfmetadataeditor.controller;

import org.ppadalka.pdfmetadataeditor.dialog.DialogWindowService;
import org.ppadalka.pdfmetadataeditor.model.PDFDocumentModel;
import org.ppadalka.pdfmetadataeditor.dto.PDFMetadataForm;
import org.ppadalka.pdfmetadataeditor.time.CalendarService;
import org.ppadalka.pdfmetadataeditor.view.PDFMetadataEditorView;

import java.io.IOException;
import java.util.function.Consumer;

public class PDFMetadataController implements PDFMetadataForm {
  private final DialogWindowService dialog;
  private final CalendarService calendar;

  private PDFDocumentModel document;
  private PDFMetadataState form = PDFMetadataState.empty();

  public PDFMetadataController(final DialogWindowService dialog,
                               final CalendarService calendar) {
    this.dialog = dialog;
    this.calendar = calendar;
  }

  public void openDocument(PDFMetadataEditorView view) {
    final var file = dialog.openPdfFile();

    if (file.isPresent()) {
      try {
        document = new PDFDocumentModel(file.get());

        updateState();
      } catch (IOException e) {
        // TODO: Show alert that document is protected by password
        // TODO: Show alert that document is corrupted
        // TODO: Handle it properly
        throw new RuntimeException(e);
      }

      view.notifyMetadataUpdated(form.getValue());
      view.notifyFileOpened();
    }
  }

  public void saveDocument(PDFMetadataEditorView view) {
    final var metadata = form.getValue();
    final var fileName = metadata.getFileName();

    try {
      document.updateMetadata(metadata, calendar::getCurrentMoment);
      document.saveWithReplace();

      updateState();

      view.notifyMetadataUpdated(form.getValue());

      dialog.showFileSaveSuccessAlert(fileName);
    } catch (IOException e) {
      dialog.showFileSaveFailedAlert(fileName);
      // TODO: Replace with logger
      System.err.println(e.getMessage());
    }
  }

  public Consumer<Boolean> createReadonlyModeToggleSubscriber(PDFMetadataEditorView view) {
    return readonly -> {
      if (readonly && form.hasChanges()) {
        form.restore();
        view.notifyMetadataUpdated(form.getValue()); // Discard changes if enter to readonly mode
      }
    };
  }

  public void close(PDFMetadataEditorView view) {
    if (!form.hasChanges() || dialog.discardChanges()) {
      // TODO: Show we close model here also?
      view.close();
    }
  }

  @Override
  public void setTitle(final String title) {
    form.setTitle(title);
  }

  @Override
  public void setAuthor(final String author) {
    form.setAuthor(author);
  }

  @Override
  public void setSubject(final String subject) {
    form.setSubject(subject);
  }

  @Override
  public void setKeywords(final String keywords) {
    form.setKeywords(keywords);
  }

  private void updateState() {
    form = PDFMetadataState.from(document.getMetadata(calendar::toLocalDateTime));
  }
}
