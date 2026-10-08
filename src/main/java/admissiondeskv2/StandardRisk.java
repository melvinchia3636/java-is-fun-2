
package admissiondeskv2;

public class StandardRisk implements RiskStrategy {
  private static final RiskScoreMultiplier<?>[] temperatureScoreMultiplier = {
      new RiskScoreMultiplier<>(39.5, 50),
      new RiskScoreMultiplier<>(37.5, 25),
  };

  private static final RiskScoreMultiplier<?>[] ageScoreMultiplier = {
      new RiskScoreMultiplier<>(65, 30),
      new RiskScoreMultiplier<Number>(50, 15)
  };

  @Override
  public int score(Patient patient) {
    return RiskScoreMultiplier.firstMatchingScore(temperatureScoreMultiplier, patient.getTemperatureC())
        + RiskScoreMultiplier.firstMatchingScore(ageScoreMultiplier, patient.getAgeYears());
  }

}
