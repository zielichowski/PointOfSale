package com.zielichowski.pos.facades;

import com.zielichowski.pos.devices.output.Display;
import com.zielichowski.pos.devices.output.OutputDevice;
import com.zielichowski.pos.model.Product;
import com.zielichowski.pos.model.ProductReceipt;
import com.zielichowski.pos.model.Receipt;
import com.zielichowski.pos.repository.ProductRepository;
import com.zielichowski.pos.util.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Created by Tomek on 29-Mar-17.
 */
@RunWith(MockitoJUnitRunner.class)
public class PrintFacadeTest {
    @Mock
    ProductRepository productRepository;
    @Mock
    Display lcdDisplay;
    @Mock
    OutputDevice printer;

    private Product productOne = new Product(1l, "One", 10.0, "1");
    private Product productTwo = new Product(2l, "Two", 20.0, "2");

    private Receipt receipt = new ProductReceipt();

    private PrintFacade printFacade;

    @Before
    public void init() {
        when(productRepository.findByBarcode("1")).thenReturn(productOne);
        when(productRepository.findByBarcode("2")).thenReturn(productTwo);
        printFacade = new PrintFacadeImpl(printer, lcdDisplay, productRepository);

    }

    @Test
    public void testCorrectBarcodeOnExistingProductInDB() {
        printFacade.printOnScan("1", receipt);
        verify(lcdDisplay).print(productOne);
        verifyNoMoreInteractions(lcdDisplay);

    }

    @Test
    public void testInvalidBarcode() {
        printFacade.printOnScan("3", receipt);
        verify(lcdDisplay).printAdditionalInformation(Message.PRODUCT_NOT_FOUND);
        verifyNoMoreInteractions(lcdDisplay);
    }

    @Test
    public void testBlankBarcode() {
        printFacade.printOnScan("", receipt);
        verify(lcdDisplay).printAdditionalInformation(Message.INVALID_BARCODE);
        verifyNoMoreInteractions(lcdDisplay);
    }

    @Test
    public void testMultiplyBarcodeScan() {
        printFacade.printOnScan("1", receipt);
        printFacade.printOnScan("2", receipt);
        verify(lcdDisplay).print(productOne);
        verify(lcdDisplay).print(productTwo);
        verifyNoMoreInteractions(lcdDisplay);
    }

    @Test
    public void testOnExit() {
        printFacade.printOnExitCode(receipt);
        verify(printer).print(receipt);
        verify(lcdDisplay).printAdditionalInformation(receipt.getTotal().toString());
        verifyNoMoreInteractions(lcdDisplay);
        verifyNoMoreInteractions(printer);

    }
}
