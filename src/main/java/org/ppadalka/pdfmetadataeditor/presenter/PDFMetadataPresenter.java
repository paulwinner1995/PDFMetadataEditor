package org.ppadalka.pdfmetadataeditor.presenter;

import org.ppadalka.pdfmetadataeditor.command.InputChangeCommand;
import org.ppadalka.pdfmetadataeditor.view.PDFMetadataEditorView;

public interface PDFMetadataPresenter {

  void openDocument(final PDFMetadataEditorView view);

  void saveDocument(final PDFMetadataEditorView view);

  <T> void executeInputChange(final InputChangeCommand<T> command);

  void close(final PDFMetadataEditorView view);
}
