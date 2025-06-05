class Product {
    private String name;
    private String barcode;
    private int quantity;

    public Product(String name, String barcode) {
        this.name = name;
        this.barcode = barcode;
        this.quantity = 1;
    }

    public Product(String name, String barcode, int quantity) {
        this.name = name;
        this.barcode = barcode;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public String getBarcode() { return barcode; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
