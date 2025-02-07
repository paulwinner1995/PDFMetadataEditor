package org.ppadalka.pdfmetadataeditor.controller;

import org.ppadalka.pdfmetadataeditor.dto.PDFMetadataForm;
import org.ppadalka.pdfmetadataeditor.view.PDFMetadataEditorView;

public interface PDFMetadataController extends PDFMetadataForm {

  void openDocument(final PDFMetadataEditorView view);

  void saveDocument(final PDFMetadataEditorView view);

  void toggleReadonlyMode(final PDFMetadataEditorView view, boolean readonly);

  void close(final PDFMetadataEditorView view);
}
