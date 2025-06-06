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
     */
    public Warehouse(int capacity) {
        this.capacity = capacity;
        this.currentStock = 0;
    }

    /**
     * Adds a product to the warehouse if there's enough capacity.
     *
     * @param product the product to be added
     * @throws InventoryException if there is not enough capacity
     */
    public void addProduct(Product product) throws InventoryException {
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
     */
    public void removeProduct(Product product) {
        if (products.remove(product)) {
            currentStock -= product.getQuantity();
            System.out.println("[LOG] Removed " + product.getName() + " from warehouse. Current stock: "
                    + currentStock + "/" + capacity);
        }
    }

    /**
     * Creates a stock move record for a product between locations.
     *
     * @param product the product to be moved
     * @param from    the source location
     * @param to      the destination location
     * @throws InventoryException if the product is not found in the warehouse
     */
    public void moveStock(Product product, Location from, Location to) throws InventoryException {
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
     */
    public boolean hasCapacity(int quantity) {
        return (currentStock + quantity <= capacity);
    }

    /**
     * Sets the capacity of the warehouse.
     *
     * @param capacity the new capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}