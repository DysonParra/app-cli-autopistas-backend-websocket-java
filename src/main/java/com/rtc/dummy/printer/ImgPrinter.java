/*
 * @fileoverview    {ImgPrinter} se encarga de realizar tareas específicas.
 *
 * @version         2.0
 *
 * @author          Dyson Arley Parra Tilano <dysontilano@gmail.com>
 *
 * @copyright       Dyson Parra
 * @see             github.com/DysonParra
 *
 * History
 * @version 1.0     Implementación realizada.
 * @version 2.0     Documentación agregada.
 */
package com.rtc.dummy.printer;

import com.rtc.dummy.printer.objecttoprint.generic.GenericObjectToPrint;
import com.rtc.dummy.printer.objecttoprint.generic.line.GenericImgLineToPrint;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;

/**
 * TODO: Definición de {@code ImgPrinter}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class ImgPrinter extends GenericPrinter<String, GenericImgLineToPrint> {

    /**
     * TODO: Definición de {@code makeImageFromGenericLines}.
     *
     * @param imageWidth
     * @param imageHeight
     * @param backGroundColor
     * @param textToPrint
     * @return
     */
    private File makeImageFromGenericLines(int imageWidth, int imageHeight, Color backGroundColor, List<GenericImgLineToPrint> textToPrint) {
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);// Represents an image with 8-bit RGBA color components packed into integer pixels.
        Graphics2D graphics2d = image.createGraphics();
        image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        graphics2d.dispose();
        graphics2d = image.createGraphics();
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setColor(backGroundColor);
        graphics2d.fillRect(0, 0, imageWidth, imageHeight);

        int offsetX;
        int offsetY = 0;
        int fontSize;
        int lineOffset;
        for (GenericImgLineToPrint line : textToPrint) {
            //System.out.println(line);
            graphics2d.setFont(line.getFont());
            graphics2d.setColor(line.getColor());
            fontSize = graphics2d.getFont().getSize();
            lineOffset = fontSize;
            lineOffset -= (int) (lineOffset * 0.25);
            offsetX = (int) (fontSize * (-0.10));
            offsetY += lineOffset;
            graphics2d.drawString(line.getText(), offsetX, offsetY);
        }

        graphics2d.dispose();
        try {
            File homedir = new File(System.getProperty("user.home"));
            File fileToWrite = new File(homedir, "imageToPrint.png");
            ImageIO.write(image, "png", fileToWrite);
            return fileToWrite;
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    /**
     * TODO: Definición de {@code printObject}.
     *
     * @param printerName
     * @param objectToPrint
     * @throws javax.print.PrintException
     * @throws java.io.IOException
     */
    @Override
    public void printObject(String printerName, GenericObjectToPrint<GenericImgLineToPrint> objectToPrint) throws PrintException, IOException {

        PrintService myService = getPrintService(printerName);
        File fileToPrint = makeImageFromGenericLines(612, 792, Color.WHITE, objectToPrint.makeListToPrint());
        if (fileToPrint != null) {
            FileInputStream fis = new FileInputStream(fileToPrint);
            Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
            DocPrintJob printJob = myService.createPrintJob();
            printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
            fis.close();
            fileToPrint.delete();
        }
    }

}
