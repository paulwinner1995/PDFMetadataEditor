package org.ppadalka.pdfmetadataeditor;

import javafx.stage.FileChooser;

import java.io.IOException;

public class PDFMetadataController {
  private PDFDocumentModel model;

  public void openDocument(PDFMetadataView view) {
    var fileChooser = new FileChooser();
    // TODO: Check this texts and extract it to bundle file
    fileChooser.setTitle("Test open file");
    fileChooser
            .getExtensionFilters()
            .add(new FileChooser.ExtensionFilter("Pdf document", "*.pdf"));

    var file = fileChooser.showOpenDialog(null);

    if (file != null) {
      try {
        model = new PDFDocumentModel(file);
      } catch (IOException e) {
        // TODO: Handle it properly
        throw new RuntimeException(e);
      }

      var metadata = model.getMetadata();

      view.update(metadata);
    }
  }
}
