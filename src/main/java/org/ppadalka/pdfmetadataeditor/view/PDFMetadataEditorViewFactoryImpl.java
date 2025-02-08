package org.ppadalka.pdfmetadataeditor.view;

import com.google.inject.Inject;
import com.google.inject.Provider;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.ppadalka.pdfmetadataeditor.dialog.DialogWindowServiceFactory;
import org.ppadalka.pdfmetadataeditor.presenter.PDFMetadataPresenter;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;

class PDFMetadataEditorViewFactoryImpl implements PDFMetadataEditorViewFactory {
  private final Provider<PDFMetadataPresenter> presenterProvider;
  private final DialogWindowServiceFactory dialogWindowServiceFactory;

  @Inject
  PDFMetadataEditorViewFactoryImpl(
          final Provider<PDFMetadataPresenter> presenterProvider,
          final DialogWindowServiceFactory dialogWindowServiceFactory
  ) {
    this.presenterProvider = presenterProvider;
    this.dialogWindowServiceFactory = dialogWindowServiceFactory;
  }

  @Override
  public PDFMetadataEditorView create(Stage window) {
    return new PDFMetadataEditorViewImpl(
            presenterProvider.get(),
            dialogWindowServiceFactory.create(window),
            createDateTimeConverter()
    );
  }

  private StringConverter<LocalDateTime> createDateTimeConverter() {
    return new LocalDateTimeStringConverter(FormatStyle.MEDIUM, FormatStyle.MEDIUM);
  }
}
