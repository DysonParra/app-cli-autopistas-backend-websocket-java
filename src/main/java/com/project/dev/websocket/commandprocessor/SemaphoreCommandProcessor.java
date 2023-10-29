/*
 * @fileoverview    {SemaphoreCommandProcessor}
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
import com.project.dev.websocket.model.front.SemaphoreMessage;
import lombok.NonNull;

/**
 * TODO: Definición de {@code SemaphoreCommandProcessor}.
 *
 * @author Dyson Parra
 * @since 11
 */
public class SemaphoreCommandProcessor implements GenericCommandProcessor<Boolean> {

    /**
     * TODO: Definición de {@code processCommand}.
     *
     * @param plcCommand
     * @param station
     * @param sender
     * @return
     */
    @Override
    public Boolean processCommand(String plcCommand, @NonNull String sender, @NonNull Station station) {

        SemaphoreMessage semaphoreMessage;
        String idSemaforo = null;
        String descripcionSemaforo = null;
        String estadoSemaforo = null;
        System.out.println("Validating code (semaphore): '" + plcCommand + "'");

        if (plcCommand.length() == 3) {
            switch (plcCommand.substring(0, 2)) {
                case "31":
                    idSemaforo = "1";
                    descripcionSemaforo = "Entrada a Dinamica";
                    break;
                case "41":
                    idSemaforo = "2";
                    descripcionSemaforo = "Dinamica a nacional";
                    break;
                case "51":
                    idSemaforo = "3";
                    descripcionSemaforo = "Dinamica a estatica";
                    break;
                case "81":
                    idSemaforo = "4";
                    descripcionSemaforo = "Estatica a nacional";
                    break;
                case "91":
                    idSemaforo = "5";
                    descripcionSemaforo = "Estatica a patio";
                    break;
            }

            switch (plcCommand.charAt(2)) {
                case '0':
                    estadoSemaforo = "Nada";
                    break;
                case '1':
                    estadoSemaforo = "Rojo";
                    break;
                case '2':
                    estadoSemaforo = "Verde";
                    break;
            }

            if (idSemaforo != null && estadoSemaforo != null) {
                semaphoreMessage = SemaphoreMessage.builder()
                        .idSemaforo(idSemaforo)
                        .descripcionSemaforo(descripcionSemaforo)
                        .estadoSemaforo(estadoSemaforo)
                        .build();

                station.sendMessageToFront(semaphoreMessage);
                System.out.println("Found code (semaphore): '" + plcCommand + "'");
                return true;
            }
        }
        System.out.println("Not found code (semaphore): '" + plcCommand + "'");
        return false;
    }

}
