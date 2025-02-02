package org.ppadalka.pdfmetadataeditor.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.ppadalka.pdfmetadataeditor.controller.PDFMetadataController;
import org.ppadalka.pdfmetadataeditor.dto.PDFMetadataView;

import java.time.LocalDateTime;
import java.util.function.Consumer;

import static org.ppadalka.pdfmetadataeditor.util.Predicates.byMenuItemId;

public class PDFMetadataEditorViewImpl implements PDFMetadataEditorView {
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
  @FXML
  private Menu menu;

  private final PDFMetadataController controller;
  private final StringConverter<LocalDateTime> datetime;

  public PDFMetadataEditorViewImpl(PDFMetadataController controller,
                                   StringConverter<LocalDateTime> datetime) {
    this.controller = controller;
    this.datetime = datetime;
  }

  @FXML
  protected void initialize() {
    subscribeToChanges(title, controller::setTitle);
    subscribeToChanges(author, controller::setAuthor);
    subscribeToChanges(subject, controller::setSubject);
    subscribeToChanges(keywords, controller::setKeywords);

    subscribeToChanges(readonly, title::setDisable);
    subscribeToChanges(readonly, author::setDisable);
    subscribeToChanges(readonly, subject::setDisable);
    subscribeToChanges(readonly, keywords::setDisable);
    subscribeToChanges(readonly, controller.createReadonlyModeToggleSubscriber(this));
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
  public void notifyMetadataUpdated(final PDFMetadataView metadata) {
    fileName.setText(metadata.getFileName());
    title.setText(metadata.getTitle());
    author.setText(metadata.getAuthor());
    subject.setText(metadata.getSubject());
    keywords.setText(metadata.getKeywords());
    created.setText(datetime.toString(metadata.getCreated()));
    modified.setText(datetime.toString(metadata.getModified()));
  }

  @Override
  public void notifyFileOpened() {
    menu.getItems().stream()
            .filter(byMenuItemId("fileSave"))
            .forEach(itm -> itm.setDisable(false));
  }

  @Override
  public void close() {
    Platform.exit();
  }

  private void subscribeToChanges(final TextInputControl control, final Consumer<String> consumer) {
    // We don't need blank line as initial value
    control.setText(null);
    // TODO: Set some delay to input field
    control.textProperty()
            .addListener((prop, prev, value) -> consumer.accept(value));
  }

  private void subscribeToChanges(final CheckBox checkbox, final Consumer<Boolean> consumer) {
    checkbox.selectedProperty()
            .addListener((prop, prev, value) -> {
              System.out.println("Checkbox: " + prev + "->" + value);
              consumer.accept(value);
            });
  }
}