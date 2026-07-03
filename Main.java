import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Person {
    private String id;
    private String name;
    private int age;
    private String phoneNumber;

    public Person(String id, String name, int age, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Age: " + age + " | Phone: " + phoneNumber;
    }
}
// Patients
class Patient extends Person {
    private List<Medication> medications;
    private List<PrescriptionRecord> activePrescriptions;

    public Patient(String id, String name, int age, String phoneNumber) {
        super(id, name, age, phoneNumber);
        this.medications = new ArrayList<>();
        this.activePrescriptions = new ArrayList<>();
    }

    public List<Medication> getMedications() { return medications; }
    public List<PrescriptionRecord> getActivePrescriptions() { return activePrescriptions; }

    public void addMedication(Medication medication) {
        this.medications.add(medication);
    }

    public void addPrescription(PrescriptionRecord prescription) {
        this.activePrescriptions.add(prescription);
    }

    @Override
    public String toString() {
        return super.toString() + " (Patient) | Active Prescriptions: " + activePrescriptions.size();
    }
}

class PrescriptionRecord {
    private String prescriptionId;
    private String medicationName;
    private int dosage;
    private LocalDate startDate;
    private LocalDate endDate;

    public PrescriptionRecord(String prescriptionId, String medicationName, int dosage, LocalDate startDate, LocalDate endDate) {
        this.prescriptionId = prescriptionId;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getPrescriptionId() { return prescriptionId; }
    public String getMedicationName() { return medicationName; }
    public int getDosage() { return dosage; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }

    @Override
    public String toString() {
        return "Prescription ID: " + prescriptionId + " | Medication: " + medicationName + " | Dosage: " + dosage + " | Start: " + startDate + " | End: " + endDate;
    }
}