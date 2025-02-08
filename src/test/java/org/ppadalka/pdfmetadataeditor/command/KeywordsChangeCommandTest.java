package org.ppadalka.pdfmetadataeditor.command;

import org.junit.jupiter.api.Test;
import org.ppadalka.pdfmetadataeditor.dto.PDFMetadata;
import org.ppadalka.pdfmetadataeditor.model.PDFMetadataForm;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KeywordsChangeCommandTest {
  private static final String OLD_KEYWORDS_VALUE = "Java, Concurrency";
  private static final String NEW_KEYWORDS_VALUE = "Java, Networks";

  private final KeywordsChangeCommand command = new KeywordsChangeCommand(null,
          OLD_KEYWORDS_VALUE,
          NEW_KEYWORDS_VALUE
  );

  @Test
  void shouldSetNewKeywordsValueToForm() {
    // given
    final var form = buildForm();
    final var meta = form.getValue();
    // when
    command.execute(form);
    // then
    assertEquals(NEW_KEYWORDS_VALUE, meta.getKeywords());
  }

  private PDFMetadataForm buildForm() {
    return PDFMetadataForm.from(PDFMetadata.builder()
            .keywords(OLD_KEYWORDS_VALUE)
            .build());
  }
}