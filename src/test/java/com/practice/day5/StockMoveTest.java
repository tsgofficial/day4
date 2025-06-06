package com.practice.day5;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the StockMove class functionality.
 * Verifies stock movement operations.
 */
public class StockMoveTest {

    private Product product;
    private Location fromLocation;
    private Location toLocation;

    @BeforeEach
    public void setUp() {
        product = new Product("Test Product", "TEST123", 5);
        fromLocation = new Location("Source Location", 20);
        toLocation = new Location("Target Location", 20);
    }

    @Test
    @DisplayName("Test stock move creation with product quantity")
    public void testStockMoveCreation() {
        StockMove move = new StockMove(product, fromLocation, toLocation);

        assertEquals(product, move.getProduct());
        assertEquals(fromLocation, move.getFromLocation());
        assertEquals(toLocation, move.getToLocation());
        assertEquals(5, move.getQuantity()); // Should default to product's quantity
    }

    @Test
    @DisplayName("Test stock move creation with specified quantity")
    public void testStockMoveCreationWithQuantity() {
        StockMove move = new StockMove(product, fromLocation, toLocation, 3);

        assertEquals(product, move.getProduct());
        assertEquals(fromLocation, move.getFromLocation());
        assertEquals(toLocation, move.getToLocation());
        assertEquals(3, move.getQuantity()); // Should use specified quantity
    }

    @Test
    @DisplayName("Test stock move string representation")
    public void testStockMoveToString() {
        StockMove move = new StockMove(product, fromLocation, toLocation);

        String expected = "Moving product: Test Product (Qty: 5) from: Source Location to: Target Location";
        assertEquals(expected, move.toString());
    }
}