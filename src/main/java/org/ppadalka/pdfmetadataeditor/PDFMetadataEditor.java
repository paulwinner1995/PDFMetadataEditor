package org.ppadalka.pdfmetadataeditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class PDFMetadataEditor extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    var bundle = ResourceBundle.getBundle("i18n/text");
    var loader = new FXMLLoader(PDFMetadataEditor.class.getResource("main-view.fxml"));

    loader.setResources(bundle);

    Scene scene = new Scene(loader.load());
    stage.setTitle(bundle.getString("app.title"));
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}