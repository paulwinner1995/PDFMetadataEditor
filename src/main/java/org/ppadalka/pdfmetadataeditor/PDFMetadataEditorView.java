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
  private CheckBox readonly;

  private PDFMetadata backup = new PDFMetadata();

  private final PDFMetadataController controller;
  private final StringConverter<LocalDateTime> datetime;

  public PDFMetadataEditorView(PDFMetadataController controller,
                               StringConverter<LocalDateTime> datetime) {
    this.controller = controller;
    this.datetime = datetime;
  }

  @FXML
  protected void initialize() {
    // We don't need blank line as initial value
    title.setText(null);
    author.setText(null);
    subject.setText(null);
  }

  @FXML
  protected void onReadonlyModeToggle() {
    controller.toggleReadonlyMode(this);
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
  public boolean isReadonlyModeActive() {
    return readonly.isSelected();
  }

  @Override
  public boolean hasChanges() {
    return !backup.equals(getCurrentState());
  }

  @Override
  public PDFMetadata getCurrentState() {
    return PDFMetadata.builder()
            .title(title.getText())
            .subject(subject.getText())
            .author(author.getText())
            .build();
  }

  @Override
  public PDFMetadata getBackup() {
    return backup;
  }

  @Override
  public void setControlsDisable(boolean readonly) {
    title.setDisable(readonly);
    subject.setDisable(readonly);
    author.setDisable(readonly);
    keywords.setDisable(readonly);
  }

  @Override
  public void updateState(PDFMetadata state) {
    backup = state;
    fileName.setText(state.getFileName());
    title.setText(state.getTitle());
    author.setText(state.getAuthor());
    subject.setText(state.getSubject());
    created.setText(datetime.toString(state.getCreated()));
    modified.setText(datetime.toString(state.getModified()));
  }
}