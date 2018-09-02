package dzem.pl.spanishtime.core;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TheTime {

    Random random = new Random();

    public TheTime() {
    }

  public Time randomTime(Configuration.Accuracy accuracy) {
      Time time = new Time();
      time.setHour(random.nextInt(11) + 1);
      time.setAmpm(random.nextInt(2) == 0 ? AmPm.AM : AmPm.PM);
      if (accuracy == Configuration.Accuracy.MINUTES_1) {
          time.setMinute(random.nextInt(60));
      } else if (accuracy == Configuration.Accuracy.MINUTES_5) {
              time.setMinute(((random.nextInt(12) + 1) * 5) % 60);
      } else if (accuracy == Configuration.Accuracy.MINUTES_10) {
          time.setMinute(((random.nextInt(6) + 1) * 10) % 60);
      } else if (accuracy == Configuration.Accuracy.MINUTES_15) {
        time.setMinute(((random.nextInt(4) + 1) * 15) % 60);
      } else {
          throw new UnsupportedOperationException("The accuracy " + accuracy + " is not implemented yet :(");
      }
      return time;
  }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<String> soundList(Time time, Configuration.Lang lang, Configuration.AnswerMethod answerMethod) {
        List<String> result = new ArrayList<>();
        String ln = lang.getCode();
        result.add("bump");

        if (answerMethod == Configuration.AnswerMethod.TRIVIAL) {
            result.add(ln + "_numeral_" + String.valueOf(time.getHour()));
            result.addAll(minutesToSounds(lang, time.getMinute()));

        } else if (answerMethod == Configuration.AnswerMethod.NATURAL) {


            if (lang == Configuration.Lang.PL) {

                if (time.getMinute() == 0) {
                    result.add(ln + "_numeral_" + String.valueOf(time.getHour()));
                } else {
                    String incomingHour = String.valueOf(((time.getHour() + 1) % 12) == 0 ? 12 : (time.getHour() + 1) % 12);
                    if (time.getMinute() == 30) {
                        result.add(ln + "_pol_do");
                        result.add(ln + "_dopelniacz_" + incomingHour);
                    } else if (time.getMinute() == 15) {
                        result.add(ln + "_kwadrans_po");
                        result.add(ln + "_dopelniacz_" + String.valueOf(time.getHour()));
                    } else if (time.getMinute() == 45) {
                        result.add(ln + "_za_kwadrans");
                        result.add(ln + "_numeral_" + incomingHour);
                    } else { //if (time.getMinute() % 5 == 0) {
                        if (time.getMinute() < 30) {
                            result.addAll(minutesToSounds(lang, time.getMinute()));
                            result.add(ln + "_po");
                            result.add(ln + "_dopelniacz_" + String.valueOf(time.getHour()));
                        } else {
                            result.add(ln + "_za");
                            result.addAll(minutesToSounds(lang, 60 - time.getMinute()));
                            result.add(ln + "_numeral_" + incomingHour);
                        }
                    }
                }
            } else if (lang == Configuration.Lang.ES) {


                if (time.getMinute() == 0) {
                    result.addAll(hourToSoundsES(time.getHour()));
                } else {
                    int incomingHour = ((time.getHour() + 1) % 12) == 0 ? 12 : (time.getHour() + 1) % 12;
                    if (time.getMinute() == 30) {
                        result.addAll(hourToSoundsES(time.getHour()));
                        result.add(ln + "_y");
                        result.add(ln + "_media");
                    } else if (time.getMinute() == 15) {
                        result.addAll(hourToSoundsES(time.getHour()));
                        result.add(ln + "_y");
                        result.add(ln + "_cuarto");
                    } else if (time.getMinute() == 45) {
                        result.addAll(hourToSoundsES(incomingHour));
                        result.add(ln + "_menos");
                        result.add(ln + "_cuarto");
                    } else { //if (time.getMinute() % 5 == 0) {
                        if (time.getMinute() < 30) {
                            result.addAll(hourToSoundsES(time.getHour()));
                            result.add(ln + "_y");
                            result.addAll(minutesToSounds(lang, time.getMinute()));
                        } else {
                            result.addAll(hourToSoundsES(incomingHour));
                            result.add(ln + "_menos");
                            result.addAll(minutesToSounds(lang, 60 - time.getMinute()));
                        }
                    }
                }


                } else {
                throw new UnsupportedOperationException("Undefined language.");
            }

        } else {
            throw new UnsupportedOperationException("The answer method: " + answerMethod + " is not implemented yet :(");
        }

        return result;
    }


    private static List<String> minutesToSounds(Configuration.Lang lang, int minutes) {
        List<String> result = new ArrayList<>();
        if (minutes == 0) {
            //do nothing
        } else if (minutes % 10 == 0) {
            result.add(lang.code + "_" + String.valueOf(minutes));
        } else {
            int decimal = minutes / 10;
            if (decimal == 1) {
                result.add(lang.code + "_1" + String.valueOf(minutes - (decimal * 10)));
            } else if (decimal == 2 && lang == Configuration.Lang.ES) {
                result.add(lang.code + "_2" + String.valueOf(minutes - (decimal * 10)));
            } else {
                if (decimal != 0) {
                    result.add(lang.code + "_" + String.valueOf(decimal) + "0");
                }
                result.add(lang.code + "_" + String.valueOf(minutes - (decimal * 10)));
            }
        }
        return result;
    }

    private static List<String> hourToSoundsES(int hour) {
        List<String> result = new ArrayList<>();
        String ln = "es";
        if (hour == 1) {
            result.add(ln + "_es_la");
            result.add(ln + "_una");
        } else {
            result.add(ln + "_son_las");
            result.add(ln + "_" + String.valueOf(hour));
        }
        return result;
    }
}
