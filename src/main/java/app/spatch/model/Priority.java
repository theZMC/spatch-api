package app.spatch.model;

public enum Priority {
  EMERGENCY("e", "emer", "emergency"),
  STANDARD("s", "std", "standard"),
  PREVENTATIVE("p", "pm", "preventative");

  private String letter;
  private String abbrev;
  private String full;

  private Priority(String letter, String abbrev, String full){
    this.letter = letter;
    this.abbrev = abbrev;
    this.full = full;
  }

  public String getLetter() {
    return letter;
  }
  public String getAbbrev() {
    return abbrev;
  }
  public String getFull() {
    return full;
  }

  public static Priority parse(String input){
    if(null == input) {
      return null;
    }
    input = input.trim();
    for(Priority priority: Priority.values()){
      if(
        priority.letter.equalsIgnoreCase(input) ||
        priority.abbrev.equalsIgnoreCase(input) ||
        priority.full.equalsIgnoreCase(input)){
          return priority;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return this.full.toUpperCase();
  }
}
