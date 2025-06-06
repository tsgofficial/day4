package com.practice.day5;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Product class functionality.
 * Verifies product attributes and behavior.
 */
public class ProductTest {

    @Test
    @DisplayName("Test product creation with default quantity")
    public void testCreateProductDefaultQuantity() {
        Product product = new Product("Test Product", "TEST123");
        assertEquals("Test Product", product.getName());
        assertEquals("TEST123", product.getBarcode());
        assertEquals(1, product.getQuantity()); // Default quantity should be 1
    }

    @Test
    @DisplayName("Test product creation with specified quantity")
    public void testCreateProductWithQuantity() {
        Product product = new Product("Test Product", "TEST123", 5);
        assertEquals("Test Product", product.getName());
        assertEquals("TEST123", product.getBarcode());
        assertEquals(5, product.getQuantity());
    }

    @Test
    @DisplayName("Test setting product quantity")
    public void testSetQuantity() {
        Product product = new Product("Test Product", "TEST123", 5);
        assertEquals(5, product.getQuantity());

        product.setQuantity(10);
        assertEquals(10, product.getQuantity());
    }

    @Test
    @DisplayName("Test service product creation inherits from product")
    public void testServiceProductInheritance() {
        ServiceProduct serviceProduct = new ServiceProduct("Cloud Service", "CLOUD123", 1);
        assertEquals("Cloud Service", serviceProduct.getName());
        assertEquals("CLOUD123", serviceProduct.getBarcode());
        assertEquals(1, serviceProduct.getQuantity());
    }
}