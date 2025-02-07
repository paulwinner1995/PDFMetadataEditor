package org.ppadalka.pdfmetadataeditor.datetime;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class DateTimeModule extends AbstractModule {
  @Provides
  CalendarService provideCalendarService() {
    return new CalendarServiceImpl();
  }
}
