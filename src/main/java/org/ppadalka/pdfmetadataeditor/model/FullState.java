package org.ppadalka.pdfmetadataeditor.model;

import org.ppadalka.pdfmetadataeditor.dto.PDFMetadata;

class FullState extends PDFMetadataForm {
  private PDFMetadata value;
  private final PDFMetadata backup;

  FullState(final PDFMetadata metadata) {
    this.value = metadata;
    this.backup = metadata.copy();
  }

  @Override
  public boolean hasChanges() {
    return !value.equals(backup);
  }

  @Override
  public void restore() {
    this.value = backup.copy();
  }

  @Override
  public PDFMetadata getValue() {
    return value;
  }

  @Override
  public void setTitle(final String title) {
    this.value.setTitle(title);
  }

  @Override
  public void setAuthor(final String author) {
    this.value.setAuthor(author);
  }

  @Override
  public void setSubject(final String subject) {
    this.value.setSubject(subject);
  }

  @Override
  public void setKeywords(final String keywords) {
    this.value.setKeywords(keywords);
  }
}
