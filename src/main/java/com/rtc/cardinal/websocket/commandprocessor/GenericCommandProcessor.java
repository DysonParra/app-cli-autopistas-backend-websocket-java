/*
 * @fileoverview    {GenericCommandProcessor} se encarga de realizar tareas específicas.
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
package com.rtc.cardinal.websocket.commandprocessor;

import com.rtc.cardinal.websocket.model.Station;
import lombok.NonNull;

/**
 * TODO: Definición de {@code GenericCommandProcessor}.
 *
 * @param <T>
 *
 * @author Dyson Parra
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
