/*
 * @fileoverview    {GenericObjectToPrint}
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
package com.project.dev.dummy.printer.objecttoprint.generic;

import java.util.List;

/**
 * TODO: Description of {@code GenericObjectToPrint}.
 *
 * @param <T>
 *
 * @author Dyson Parra
 * @since 11
 */
public interface GenericObjectToPrint<T> {

    /**
     * TODO: Description of {@code makeListToPrint}.
     *
     * @return
     */
    public abstract List<T> makeListToPrint();
}
