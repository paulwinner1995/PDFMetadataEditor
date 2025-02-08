package org.ppadalka.pdfmetadataeditor.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class PDFMetadata implements PDFMetadataView {
  private final String fileName;
  private String title;
  private String author;
  private String subject;
  private String keywords;
  private final LocalDateTime created;
  private final LocalDateTime modified;

  private PDFMetadata(final Builder builder) {
    fileName = builder.fileName;
    title = builder.title;
    author = builder.author;
    subject = builder.subject;
    keywords = builder.keywords;
    created = builder.created;
    modified = builder.modified;
  }

  public PDFMetadata copy() {
    return PDFMetadata.builder()
            .fileName(fileName)
            .title(title)
            .author(author)
            .subject(subject)
            .keywords(keywords)
            .created(created)
            .modified(modified)
            .build();
  }

  @Override
  public String getFileName() {
    return fileName;
  }

  @Override
  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  @Override
  public String getAuthor() {
    return author;
  }

  public void setAuthor(final String author) {
    this.author = author;
  }

  @Override
  public String getSubject() {
    return subject;
  }

  public void setSubject(final String subject) {
    this.subject = subject;
  }

  @Override
  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(final String keywords) {
    this.keywords = keywords;
  }

  @Override
  public LocalDateTime getCreated() {
    return created;
  }

  @Override
  public LocalDateTime getModified() {
    return modified;
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PDFMetadata that)) return false;

    return Objects.equals(title, that.title) &&
            Objects.equals(author, that.author) &&
            Objects.equals(subject, that.subject) &&
            Objects.equals(keywords, that.keywords);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, author, subject, keywords);
  }

  public static class Builder {
    private String fileName;
    private String title;
    private String author;
    private String subject;
    private String keywords;
    private LocalDateTime created;
    private LocalDateTime modified;

    public PDFMetadata build() {
      return new PDFMetadata(this);
    }

    public Builder fileName(final String fileName) {
      this.fileName = fileName;
      return this;
    }

    public Builder title(final String title) {
      this.title = title;
      return this;
    }

    public Builder author(final String author) {
      this.author = author;
      return this;
    }

    public Builder subject(final String subject) {
      this.subject = subject;
      return this;
    }

    public Builder keywords(final String keywords) {
      this.keywords = keywords;
      return this;
    }

    public Builder created(final LocalDateTime created) {
      this.created = created;
      return this;
    }

    public Builder modified(final LocalDateTime modified) {
      this.modified = modified;
      return this;
    }
  }
}
