package org.ppadalka.pdfmetadataeditor.view;

import org.ppadalka.pdfmetadataeditor.dto.PDFMetadataView;

import java.io.File;
import java.util.Optional;

public interface PDFMetadataEditorView {

  Optional<File> showOpenFileDialog();

  boolean showDiscardChangesDialog();

  void showFileSaveSuccessAlert(final String fileName);

  void showFileSaveFailedAlert(final String fileName);

  void notifyMetadataUpdated(final PDFMetadataView metadata);

  void notifyFileOpened();

  void close();
}
