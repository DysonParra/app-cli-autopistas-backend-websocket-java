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
package com.rtc.cardinal.websocket.commandprocessor;

import com.rtc.cardinal.websocket.model.Station;
import lombok.NonNull;

/**
 * TODO: Definición de {@code GenericCommandProcessor}.
 *
 * @author Dyson Parra
 * @param <T>
 * @since 1.8
 */
public interface GenericCommandProcessor<T> {

    /**
     * TODO: Definición de {@code processCommand}.
     *
     * @param plcCommand
     * @param station
     * @param sender
     * @return
     */
    public abstract T processCommand(String plcCommand, @NonNull String sender, @NonNull Station station);

}
