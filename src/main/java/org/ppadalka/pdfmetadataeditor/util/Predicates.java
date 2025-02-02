package org.ppadalka.pdfmetadataeditor.util;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.MenuItem;

import java.util.Objects;
import java.util.function.Predicate;

public final class Predicates {
  private Predicates() {
  }

  public static Predicate<ButtonData> buttonPressed(final ButtonData button) {
    return pressed -> pressed == button;
  }

  public static Predicate<MenuItem> byMenuItemId(final String id) {
    return it -> Objects.equals(it.getId(), id);
  }
}

