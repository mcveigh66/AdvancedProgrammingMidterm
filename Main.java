import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MedicationTrackingSystem system = new MedicationTrackingSystem();
        Scanner scanner = new Scanner(System.in);

        setupMockData(system);

        while (true) {
            System.out.println("\n==================================================");
            System.out.println("         PHARMACY ENTERPRISE CONSOLE MENU         ");
            System.out.println("==================================================");
            System.out.println("1.  Add Record (Patient / Doctor / Medication)");
            System.out.println("2.  Edit Records");
            System.out.println("3.  Delete Records");
            System.out.println("4.  Search Master Names Dictionary");
            System.out.println("5.  Link Patient File to Doctor");
            System.out.println("6.  Accept & Log a New Written Prescription");
            System.out.println("7.  Generate System Inventory & Summary Report");
            System.out.println("8.  Audit Expired Storage Lots");
            System.out.println("9.  Filter Prescriptions by Prescribing Doctor");
            System.out.println("10. Display 1-Year Patient Drug Summary lists");
            System.out.println("11. Replenish Inventory / Restock");
            System.out.println("12. Terminate Workspace Context");
            System.out.print("Select operational choice (1-12): ");

            int selection;
            try {
                selection = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input format error. Provide numerical menu choices.");
                continue;
            }

            switch (selection) {
                case 1 -> {
                    System.out.print("Add target type (1: Patient, 2: Doctor, 3: Medication): ");
                    int addChoice = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Unique Tracking ID: "); String id = scanner.nextLine();
                    System.out.print("Enter Descriptive Name: "); String name = scanner.nextLine();
                    
                    if (addChoice == 1 || addChoice == 2) {
                        System.out.print("Enter Age: "); int age = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Contact Phone Number: "); String phone = scanner.nextLine();
                        if (addChoice == 1) system.addPatient(new Patient(id, name, age, phone));
                        else {
                            System.out.print("Enter Medical Specialization field: "); String spec = scanner.nextLine();
                            system.addDoctor(new Doctor(id, name, age, phone, spec));
                        }
                    } else if (addChoice == 3) {
                        System.out.print("Enter Dosage Metric (e.g., 20mg): "); String dose = scanner.nextLine();
                        System.out.print("Enter Batch Stock Volume: "); int qty = Integer.parseInt(scanner.nextLine());
                        system.addMedication(new Medication(id, name, dose, qty));
                    }
                    System.out.println("Record appended successfully.");
                }

                case 2 -> {
                    System.out.print("Edit Target Type (1: Patient, 2: Doctor, 3: Medication): ");
                    int editChoice = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Existing Entity ID: "); String eId = scanner.nextLine();
                    boolean updated = false;
                    
                    switch (editChoice) {
                        case 1 -> {
                            System.out.print("Enter New Name: "); String nName = scanner.nextLine();
                            System.out.print("Enter New Age: "); int nAge = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter New Phone: "); String nPhone = scanner.nextLine();
                            updated = system.editPatient(eId, nName, nAge, nPhone);
                    }
                        case 2 -> {
                            System.out.print("Enter New Name: "); String nName2 = scanner.nextLine();
                            System.out.print("Enter New Age: "); int nAge2 = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter New Phone: "); String nPhone2 = scanner.nextLine();
                            System.out.print("Enter New Specialization: "); String nSpec = scanner.nextLine();
                            updated = system.editDoctor(eId, nName2, nAge2, nPhone2, nSpec);
                    }
                        case 3 -> {
                            System.out.print("Enter New Medication Name: "); String nName3 = scanner.nextLine();
                            System.out.print("Enter Dose Target: "); String nDose = scanner.nextLine();
                            System.out.print("Set Storage Vol Level: "); int nQty = Integer.parseInt(scanner.nextLine());
                            updated = system.editMedication(eId, nName3, nDose, nQty);
                    }
                    }
                    System.out.println(updated ? "Modification committed successfully." : "Record targeting ID failed verification lookup.");
                }

                case 3 -> {
                    System.out.print("Delete category (1: Patient, 2: Doctor, 3: Medication): ");
                    int delChoice = Integer.parseInt(scanner.nextLine());
                    System.out.print("Confirm Record ID to wipe: "); String delId = scanner.nextLine();
                    boolean traceRemoved = false;
                    
                    switch (delChoice) {
                        case 1 -> traceRemoved = system.deletePatient(delId);
                        case 2 -> traceRemoved = system.deleteDoctor(delId);
                        case 3 -> traceRemoved = system.deleteMedication(delId);
                        default -> {
                    }
                    }
                    
                    System.out.println(traceRemoved ? "Data wiped cleanly from database logs." : "Entity context not found.");
                }


                case 4 -> {
                    System.out.print("Enter exact name key to scan: ");
                    system.searchByName(scanner.nextLine());
                }

                case 5 -> {
                    System.out.print("Source Doctor Tracking ID: "); String docId = scanner.nextLine();
                    System.out.print("Recipient Patient Tracking ID: "); String patId = scanner.nextLine();
                    system.linkPatientToDoctor(docId, patId);
                }

                case 6 -> {
                    System.out.print("Set New Script Log Rx ID: "); String rxId = scanner.nextLine();
                    System.out.print("Authorizing Doctor ID: "); String rDocId = scanner.nextLine();
                    System.out.print("Recipient Patient ID: "); String rPatId = scanner.nextLine();
                    System.out.print("Target Dispensing Drug ID: "); String rMedId = scanner.nextLine();
                    system.acceptPrescription(rxId, rDocId, rPatId, rMedId);
                }

                case 7 -> system.generateFullSystemReport();

                case 8 -> system.checkExpiredMedications();

                case 9 -> {
                    System.out.print("Filter criteria doctor ID: ");
                    system.printPrescriptionsByDoctor(scanner.nextLine());
                }

                case 10 -> system.printPatientPrescriptionSummary();

                case 11 -> {
                    System.out.print("Replenishment matrix model (1: Static volume step, 2: Dynamic random distribution): ");
                    int restockType = Integer.parseInt(scanner.nextLine());
                    int quantityStep = 0;
                    if (restockType == 1) {
                        System.out.print("Quantity addition block level: ");
                        quantityStep = Integer.parseInt(scanner.nextLine());
                    }
                    system.restockMedications(restockType, quantityStep);
                }

                case 12 -> {
                    System.out.println("Graceful shutdown active. Flushing volatile memory blocks... Terminated.");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Menu operation execution exception. Value out of index bounds.");
            }
        }
    }

    private static void setupMockData(MedicationTrackingSystem system) {
        Doctor docA = new Doctor("D01", "Dr. Sarah Smith", 45, "555-0192", "Cardiology");
        Doctor docB = new Doctor("D02", "Dr. John Doe", 50, "555-0843", "Pediatrics");
        system.addDoctor(docA); system.addDoctor(docB);

        Patient patA = new Patient("P01", "Alice Green", 29, "555-9011");
        Patient patB = new Patient("P02", "Bob Vance", 61, "555-3200");
        system.addPatient(patA); system.addPatient(patB);

        Medication medA = new Medication("M01", "Amoxicillin", "500mg", 120, LocalDate.now().plusMonths(6));
        Medication medB = new Medication("M02", "Ibuprofen", "200mg", 450); 
        Medication medC = new Medication("M03", "Lisinopril", "10mg", 80, LocalDate.now().plusMonths(10));
        system.addMedication(medA); system.addMedication(medB); system.addMedication(medC);

        system.acceptPrescription("RX99", "D01", "P01", "M01");
    }
}