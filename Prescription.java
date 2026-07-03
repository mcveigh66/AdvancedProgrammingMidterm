import java.time.LocalDate;

public class Prescription {
    private final String id;
    private final Doctor doctor;
    private final Patient patient;
    private final Medication medication;
    private final LocalDate prescriptionExpiry;
    private final LocalDate dateIssued;

    public Prescription(String id, Doctor doctor, Patient patient, Medication medication) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.dateIssued = LocalDate.now();
        this.prescriptionExpiry = this.dateIssued.plusYears(1);
    }

    public String getId() { return id; }
    public Doctor getDoctor() { return doctor; }
    public Patient getPatient() { return patient; }
    public Medication getMedication() { return medication; }
    public LocalDate getPrescriptionExpiry() { return prescriptionExpiry; }
    public LocalDate getDateIssued() { return dateIssued; }

    @Override
    public String toString() {
        return "Rx ID: " + id + " | Doctor: " + doctor.getName() + 
               " | Patient: " + patient.getName() + " | Med: " + medication.getName() + 
               " | Expires: " + prescriptionExpiry;
    }
}