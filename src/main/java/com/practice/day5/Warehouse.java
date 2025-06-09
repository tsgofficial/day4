package com.practice.day5;
import java.util.*;

/**
 * Represents a warehouse for storing and managing products.
 * Handles inventory operations like adding, removing and moving products.
 */
class Warehouse {
    private List<Product> products = new ArrayList<>();
    private List<StockMove> stockMoves = new ArrayList<>();
    private int capacity;
    private int currentStock;

    /**
     * Creates a new warehouse with default capacity of 10.
     */
    public Warehouse() {
        this.capacity = 10;
        this.currentStock = 0;
    }

    /**
     * Creates a new warehouse with specified capacity.
     *
     * @param capacity the maximum capacity of the warehouse
     * @throws IllegalArgumentException if capacity is negative or zero
     */
    public Warehouse(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Warehouse capacity must be greater than zero");
        }
        this.capacity = capacity;
        this.currentStock = 0;
    }

    /**
     * Adds a product to the warehouse if there's enough capacity.
     *
     * @param product the product to be added
     * @throws InventoryException if there is not enough capacity
     * @throws IllegalArgumentException if product is null or has negative quantity
     */
    public void addProduct(Product product) throws InventoryException {
        if (product == null) {
            throw new IllegalArgumentException("Cannot add null product to warehouse");
        }
        
        if (product.getQuantity() <= 0) {
            throw new IllegalArgumentException("Product quantity must be positive");
        }
        
        if (currentStock + product.getQuantity() > capacity) {
            throw new InventoryException("Cannot add product. Warehouse capacity exceeded!");
        }
        
        products.add(product);
        currentStock += product.getQuantity();
        System.out.println("[LOG] Added " + product.getName() + " to warehouse. Current stock: " 
            + currentStock + "/" + capacity);
    }

    /**
     * Removes a product from the warehouse.
     *
     * @param product the product to be removed
     * @throws IllegalArgumentException if product is null
     * @return true if the product was removed, false if it wasn't found
     */
    public boolean removeProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Cannot remove null product from warehouse");
        }
        
        if (products.remove(product)) {
            currentStock -= product.getQuantity();
            System.out.println("[LOG] Removed " + product.getName() + " from warehouse. Current stock: " 
                + currentStock + "/" + capacity);
            return true;
        }
        return false;
    }

    /**
     * Creates a stock move record for a product between locations.
     *
     * @param product the product to be moved
     * @param from    the source location
     * @param to      the destination location
     * @throws InventoryException if the product is not found in the warehouse
     * @throws IllegalArgumentException if product, from or to is null
     */
    public void moveStock(Product product, Location from, Location to) throws InventoryException {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        
        if (from == null) {
            throw new IllegalArgumentException("Source location cannot be null");
        }
        
        if (to == null) {
            throw new IllegalArgumentException("Destination location cannot be null");
        }
        
        if (from.equals(to)) {
            throw new IllegalArgumentException("Source and destination locations cannot be the same");
        }
        
        if (!products.contains(product)) {
            throw new InventoryException("Cannot move product. Product not found in warehouse!");
        }
        
        StockMove move = new StockMove(product, from, to);
        stockMoves.add(move);
        System.out.println("[LOG] " + move);
    }

    /**
     * Prints the list of products in the warehouse.
     */
    public void printProducts() {
        System.out.println("Current Products in Warehouse:");
        for (Product p : products) {
            System.out.println("- " + p.getName() + " [Barcode: " + p.getBarcode() 
                + ", Quantity: " + p.getQuantity() + "]");
        }
        System.out.println("Stock level: " + currentStock + "/" + capacity);
    }
    
    /**
     * Gets the total capacity of the warehouse.
     *
     * @return the warehouse capacity
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Gets the current stock level in the warehouse.
     *
     * @return the current stock
     */
    public int getCurrentStock() {
        return currentStock;
    }
    
    /**
     * Checks if the warehouse has enough capacity for a given quantity.
     *
     * @param quantity the quantity to check
     * @return true if there's enough capacity, false otherwise
     * @throws IllegalArgumentException if quantity is negative
     */
    public boolean hasCapacity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Cannot check capacity for negative quantity");
        }
        return (currentStock + quantity <= capacity);
    }
    
    /**
     * Sets the capacity of the warehouse.
     *
     * @param capacity the new capacity
     * @throws IllegalArgumentException if capacity is less than current stock or negative
     */
    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Warehouse capacity cannot be negative");
        }
        
        if (capacity < currentStock) {
            throw new IllegalArgumentException("New capacity cannot be less than current stock level");
        }
        
        this.capacity = capacity;
    }
}