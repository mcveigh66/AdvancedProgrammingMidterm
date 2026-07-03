import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private final List<Medication> medications;
    private List<Prescription> activePrescriptions;

    public Patient(String id, String name, int age, String phoneNumber) {
        super(id, name, age, phoneNumber);
        this.medications = new ArrayList<>();
        this.activePrescriptions = new ArrayList<>();
    }

    public List<Medication> getMedications() { return medications; }
    public List<Prescription> getActivePrescriptions() { return activePrescriptions; }
    public void addMedication(Medication medication) { this.medications.add(medication); }
    public void addPrescription(Prescription prescription) { this.activePrescriptions.add(prescription); }

    @Override
    public String toString() {
        return super.toString() + " (Patient) | Active Prescriptions: " + activePrescriptions.size();
    }

    public void setActivePrescriptions(List<Prescription> activePrescriptions) {
        this.activePrescriptions = activePrescriptions;
    }
}
