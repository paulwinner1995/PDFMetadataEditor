package org.ppadalka.pdfmetadataeditor.view;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import javafx.fxml.FXMLLoader;

import java.util.ResourceBundle;

public class ViewModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(PDFMetadataEditorViewFactory.class).to(PDFMetadataEditorViewFactoryImpl.class);
  }

  @Provides
  FXMLLoader provideFxmlLoader(final ResourceBundle bundle) {
    return new FXMLLoader(getClass().getResource("main.fxml"), bundle);
  }
}
