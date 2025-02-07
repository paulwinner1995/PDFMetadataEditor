package org.ppadalka.pdfmetadataeditor.controller;

import com.google.inject.Inject;
import org.ppadalka.pdfmetadataeditor.model.PDFDocumentModel;
import org.ppadalka.pdfmetadataeditor.datetime.CalendarService;
import org.ppadalka.pdfmetadataeditor.view.PDFMetadataEditorView;

import java.io.IOException;

class PDFMetadataControllerImpl implements PDFMetadataController {
  private final CalendarService calendar;

  private PDFDocumentModel document;
  private PDFMetadataState form = PDFMetadataState.empty();

  @Inject
  PDFMetadataControllerImpl(final CalendarService calendar) {
    this.calendar = calendar;
  }

  @Override
  public void openDocument(final PDFMetadataEditorView view) {
    final var file = view.showOpenFileDialog();

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

  @Override
  public void saveDocument(final PDFMetadataEditorView view) {
    final var metadata = form.getValue();
    final var fileName = metadata.getFileName();

    try {
      document.updateMetadata(metadata, calendar::getCurrentMoment);
      document.saveWithReplace();

      updateState();

      view.notifyMetadataUpdated(form.getValue());
      view.showFileSaveSuccessAlert(fileName);
    } catch (IOException e) {
      view.showFileSaveFailedAlert(fileName);
      // TODO: Replace with logger
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void toggleReadonlyMode(final PDFMetadataEditorView view, boolean readonly) {
    if (readonly && form.hasChanges()) {
      form.restore();
      view.notifyMetadataUpdated(form.getValue()); // Discard changes if enter to readonly mode
    }
  }

  public void close(final PDFMetadataEditorView view) {
    if (!form.hasChanges() || view.showDiscardChangesDialog()) {
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
