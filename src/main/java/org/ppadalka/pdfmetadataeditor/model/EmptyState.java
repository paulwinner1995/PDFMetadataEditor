package org.ppadalka.pdfmetadataeditor.model;


import org.ppadalka.pdfmetadataeditor.dto.PDFMetadata;

class EmptyState extends PDFMetadataForm {
  EmptyState() {
  }

  @Override
  public boolean hasChanges() {
    return false;
  }

  @Override
  public void restore() {
    // Do nothing
  }

  @Override
  public PDFMetadata getValue() {
    return null;
  }

  @Override
  public void setTitle(String title) {
    // Do nothing
  }

  @Override
  public void setAuthor(String author) {
    // Do nothing
  }

  @Override
  public void setSubject(String subject) {
    // Do nothing
  }

  @Override
  public void setKeywords(String keywords) {
    // Do nothing
  }
}
