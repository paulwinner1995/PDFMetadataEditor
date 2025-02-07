package org.ppadalka.pdfmetadataeditor;

import com.google.inject.AbstractModule;
import org.ppadalka.pdfmetadataeditor.controller.ControllerModule;
import org.ppadalka.pdfmetadataeditor.datetime.DateTimeModule;
import org.ppadalka.pdfmetadataeditor.dialog.DialogModule;
import org.ppadalka.pdfmetadataeditor.i18n.I18nModule;
import org.ppadalka.pdfmetadataeditor.view.ViewModule;

class PDFMetadataEditorModule extends AbstractModule {
  @Override
  protected void configure() {
    install(new ControllerModule());
    install(new DateTimeModule());
    install(new DialogModule());
    install(new I18nModule());
    install(new ViewModule());
  }
}
