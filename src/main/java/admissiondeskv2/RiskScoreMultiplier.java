package admissiondeskv2;

public class RiskScoreMultiplier<T extends Number> {
  private final T conditionThreshold;
  private final int scoreMultiplier;

  public RiskScoreMultiplier(T conditionThreshold, int scoreMultiplier) {
    this.scoreMultiplier = scoreMultiplier;
    this.conditionThreshold = conditionThreshold;
  }

  private int scoreFor(Number conditionValue) {
    if (conditionValue.doubleValue() >= conditionThreshold.doubleValue()) {
      return scoreMultiplier;
    }

    return 0;
  }

  public static int firstMatchingScore(RiskScoreMultiplier<?>[] scoreMultipliers, Number conditionValue) {
    for (RiskScoreMultiplier<?> multiplier : scoreMultipliers) {
      int score = multiplier.scoreFor(conditionValue);

      if (score != 0) {
        return score;
      }
    }

    return 0;
  }
}
