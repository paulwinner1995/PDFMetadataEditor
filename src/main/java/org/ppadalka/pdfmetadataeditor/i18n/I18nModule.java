package org.ppadalka.pdfmetadataeditor.i18n;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.util.ResourceBundle;

public class I18nModule extends AbstractModule {
  @Provides
  @Singleton
  ResourceBundle provideResourceBundle() {
    return ResourceBundle.getBundle("i18n/text");
  }
}
