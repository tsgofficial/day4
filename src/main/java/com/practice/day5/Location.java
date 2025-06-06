package com.practice.day5;
public class Location {
    private String name;
    private int capacity;
    private int currentStock;

    public Location(String name) {
        this.name = name;
        this.capacity = 100;
        this.currentStock = 0;
    }
    
    public Location(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.currentStock = 0;
    }

    public String getName() {
        return name;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public int getCurrentStock() {
        return currentStock;
    }
    
    public void addStock(int quantity) throws InventoryException {
        if (currentStock + quantity > capacity) {
            throw new InventoryException("Cannot add stock. Location capacity exceeded!");
        }
        currentStock += quantity;
    }
    
    public void removeStock(int quantity) throws InventoryException {
        if (currentStock < quantity) {
            throw new InventoryException("Cannot remove stock. Not enough stock at location!");
        }
        currentStock -= quantity;
    }
    
    public boolean hasCapacity(int quantity) {
        return (currentStock + quantity <= capacity);
    }
}