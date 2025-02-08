package org.ppadalka.pdfmetadataeditor.model;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.ppadalka.pdfmetadataeditor.dto.PDFMetadata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.function.Function;
import java.util.function.Supplier;

public class PDFDocumentModel {
  private final Path filepath;
  private final PDDocument document;

  public PDFDocumentModel(File file) throws IOException {
    this.filepath = file.toPath();
    this.document = Loader.loadPDF(file);
  }

  public PDFMetadata getMetadata(final Function<Calendar, LocalDateTime> converter) {
    final var info = document.getDocumentInformation();

    return PDFMetadata.builder()
            .fileName(filepath.getFileName()
                    .toString())
            .title(info.getTitle())
            .author(info.getAuthor())
            .subject(info.getSubject())
            .keywords(info.getKeywords())
            .created(converter.apply(info.getCreationDate()))
            .modified(converter.apply(info.getModificationDate()))
            .build();
  }

  public void updateMetadata(final PDFMetadata metadata, final Supplier<Calendar> currentMoment) {
    final var info = document.getDocumentInformation();

    info.setTitle(metadata.getTitle());
    info.setAuthor(metadata.getAuthor());
    info.setSubject(metadata.getSubject());
    info.setKeywords(metadata.getKeywords());
    info.setModificationDate(currentMoment.get());
  }

  public void saveWithReplace() throws IOException {
    final var sourceFile = filepath.toFile();

    if (sourceFile.delete()) {
      document.save(filepath.toFile());
    }

    // TODO: What if file cannot be deleted?
  }

  public void close() throws IOException {
    document.close();
  }
}
