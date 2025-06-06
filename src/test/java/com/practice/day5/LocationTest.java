package com.practice.day5;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Location class functionality.
 * Verifies location stock management operations.
 */
public class LocationTest {

    private Location location;

    @BeforeEach
    public void setUp() {
        // Create a new location with capacity of 20 before each test
        location = new Location("Test Location", 20);
    }

    @Test
    @DisplayName("Test adding stock to location")
    public void testAddStock() throws InventoryException {
        // Add stock to the location
        location.addStock(10);
        assertEquals(10, location.getCurrentStock());

        // Add more stock
        location.addStock(5);
        assertEquals(15, location.getCurrentStock());
    }

    @Test
    @DisplayName("Test adding stock that exceeds capacity")
    public void testAddStockExceedsCapacity() {
        // Assert that an exception is thrown when adding beyond capacity
        Exception exception = assertThrows(InventoryException.class, () -> {
            location.addStock(25);
        });

        String expectedMessage = "Cannot add stock. Location capacity exceeded!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Test removing stock from location")
    public void testRemoveStock() throws InventoryException {
        // First add some stock
        location.addStock(15);
        assertEquals(15, location.getCurrentStock());

        // Then remove some
        location.removeStock(5);
        assertEquals(10, location.getCurrentStock());
    }

    @Test
    @DisplayName("Test removing more stock than available")
    public void testRemoveTooMuchStock() throws InventoryException {
        // Add some stock
        location.addStock(10);

        // Try to remove more than available
        Exception exception = assertThrows(InventoryException.class, () -> {
            location.removeStock(15);
        });

        String expectedMessage = "Cannot remove stock. Not enough stock at location!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Test checking location capacity")
    public void testHasCapacity() {
        assertTrue(location.hasCapacity(10));
        assertTrue(location.hasCapacity(20));
        assertFalse(location.hasCapacity(21));
    }
}