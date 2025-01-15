module org.ppadalka.pdfmetadataeditor {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;

  opens org.ppadalka.pdfmetadataeditor to javafx.fxml;
  exports org.ppadalka.pdfmetadataeditor;
}