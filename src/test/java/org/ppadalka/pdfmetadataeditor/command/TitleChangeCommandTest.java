package org.ppadalka.pdfmetadataeditor.command;

import org.junit.jupiter.api.Test;
import org.ppadalka.pdfmetadataeditor.dto.PDFMetadata;
import org.ppadalka.pdfmetadataeditor.model.PDFMetadataForm;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TitleChangeCommandTest {
  private static final String OLD_TITLE_VALUE = "Effective Java (First Edition)";
  private static final String NEW_TITLE_VALUE = "Effective Java (Second Edition)";

  private final TitleChangeCommand command = new TitleChangeCommand(null, OLD_TITLE_VALUE, NEW_TITLE_VALUE);

  @Test
  void shouldSetNewTitleValueToForm() {
    // given
    final var form = buildForm();
    final var meta = form.getValue();
    // when
    command.execute(form);
    // then
    assertEquals(NEW_TITLE_VALUE, meta.getTitle());
  }

  private PDFMetadataForm buildForm() {
    return PDFMetadataForm.from(PDFMetadata.builder()
            .title(OLD_TITLE_VALUE)
            .build());
  }
}