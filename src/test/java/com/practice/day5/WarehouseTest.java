package com.practice.day5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

 /**
 * Test class for the Warehouse class functionality.
 * Follows TDD principles to verify warehouse operations.
 */
public class WarehouseTest {

    private Warehouse warehouse;
    private Product product;

    @BeforeEach
    public void setUp() {
        // Create a new warehouse with capacity of 10 before each test
        warehouse = new Warehouse(10);
        product = new Product("Test Product", "TEST123", 2);
    }

    @Test
    @DisplayName("Test adding product to warehouse")
    public void testAddProduct() throws InventoryException {
        // Add a product and verify it increases the stock level
        warehouse.addProduct(product);
        assertEquals(2, warehouse.getCurrentStock());
    }

    @Test
    @DisplayName("Test adding product that exceeds capacity")
    public void testAddProductExceedsCapacity() {
        // Add a product that exceeds warehouse capacity
        Product largeProduct = new Product("Large Product", "LARGE123", 15);

        // Assert that an exception is thrown when adding beyond capacity
        Exception exception = assertThrows(InventoryException.class, () -> {
            warehouse.addProduct(largeProduct);
        });

        String expectedMessage = "Cannot add product. Warehouse capacity exceeded!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Test removing product from warehouse")
    public void testRemoveProduct() throws InventoryException {
        // First add a product
        warehouse.addProduct(product);
        assertEquals(2, warehouse.getCurrentStock());

        // Then remove it
        boolean result = warehouse.removeProduct(product);
        assertTrue(result);
        assertEquals(0, warehouse.getCurrentStock());
    }

    @Test
    @DisplayName("Test checking warehouse capacity")
    public void testHasCapacity() {
        assertTrue(warehouse.hasCapacity(5));
        assertTrue(warehouse.hasCapacity(10));
        assertFalse(warehouse.hasCapacity(11));
    }

    @Test
    @DisplayName("Test moving stock between locations")
    public void testMoveStock() throws InventoryException {
        // First add a product to make it available in warehouse
        warehouse.addProduct(product);

        Location sourceLocation = new Location("Source", 20);
        Location targetLocation = new Location("Target", 20);

        // Move stock between locations
        warehouse.moveStock(product, sourceLocation, targetLocation);

        // Verify the stock move was recorded (would need a getter method to fully test)
    }

    @Test
    @DisplayName("Test moving non-existent product")
    public void testMoveNonExistentStock() {
        // Try to move a product that doesn't exist in warehouse
        Product nonExistentProduct = new Product("Non-existent", "NONE123", 1);
        Location sourceLocation = new Location("Source", 20);
        Location targetLocation = new Location("Target", 20);

        Exception exception = assertThrows(InventoryException.class, () -> {
            warehouse.moveStock(nonExistentProduct, sourceLocation, targetLocation);
        });

        String expectedMessage = "Cannot move product. Product not found in warehouse!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @DisplayName("Test creating warehouse with negative capacity throws exception")
    public void testNegativeCapacityThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Warehouse(-10);
        });
        
        String expectedMessage = "Warehouse capacity must be greater than zero";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @DisplayName("Test creating warehouse with zero capacity throws exception")
    public void testZeroCapacityThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Warehouse(0);
        });
        
        String expectedMessage = "Warehouse capacity must be greater than zero";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @DisplayName("Test adding null product throws exception")
    public void testAddNullProductThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            warehouse.addProduct(null);
        });
        
        String expectedMessage = "Cannot add null product to warehouse";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @DisplayName("Test adding product with zero quantity throws exception")
    public void testAddZeroQuantityProductThrowsException() throws InventoryException {
        Product zeroProduct = new Product("Zero Product", "ZERO123", 0);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            warehouse.addProduct(zeroProduct);
        });
        
        String expectedMessage = "Product quantity must be positive";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @DisplayName("Test removing null product throws exception")
    public void testRemoveNullProductThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            warehouse.removeProduct(null);
        });
        
        String expectedMessage = "Cannot remove null product from warehouse";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @DisplayName("Test moving null product throws exception")
    public void testMoveNullProductThrowsException() {
        Location sourceLocation = new Location("Source", 20);
        Location targetLocation = new Location("Target", 20);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            warehouse.moveStock(null, sourceLocation, targetLocation);
        });
        
        String expectedMessage = "Product cannot be null";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @DisplayName("Test moving product with null source location throws exception")
    public void testMoveNullSourceLocationThrowsException() throws InventoryException {
        warehouse.addProduct(product);
        Location targetLocation = new Location("Target", 20);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            warehouse.moveStock(product, null, targetLocation);
        });
        
        String expectedMessage = "Source location cannot be null";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @DisplayName("Test moving product with null destination location throws exception")
    public void testMoveNullDestinationLocationThrowsException() throws InventoryException {
        warehouse.addProduct(product);
        Location sourceLocation = new Location("Source", 20);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            warehouse.moveStock(product, sourceLocation, null);
        });
        
        String expectedMessage = "Destination location cannot be null";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @DisplayName("Test moving product to same location throws exception")
    public void testMoveSameLocationThrowsException() throws InventoryException {
        warehouse.addProduct(product);
        Location location = new Location("Same", 20);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            warehouse.moveStock(product, location, location);
        });
        
        String expectedMessage = "Source and destination locations cannot be the same";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @DisplayName("Test checking capacity with negative quantity throws exception")
    public void testHasCapacityNegativeQuantityThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            warehouse.hasCapacity(-5);
        });
        
        String expectedMessage = "Cannot check capacity for negative quantity";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @DisplayName("Test setting negative capacity throws exception")
    public void testSetNegativeCapacityThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            warehouse.setCapacity(-10);
        });
        
        String expectedMessage = "Warehouse capacity cannot be negative";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @DisplayName("Test setting capacity less than current stock throws exception")
    public void testSetCapacityLessThanStockThrowsException() throws InventoryException {
        warehouse.addProduct(product); // Current stock is now 2
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            warehouse.setCapacity(1);
        });
        
        String expectedMessage = "New capacity cannot be less than current stock level";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
}