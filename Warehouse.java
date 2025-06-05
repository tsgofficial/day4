import java.util.*;

class Warehouse {
    private List<Product> products = new ArrayList<>();
    private List<StockMove> stockMoves = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void moveStock(Product product, Location from, Location to) {
        StockMove move = new StockMove(product, from, to);
        stockMoves.add(move);
        System.out.println("[LOG] " + move);
    }

    public void printProducts() {
        System.out.println("Current Products in Warehouse:");
        for (Product p : products) {
            System.out.println("- " + p.getName() + " [Barcode: " + p.getBarcode() + "]");
        }
    }
}