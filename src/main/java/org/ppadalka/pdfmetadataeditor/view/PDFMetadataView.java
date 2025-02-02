package org.ppadalka.pdfmetadataeditor.view;

import org.ppadalka.pdfmetadataeditor.model.PDFMetadata;

public interface PDFMetadataView {
  boolean isReadonlyModeActive();

  boolean hasChanges();

  PDFMetadata getCurrentState();

  PDFMetadata getBackup();

  void setControlsDisable(final boolean disable);

  void updateState(final PDFMetadata state);

  void close();
}
