package org.ppadalka.pdfmetadataeditor;

import com.google.inject.AbstractModule;
import org.ppadalka.pdfmetadataeditor.datetime.DateTimeModule;
import org.ppadalka.pdfmetadataeditor.dialog.DialogModule;
import org.ppadalka.pdfmetadataeditor.i18n.I18nModule;
import org.ppadalka.pdfmetadataeditor.presenter.PresenterModule;
import org.ppadalka.pdfmetadataeditor.view.ViewModule;

class PDFMetadataEditorModule extends AbstractModule {
  @Override
  protected void configure() {
    install(new DateTimeModule());
    install(new DialogModule());
    install(new I18nModule());
    install(new PresenterModule());
    install(new ViewModule());
  }
}
