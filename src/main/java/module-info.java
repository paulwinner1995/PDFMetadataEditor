module org.ppadalka.pdfmetadataeditor {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires org.apache.pdfbox;
  requires org.apache.commons.logging;

  opens org.ppadalka.pdfmetadataeditor to javafx.fxml;
  exports org.ppadalka.pdfmetadataeditor;
  exports org.ppadalka.pdfmetadataeditor.dialog;
  exports org.ppadalka.pdfmetadataeditor.time;
  opens org.ppadalka.pdfmetadataeditor.dialog to javafx.fxml;
  exports org.ppadalka.pdfmetadataeditor.model;
  opens org.ppadalka.pdfmetadataeditor.model to javafx.fxml;
  exports org.ppadalka.pdfmetadataeditor.controller;
  opens org.ppadalka.pdfmetadataeditor.controller to javafx.fxml;
  exports org.ppadalka.pdfmetadataeditor.view;
  opens org.ppadalka.pdfmetadataeditor.view to javafx.fxml;
}