/*
 * @overview        {GenericImgLineToPrint}
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
package com.project.dev.dummy.printer.objecttoprint.generic.line;

import java.awt.Color;
import java.awt.Font;
import lombok.Data;

/**
 * TODO: Description of {@code GenericImgLineToPrint}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
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
     * TODO: Description of method {@code GenericImgLineToPrint}.
     *
     */
    public GenericImgLineToPrint() {

    }

    /**
     * TODO: Description of method {@code GenericImgLineToPrint}.
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
