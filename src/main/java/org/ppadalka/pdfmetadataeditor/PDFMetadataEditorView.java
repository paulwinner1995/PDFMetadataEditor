package org.ppadalka.pdfmetadataeditor;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.time.LocalDateTime;

public class PDFMetadataEditorView implements PDFMetadataView {
  @FXML
  private Text fileName;
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
  private final StringConverter<LocalDateTime> datetime;

  public PDFMetadataEditorView(PDFMetadataController controller,
                               StringConverter<LocalDateTime> datetime) {
    this.controller = controller;
    this.datetime = datetime;
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
    fileName.setText(metadata.getFileName());
    title.setText(metadata.getTitle());
    author.setText(metadata.getAuthor());
    subject.setText(metadata.getSubject());
    created.setText(datetime.toString(metadata.getCreated()));
    modified.setText(datetime.toString(metadata.getModified()));
  }
}