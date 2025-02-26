/*
 * @fileoverview    {InternCommandElement}
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
 * TODO: Description of {@code InternCommandElement}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
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
     * TODO: Description of {@code InternCommandElement}.
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
     * TODO: Description of {@code getPrintTextInternCommand}.
     *
     * @return
     */
    protected String getPrintTextInternCommand() {
        return (internCommand == null) ? "" : internCommand;
    }

    /**
     * Get the current {@code Object} as {@code String}.
     *
     * @return {@code String} representing this {@code Object}.
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
