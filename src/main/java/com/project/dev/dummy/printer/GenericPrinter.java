/*
 * @fileoverview    {GenericPrinter}
 *
 * @version         2.0
 *
 * @author          Dyson Arley Parra Tilano <dysontilano@gmail.com>
 *
 * @copyright       Dyson Parra
 * @see             github.com/DysonParra
 *
 * History
 * @version 1.0     Implementation done.
 * @version 2.0     Documentation added.
 */
package com.project.dev.dummy.printer;

import com.project.dev.dummy.printer.objecttoprint.generic.GenericObjectToPrint;
import java.io.IOException;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;

/**
 * TODO: Description of {@code GenericPrinter}.
 *
 * @param <T>
 * @param <U>
 *
 * @author Dyson Parra
 * @since 11
 */
public abstract class GenericPrinter<T, U> {

    /**
     * TODO: Description of {@code getPrinters}.
     *
     */
    public void getPrinters() {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Number of print services: " + printServices.length);

        for (PrintService printer : printServices)
            System.out.println("Printer: '" + printer.getName() + "'");
    }

    /**
     * TODO: Description of {@code getPrintService}.
     *
     * @param printerName
     * @return
     * @throws javax.print.PrintException
     */
    public PrintService getPrintService(String printerName) throws PrintException {
        PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
        patts.add(Sides.DUPLEX);
        PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printer : ps)
            System.out.println("Printer: '" + printer.getName() + "'");

        if (ps.length == 0)
            throw new IllegalStateException("No Printer found");
        //System.out.println("Available printers: " + Arrays.toString(ps));

        PrintService myService = null;
        for (PrintService printService : ps)
            if (printService.getName().equals(printerName)) {
                myService = printService;
                break;
            }
        if (myService == null)
            throw new IllegalStateException("Printer not found");
        return myService;
    }

    public abstract void printObject(T printerInformation, GenericObjectToPrint<U> objectToPrint) throws PrintException, IOException;

}
