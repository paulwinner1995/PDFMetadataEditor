package org.ppadalka.pdfmetadataeditor;

import java.time.LocalDateTime;
import java.util.Objects;

public class PDFMetadata {
  private String fileName;
  private String title;
  private String author;
  private String subject;
  private LocalDateTime created;
  private LocalDateTime modified;

  public PDFMetadata() {
  }

  private PDFMetadata(final Builder builder) {
    fileName = builder.fileName;
    title = builder.title;
    author = builder.author;
    subject = builder.subject;
    created = builder.created;
    modified = builder.modified;
  }

  public String getFileName() {
    return fileName;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String getSubject() {
    return subject;
  }

  public LocalDateTime getCreated() {
    return created;
  }

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
            Objects.equals(subject, that.subject);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, author, subject);
  }

  public static class Builder {
    private String fileName;
    private String title;
    private String author;
    private String subject;
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
