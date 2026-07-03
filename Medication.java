import java.time.LocalDate;
import java.util.Random;

public class Medication {
    private String id;
    private String name;
    private String dose;
    private int quantityInStock;
    private LocalDate expiryDate;

    public Medication(String id, String name, String dose, int quantityInStock) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.quantityInStock = quantityInStock;
        this.expiryDate = generateRandomPastDate();
    }

    public Medication(String id, String name, String dose, int quantityInStock, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.quantityInStock = quantityInStock;
        this.expiryDate = expiryDate;
    }

    private LocalDate generateRandomPastDate() {
        Random random = new Random();
        int daysAgo = random.nextInt(365 * 3) + 1;
        return LocalDate.now().minusDays(daysAgo);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDose() { return dose; }
    public void setDose(String dose) { this.dose = dose; }
    public int getQuantityInStock() { return quantityInStock; }
    public void setQuantityInStock(int quantityInStock) { this.quantityInStock = quantityInStock; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public boolean isExpired() { return this.expiryDate.isBefore(LocalDate.now()); }

    @Override
    public String toString() {
        return "Med ID: " + id + " | Name: " + name + " | Dose: " + dose + 
               " | Qty: " + quantityInStock + " | Expires: " + expiryDate + (isExpired() ? " [EXPIRED]" : "");
    }
}
