package org.ppadalka.pdfmetadataeditor;

public interface PDFMetadataView {
  boolean isReadonlyModeActive();

  void setControlsDisable(final boolean disable);

  void update(final PDFMetadata metadata);
}
