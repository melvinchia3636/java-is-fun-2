package admissiondeskv2;

public class OutbreakRisk implements RiskStrategy {
  private static final RiskScoreMultiplier<?>[] temperatureScoreMultiplier = {
      new RiskScoreMultiplier<>(38, 60),
      new RiskScoreMultiplier<>(37.5, 40),
  };

  private static final RiskScoreMultiplier<?>[] ageScoreMultiplier = {
      new RiskScoreMultiplier<>(65, 30),
  };

  @Override
  public int score(Patient patient) {
    return RiskScoreMultiplier.firstMatchingScore(temperatureScoreMultiplier, patient.getTemperatureC())
        + RiskScoreMultiplier.firstMatchingScore(ageScoreMultiplier, patient.getAgeYears());
  }

}