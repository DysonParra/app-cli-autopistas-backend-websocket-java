/*
 * @fileoverview {SizeElement} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {SizeElement} fue realizada el 31/07/2022.
 * @Dev - La primera version de {SizeElement} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.websocket.model.element;

import lombok.Builder;
import lombok.Data;

/**
 * TODO: Definición de {@code SizeElement}.
 *
 * @author Dyson Parra
 * @since 1.8
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
     * TODO: Definición de {@code SizeElement}.
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
     * TODO: Definición de {@code getPrintTextSize}.
     *
     * @return
     */
    protected String getPrintTextSize() {
        return size + "";
    }

    /**
     * Obtiene el valor en {String} del objeto actual.
     *
     * @return un {String} con la representación del objeto.
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
