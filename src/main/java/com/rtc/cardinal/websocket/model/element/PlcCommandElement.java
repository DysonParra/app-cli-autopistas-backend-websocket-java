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
 * TODO: Definición de {@code PlcCommandElement}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public class PlcCommandElement extends GenericElement {

    // Non static block.
    {
        this.type = TYPE_PLC_COMMAND;
    }
    private String command;

    /**
     * TODO: Definición de {@code PlcCommandElement}.
     *
     * @param sender
     * @param command
     */
    @Builder
    public PlcCommandElement(String sender, String command) {
        super(sender);
        //this.sender = sender;
        this.command = command;
    }

    /**
     * TODO: Definición de {@code getPrintTextCommand}.
     *
     * @return
     */
    protected String getPrintTextCommand() {
        return (command == null) ? "" : command;
    }

    /**
     * Obtiene el valor en {String} del objeto actual.
     *
     * @return un {String} con la representación del objeto.
     */
    @Override
    public String toString() {
        String text = "";
        text += "{" + "'" + getPrintTextCommand() + "'";
        text += ", " + "'" + getPrintTextSender() + "'";
        if (recoveryDate != null)
            text += ", " + "'" + getPrintTextRecoveryDate() + "'";
        text += "}";
        return text;
    }

}
