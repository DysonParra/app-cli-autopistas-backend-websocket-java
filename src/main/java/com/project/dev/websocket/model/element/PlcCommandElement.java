/*
 * @fileoverview    {PlcCommandElement}
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
 * TODO: Description of {@code PlcCommandElement}.
 *
 * @author Dyson Parra
 * @since 11
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
     * TODO: Description of {@code PlcCommandElement}.
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
     * TODO: Description of {@code getPrintTextCommand}.
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
