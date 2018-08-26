package dzem.pl.spanishtime.core;

import java.io.Serializable;

public class Time implements Serializable {

  private int hour;
  private int minute;
  private AmPm ampm;

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public int getMinute() {
    return minute;
  }

  public void setMinute(int minute) {
    this.minute = minute;
  }

  public AmPm getAmpm() {
    return ampm;
  }

  public void setAmpm(AmPm ampm) {
    this.ampm = ampm;
  }

  @Override
  public String toString() {
    return hour + ":" + String.format("%02d", minute) + ampm;
  }

}
