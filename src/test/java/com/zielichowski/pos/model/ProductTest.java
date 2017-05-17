package com.zielichowski.pos.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Tomek on 29-Mar-17.
 */
public class ProductTest {
    private Product product = new Product(1l, "FirstProduct", 20.0, "1");
    private Exporter exporter = new DemoFormatExporter();

    @Test
    public void testProductPrint() {
        String expected = "FirstProduct" + " \t" + "20.0";
        Assert.assertEquals(expected, product.print(exporter));
    }
}
