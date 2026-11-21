package campusbikeshare;

import campusbikeshare.model.Bike;
import campusbikeshare.model.BikeRepository;
import campusbikeshare.model.InMemoryBikeRepository;

public class Main {
    public static void main(String[] args) {
        // Program against the interface (Unit 8 & Unit 11 polymorphism)
        BikeRepository repo = new InMemoryBikeRepository();

        System.out.println("=== 1. CREATE RECORDS ===");
        Bike b1 = repo.save(new Bike("BK-101", "Trek FX 2", 5.00, true));
        Bike b2 = repo.save(new Bike("BK-102", "Giant Escape 3", 4.50, false));
        Bike b3 = repo.save(new Bike("BK-103", "Specialized Sirrus", 6.00, true));
        System.out.println("Created: " + b1);
        System.out.println("Created: " + b2);
        System.out.println("Created: " + b3);

        System.out.println("\n=== 2. READ ALL RECORDS ===");
        printAll(repo);

        System.out.println("\n=== 3. UPDATE RECORD (ID 2) ===");
        Bike toUpdate = repo.findById(2);
        if (toUpdate != null) {
            toUpdate.setHourlyRate(5.25);
            toUpdate.setAvailable(true);
            repo.save(toUpdate);
            System.out.println("Updated: " + repo.findById(2));
        }
        printAll(repo);

        System.out.println("\n=== 4. DELETE RECORD (ID 1) ===");
        repo.deleteById(1);
        System.out.println("Deleted bike with ID 1.");
        printAll(repo);

        System.out.println("\n=== 5. TEST VALIDATION & EDGE CASES ===");
        try {
            repo.save(new Bike("BK-999", "Bad Rate Bike", -10.00, true));
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected validation error: " + e.getMessage());
        }

        Bike missing = repo.findById(999);
        System.out.println("Lookup non-existent ID 999 result: " + missing);
    }

    private static void printAll(BikeRepository repo) {
        for (Bike b : repo.findAll()) {
            System.out.println("  " + b);
        }
    }
}
