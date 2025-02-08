package org.ppadalka.pdfmetadataeditor.command;

import org.ppadalka.pdfmetadataeditor.model.PDFMetadataForm;
import org.ppadalka.pdfmetadataeditor.view.PDFMetadataEditorView;

public class ToggleReadonlyCommand extends InputChangeCommand<Boolean> {

  public ToggleReadonlyCommand(PDFMetadataEditorView view, Boolean oldValue, Boolean newValue) {
    super(view, oldValue, newValue);
  }

  @Override
  public void execute(PDFMetadataForm form) {
    if (newValue && form.hasChanges()) {
      form.restore();
      view.notifyMetadataUpdated(form.getValue()); // Discard changes if enter to readonly mode
    }
  }
}
