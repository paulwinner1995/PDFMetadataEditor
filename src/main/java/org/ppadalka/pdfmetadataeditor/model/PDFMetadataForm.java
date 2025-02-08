package org.ppadalka.pdfmetadataeditor.model;

import org.ppadalka.pdfmetadataeditor.dto.PDFMetadata;

public abstract class PDFMetadataForm {

  public abstract boolean hasChanges();

  public abstract void restore();

  public abstract void setTitle(String title);

  public abstract void setAuthor(String author);

  public abstract void setSubject(String subject);

  public abstract void setKeywords(String keywords);

  public abstract PDFMetadata getValue();

  public static PDFMetadataForm from(final PDFMetadata metadata) {
    return new FullState(metadata);
  }

  public static PDFMetadataForm empty() {
    return new EmptyState();
  }
}
