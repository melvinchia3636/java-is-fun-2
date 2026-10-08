package admissiondeskv2.listeners;

import admissiondeskv2.AdmissionListener;
import admissiondeskv2.Patient;

public class WardBoard implements AdmissionListener {
  @Override
  public void onAdmitted(Patient patient, String priority, int riskScore) {
    System.out.printf("[Ward board] %s | %s\n", patient.getName(), priority);
  }
}
