package org.ppadalka.pdfmetadataeditor.command;

import org.junit.jupiter.api.Test;
import org.ppadalka.pdfmetadataeditor.dto.PDFMetadata;
import org.ppadalka.pdfmetadataeditor.model.PDFMetadataForm;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubjectChangeCommandTest {
  private static final String OLD_SUBJECT_VALUE = "Concurrency";
  private static final String NEW_SUBJECT_VALUE = "Networks";

  private final SubjectChangeCommand command = new SubjectChangeCommand(null,
          OLD_SUBJECT_VALUE,
          NEW_SUBJECT_VALUE
  );

  @Test
  void shouldSetNewSubjectValueToForm() {
    // given
    final var form = buildForm();
    final var meta = form.getValue();
    // when
    command.execute(form);
    // then
    assertEquals(NEW_SUBJECT_VALUE, meta.getSubject());
  }

  private PDFMetadataForm buildForm() {
    return PDFMetadataForm.from(PDFMetadata.builder()
            .subject(OLD_SUBJECT_VALUE)
            .build());
  }
}