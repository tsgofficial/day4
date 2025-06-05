import java.util.*;

class Warehouse {
    private List<Product> products = new ArrayList<>();
    private List<StockMove> stockMoves = new ArrayList<>();
    private int capacity;
    private int currentStock;

    public Warehouse() {
        this.capacity = 10;
        this.currentStock = 0;
    }

    public Warehouse(int capacity) {
        this.capacity = capacity;
        this.currentStock = 0;
    }

    public void addProduct(Product product) throws InventoryException {
        if (currentStock + product.getQuantity() > capacity) {
            throw new InventoryException("Cannot add product. Warehouse capacity exceeded!");
        }
        
        products.add(product);
        currentStock += product.getQuantity();
        System.out.println("[LOG] Added " + product.getName() + " to warehouse. Current stock: " 
            + currentStock + "/" + capacity);
    }

    public void removeProduct(Product product) {
        if (products.remove(product)) {
            currentStock -= product.getQuantity();
            System.out.println("[LOG] Removed " + product.getName() + " from warehouse. Current stock: " 
                + currentStock + "/" + capacity);
        }
    }

    public void moveStock(Product product, Location from, Location to) throws InventoryException {
        if (!products.contains(product)) {
            throw new InventoryException("Cannot move product. Product not found in warehouse!");
        }
        
        StockMove move = new StockMove(product, from, to);
        stockMoves.add(move);
        System.out.println("[LOG] " + move);
    }

    public void printProducts() {
        System.out.println("Current Products in Warehouse:");
        for (Product p : products) {
            System.out.println("- " + p.getName() + " [Barcode: " + p.getBarcode() 
                + ", Quantity: " + p.getQuantity() + "]");
        }
        System.out.println("Stock level: " + currentStock + "/" + capacity);
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public int getCurrentStock() {
        return currentStock;
    }
    
    public boolean hasCapacity(int quantity) {
        return (currentStock + quantity <= capacity);
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}