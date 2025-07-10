/*
 * @overview        {DisplaySpeedElement}
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
 * TODO: Description of {@code DisplaySpeedElement}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
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
     * TODO: Description of method {@code DisplaySpeedElement}.
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
     * TODO: Description of method {@code getPrintTextSpeed}.
     *
     * @return
     */
    protected String getPrintTextSpeed() {
        return String.format("%02d", speed);
    }

    /**
     * Get the current {@code Object} as {@code String}.
     *
     * @return {@code String} representing this {@code Object}.
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
     * TODO: Description of method {@code getMessage}.
     *
     * @return
     */
    @Override
    public String getMessage() {
        return getPrintTextSpeed();
    }

    /**
     * TODO: Description of method {@code isSpeed}.
     *
     * @return
     */
    @Override
    public boolean isSpeed() {
        return true;
    }

}
