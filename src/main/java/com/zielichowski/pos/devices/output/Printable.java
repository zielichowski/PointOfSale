package com.zielichowski.pos.devices.output;

import com.zielichowski.pos.model.Exporter;

/**
 * Created by Tomek on 27-Mar-17.
 */
public interface Printable {
    String print(Exporter exporter);
}
