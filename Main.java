public class Main {
    public static void main(String[] args) {
        try {

            Warehouse warehouse = new Warehouse(15);

            Product phone = new Product("iPhone 15", "12345ABC", 3);
            ServiceProduct cloud = new ServiceProduct("Cloud Backup", "SERVICE001", 1);
            Product laptop = new Product("MacBook Pro", "98765XYZ", 2);

            warehouse.addProduct(phone);
            warehouse.addProduct(cloud);
            warehouse.printProducts();

            Location storage = new Location("Storage Room A", 20);
            Location shelf = new Location("Shelf B", 10);

            try {
                warehouse.moveStock(phone, storage, shelf);
                storage.removeStock(phone.getQuantity());
                shelf.addStock(phone.getQuantity());
                System.out.println("Stock moved successfully! Shelf stock: " + shelf.getCurrentStock() +
                        "/" + shelf.getCapacity());
            } catch (InventoryException e) {
                System.out.println("Error moving stock: " + e.getMessage());
            }

            try {
                warehouse.addProduct(laptop);
                System.out.println("Product added successfully!");
            } catch (InventoryException e) {
                System.out.println("Error adding product: " + e.getMessage());
            }

            warehouse.removeProduct(cloud);
            warehouse.printProducts();

            Product manyPhones = new Product("Budget Phone", "BUDGET123", 12);
            try {
                warehouse.addProduct(manyPhones);
            } catch (InventoryException e) {
                System.out.println("Error adding product: " + e.getMessage());
            }

            warehouse.printProducts();

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
