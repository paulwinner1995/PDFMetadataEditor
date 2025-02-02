package org.ppadalka.pdfmetadataeditor.view;

import org.ppadalka.pdfmetadataeditor.model.PDFMetadata;

public interface PDFMetadataEditorView {

  void notifyMetadataUpdated(final PDFMetadata state);

  void notifyFileOpened();

  void close();
}
