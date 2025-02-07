package org.ppadalka.pdfmetadataeditor.datetime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

class CalendarServiceImpl implements CalendarService {
  CalendarServiceImpl() {
  }

  @Override
  public Calendar getCurrentMoment() {
    final var instant = Instant.now();
    final var calendar = Calendar.getInstance();

    calendar.setTime(Date.from(instant));

    return calendar;
  }

  @Override
  public LocalDateTime toLocalDateTime(final Calendar calendar) {
    return calendar
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
  }
}
