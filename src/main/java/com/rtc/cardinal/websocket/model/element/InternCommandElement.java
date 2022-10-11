/*
 * @fileoverview {InternCommandElement} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {InternCommandElement} fue realizada el 31/07/2022.
 * @Dev - La primera version de {InternCommandElement} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.websocket.model.element;

import lombok.Builder;
import lombok.Data;

/**
 * TODO: Definición de {@code InternCommandElement}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public class InternCommandElement extends GenericElement {

    public static final String COMMAND_INIT = "100";
    public static final String COMMAND_PRINT = "101";
    public static final String COMMAND_VALIDATE_AND_CLEAR = "102";

    // Non static block.
    {
        this.type = TYPE_PLC_INTERN_COMMAND;
    }
    private String internCommand;

    /**
     * TODO: Definición de {@code InternCommandElement}.
     *
     * @param sender
     * @param internCommand
     */
    @Builder
    public InternCommandElement(String sender, String internCommand) {
        super(sender);
        this.internCommand = internCommand;
    }

    /**
     * TODO: Definición de {@code getPrintTextInternCommand}.
     *
     * @return
     */
    protected String getPrintTextInternCommand() {
        return (internCommand == null) ? "" : internCommand;
    }

    /**
     * Obtiene el valor en {String} del objeto actual.
     *
     * @return un {String} con la representación del objeto.
     */
    @Override
    public String toString() {
        String text = "";
        text += "{" + "'" + getPrintTextInternCommand() + "'";
        text += ", " + "'" + getPrintTextSender() + "'";
        if (recoveryDate != null)
            text += ", " + "'" + getPrintTextRecoveryDate() + "'";
        text += "}";
        return text;
    }

}
