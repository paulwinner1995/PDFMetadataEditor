package org.ppadalka.pdfmetadataeditor.command;

import org.junit.jupiter.api.Test;
import org.ppadalka.pdfmetadataeditor.dto.PDFMetadata;
import org.ppadalka.pdfmetadataeditor.model.PDFMetadataForm;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorChangeCommandTest {
  private static final String OLD_AUTHOR_VALUE = "Joshua Bloch";
  private static final String NEW_AUTHOR_VALUE = "Doug Lea";

  private final AuthorChangeCommand command = new AuthorChangeCommand(null, OLD_AUTHOR_VALUE, NEW_AUTHOR_VALUE);

  @Test
  void shouldSetNewAuthorValueToForm() {
    // given
    final var form = buildForm();
    final var meta = form.getValue();
    // when
    command.execute(form);
    // then
    assertEquals(NEW_AUTHOR_VALUE, meta.getAuthor());
  }

  private PDFMetadataForm buildForm() {
    return PDFMetadataForm.from(PDFMetadata.builder()
            .author(OLD_AUTHOR_VALUE)
            .build());
  }
}