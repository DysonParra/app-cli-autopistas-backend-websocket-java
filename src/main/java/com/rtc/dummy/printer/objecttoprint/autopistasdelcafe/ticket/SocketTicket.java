/*
 * @fileoverview {FileName} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {FileName} fue realizada el 31/07/2022.
 * @Dev - La primera version de {FileName} fue escrita por Dyson A. Parra T.
 */
package com.rtc.dummy.printer.objecttoprint.autopistasdelcafe.ticket;

import com.rtc.dummy.printer.objecttoprint.generic.GenericObjectToPrint;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Definición de {@code SocketTicket}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SocketTicket implements GenericObjectToPrint<String> {

    private String consecutivo;
    private String operador;
    private String sentidoVia;
    private String fechaRecuperacion;
    private String pesoRegistrado;
    private String pesoMaximo;
    private String sobrePeso;
    private String categoria;
    private String placa;

    /**
     * TODO: Definición de {@code makeListToPrint}.
     *
     * @return
     */
    @Override
    public List<String> makeListToPrint() {

        String lineToPrint;
        List<String> textToPrint = new ArrayList<>();
        // 48 characters per line in size 9.
        textToPrint.add("           " + "CONCESION AUTOPISTAS DEL CAFE" + "     ");
        textToPrint.add("");
        textToPrint.add("                " + "Contrato Concesion" + "           ");
        textToPrint.add("");
        textToPrint.add("          " + "ESTACION DE PESAJE DE CALARCA" + "      ");
        textToPrint.add("");
        textToPrint.add("             " + "VIGILADO SUPERTRANSPORTE" + "        ");
        textToPrint.add("");
        textToPrint.add("                       " + "ANI" + "                    ");
        textToPrint.add("");

        lineToPrint = makeLineToPrint(this.operador);
        textToPrint.add("               " + "Operador: " + lineToPrint);
        textToPrint.add("");

        lineToPrint = makeLineToPrint(this.consecutivo);
        textToPrint.add("            " + "Consecutivo: " + lineToPrint);
        textToPrint.add("");

        lineToPrint = makeLineToPrint(this.sentidoVia);
        textToPrint.add("            " + "Sentido via: " + lineToPrint);
        textToPrint.add("");

        String dateToPrint = "                  " + "Fecha: " + fechaRecuperacion;
        textToPrint.add(dateToPrint);
        textToPrint.add("");

        lineToPrint = makeLineToPrint(this.pesoRegistrado);
        textToPrint.add("        " + "Peso registrado: " + lineToPrint);
        textToPrint.add("");

        lineToPrint = makeLineToPrint(this.pesoMaximo);
        textToPrint.add("            " + "Peso maximo: " + lineToPrint);
        textToPrint.add("");

        lineToPrint = makeLineToPrint(this.sobrePeso);
        textToPrint.add("              " + "Sobrepeso: " + lineToPrint);
        textToPrint.add("");

        lineToPrint = makeLineToPrint(this.categoria);
        textToPrint.add("              " + "Categoria: " + lineToPrint);
        textToPrint.add("");

        lineToPrint = makeLineToPrint(this.placa);
        textToPrint.add("                  " + "Placa: " + lineToPrint);
        textToPrint.add("");

        return textToPrint;
    }

    /**
     * TODO: Definición de {@code makeLineToPrint}.
     *
     * @param value
     * @return
     */
    private String makeLineToPrint(String value) {
        String lineToPrint = value;
        if (value == null)
            lineToPrint = "";
        if (lineToPrint.length() > 46)
            lineToPrint = lineToPrint.substring(0, 46) + "                         " + lineToPrint.substring(46);
        if (lineToPrint.length() > 23)
            lineToPrint = lineToPrint.substring(0, 23) + "                         " + lineToPrint.substring(23);
        return lineToPrint;
    }

}
