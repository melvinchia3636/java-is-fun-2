package admissiondeskv2.listeners;

import admissiondeskv2.AdmissionListener;
import admissiondeskv2.Patient;

public class AuditLog implements AdmissionListener {
  @Override
  public void onAdmitted(Patient patient, String priority, int riskScore) {
    System.out.printf("[Audit] %s score=%d\n", patient.getName(), riskScore);
  }
}
