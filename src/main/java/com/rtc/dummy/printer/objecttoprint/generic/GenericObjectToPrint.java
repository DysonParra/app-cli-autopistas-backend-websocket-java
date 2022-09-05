/*
 * @fileoverview {FileName} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {FileName} fue realizada el 31/07/2022.
 * @Dev - La primera version de {FileName} fue escrita por Dyson A. Parra T.
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
