module org.ppadalka.pdfmetadataeditor {
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires org.apache.pdfbox;
  requires org.apache.commons.logging;

  exports org.ppadalka.pdfmetadataeditor.view;
  exports org.ppadalka.pdfmetadataeditor;

  opens org.ppadalka.pdfmetadataeditor.view to javafx.fxml;
}