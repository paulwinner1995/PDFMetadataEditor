package org.ppadalka.pdfmetadataeditor.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.ppadalka.pdfmetadataeditor.controller.PDFMetadataController;
import org.ppadalka.pdfmetadataeditor.model.PDFMetadata;

import java.time.LocalDateTime;

public class PDFMetadataViewJfxImpl implements PDFMetadataView {
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

  public PDFMetadataViewJfxImpl(PDFMetadataController controller,
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
    keywords.setText(null);
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
  protected void onFileSave() {
    controller.saveDocument(this);
  }

  @FXML
  protected void onCloseButtonClick() {
    controller.close(this);
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
            .fileName(fileName.getText())
            .title(title.getText())
            .subject(subject.getText())
            .author(author.getText())
            .keywords(keywords.getText())
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
    keywords.setText(state.getKeywords());
    subject.setText(state.getSubject());
    created.setText(datetime.toString(state.getCreated()));
    modified.setText(datetime.toString(state.getModified()));
  }

  @Override
  public void close() {
    Platform.exit();
  }
}