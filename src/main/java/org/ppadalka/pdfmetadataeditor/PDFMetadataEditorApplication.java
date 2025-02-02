package org.ppadalka.pdfmetadataeditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.ppadalka.pdfmetadataeditor.controller.PDFMetadataController;
import org.ppadalka.pdfmetadataeditor.dialog.DialogWindowServiceImpl;
import org.ppadalka.pdfmetadataeditor.time.CalendarServiceImpl;
import org.ppadalka.pdfmetadataeditor.view.PDFMetadataEditorViewImpl;

import java.io.IOException;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

public class PDFMetadataEditorApplication extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    var bundle = ResourceBundle.getBundle("i18n/text");
    var loader = new FXMLLoader(PDFMetadataEditorApplication.class.getResource("view/main.fxml"));

    loader.setResources(bundle);
    loader.setControllerFactory(createFactory(bundle, stage));

    Scene scene = new Scene(loader.load());
    stage.setTitle(bundle.getString("app.title"));
    stage.setScene(scene);
    stage.show();
  }

  private Callback<Class<?>, Object> createFactory(final ResourceBundle bundle, final Stage stage) {
    return param -> {
      final var dialogWindowService = new DialogWindowServiceImpl(bundle, stage);
      final var controller = new PDFMetadataController(dialogWindowService, new CalendarServiceImpl());

      return new PDFMetadataEditorViewImpl(controller, new LocalDateTimeStringConverter(
              FormatStyle.MEDIUM,
              FormatStyle.MEDIUM
      ));
    };
  }

  public static void main(String[] args) {
    launch();
  }
}