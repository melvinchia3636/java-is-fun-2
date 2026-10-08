package admissiondeskv2;

public interface RiskStrategy {
  int score(Patient patient);

}
