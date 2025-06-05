class ServiceProduct extends Product {
    public ServiceProduct(String name, String barcode) {
        super(name, barcode);
    }
    
    public ServiceProduct(String name, String barcode, int quantity) {
        super(name, barcode, quantity);
    }
}