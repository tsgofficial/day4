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
    
    @Test
    @DisplayName("Test product creation with negative quantity throws exception")
    public void testNegativeQuantityThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("Test Product", "TEST123", -5);
        });
        
        String expectedMessage = "Product quantity cannot be negative";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @DisplayName("Test setting negative quantity throws exception")
    public void testSetNegativeQuantityThrowsException() {
        Product product = new Product("Test Product", "TEST123", 5);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setQuantity(-10);
        });
        
        String expectedMessage = "Product quantity cannot be negative";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @DisplayName("Test null barcode throws exception")
    public void testNullBarcodeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("Test Product", null);
        });
        
        String expectedMessage = "Product barcode cannot be null";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @DisplayName("Test short barcode throws exception")
    public void testShortBarcodeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("Test Product", "AB");
        });
        
        String expectedMessage = "Product barcode must be at least 3 characters long";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
}