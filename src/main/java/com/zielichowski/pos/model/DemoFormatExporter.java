package com.zielichowski.pos.model;

/**
 * Created by Tomek on 17-May-17.
 */
public class DemoFormatExporter implements Exporter {
    private String toPrint = "";

    @Override
    public void addFieldToPrint(String field) {
        toPrint += field;
        if (!toPrint.isEmpty())
            toPrint += " \t";

    }

    @Override
    public String toString() {
        return toPrint;
    }
}
