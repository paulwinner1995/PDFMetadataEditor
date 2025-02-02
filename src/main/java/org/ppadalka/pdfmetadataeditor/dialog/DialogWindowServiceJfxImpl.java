package org.ppadalka.pdfmetadataeditor.dialog;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class DialogWindowServiceJfxImpl implements DialogWindowService {
  private final ResourceBundle bundle;
  private final Stage window;

  public DialogWindowServiceJfxImpl(final ResourceBundle bundle, final Stage window) {
    this.bundle = bundle;
    this.window = window;
  }

  public Optional<File> openPdfFile() {
    final var fileChooser = new FileChooser();

    fileChooser.setTitle(bundle.getString("file.open.chooser.title"));
    fileChooser
            .getExtensionFilters()
            .add(new ExtensionFilter(
                    bundle.getString("file.open.chooser.options.pdf.description"),
                    "*.pdf"
            ));

    return Optional.ofNullable(fileChooser.showOpenDialog(window));
  }

  public boolean discardChanges() {
    final var dialog = new Dialog<ButtonType>();

    dialog.setTitle(bundle.getString("discard.changes.dialog.title"));
    dialog.setContentText(bundle.getString("discard.changes.dialog.content"));
    dialog.getDialogPane()
            .getButtonTypes()
            .addAll(new ButtonType(bundle.getString("dialog.yes.button"), ButtonData.YES),
                    new ButtonType(bundle.getString("dialog.no.button"), ButtonData.NO));

    return dialog
            .showAndWait()
            .map(ButtonType::getButtonData)
            .filter(pressed(ButtonData.YES))
            .isPresent();
  }

  @Override
  public void showSaveFileNotification(final String fileName) {
    final var alert = new Alert(AlertType.INFORMATION);

    alert.setTitle(fileName);
    alert.setHeaderText(bundle.getString("file.save.notification.content"));
    alert.show();
  }

  private Predicate<ButtonData> pressed(final ButtonData button) {
    return pressed -> pressed == button;
  }
}
