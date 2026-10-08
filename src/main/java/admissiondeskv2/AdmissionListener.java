package admissiondeskv2;

public interface AdmissionListener {
  void onAdmitted(Patient patient, String priority, int riskScore);
}
