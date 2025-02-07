package org.ppadalka.pdfmetadataeditor.dialog;

import javafx.stage.Stage;

public interface DialogWindowServiceFactory {
  DialogWindowService create(Stage stage);
}
