package org.ppadalka.pdfmetadataeditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.ppadalka.pdfmetadataeditor.time.CalendarServiceImpl;
import org.ppadalka.pdfmetadataeditor.dialog.DialogWindowServiceJfxImpl;

import java.io.IOException;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

public class PDFMetadataEditor extends Application {
  // TODO: Use some dependency injection mechanism here
  private PDFMetadataEditorView build(ResourceBundle bundle, Stage stage) {
    final var dialogWindowService = new DialogWindowServiceJfxImpl(bundle, stage);
    final var controller = new PDFMetadataController(dialogWindowService, new CalendarServiceImpl());

    return new PDFMetadataEditorView(controller, new LocalDateTimeStringConverter(
            FormatStyle.MEDIUM,
            FormatStyle.MEDIUM
    ));
  }

  @Override
  public void start(Stage stage) throws IOException {
    var bundle = ResourceBundle.getBundle("i18n/text");
    var loader = new FXMLLoader(PDFMetadataEditor.class.getResource("main-view.fxml"));

    loader.setResources(bundle);
    loader.setControllerFactory(t -> build(bundle, stage));

    Scene scene = new Scene(loader.load());
    stage.setTitle(bundle.getString("app.title"));
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}