package org.ppadalka.pdfmetadataeditor.presenter;

import org.ppadalka.pdfmetadataeditor.dto.PDFMetadata;
import org.ppadalka.pdfmetadataeditor.dto.PDFMetadataForm;

abstract class PDFMetadataState implements PDFMetadataForm {

  public abstract boolean hasChanges();

  public abstract void restore();

  public abstract PDFMetadata getValue();

  public static PDFMetadataState from(final PDFMetadata metadata) {
    return new FullState(metadata);
  }

  public static PDFMetadataState empty() {
    return new EmptyState();
  }
}

class FullState extends PDFMetadataState {
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

class EmptyState extends PDFMetadataState {
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
