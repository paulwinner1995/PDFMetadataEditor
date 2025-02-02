package org.ppadalka.pdfmetadataeditor.time;

import java.time.Instant;
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
}
