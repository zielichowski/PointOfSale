package com.zielichowski.pos.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Tomek on 29-Mar-17.
 */
public class ProductReceiptTest {
    private Receipt receipt = new ProductReceipt();

    @Before
    public void addTwoProductsToProductReceipt() {
        Product productOne = new Product(1l, "One", 10.0, "1");
        Product productTwo = new Product(2l, "Two", 20.0, "2");
        receipt.addToReceipt(productOne);
        receipt.addToReceipt(productTwo);

    }

    @Test
    public void testProductReceiptPrint() {

        StringBuilder stringBuilder = new StringBuilder(100);
        String expected = stringBuilder.append("One").append("\t").append(10.0).append("\n")
                .append("Two").append("\t").append(20.0).append("\n")
                .append(30.0).toString();

        Assert.assertEquals(expected, receipt.print());
    }

    @Test
    public void testGetTotal() {
        Double expected = 30.0;
        Assert.assertEquals(expected, receipt.getTotal(), 0.00);
    }

    @Test
    public void testClearReceipt() {
        String expectedPrint = "0.0";
        Double expectedTotal = 0.0;

        receipt.clear();

        Assert.assertEquals(expectedTotal,receipt.getTotal());
        Assert.assertEquals(expectedPrint,receipt.print());
    }

}
