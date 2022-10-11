/*
 * @fileoverview {GenericSerialMessageListener} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {GenericSerialMessageListener} fue realizada el 31/07/2022.
 * @Dev - La primera version de {GenericSerialMessageListener} fue escrita por Dyson A. Parra T.
 */
package com.rtc.dummy.serial.generic.client;

/**
 * TODO: Definición de {@code GenericSerialMessageListener}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public abstract class GenericSerialMessageListener {

    /**
     * TODO: Definición de {@code onMessage}.
     *
     * @param message
     */
    public abstract void onMessage(String message);

    /**
     * TODO: Definición de {@code onResponse}.
     *
     * @param response
     */
    public abstract void onResponse(String response);

}
