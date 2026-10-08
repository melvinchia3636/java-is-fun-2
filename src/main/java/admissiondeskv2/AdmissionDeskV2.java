package admissiondeskv2;

import java.util.ArrayList;
import java.util.List;

public final class AdmissionDeskV2 {
  private RiskStrategy strategy;

  private final List<AdmissionListener> listeners = new ArrayList<>();

  public AdmissionDeskV2(RiskStrategy strategy) {
    this.setStrategy(strategy);
  }

  public void setStrategy(RiskStrategy strategy) {
    if (strategy == null) {
      throw new NullPointerException("strategy must be defined");
    }

    this.strategy = strategy;
  }

  public void addListener(AdmissionListener listener) {
    if (listener == null) {
      throw new NullPointerException("listener must be defined");
    }

    this.listeners.add(listener);
  }

  public void removeListener(AdmissionListener listener) {
    this.listeners.remove(listener);
  }

  public String admit(Patient patient) {
    int riskScore = this.strategy.score(patient);
    String priority = PriorityClassification.classify(riskScore);

    for (AdmissionListener listener : this.listeners) {
      listener.onAdmitted(patient, priority, riskScore);
    }

    return priority;
  }
}