package org.ppadalka.pdfmetadataeditor;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Calendar;

public class PDFDocumentModel {
  private final File file;
  private final PDDocument document;

  public PDFDocumentModel(File file) throws IOException {
    this.file = file;
    this.document = Loader.loadPDF(file);
  }

  public PDFMetadata getMetadata() {
    final var info = document.getDocumentInformation();

    return PDFMetadata.builder()
            .fileName(file.getName())
            .title(info.getTitle())
            .author(info.getAuthor())
            .subject(info.getSubject())
            .keywords(info.getKeywords())
            .created(info.getCreationDate()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime()
            )
            .modified(info.getModificationDate()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime()
            )
            .build();
  }

  public void setMetadata(final PDFMetadata metadata, final Calendar modificationDate) {
    final var info = document.getDocumentInformation();

    info.setTitle(metadata.getTitle());
    info.setAuthor(metadata.getAuthor());
    info.setSubject(metadata.getSubject());
    info.setKeywords(metadata.getKeywords());
    info.setModificationDate(modificationDate);
  }

  public void save() throws IOException {
    document.save(file);
  }
}
