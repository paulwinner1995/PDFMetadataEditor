package org.ppadalka.pdfmetadataeditor;

import java.time.LocalDateTime;

public class PDFMetadata {
  private String title;
  private String author;
  private String subject;
  private LocalDateTime created;
  private LocalDateTime modified;

  public PDFMetadata() {
  }

  private PDFMetadata(final Builder builder) {
    title = builder.title;
    author = builder.author;
    subject = builder.subject;
    created = builder.created;
    modified = builder.modified;
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

  public static class Builder {
    private String title;
    private String author;
    private String subject;
    private LocalDateTime created;
    private LocalDateTime modified;

    public PDFMetadata build() {
      return new PDFMetadata(this);
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
