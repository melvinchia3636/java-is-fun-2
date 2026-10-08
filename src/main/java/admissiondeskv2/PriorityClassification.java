package admissiondeskv2;

public class PriorityClassification {
  private static final PriorityClassification[] CLASSIFICATIONS = {
      new PriorityClassification("URGENT", 70),
      new PriorityClassification("MODERATE", 30),
      new PriorityClassification("ROUTINE", 0),
  };

  private final String name;
  private final int threshold;

  PriorityClassification(String name, int threshold) {
    this.name = name;
    this.threshold = threshold;
  }

  public String getName() {
    return this.name;
  }

  public int getThreshold() {
    return this.threshold;
  }

  public static String classify(int score) {
    for (PriorityClassification classification : CLASSIFICATIONS) {
      if (score >= classification.getThreshold()) {
        return classification.getName();
      }
    }

    throw new Error("Unable to classify score");
  }
}
