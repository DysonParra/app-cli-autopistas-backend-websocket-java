/*
 * @fileoverview    {SocketPrinter} se encarga de realizar tareas específicas.
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
package com.project.dev.dummy.printer;

import com.project.dev.dummy.printer.objecttoprint.generic.GenericObjectToPrint;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.print.PrintException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO: Definición de {@code SocketPrinter}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public class SocketPrinter extends GenericPrinter<Socket, String> {

    protected String name;
    protected String printerIpAddress;
    protected int printerPort;
    protected long retryTime = 1000;
    protected long maxTries = 5;
    protected Socket printerSocket;
    @Setter(AccessLevel.NONE)
    protected boolean connected = false;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    protected final Object synchronizer = new Object();

    /**
     * TODO: Definición de {@code SocketPrinter}.
     *
     */
    public SocketPrinter() {
        super();
        this.name = "SocketPrinter";
    }

    /**
     * TODO: Definición de {@code SocketPrinter}.
     *
     * @param printerSocket
     */
    public SocketPrinter(Socket printerSocket) {
        this.printerSocket = printerSocket;
        this.name = "SocketPrinter ";
        if (printerSocket != null)
            this.name += printerSocket.getInetAddress() + ":" + printerSocket.getPort();
    }

    /**
     * TODO: Definición de {@code SocketPrinter}.
     *
     * @param printerIpAddress
     * @param printerPort
     */
    public SocketPrinter(String printerIpAddress, int printerPort) {
        this.name = "SocketPrinter " + printerIpAddress + ":" + printerPort;
        this.printerIpAddress = printerIpAddress;
        this.printerPort = printerPort;
        this.printerSocket = connectToServer(printerIpAddress, printerPort);
        if (printerSocket != null)
            this.connected = true;
    }

    /**
     * TODO: Definición de {@code connectToServer}.
     *
     * @param printerIpAddress
     * @param printerPort
     * @return
     */
    private Socket connectToServer(String printerIpAddress, int printerPort) {
        int tries = 0;
        Socket socket = null;
        do {
            try {
                tries++;
                System.out.println("Trying to connect client '" + name + "' to server in " + printerIpAddress + ":" + printerPort + " (Trie " + tries + ")");
                socket = new Socket(printerIpAddress, printerPort);
                System.out.println("Client " + "'" + name + "' is connected to server in " + printerIpAddress + ":" + printerPort);
                break;
            } catch (Exception e) {
                System.out.println(" Error connecting client '" + name + "' to server in " + printerIpAddress + ":" + printerPort);
                this.connected = false;
                if (this.retryTime > 0) {
                    System.out.println(" Waiting " + retryTime / 1000.0 + " seconds for reconnect client '" + name + "'..." + "\n");
                    try {
                        //Thread.sleep(this.retryTime);
                        synchronized (synchronizer) {
                            synchronizer.wait(retryTime);
                        }
                    } catch (InterruptedException ex) {
                        System.out.println("Error waiting to retry..." + "\n");
                    }
                } else {
                    socket = null;
                    break;
                }
            }
        } while (this.maxTries != tries);
        return socket;
    }

    /**
     * TODO: Definición de {@code printObject}.
     *
     * @param printerSocket
     * @param objectToPrint
     * @throws javax.print.PrintException
     * @throws java.io.IOException
     */
    @Override
    public void printObject(Socket printerSocket, GenericObjectToPrint<String> objectToPrint) throws PrintException, IOException {
        if (printerSocket == null)
            throw new PrintException("Error printing, socket not especified.");
        File homedir = new File(System.getProperty("user.home"));
        File fileToPrint = new File(homedir, "textToPrint.txt");
        fileToPrint.createNewFile();
        FileWriter myWriter = new FileWriter(fileToPrint);
        for (String line : objectToPrint.makeListToPrint())
            //System.out.println(line);
            myWriter.write(line);
        myWriter.close();

        //System.out.println("Printing");
        PrintWriter printerCout = new PrintWriter(printerSocket.getOutputStream(), true);
        setFontSize(printerCout, (char) 8);
        setAlign(printerCout, "left");

        objectToPrint.makeListToPrint().forEach(line -> printerCout.println(line));
        endPrint(printerCout);
        //fileToPrint.delete();
        System.out.println("Printed: " + objectToPrint);
    }

    /**
     * TODO: Definición de {@code printObject}.
     *
     * @param objectToPrint
     * @throws javax.print.PrintException
     * @throws java.io.IOException
     */
    public void printObject(GenericObjectToPrint<String> objectToPrint) throws PrintException, IOException {
        if (!connected)
            throw new PrintException("Error printing, printer is disconnected.");
        printObject(this.printerSocket, objectToPrint);
    }

    /**
     * TODO: Definición de {@code printObject}.
     *
     * @param printerIpAddress
     * @param objectToPrint
     * @param printerPort
     * @throws javax.print.PrintException
     * @throws java.io.IOException
     */
    public void printObject(String printerIpAddress, int printerPort, GenericObjectToPrint<String> objectToPrint) throws PrintException, IOException {
        Socket socket = connectToServer(printerIpAddress, printerPort);
        if (socket == null)
            throw new PrintException("Error printing, printer is disconnected.");
        printObject(socket, objectToPrint);
        socket.close();
    }

    /**
     * TODO: Definición de {@code sendCommand}.
     *
     * @param printerCout
     * @param command
     */
    private void sendCommand(PrintWriter printerCout, char[] command) {
        printerCout.write(command);
    }

    /**
     * TODO: Definición de {@code setFontSize}.
     *
     * @param printerCout
     * @param fontSize
     */
    private void setFontSize(PrintWriter printerCout, char fontSize) {
        sendCommand(printerCout, new char[]{(char) 0x1D, (char) 0x21, fontSize}); //font size
    }

    /**
     * TODO: Definición de {@code setAlign}.
     *
     * @param printerCout
     * @param align
     */
    private void setAlign(PrintWriter printerCout, String align) {

        int alignCode = 0;
        switch (align) {
            case "left":
            case "Left":
                alignCode = 0;
                break;

            case "medium":
            case "Medium":
                alignCode = 1;
                break;

            case "right":
            case "Right":
                alignCode = 2;
                break;
        }
        sendCommand(printerCout, new char[]{(char) 0x1B, (char) 0x61, (char) alignCode}); //center.
    }

    /**
     * TODO: Definición de {@code endPrint}.
     *
     * @param printerCout
     */
    private void endPrint(PrintWriter printerCout) {
        printerCout.println("\n");
        printerCout.println("\n");
        printerCout.println("\n");
        sendCommand(printerCout, new char[]{(char) 0x1B, (char) 0x69});
        printerCout.println("\n");
    }
}
