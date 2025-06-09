package com.practice.day5;

public class Product {
    private String name;
    private String barcode;
    private int quantity;

    /**
     * Creates a product with a default quantity of 1.
     *
     * @param name    the name of the product
     * @param barcode the barcode of the product
     * @throws IllegalArgumentException if barcode is invalid or null
     */
    public Product(String name, String barcode) {
        this.name = name;
        validateBarcode(barcode);
        this.barcode = barcode;
        this.quantity = 1;
    }

    /**
     * Creates a product with a specified quantity.
     *
     * @param name     the name of the product
     * @param barcode  the barcode of the product
     * @param quantity the quantity of the product
     * @throws IllegalArgumentException if quantity is negative or barcode is invalid
     */
    public Product(String name, String barcode, int quantity) {
        this.name = name;
        validateBarcode(barcode);
        this.barcode = barcode;
        if (quantity < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    /**
     * Validates that the barcode is not null and has at least 3 characters.
     *
     * @param barcode the barcode to validate
     * @throws IllegalArgumentException if barcode is invalid
     */
    private void validateBarcode(String barcode) {
        if (barcode == null) {
            throw new IllegalArgumentException("Product barcode cannot be null");
        }
        if (barcode.length() < 3) {
            throw new IllegalArgumentException("Product barcode must be at least 3 characters long");
        }
    }

    public String getName() { return name; }
    public String getBarcode() { return barcode; }
    public int getQuantity() { return quantity; }
    
    /**
     * Sets the quantity of the product.
     *
     * @param quantity the new quantity
     * @throws IllegalArgumentException if quantity is negative
     */
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }
        this.quantity = quantity;
    }
}
