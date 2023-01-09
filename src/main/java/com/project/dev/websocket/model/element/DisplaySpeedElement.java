/*
 * @fileoverview    {DisplaySpeedElement} se encarga de realizar tareas específicas.
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
 * TODO: Definición de {@code DisplaySpeedElement}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public class DisplaySpeedElement extends GenericElement implements DisplayElement {

    // Non static block.
    {
        this.type = TYPE_DISPLAY_SPEED;
    }
    private int speed;

    /**
     * TODO: Definición de {@code DisplaySpeedElement}.
     *
     * @param sender
     * @param speed
     */
    @Builder
    public DisplaySpeedElement(String sender, int speed) {
        super(sender);
        this.speed = speed > 99 ? 99 : (speed) < 0 ? 0 : speed;
    }

    /**
     * TODO: Definición de {@code getPrintTextSpeed}.
     *
     * @return
     */
    protected String getPrintTextSpeed() {
        return String.format("%02d", speed);
    }

    /**
     * Obtiene el valor en {String} del objeto actual.
     *
     * @return un {String} con la representación del objeto.
     */
    @Override
    public String toString() {
        String text = "";
        text += "{" + getPrintTextSpeed();
        text += ", " + "'" + getPrintTextSender() + "'";
        if (recoveryDate != null)
            text += ", " + "'" + getPrintTextRecoveryDate() + "'";
        text += "}";
        return text;
    }

    /**
     * TODO: Definición de {@code getMessage}.
     *
     * @return
     */
    @Override
    public String getMessage() {
        return getPrintTextSpeed();
    }

    /**
     * TODO: Definición de {@code isSpeed}.
     *
     * @return
     */
    @Override
    public boolean isSpeed() {
        return true;
    }

}
