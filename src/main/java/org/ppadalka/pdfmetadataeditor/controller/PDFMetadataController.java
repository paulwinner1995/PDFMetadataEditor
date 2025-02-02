package org.ppadalka.pdfmetadataeditor.controller;

import org.ppadalka.pdfmetadataeditor.dialog.DialogWindowService;
import org.ppadalka.pdfmetadataeditor.model.PDFDocumentModel;
import org.ppadalka.pdfmetadataeditor.model.PDFMetadata;
import org.ppadalka.pdfmetadataeditor.model.PDFMetadataAdapter;
import org.ppadalka.pdfmetadataeditor.time.CalendarService;
import org.ppadalka.pdfmetadataeditor.view.PDFMetadataEditorView;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

public class PDFMetadataController implements PDFMetadataAdapter {
  private final DialogWindowService dialog;
  private final CalendarService calendar;

  private PDFDocumentModel document;
  private PDFMetadataState state;

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

      view.notifyMetadataUpdated(state.getValue());
      view.notifyFileOpened();
    }
  }

  public void saveDocument(PDFMetadataEditorView view) {
    final var metadata = state.getValue();
    final var fileName = metadata.getFileName();

    try {
      document.updateMetadata(metadata, calendar::getCurrentMoment);
      document.saveWithReplace();

      updateState();

      view.notifyMetadataUpdated(state.getValue());

      dialog.showFileSaveSuccessAlert(fileName);
    } catch (IOException e) {
      dialog.showFileSaveFailedAlert(fileName);
      // TODO: Replace with logger
      System.err.println(e.getMessage());
    }
  }

  public Consumer<Boolean> createReadonlyModeToggleSubscriber(PDFMetadataEditorView view) {
    return readonly -> {
      if (readonly && state.hasChanges()) {
        state.restore();
        // Discard changes if enter to readonly mode
        view.notifyMetadataUpdated(state.getValue());
      }
    };
  }

  public void close(PDFMetadataEditorView view) {
    if (!state.hasChanges() || dialog.discardChanges()) {
      // TODO: Show we close model here also?
      view.close();
    }
  }

  @Override
  public void setTitle(final String title) {
    state.setTitle(title);
  }

  @Override
  public void setAuthor(final String author) {
    state.setAuthor(author);
  }

  @Override
  public void setSubject(final String subject) {
    state.setSubject(subject);
  }

  @Override
  public void setKeywords(final String keywords) {
    state.setKeywords(keywords);
  }

  private void updateState() {
    state = new PDFMetadataState(document.getMetadata(calendar::toLocalDateTime));
  }

  private final static class PDFMetadataState implements PDFMetadataAdapter {
    private PDFMetadata value;
    private final PDFMetadata backup;

    public PDFMetadataState(final PDFMetadata metadata) {
      this.value = metadata;
      this.backup = metadata.copy();
    }

    public void restore() {
      this.value = backup.copy();
    }

    public boolean hasChanges() {
      return !Objects.equals(value, backup);
    }

    public PDFMetadata getValue() {
      return value;
    }

    @Override
    public void setTitle(final String title) {
      this.value.setTitle(title);
    }

    @Override
    public void setAuthor(final String author) {
      this.value.setAuthor(author);
    }

    @Override
    public void setSubject(final String subject) {
      this.value.setSubject(subject);
    }

    @Override
    public void setKeywords(final String keywords) {
      this.value.setKeywords(keywords);
    }
  }
}
