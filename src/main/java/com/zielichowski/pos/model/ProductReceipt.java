package com.zielichowski.pos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomek on 27-Mar-17.
 */
public class ProductReceipt implements Receipt {

    private List<Product> productList = new ArrayList<>();

    @Override
    public void addToReceipt(Product item) {
        productList.add(item);
    }

    @Override
    public Double getTotal() {
        return productList.stream().mapToDouble(Product::getPrice).sum();
    }

/*    @Override
    public String print() {
        StringBuilder stringBuilder = new StringBuilder(100);
        productList.forEach(p -> stringBuilder.append(p.getName()).append("\t").append(p.getPrice()).append("\n"));
        stringBuilder.append(getTotal());
        return stringBuilder.toString();
    }*/

    @Override
    public void clear() {
        productList.clear();
    }

    @Override
    public String print(Exporter exporter) {
        productList.forEach(p -> {
            exporter.addFieldToPrint(p.getName());
            exporter.addFieldToPrint(p.getPrice().toString());
        });
        exporter.addFieldToPrint(getTotal().toString());
        return exporter.toString();
    }
}
