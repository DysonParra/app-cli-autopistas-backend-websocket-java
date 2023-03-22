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
 * @version 1.0     Implementación realizada.
 * @version 2.0     Documentación agregada.
 */
package com.project.dev.websocket.model.element;

import lombok.Builder;
import lombok.Data;

/**
 * TODO: Definición de {@code DisplayMessageElement}.
 *
 * @author Dyson Parra
 * @since 1.8
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
     * TODO: Definición de {@code DisplayMessageElement}.
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
     * TODO: Definición de {@code getPrintTextMessage}.
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
     * TODO: Definición de {@code isSpeed}.
     *
     * @return
     */
    @Override
    public boolean isSpeed() {
        return false;
    }
}
