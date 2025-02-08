package org.ppadalka.pdfmetadataeditor.command;

import org.ppadalka.pdfmetadataeditor.model.PDFMetadataForm;
import org.ppadalka.pdfmetadataeditor.view.PDFMetadataEditorView;

public abstract class InputChangeCommand<T> {
  protected final PDFMetadataEditorView view;
  protected final T oldValue;
  protected final T newValue;

  protected InputChangeCommand(final PDFMetadataEditorView view, final T oldValue, final T newValue) {
    this.view = view;
    this.oldValue = oldValue;
    this.newValue = newValue;
  }

  public abstract void execute(final PDFMetadataForm form);
}
