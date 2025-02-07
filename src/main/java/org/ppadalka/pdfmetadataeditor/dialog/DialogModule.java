package org.ppadalka.pdfmetadataeditor.dialog;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class DialogModule extends AbstractModule {
  @Override
  protected void configure() {
    install(new FactoryModuleBuilder()
            .implement(DialogWindowService.class, DialogWindowServiceImpl.class)
            .build(DialogWindowServiceFactory.class));
  }
}
