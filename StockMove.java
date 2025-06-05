class StockMove {
    private Product product;
    private Location fromLocation;
    private Location toLocation;

    public StockMove(Product product, Location from, Location to) {
        this.product = product;
        this.fromLocation = from;
        this.toLocation = to;
    }

    public String toString() {
        return "Moving product: " + product.getName() +
                " from: " + fromLocation.getName() +
                " to: " + toLocation.getName();
    }
}