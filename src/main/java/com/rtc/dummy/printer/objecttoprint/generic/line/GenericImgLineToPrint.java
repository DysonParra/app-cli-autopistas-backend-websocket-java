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
