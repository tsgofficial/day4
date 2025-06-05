class StockMove {
    private Product product;
    private Location fromLocation;
    private Location toLocation;
    private int quantity;

    public StockMove(Product product, Location from, Location to) {
        this.product = product;
        this.fromLocation = from;
        this.toLocation = to;
        this.quantity = product.getQuantity();
    }
    
    public StockMove(Product product, Location from, Location to, int quantity) {
        this.product = product;
        this.fromLocation = from;
        this.toLocation = to;
        this.quantity = quantity;
    }

    public String toString() {
        return "Moving product: " + product.getName() +
                " (Qty: " + quantity + ") from: " + fromLocation.getName() +
                " to: " + toLocation.getName();
    }
    
    public Product getProduct() {
        return product;
    }
    
    public Location getFromLocation() {
        return fromLocation;
    }
    
    public Location getToLocation() {
        return toLocation;
    }
    
    public int getQuantity() {
        return quantity;
    }
}