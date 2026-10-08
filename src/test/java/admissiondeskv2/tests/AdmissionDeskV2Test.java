package admissiondeskv2.tests;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import admissiondeskv2.AdmissionDeskV2;
import admissiondeskv2.OutbreakRisk;
import admissiondeskv2.Patient;
import admissiondeskv2.StandardRisk;

class AdmissionDeskV2Test {
  @Test
  void standardScore_feverSenior_returns55() {
    assertEquals(55, new StandardRisk().score(new Patient("Maria", 38.9, 70)));
  }

  @Test
  void outbreakScore_feverSenior_returns90() {
    assertEquals(90, new OutbreakRisk().score(new Patient("Devon", 38.2, 68)));
  }

  @Test
  void outbreakScore_exactFeverThreshold_takesHighBand() {
    assertEquals(60, new OutbreakRisk().score(new Patient("Kim", 38.0, 30)));
  }

  @Test
  void outbreakScore_belowFeverThreshold_takesLowerBand() {
    assertEquals(40, new OutbreakRisk().score(new Patient("Lee", 37.9, 30)));
  }

  @Test
  void classify_scoreAtUrgentThreshold_returnsUrgent() {
    assertEquals("URGENT", admissiondeskv2.PriorityClassification.classify(70));
  }

  @Test
  void classify_scoreJustBelowUrgentThreshold_returnsModerate() {
    assertEquals("MODERATE", admissiondeskv2.PriorityClassification.classify(69));
  }

  @Test
  void admit_strategySwap_changesPriority() {
    AdmissionDeskV2 desk = new AdmissionDeskV2(new StandardRisk());
    assertEquals("MODERATE", desk.admit(new Patient("Priya", 38.2, 68)));
    desk.setStrategy(new OutbreakRisk());
    assertEquals("URGENT", desk.admit(new Patient("Priya", 38.2, 68)));
  }

  @Test
  void admit_registeredListener_receivesOneEvent() {
    AdmissionDeskV2 desk = new AdmissionDeskV2(new StandardRisk());
    List<String> received = new ArrayList<>();
    desk.addListener((patient, priority, score) -> received.add(patient.getName() + " | " + priority + " | " + score));
    desk.admit(new Patient("Maria", 38.9, 70));
    assertEquals(List.of("Maria | MODERATE | 55"), received);
  }

  @Test
  void constructor_nullStrategy_throws() {
    NullPointerException thrown = assertThrows(NullPointerException.class, () -> new AdmissionDeskV2(null));
    assertEquals("strategy must be defined", thrown.getMessage());
  }

  @Test
  void addListener_nullListener_throws() {
    AdmissionDeskV2 desk = new AdmissionDeskV2(new StandardRisk());
    NullPointerException thrown = assertThrows(NullPointerException.class, () -> desk.addListener(null));
    assertEquals("listener must be defined", thrown.getMessage());
  }

}