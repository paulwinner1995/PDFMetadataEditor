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
import org.ppadalka.pdfmetadataeditor.command.AuthorChangeCommand;
import org.ppadalka.pdfmetadataeditor.command.InputChangeCommand;
import org.ppadalka.pdfmetadataeditor.command.KeywordsChangeCommand;
import org.ppadalka.pdfmetadataeditor.command.SubjectChangeCommand;
import org.ppadalka.pdfmetadataeditor.command.TitleChangeCommand;
import org.ppadalka.pdfmetadataeditor.command.ToggleReadonlyCommand;
import org.ppadalka.pdfmetadataeditor.dialog.DialogWindowService;
import org.ppadalka.pdfmetadataeditor.dto.PDFMetadataView;
import org.ppadalka.pdfmetadataeditor.presenter.PDFMetadataPresenter;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;
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

  private final PDFMetadataPresenter presenter;
  private final DialogWindowService dialog;
  private final StringConverter<LocalDateTime> datetime;


  public PDFMetadataEditorViewImpl(final PDFMetadataPresenter presenter,
                                   final DialogWindowService dialog,
                                   final StringConverter<LocalDateTime> datetime) {
    this.presenter = presenter;
    this.dialog = dialog;
    this.datetime = datetime;
  }

  @Override
  public Optional<File> showOpenFileDialog() {
    return dialog.openPdfFile();
  }

  @Override
  public boolean showDiscardChangesDialog() {
    return dialog.discardChanges();
  }

  @Override
  public void showFileSaveSuccessAlert(final String fileName) {
    dialog.showFileSaveSuccessAlert(fileName);
  }

  @Override
  public void showFileSaveFailedAlert(final String fileName) {
    dialog.showFileSaveFailedAlert(fileName);
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

  @FXML
  protected void initialize() {
    subscribeToChanges(title, TitleChangeCommand::new);
    subscribeToChanges(author, AuthorChangeCommand::new);
    subscribeToChanges(subject, SubjectChangeCommand::new);
    subscribeToChanges(keywords, KeywordsChangeCommand::new);
    subscribeToChanges(readonly, this::onReadonlyChange);
  }

  @FXML
  protected void onFileOpen() {
    presenter.openDocument(this);
  }

  @FXML
  protected void onFileSave() {
    presenter.saveDocument(this);
  }

  @FXML
  protected void onCloseButtonClick() {
    presenter.close(this);
  }

  private void onReadonlyChange(boolean readonly) {
    title.setDisable(readonly);
    author.setDisable(readonly);
    subject.setDisable(readonly);
    keywords.setDisable(readonly);
    presenter.executeInputChange(new ToggleReadonlyCommand(this, !readonly, readonly));
  }

  private void subscribeToChanges(final TextInputControl control, final InputChangeCommandFactory<String> command) {
    // TODO: Set some delay to input field
    control.textProperty()
            .addListener((prop, prev, value) -> presenter.executeInputChange(command.create(this, prev, value)));
  }

  private void subscribeToChanges(final CheckBox checkbox, final Consumer<Boolean> consumer) {
    checkbox.selectedProperty()
            .addListener((prop, prev, value) -> {
              System.out.println("Checkbox: " + prev + "->" + value);
              consumer.accept(value);
            });
  }

  @FunctionalInterface
  private interface InputChangeCommandFactory<T> {
    InputChangeCommand<T> create(final PDFMetadataEditorView view, final T oldValue, final T newValue);
  }
}