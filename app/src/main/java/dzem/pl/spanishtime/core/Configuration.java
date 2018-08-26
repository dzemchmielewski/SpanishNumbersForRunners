package dzem.pl.spanishtime.core;

public class Configuration {

  public enum Accuracy {
    MINUTES_15, MINUTES_10, MINUTES_5, MINUTES_1;
  }

  public enum AnswerMethod {
    TRIVIAL, NATURAL;
  }

  public enum Lang {
    PL("pl", "polski"), ES("es", "espańol");
    String code;
    String name;

    Lang(String code, String name) {
      this.code = code;
      this.name = name;
    }

    public String getCode() {
      return code;
    }

    public String getName() {
      return name;
    }
  }

  private Accuracy accuracy = Accuracy.MINUTES_5;
  private AnswerMethod answerMethod = AnswerMethod.NATURAL;
  private Lang firstLang = Lang.PL;
  private Lang secondLang = Lang.ES;

  private int waitBeforeAnswer = 2 * 1000; //miliseconds
  private int waitBeforeNextQuestion = 5 * 1000; //miliseconds

  public Accuracy getAccuracy() {
    return accuracy;
  }

  public void setAccuracy(Accuracy accuracy) {
    this.accuracy = accuracy;
  }

  public int getWaitBeforeAnswer() {
    return waitBeforeAnswer;
  }

  public void setWaitBeforeAnswer(int waitBeforeAnswer) {
    this.waitBeforeAnswer = waitBeforeAnswer;
  }

  public AnswerMethod getAnswerMethod() {
    return answerMethod;
  }

  public void setAnswerMethod(AnswerMethod answerMethod) {
    this.answerMethod = answerMethod;
  }

  public Lang getFirstLang() {
    return firstLang;
  }

  public void setFirstLang(Lang firstLang) {
    this.firstLang = firstLang;
  }

  public Lang getSecondLang() {
    return secondLang;
  }

  public void setSecondLang(Lang secondLang) {
    this.secondLang = secondLang;
  }

    public int getWaitBeforeNextQuestion() {
        return waitBeforeNextQuestion;
    }

    public void setWaitBeforeNextQuestion(int waitBeforeNextQuestion) {
        this.waitBeforeNextQuestion = waitBeforeNextQuestion;
    }
}
