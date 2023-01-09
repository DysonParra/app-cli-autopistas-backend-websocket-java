/*
 * @fileoverview    {WeightElement} se encarga de realizar tareas específicas.
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
 * TODO: Definición de {@code WeightElement}.
 *
 * @author Dyson Parra
 * @since 1.8
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
     * TODO: Definición de {@code WeightElement}.
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
     * TODO: Definición de {@code getPrintTextWeight}.
     *
     * @return
     */
    protected String getPrintTextWeight() {
        return weight + " KG";
    }

    /**
     * Obtiene el valor en {String} del objeto actual.
     *
     * @return un {String} con la representación del objeto.
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
