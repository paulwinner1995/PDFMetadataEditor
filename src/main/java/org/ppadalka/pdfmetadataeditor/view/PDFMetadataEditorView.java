package org.ppadalka.pdfmetadataeditor.view;

import org.ppadalka.pdfmetadataeditor.dto.PDFMetadataView;

public interface PDFMetadataEditorView {

  void notifyMetadataUpdated(final PDFMetadataView metadata);

  void notifyFileOpened();

  void close();
}
