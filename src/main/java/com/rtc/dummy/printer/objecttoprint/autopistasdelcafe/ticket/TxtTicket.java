/*
 * @fileoverview    {TxtTicket} se encarga de realizar tareas específicas.
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
package com.rtc.dummy.printer.objecttoprint.autopistasdelcafe.ticket;

import com.rtc.dummy.printer.objecttoprint.generic.GenericObjectToPrint;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Definición de {@code TxtTicket}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class TxtTicket implements GenericObjectToPrint<String> {

    /**
     * TODO: Definición de {@code makeListToPrint}.
     *
     * @return
     */
    @Override
    public List<String> makeListToPrint() {

        List<String> textToPrint = new ArrayList<>();
        textToPrint.add("\u250C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2510\n");
        textToPrint.add("\u2502                                                        \u2502\n");
        textToPrint.add("\u2502  CONSESION AUTOPISTAS DEL CAFE                         \u2502\n");
        textToPrint.add("\u2502                                                        \u2502\n");
        textToPrint.add("\u2502  Contrato Consesión                                    \u2502\n");
        textToPrint.add("\u2502                                                        \u2502\n");
        textToPrint.add("\u2502  ESTACION DE PESAJE DE CALARCA                         \u2502\n");
        textToPrint.add("\u2502                                                        \u2502\n");
        textToPrint.add("\u2502  VIGILADO SUPERTRANSPORTE                              \u2502\n");
        textToPrint.add("\u2502                                                        \u2502\n");
        textToPrint.add("\u2502  ANI                                                   \u2502\n");
        textToPrint.add("\u2514\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2518 \n");
        textToPrint.add("\n");
        textToPrint.add(" Operador  LUZ AMPARO CRUZ LUGO\n");
        textToPrint.add("\n");
        textToPrint.add(" Sentido vía: 1->\n");
        textToPrint.add("\n");
        textToPrint.add(" Fecha:  26/ene/2021    Hora: 13:48    Número: 530\n");
        textToPrint.add("\n");
        textToPrint.add(" Peso registrado:  13250\n");
        textToPrint.add("\n");
        textToPrint.add(" Peso máximo:  17000\n");
        textToPrint.add("\n");
        textToPrint.add(" Sobrepeso:  0\n");
        textToPrint.add("\n");
        textToPrint.add(" Año reg:  2017\n");
        textToPrint.add("\n");
        textToPrint.add(" Placa SQY260\n");
        textToPrint.add("\n");
        textToPrint.add(" Tipo veh: C2\n");
        textToPrint.add("\n");
        textToPrint.add(" Carrocería: CISTERNA\n");
        textToPrint.add("\n");
        textToPrint.add(" Carga: \n");
        textToPrint.add("\n");
        textToPrint.add(" Empresa: AVICOLA LA HORESTA\n");
        textToPrint.add("\n");
        textToPrint.add(" Observaciones: \n");
        textToPrint.add("\n");
        textToPrint.add(" P1:0 P2:0 P3:0\n");
        textToPrint.add("\n");
        textToPrint.add(" Pmax1:0 Pmax2:0 Pmax3:0\n");
        textToPrint.add("\n");
        return textToPrint;
    }

}
