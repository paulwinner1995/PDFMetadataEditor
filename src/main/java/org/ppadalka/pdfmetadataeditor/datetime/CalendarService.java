package org.ppadalka.pdfmetadataeditor.datetime;

import java.time.LocalDateTime;
import java.util.Calendar;

public interface CalendarService {
  Calendar getCurrentMoment();

  LocalDateTime toLocalDateTime(final Calendar calendar);
}
