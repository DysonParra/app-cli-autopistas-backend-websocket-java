/*
 * @fileoverview    {GenericImgLineToPrint} se encarga de realizar tareas específicas.
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
package com.rtc.dummy.printer.objecttoprint.generic.line;

import java.awt.Color;
import java.awt.Font;
import lombok.Data;

/**
 * TODO: Definición de {@code GenericImgLineToPrint}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public class GenericImgLineToPrint {

    private Font font;
    private Color color;
    private String text;

    /**
     * TODO: Definición de {@code GenericImgLineToPrint}.
     *
     */
    public GenericImgLineToPrint() {

    }

    /**
     * TODO: Definición de {@code GenericImgLineToPrint}.
     *
     * @param font
     * @param text
     * @param color
     */
    public GenericImgLineToPrint(Font font, Color color, String text) {
        this.font = font;
        this.color = color;
        this.text = text;
    }

}
