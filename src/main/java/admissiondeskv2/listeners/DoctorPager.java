package admissiondeskv2.listeners;

import admissiondeskv2.AdmissionListener;
import admissiondeskv2.Patient;

public class DoctorPager implements AdmissionListener {
  @Override
  public void onAdmitted(Patient patient, String priority, int riskScore) {
    if (!priority.equals("URGENT"))
      return;

    System.out.printf("[Pager] Doctor on call: %s\n",
        patient.getName());
  }
}
