package com.zielichowski.pos.facades;

import com.zielichowski.pos.model.Receipt;

/**
 * Created by Tomek on 29-Mar-17.
 */
public interface PrintFacade {
    void printOnExitCode(Receipt receipt);

    void printOnScan(String barcode, Receipt receipt);
}
