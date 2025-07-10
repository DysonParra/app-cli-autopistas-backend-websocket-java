/*
 * @overview        {GenericCommandProcessor}
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
package com.project.dev.websocket.commandprocessor;

import com.project.dev.websocket.model.Station;
import lombok.NonNull;

/**
 * TODO: Description of {@code GenericCommandProcessor}.
 *
 * @param <T>
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
public interface GenericCommandProcessor<T> {

    /**
     * TODO: Description of method {@code processCommand}.
     *
     * @param plcCommand
     * @param station
     * @param sender
     * @return
     */
    public abstract T processCommand(String plcCommand, @NonNull String sender, @NonNull Station station);

}
