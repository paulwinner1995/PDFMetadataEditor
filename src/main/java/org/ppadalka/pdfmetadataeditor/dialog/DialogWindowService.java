package org.ppadalka.pdfmetadataeditor.dialog;

import java.io.File;
import java.util.Optional;

public interface DialogWindowService {
  Optional<File> openPdfFile();

  boolean discardChanges();

  void showSaveFileNotification(final String fileName);
}
