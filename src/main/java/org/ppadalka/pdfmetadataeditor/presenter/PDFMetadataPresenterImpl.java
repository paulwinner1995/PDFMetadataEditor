package org.ppadalka.pdfmetadataeditor.presenter;

import com.google.inject.Inject;
import org.ppadalka.pdfmetadataeditor.command.InputChangeCommand;
import org.ppadalka.pdfmetadataeditor.datetime.CalendarService;
import org.ppadalka.pdfmetadataeditor.model.PDFDocumentModel;
import org.ppadalka.pdfmetadataeditor.model.PDFMetadataForm;
import org.ppadalka.pdfmetadataeditor.view.PDFMetadataEditorView;

import java.io.IOException;

class PDFMetadataPresenterImpl implements PDFMetadataPresenter {
  private final CalendarService calendar;

  private PDFDocumentModel document;
  private PDFMetadataForm form = PDFMetadataForm.empty();

  @Inject
  PDFMetadataPresenterImpl(final CalendarService calendar) {
    this.calendar = calendar;
  }

  @Override
  public void openDocument(final PDFMetadataEditorView view) {
    final var file = view.showOpenFileDialog();

    if (file.isPresent()) {
      try {
        // Close previously opened document if exist
        if (document != null) {
          document.close();
        }

        document = new PDFDocumentModel(file.get());

        updateForm();
      } catch (IOException e) {
        // TODO: Show alert that document is protected by password
        // TODO: Show alert that document is corrupted
        // TODO: Handle it properly
        // TODO: Replace with logger
        System.err.println(e.getMessage());
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

      updateForm();

      view.notifyMetadataUpdated(form.getValue());
      view.showFileSaveSuccessAlert(fileName);
    } catch (IOException e) {
      view.showFileSaveFailedAlert(fileName);
      // TODO: Replace with logger
      System.err.println(e.getMessage());
    }
  }

  @Override
  public <T> void executeInputChange(final InputChangeCommand<T> command) {
    command.execute(form);
  }

  public void close(final PDFMetadataEditorView view) {
    if (!form.hasChanges() || view.showDiscardChangesDialog()) {
      try {
        document.close();
        view.close();
      } catch (IOException e) {
        // TODO: Replace with logger
        System.err.println(e.getMessage());
      }
    }
  }

  private void updateForm() {
    form = PDFMetadataForm.from(document.getMetadata(calendar::toLocalDateTime));
  }
}
