package com.practice.day5;
public class Main {
    public static void main(String[] args) {
        System.out.println("=== TESTING ERROR HANDLING IN WAREHOUSE SYSTEM ===\n");
        
        try {
            // Test 1: Create warehouse with valid capacity
            System.out.println("1. Creating warehouse with capacity 15...");
            Warehouse warehouse = new Warehouse(15);
            System.out.println("✓ Warehouse created successfully!\n");

            // Test 2: Create valid products
            System.out.println("2. Creating valid products...");
            Product phone = new Product("iPhone 15", "12345ABC", 3);
            ServiceProduct cloud = new ServiceProduct("Cloud Backup", "SERVICE001", 1);
            Product laptop = new Product("MacBook Pro", "98765XYZ", 2);
            System.out.println("✓ Products created successfully!\n");

            // Test 3: Add products to warehouse
            System.out.println("3. Adding products to warehouse...");
            warehouse.addProduct(phone);
            warehouse.addProduct(cloud);
            warehouse.printProducts();
            System.out.println();

            // Test 4: Test invalid warehouse capacity
            System.out.println("4. Testing invalid warehouse capacity...");
            try {
                new Warehouse(0);
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Caught expected error: " + e.getMessage());
            }
            
            try {
                new Warehouse(-5);
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Caught expected error: " + e.getMessage());
            }
            System.out.println();

            // Test 5: Test invalid product creation - negative quantity
            System.out.println("5. Testing invalid product creation (negative quantity)...");
            try {
                new Product("Invalid Product", "INVALID123", -5);
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Caught expected error: " + e.getMessage());
            }
            System.out.println();

            // Test 6: Test invalid barcode
            System.out.println("6. Testing invalid barcode...");
            try {
                new Product("Bad Barcode", "AB", 1);
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Caught expected error: " + e.getMessage());
            }
            
            try {
                new Product("Null Barcode", null, 1);
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Caught expected error: " + e.getMessage());
            }
            System.out.println();

            // Test 7: Test adding null product
            System.out.println("7. Testing adding null product...");
            try {
                warehouse.addProduct(null);
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Caught expected error: " + e.getMessage());
            }
            System.out.println();

            // Test 8: Test warehouse capacity exceeded
            System.out.println("8. Testing warehouse capacity exceeded...");
            Product manyPhones = new Product("Budget Phone", "BUDGET123", 12);
            try {
                warehouse.addProduct(manyPhones);
            } catch (InventoryException e) {
                System.out.println("✓ Caught expected error: " + e.getMessage());
            }
            System.out.println();

            // Test 9: Test removing null product
            System.out.println("9. Testing removing null product...");
            try {
                warehouse.removeProduct(null);
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Caught expected error: " + e.getMessage());
            }
            System.out.println();

            // Test 10: Test location operations
            System.out.println("10. Testing location operations...");
            Location storage = new Location("Storage Room A", 20);
            Location shelf = new Location("Shelf B", 10);

            try {
                warehouse.moveStock(phone, storage, shelf);
                storage.removeStock(phone.getQuantity());
                shelf.addStock(phone.getQuantity());
                System.out.println("✓ Stock moved successfully! Shelf stock: " + shelf.getCurrentStock() +
                        "/" + shelf.getCapacity());
            } catch (InventoryException e) {
                System.out.println("Error moving stock: " + e.getMessage());
            }
            System.out.println();

            // Test 11: Test adding product with zero quantity
            System.out.println("11. Testing adding product with zero quantity...");
            try {
                Product zeroQuantity = new Product("Zero Product", "ZERO123", 0);
                warehouse.addProduct(zeroQuantity);
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Caught expected error: " + e.getMessage());
            }
            System.out.println();

            // Test 12: Successfully add valid product
            System.out.println("12. Adding valid product...");
            try {
                warehouse.addProduct(laptop);
                System.out.println("✓ Product added successfully!");
            } catch (InventoryException e) {
                System.out.println("Error adding product: " + e.getMessage());
            }
            System.out.println();

            // Test 13: Remove valid product
            System.out.println("13. Removing valid product...");
            boolean removed = warehouse.removeProduct(cloud);
            if (removed) {
                System.out.println("✓ Product removed successfully!");
            } else {
                System.out.println("Product not found in warehouse");
            }
            System.out.println();

            // Final warehouse state
            System.out.println("=== FINAL WAREHOUSE STATE ===");
            warehouse.printProducts();

            System.out.println("\n=== ALL ERROR HANDLING TESTS COMPLETED ===");

        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
