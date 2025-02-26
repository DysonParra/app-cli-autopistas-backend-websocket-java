/*
 * @fileoverview    {SizeElement}
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
package com.project.dev.websocket.model.element;

import lombok.Builder;
import lombok.Data;

/**
 * TODO: Description of {@code SizeElement}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public class SizeElement extends GenericElement {

    // Non static block.
    {
        this.type = TYPE_SIZE;
    }
    private int size;

    /**
     * TODO: Description of method {@code SizeElement}.
     *
     * @param sender
     * @param size
     */
    @Builder
    public SizeElement(String sender, int size) {
        super(sender);
        this.size = size;
    }

    /**
     * TODO: Description of method {@code getPrintTextSize}.
     *
     * @return
     */
    protected String getPrintTextSize() {
        return size + "";
    }

    /**
     * Get the current {@code Object} as {@code String}.
     *
     * @return {@code String} representing this {@code Object}.
     */
    @Override
    public String toString() {
        String text = "";
        text += "{" + getPrintTextSize();
        text += ", " + "'" + getPrintTextSender() + "'";
        if (recoveryDate != null)
            text += ", " + "'" + getPrintTextRecoveryDate() + "'";
        text += "}";
        return text;
    }

}
