package admissiondeskv2;

import admissiondeskv2.listeners.AuditLog;
import admissiondeskv2.listeners.DoctorPager;
import admissiondeskv2.listeners.WardBoard;

public class Main {
  public static void main(String[] args) {
    Patient maria = new Patient("Maria", 38.9, 70);
    Patient ahmad = new Patient("Ahmad", 37.2, 30);
    Patient priya = new Patient("Priya", 38.2, 68);

    AdmissionDeskV2 admissionDesk = new AdmissionDeskV2(new StandardRisk());
    admissionDesk.addListener(new WardBoard());
    admissionDesk.addListener(new DoctorPager());
    admissionDesk.addListener(new AuditLog());

    admissionDesk.admit(maria);

    admissionDesk.setStrategy(new OutbreakRisk());
    admissionDesk.admit(ahmad);
    admissionDesk.admit(priya);

  }
}
