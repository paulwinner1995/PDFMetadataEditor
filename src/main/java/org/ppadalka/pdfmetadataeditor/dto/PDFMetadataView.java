package org.ppadalka.pdfmetadataeditor.dto;

import java.time.LocalDateTime;

public interface PDFMetadataView {
  String getFileName();

  String getTitle();

  String getAuthor();

  String getSubject();

  String getKeywords();

  LocalDateTime getCreated();

  LocalDateTime getModified();
}
