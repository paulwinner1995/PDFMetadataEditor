package org.ppadalka.pdfmetadataeditor.view;

import com.google.inject.Inject;
import com.google.inject.Provider;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.ppadalka.pdfmetadataeditor.controller.PDFMetadataController;
import org.ppadalka.pdfmetadataeditor.dialog.DialogWindowServiceFactory;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;

class PDFMetadataEditorViewFactoryImpl implements PDFMetadataEditorViewFactory {
  private final Provider<PDFMetadataController> controllerProvider;
  private final DialogWindowServiceFactory dialogWindowServiceFactory;

  @Inject
  PDFMetadataEditorViewFactoryImpl(
          final Provider<PDFMetadataController> controllerProvider,
          final DialogWindowServiceFactory dialogWindowServiceFactory
  ) {
    this.controllerProvider = controllerProvider;
    this.dialogWindowServiceFactory = dialogWindowServiceFactory;
  }

  @Override
  public PDFMetadataEditorView create(Stage window) {
    return new PDFMetadataEditorViewImpl(
            controllerProvider.get(),
            dialogWindowServiceFactory.create(window),
            createDateTimeConverter()
    );
  }

  private StringConverter<LocalDateTime> createDateTimeConverter() {
    return new LocalDateTimeStringConverter(FormatStyle.MEDIUM, FormatStyle.MEDIUM);
  }
}
