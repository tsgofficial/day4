public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        Product phone = new Product("iPhone 15", "12345ABC");
        ServiceProduct cloud = new ServiceProduct("Cloud Backup", "SERVICE001");

        warehouse.addProduct(phone);
        warehouse.addProduct(cloud);
        warehouse.printProducts();

        Location storage = new Location("Storage Room A");
        Location shelf = new Location("Shelf B");

        warehouse.moveStock(phone, storage, shelf);

        warehouse.removeProduct(cloud);
        warehouse.printProducts();
    }
}
