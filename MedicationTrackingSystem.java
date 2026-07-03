import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MedicationTrackingSystem {
    @SuppressWarnings("FieldMayBeFinal")
    private List<Patient> patients = new ArrayList<>();
    @SuppressWarnings("FieldMayBeFinal")
    private List<Doctor> doctors = new ArrayList<>();
    @SuppressWarnings("FieldMayBeFinal")
    private List<Medication> medications = new ArrayList<>();
    @SuppressWarnings("FieldMayBeFinal")
    private List<Prescription> prescriptions = new ArrayList<>();

    public void addPatient(Patient p) { patients.add(p); }
    public void addDoctor(Doctor d) { doctors.add(d); }
    public void addMedication(Medication m) { medications.add(m); }

    public void searchByName(String name) {
        System.out.println("\n--- Search Results for \"" + name + "\" ---");
        boolean found = false;
        for (Medication m : medications) {
            if (m.getName().equalsIgnoreCase(name)) { System.out.println("[Medication] " + m); found = true; }
        }
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(name)) { System.out.println("[Patient] " + p); found = true; }
        }
        for (Doctor d : doctors) {
            if (d.getName().equalsIgnoreCase(name)) { System.out.println("[Doctor] " + d); found = true; }
        }
        if (!found) System.out.println("No records found matching that name.");
    }

    public void linkPatientToDoctor(String doctorId, String patientId) {
        Doctor doc = findDoctorById(doctorId);
        Patient pat = findPatientById(patientId);
        if (doc != null && pat != null) {
            doc.addPatient(pat);
            System.out.println("Linked Patient '" + pat.getName() + "' to Doctor '" + doc.getName() + "' successfully.");
        } else {
            System.out.println("Error: Verified tracking records could not be found for the supplied IDs.");
        }
    }

    public void acceptPrescription(String rxId, String doctorId, String patientId, String medId) {
        Doctor doc = findDoctorById(doctorId);
        Patient pat = findPatientById(patientId);
        Medication med = findMedicationById(medId);

        if (doc == null || pat == null || med == null) {
            System.out.println("Error: Prescription submission declined. Ensure Doctor, Patient, and Drug IDs exist.");
            return;
        }

        Prescription rx = new Prescription(rxId, doc, pat, med);
        prescriptions.add(rx);
        pat.addPrescription(rx);
        pat.addMedication(med);
        doc.addPatient(pat);
        System.out.println("Prescription successfully processed and attached to Patient file.");
    }

    public boolean editMedication(String id, String newName, String newDose, int newQty) {
        Medication m = findMedicationById(id);
        if (m != null) { m.setName(newName); m.setDose(newDose); m.setQuantityInStock(newQty); return true; }
        return false;
    }

    public boolean editPatient(String id, String newName, int newAge, String newPhone) {
        Patient p = findPatientById(id);
        if (p != null) { p.setName(newName); p.setAge(newAge); p.setPhoneNumber(newPhone); return true; }
        return false;
    }

    public boolean editDoctor(String id, String newName, int newAge, String newPhone, String newSpec) {
        Doctor d = findDoctorById(id);
        if (d != null) { d.setName(newName); d.setAge(newAge); d.setPhoneNumber(newPhone); d.setSpecialization(newSpec); return true; }
        return false;
    }

    public boolean deletePatient(String id) { return patients.removeIf(p -> p.getId().equals(id)); }
    public boolean deleteDoctor(String id) { return doctors.removeIf(d -> d.getId().equals(id)); }
    public boolean deleteMedication(String id) { return medications.removeIf(m -> m.getId().equals(id)); }

    public void generateFullSystemReport() {
        System.out.println("\n==================================================");
        System.out.println("               SYSTEM DATA MASTER REPORT          ");
        System.out.println("==================================================");
        System.out.println("\n[Registered Doctors]");
        for (Doctor d : doctors) System.out.println(d);
        System.out.println("\n[Registered Patients]");
        for (Patient p : patients) System.out.println(p);
        System.out.println("\n[Medications Inventory]");
        for (Medication m : medications) System.out.println(m);
        System.out.println("\n[All System Prescriptions]");
        for (Prescription r : prescriptions) System.out.println(r);
        System.out.println("==================================================");
    }

    public void checkExpiredMedications() {
        System.out.println("\n--- EXPIRED INVENTORY REPORT ---");
        boolean found = false;
        for (Medication m : medications) {
            if (m.isExpired()) { System.out.println(m); found = true; }
        }
        if (!found) System.out.println("Confirmed: No expired stocks found.");
    }

    public void printPrescriptionsByDoctor(String doctorId) {
        System.out.println("\n--- Scripts Signed by Doctor (ID: " + doctorId + ") ---");
        boolean found = false;
        for (Prescription r : prescriptions) {
            if (r.getDoctor().getId().equals(doctorId)) { System.out.println(r); found = true; }
        }
        if (!found) System.out.println("No matching script issuance records under that ID.");
    }

    public void printPatientPrescriptionSummary() {
        System.out.println("\n--- Historical Patient Prescriptions Summary (Past Year) ---");
        for (Patient p : patients) {
            System.out.print("Patient: " + p.getName() + " -> Drugs: ");
            if (p.getActivePrescriptions().isEmpty()) {
                System.out.println("[No assigned records]");
            } else {
                List<String> drugNames = new ArrayList<>();
                for (Prescription r : p.getActivePrescriptions()) {
                    if (r.getDateIssued().isAfter(LocalDate.now().minusYears(1))) { drugNames.add(r.getMedication().getName()); }
                }
                System.out.println(drugNames.isEmpty() ? "[None within the past calendar year]" : String.join(", ", drugNames));
            }
        }
    }

    public void restockMedications(int choice, int staticAmount) {
        Random r = new Random();
        for (Medication m : medications) {
            int addedUnits = (choice == 1) ? staticAmount : r.nextInt(50) + 10;
            m.setQuantityInStock(m.getQuantityInStock() + addedUnits);
        }
        System.out.println("Stock replenishment completed successfully across inventory.");
    }

    public Doctor findDoctorById(String id) { for (Doctor d : doctors) if (d.getId().equals(id)) return d; return null; }
    public Patient findPatientById(String id) { for (Patient p : patients) if (p.getId().equals(id)) return p; return null; }
    public Medication findMedicationById(String id) { for (Medication m : medications) if (m.getId().equals(id)) return m; return null; }
}
