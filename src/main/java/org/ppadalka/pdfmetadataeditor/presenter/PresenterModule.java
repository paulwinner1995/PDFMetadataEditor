package org.ppadalka.pdfmetadataeditor.presenter;

import com.google.inject.AbstractModule;

public class PresenterModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(PDFMetadataPresenter.class).to(PDFMetadataPresenterImpl.class);
  }
}
