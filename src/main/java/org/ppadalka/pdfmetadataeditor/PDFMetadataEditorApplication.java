package org.ppadalka.pdfmetadataeditor;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ppadalka.pdfmetadataeditor.view.PDFMetadataEditorViewFactory;

import java.io.IOException;
import java.util.ResourceBundle;

public class PDFMetadataEditorApplication extends Application {

  private static Injector injector;

  @Inject
  FXMLLoader loader;
  @Inject
  ResourceBundle bundle;
  @Inject
  PDFMetadataEditorViewFactory viewFactory;

  @Override
  public void init() throws Exception {
    injector.injectMembers(this);
  }

  @Override
  public void start(Stage stage) throws IOException {
    loader.setControllerFactory(t -> viewFactory.create(stage));

    Scene scene = new Scene(loader.load());
    stage.setTitle(bundle.getString("app.title"));
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    injector = Guice.createInjector(new PDFMetadataEditorModule());
    launch();
  }
}