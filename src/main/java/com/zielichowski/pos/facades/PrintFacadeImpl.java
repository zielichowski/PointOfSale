package com.zielichowski.pos.facades;

import com.zielichowski.pos.devices.output.Display;
import com.zielichowski.pos.devices.output.OutputDevice;
import com.zielichowski.pos.model.Product;
import com.zielichowski.pos.model.Receipt;
import com.zielichowski.pos.repository.ProductRepository;
import com.zielichowski.pos.util.Message;

import java.util.Optional;

/**
 * Created by Tomek on 29-Mar-17.
 */
public class PrintFacadeImpl implements PrintFacade {
    private OutputDevice printer;
    private Display lcdDisplay;
    private ProductRepository productRepository;

    public PrintFacadeImpl(OutputDevice printer, Display lcdDisplay, ProductRepository productRepository) {
        this.printer = printer;
        this.lcdDisplay = lcdDisplay;
        this.productRepository = productRepository;
    }

    @Override
    public void printOnExitCode(Receipt receipt) {
        printer.print(receipt);
        lcdDisplay.printAdditionalInformation(receipt.getTotal().toString());
    }

    @Override
    public void printOnScan(String barcode, Receipt receipt) {
        if (isNotValid(barcode)) {
            lcdDisplay.printAdditionalInformation(Message.INVALID_BARCODE);
        } else {
            handleValidCode(barcode, receipt);
        }
    }

    private void handleValidCode(String barcode, Receipt receipt) {
        Optional<Product> productByBarcode = getProductByBarcode(barcode);

        if (productByBarcode.isPresent()) {
            Product product = productByBarcode.get();
            handleCorrectProduct(product, receipt);
        } else {
            lcdDisplay.printAdditionalInformation(Message.PRODUCT_NOT_FOUND);
        }
    }

    private Optional<Product> getProductByBarcode(String barcode) {
        return Optional.ofNullable(productRepository.findByBarcode(barcode));
    }

    private void handleCorrectProduct(Product product, Receipt receipt) {
        lcdDisplay.print(product);
        receipt.addToReceipt(product);
    }

    private boolean isNotValid(String barcode) {
        return (barcode.isEmpty());
    }
}
