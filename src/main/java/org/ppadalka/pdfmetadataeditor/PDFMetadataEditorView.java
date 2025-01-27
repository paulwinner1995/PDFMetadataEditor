package org.ppadalka.pdfmetadataeditor;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PDFMetadataEditorView implements PDFMetadataView {
  @FXML
  private TextField title;
  @FXML
  private TextField subject;
  @FXML
  private TextField author;
  @FXML
  private TextArea keywords;
  @FXML
  private Text created;
  @FXML
  private Text modified;
  @FXML
  private CheckBox readOnly;

  private final PDFMetadataController controller;

  public PDFMetadataEditorView(PDFMetadataController controller) {
    this.controller = controller;
  }

  @FXML
  protected void onReadOnlyStatusChanges() {
    final boolean isReadOnly = readOnly.isSelected();

    title.setDisable(isReadOnly);
    subject.setDisable(isReadOnly);
    author.setDisable(isReadOnly);
    keywords.setDisable(isReadOnly);
  }

  @FXML
  protected void onFileOpen() {
    controller.openDocument(this);
  }

  @FXML
  protected void onClose() {
    Platform.exit();
  }

  @Override
  public void update(PDFMetadata metadata) {
    title.setText(metadata.getTitle());
    author.setText(metadata.getAuthor());
    subject.setText(metadata.getSubject());
    created.setText(metadata.getCreated().toString());
    modified.setText(metadata.getModified().toString());
  }
}