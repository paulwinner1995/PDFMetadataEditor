package org.ppadalka.pdfmetadataeditor.command;

import org.ppadalka.pdfmetadataeditor.model.PDFMetadataForm;
import org.ppadalka.pdfmetadataeditor.view.PDFMetadataEditorView;

public class KeywordsChangeCommand extends InputChangeCommand<String> {

  public KeywordsChangeCommand(PDFMetadataEditorView view, String oldValue, String newValue) {
    super(view, oldValue, newValue);
  }

  @Override
  public void execute(final PDFMetadataForm form) {
    form.setKeywords(newValue);
  }
}
