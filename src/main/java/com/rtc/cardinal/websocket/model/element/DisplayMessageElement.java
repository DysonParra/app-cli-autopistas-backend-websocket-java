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
package com.rtc.cardinal.websocket.model.element;

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
