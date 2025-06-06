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
        warehouse.removeProduct(product);
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
}