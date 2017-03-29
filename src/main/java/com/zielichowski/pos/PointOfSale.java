package com.zielichowski.pos;

import com.zielichowski.pos.facades.PrintFacade;
import com.zielichowski.pos.devices.input.BarcodeScanner;
import com.zielichowski.pos.listeners.BarcodeListener;
import com.zielichowski.pos.listeners.ExitCodeListener;
import com.zielichowski.pos.model.ProductReceipt;
import com.zielichowski.pos.model.Receipt;

/**
 * Created by Tomek on 27-Mar-17.
 */
public class PointOfSale implements BarcodeListener, ExitCodeListener {
    private BarcodeScanner barcodeScanner;
    private PrintFacade printFacade;
    private Receipt productReceipt = new ProductReceipt();

    public PointOfSale(BarcodeScanner barcodeScanner, PrintFacade printFacade) {
        this.barcodeScanner = barcodeScanner;
        this.printFacade = printFacade;
    }

    @Override
    public void onExitCode() {
        printFacade.printOnExitCode(this.productReceipt);
        productReceipt.clear();
    }

    @Override
    public void onScan() {
        String barcode = barcodeScanner.readBarcode();
        printFacade.printOnScan(barcode, this.productReceipt);

    }


}
