package com.zielichowski.pos.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Tomek on 29-Mar-17.
 */
public class ProductReceiptTest {
    private Receipt receipt = new ProductReceipt();
    private Exporter exporter = new DemoFormatExporter();

    @Before
    public void addTwoProductsToProductReceipt() {
        Product productOne = new Product(1l, "One", 10.0, "1");
        Product productTwo = new Product(2l, "Two", 20.0, "2");
        receipt.addToReceipt(productOne);
        receipt.addToReceipt(productTwo);

    }

    @Test
    public void testProductReceiptPrint() {

        String expected = "One" + " \t" + 10.0 + " \t" +
                "Two" + " \t" + 20.0 + " \t" +
                30.0+" \t";

        Assert.assertEquals(expected, receipt.print(exporter));
    }

    @Test
    public void testGetTotal() {
        Double expected = 30.0;
        Assert.assertEquals(expected, receipt.getTotal(), 0.0001);
    }

    @Test
    public void testClearReceipt() {
        String expectedPrint = "0.0"+" \t";
        Double expectedTotal = 0.0;

        receipt.clear();

        Assert.assertEquals(expectedTotal, receipt.getTotal());
        Assert.assertEquals(expectedPrint, receipt.print(exporter));
    }

}
