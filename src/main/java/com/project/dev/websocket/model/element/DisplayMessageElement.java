/*
 * @fileoverview    {DisplayMessageElement}
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
 * TODO: Description of {@code DisplayMessageElement}.
 *
 * @author Dyson Parra
 * @since 11
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public class DisplayMessageElement extends GenericElement implements DisplayElement {

    // Non static block.
    {
        this.type = TYPE_DISPLAY_MESSAGE;
    }
    private String message;

    /**
     * TODO: Description of {@code DisplayMessageElement}.
     *
     * @param sender
     * @param message
     */
    @Builder
    public DisplayMessageElement(String sender, String message) {
        super(sender);
        this.message = message;
    }

    /**
     * TODO: Description of {@code getPrintTextMessage}.
     *
     * @return
     */
    protected String getPrintTextMessage() {
        return (message == null) ? "" : "'" + message + "'";
    }

    /**
     * Obtiene el valor en {String} del objeto actual.
     *
     * @return un {String} con la representación del objeto.
     */
    @Override
    public String toString() {
        String text = "";
        text += "{" + getPrintTextMessage();
        text += ", " + "'" + getPrintTextSender() + "'";
        if (recoveryDate != null)
            text += ", " + "'" + getPrintTextRecoveryDate() + "'";
        text += "}";
        return text;
    }

    /**
     * TODO: Description of {@code isSpeed}.
     *
     * @return
     */
    @Override
    public boolean isSpeed() {
        return false;
    }
}
