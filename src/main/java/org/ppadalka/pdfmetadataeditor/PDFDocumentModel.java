package org.ppadalka.pdfmetadataeditor;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;

public class PDFDocumentModel {
  private final File file;
  private final PDDocument document;

  public PDFDocumentModel(File file) throws IOException {
    this.file = file;
    this.document = Loader.loadPDF(file);
  }

  public PDFMetadata getMetadata() {
    var info = document.getDocumentInformation();

    return PDFMetadata.builder()
            .title(info.getTitle())
            .author(info.getAuthor())
            .subject(info.getSubject())
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
}
