package org.ppadalka.pdfmetadataeditor;

import java.io.IOException;

public class PDFMetadataController {
  private final DialogWindowService dialogWindowService;

  private PDFDocumentModel model;

  public PDFMetadataController(final DialogWindowService dialogWindowService) {
    this.dialogWindowService = dialogWindowService;
  }

  public void openDocument(PDFMetadataView view) {
    final var file = dialogWindowService.openPdfFile();

    if (file.isPresent()) {
      try {
        model = new PDFDocumentModel(file.get());
      } catch (IOException e) {
        // TODO: Handle it properly
        throw new RuntimeException(e);
      }

      var metadata = model.getMetadata();

      view.updateState(metadata);
    }
  }

  public void toggleReadonlyMode(PDFMetadataView view) {
    final boolean readonly = view.isReadonlyModeActive();

    if (readonly && view.hasChanges()) {
      // Discard changes if enter to readonly mode
      view.updateState(view.getBackup());
    }

    view.setControlsDisable(readonly);
  }

  public void close(PDFMetadataView view) {
    if (!view.hasChanges() || dialogWindowService.discardChanges()) {
      // TODO: Close model
      view.close();
    }
  }
}
