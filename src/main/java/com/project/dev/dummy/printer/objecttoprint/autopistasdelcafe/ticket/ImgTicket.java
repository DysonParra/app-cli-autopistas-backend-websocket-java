/*
 * @fileoverview    {ImgTicket}
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
package com.project.dev.dummy.printer.objecttoprint.autopistasdelcafe.ticket;

import com.project.dev.dummy.printer.objecttoprint.generic.GenericObjectToPrint;
import com.project.dev.dummy.printer.objecttoprint.generic.line.GenericImgLineToPrint;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Definición de {@code ImgTicket}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class ImgTicket implements GenericObjectToPrint<GenericImgLineToPrint> {

    /**
     * TODO: Definición de {@code makeListToPrint}.
     *
     * @return
     */
    @Override
    public List<GenericImgLineToPrint> makeListToPrint() {
        List<GenericImgLineToPrint> textToPrint = new ArrayList<>();
        //textToPrint.add(new LineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, "\u250C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2510"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, "\u2502                                                                                  \u2502"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, "\u2502  CONSESION AUTOPISTAS DEL CAFE                   \u2009\u2009\u2502"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, "\u2502                                                                                  \u2502"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, "\u2502  Contrato Consesión                                         \u2009\u2009\u2009\u2009\u2009\u2009\u2009\u2009\u2009\u2009\u2502"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, "\u2502                                                                                  \u2502"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, "\u2502  ESTACION DE PESAJE DE CALARCA                      \u2502"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, "\u2502                                                                                  \u2502"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, "\u2502  VIGILADO SUPERTRANSPORTE                             \u2502"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, "\u2502                                                                                  \u2502"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, "\u2502  ANI                                                                         \u2009\u2009\u2502"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, "\u2514\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2518 "));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, " Operador  LUZ AMPARO CRUZ LUGO"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, " Sentido vía: 1->"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, " Fecha:  26/ene/2021    Hora: 13:48    Número: 530"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, " Peso registrado:  13250"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, " Peso máximo:  17000"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, " Sobrepeso:  0"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, " Año reg:  2017"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, " Placa SQY260"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, " Tipo veh: C2"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, " Carrocería: CISTERNA"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, " Carga: "));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, " Empresa: AVICOLA LA HORESTA"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, " Observaciones: "));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, " P1:0 P2:0 P3:0"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, " Pmax1:0 Pmax2:0 Pmax3:0"));
        textToPrint.add(new GenericImgLineToPrint(new Font("TimesNewRoman", Font.PLAIN, 20), Color.BLACK, ""));
        return textToPrint;
    }

}
