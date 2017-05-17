package com.zielichowski.pos.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Tomek on 17-May-17.
 */
public class DemoFormatExporterTest {
    private Exporter demoFormatExporter = new DemoFormatExporter();

    @Test
    public void shouldReturnAppropriateFormat() {
        demoFormatExporter.addFieldToPrint("one");
        demoFormatExporter.addFieldToPrint("two");

        String outputExpected = "one" + " \t" + "two" + " \t";

        assertEquals(outputExpected, demoFormatExporter.toString());
    }
}
