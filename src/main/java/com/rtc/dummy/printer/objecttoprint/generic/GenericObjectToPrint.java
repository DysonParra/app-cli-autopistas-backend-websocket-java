/*
 * @fileoverview    {GenericObjectToPrint} se encarga de realizar tareas específicas.
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
package com.rtc.dummy.printer.objecttoprint.generic;

import java.util.List;

/**
 * TODO: Definición de {@code GenericObjectToPrint}.
 *
 * @author Dyson Parra
 * @param <T>
 * @since 1.8
 */
public interface GenericObjectToPrint<T> {

    /**
     * TODO: Definición de {@code makeListToPrint}.
     *
     * @return
     */
    public abstract List<T> makeListToPrint();
}
