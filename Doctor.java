import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
    private String specialization;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Patient> managedPatients;

    public Doctor(String id, String name, int age, String phoneNumber, String specialization) {
        super(id, name, age, phoneNumber);
        this.specialization = specialization;
        this.managedPatients = new ArrayList<>();
    }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public List<Patient> getManagedPatients() { return managedPatients; }

    public void addPatient(Patient patient) {
        if (!managedPatients.contains(patient)) {
            managedPatients.add(patient);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Specialization: " + specialization + " | Managed Patients: " + managedPatients.size();
    }
}
