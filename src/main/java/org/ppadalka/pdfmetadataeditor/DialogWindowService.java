package org.ppadalka.pdfmetadataeditor;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;
import java.util.ResourceBundle;

public class DialogWindowService {
  private final ResourceBundle bundle;
  private final Stage window;

  public DialogWindowService(final ResourceBundle bundle, final Stage window) {
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
}
