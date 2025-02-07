module org.ppadalka.pdfmetadataeditor {
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires org.apache.pdfbox;
  requires org.apache.commons.logging;
  requires com.google.guice;
  requires com.google.guice.extensions.assistedinject;
  requires jakarta.inject;

  exports org.ppadalka.pdfmetadataeditor;

  opens org.ppadalka.pdfmetadataeditor.controller to com.google.guice;
  opens org.ppadalka.pdfmetadataeditor.dialog to com.google.guice;
  opens org.ppadalka.pdfmetadataeditor.datetime to com.google.guice;
  opens org.ppadalka.pdfmetadataeditor.i18n to com.google.guice;
  opens org.ppadalka.pdfmetadataeditor.view to com.google.guice, javafx.fxml;
  opens org.ppadalka.pdfmetadataeditor to com.google.guice;
}