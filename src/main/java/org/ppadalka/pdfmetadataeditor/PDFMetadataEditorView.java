package org.ppadalka.pdfmetadataeditor;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PDFMetadataEditorView {
  @FXML
  private TextField title;
  @FXML
  private TextField subject;
  @FXML
  private TextField author;
  @FXML
  private TextArea keywords;
  @FXML
  private CheckBox readOnly;

  @FXML
  protected void onReadOnlyStatusChanges() {
    final boolean isReadOnly = readOnly.isSelected();

    title.setDisable(isReadOnly);
    subject.setDisable(isReadOnly);
    author.setDisable(isReadOnly);
    keywords.setDisable(isReadOnly);
  }

  @FXML
  protected void onClose() {
    Platform.exit();
  }
}