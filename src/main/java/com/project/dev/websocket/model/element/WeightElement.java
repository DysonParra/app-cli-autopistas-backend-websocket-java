/*
 * @fileoverview    {WeightElement}
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
 * TODO: Description of {@code WeightElement}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public class WeightElement extends GenericElement {

    // Non static block.
    {
        this.type = TYPE_WEIGHT;
    }
    private int weight;

    /**
     * TODO: Description of {@code WeightElement}.
     *
     * @param sender
     * @param weight
     */
    @Builder
    public WeightElement(String sender, int weight) {
        super(sender);
        this.weight = weight;
    }

    /**
     * TODO: Description of {@code getPrintTextWeight}.
     *
     * @return
     */
    protected String getPrintTextWeight() {
        return weight + " KG";
    }

    /**
     * Get the current {@code Object} as {@code String}.
     *
     * @return {@code String} representing this {@code Object}.
     */
    @Override
    public String toString() {
        String text = "";
        text += "{" + getPrintTextWeight();
        text += ", " + "'" + getPrintTextSender() + "'";
        if (recoveryDate != null)
            text += ", " + "'" + getPrintTextRecoveryDate() + "'";
        text += "}";
        return text;
    }

}
