package com.zielichowski.pos.model;

import com.zielichowski.pos.devices.output.Printable;

/**
 * Created by Tomek on 27-Mar-17.
 */
public interface Receipt extends Printable {
    void addToReceipt(Product item);
    Double getTotal();
    void clear();

}
