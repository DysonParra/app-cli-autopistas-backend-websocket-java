/*
 * @fileoverview    {GenericElement} se encarga de realizar tareas específicas.
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
package com.rtc.cardinal.websocket.model.element;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Data;

/**
 * TODO: Definición de {@code GenericElement}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public abstract class GenericElement {

    public static final String TYPE_INVALID = "0";
    public static final String TYPE_PLC_COMMAND = "1";
    public static final String TYPE_PLC_INTERN_COMMAND = "2";
    public static final String TYPE_DISPLAY_SPEED = "3";
    public static final String TYPE_DISPLAY_MESSAGE = "4";
    public static final String TYPE_CAMERA_PLATE = "5";
    public static final String TYPE_WEIGHT = "6";
    public static final String TYPE_SIZE = "7";

    protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    protected String type;
    protected String sender;
    protected Date recoveryDate = new Date();

    /**
     * TODO: Definición de {@code GenericElement}.
     *
     * @param sender
     */
    public GenericElement(String sender) {
        this.sender = sender;
    }

    /**
     * TODO: Definición de {@code getPrintTextSender}.
     *
     * @return
     */
    protected String getPrintTextSender() {
        return (sender == null) ? "Unknown" : sender;
    }

    /**
     * TODO: Definición de {@code getPrintTextRecoveryDate}.
     *
     * @return
     */
    protected String getPrintTextRecoveryDate() {
        return (recoveryDate == null) ? "" : DATE_FORMAT.format(recoveryDate) + "";
    }

}
