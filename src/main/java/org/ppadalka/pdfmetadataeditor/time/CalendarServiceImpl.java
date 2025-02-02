package org.ppadalka.pdfmetadataeditor.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class CalendarServiceImpl implements CalendarService {

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
