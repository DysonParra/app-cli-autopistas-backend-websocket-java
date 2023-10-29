/*
 * @fileoverview    {TxtPrinter}
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;

/**
 * TODO: Definición de {@code TxtPrinter}.
 *
 * @author Dyson Parra
 * @since 11
 */
public class TxtPrinter extends GenericPrinter<String, String> {

    /**
     * TODO: Definición de {@code printObject}.
     *
     * @param printerName
     * @param objectToPrint
     * @throws javax.print.PrintException
     * @throws java.io.IOException
     */
    @Override
    public void printObject(String printerName, GenericObjectToPrint<String> objectToPrint) throws PrintException, IOException {
        PrintService myService = getPrintService(printerName);
        File homedir = new File(System.getProperty("user.home"));
        File fileToPrint = new File(homedir, "textToPrint.txt");
        fileToPrint.createNewFile();
        FileWriter myWriter = new FileWriter(fileToPrint);
        for (String line : objectToPrint.makeListToPrint()) {
            //System.out.println(line);
            myWriter.write(line);
        }
        myWriter.close();
        FileInputStream fis = new FileInputStream(fileToPrint);
        Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
        DocPrintJob printJob = myService.createPrintJob();
        printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
        fis.close();
        //fileToPrint.delete();
    }

}
