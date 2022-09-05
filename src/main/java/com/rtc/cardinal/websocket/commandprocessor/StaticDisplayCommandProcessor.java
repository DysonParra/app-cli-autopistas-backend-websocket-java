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
import com.rtc.cardinal.websocket.model.element.DisplayMessageElement;
import com.rtc.cardinal.websocket.peripheric.Peripheric;
import lombok.NonNull;

/**
 * TODO: Definición de {@code StaticDisplayCommandProcessor}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class StaticDisplayCommandProcessor implements GenericCommandProcessor<Boolean> {

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
        String message = null;
        Integer staticWeght;
        System.out.println("Validating code (static display): '" + plcCommand + "'");
        switch (plcCommand) {
            case "631":
                message = "ESPERANDO VEHICULO";
                break;
            case "632":
                message = "ENTRANDO";
                break;
            case "633":
                message = "DETENER";
                break;
            case "634":
                message = "RETROCEDER";
                break;
            case "635":
                message = "AVANZAR";
                break;
            case "636":
                message = "POSICIONADO";
                break;
            case "637":
                message = "VERIFICAR TAMANO CAMION";
                break;
            case "E55":
                message = "";
                break;
            case "E40":
                staticWeght = 0;
                try {
                    staticWeght = (Integer) station.getPeriphericInfo(Station.ID_STATIC_WEIGHT + 1, Peripheric.GET_LAST_WEIGHT);
                } catch (ClassCastException | NullPointerException e) {
                }
                message = "A VIA NACIONAL <-- " + staticWeght + " KG";
                break;
            case "E41":
                staticWeght = 0;
                try {
                    staticWeght = (Integer) station.getPeriphericInfo(Station.ID_STATIC_WEIGHT + 1, Peripheric.GET_LAST_WEIGHT);
                } catch (ClassCastException | NullPointerException e) {
                }
                staticWeght = (staticWeght == null) ? 0 : staticWeght;
                message = staticWeght + " KG" + " --> A PATIOS";
                break;
            case "C44":
                staticWeght = 0;
                try {
                    staticWeght = (Integer) station.getPeriphericInfo(Station.ID_STATIC_WEIGHT + 1, Peripheric.GET_LAST_WEIGHT);
                } catch (ClassCastException | NullPointerException e) {
                }
                staticWeght = (staticWeght == null) ? 0 : staticWeght;
                message = staticWeght + " KG";
                break;
        }

        if (message != null) {
            station.sendInfoToPeripheric(Station.ID_STATIC_DISPLAY + 1,
                    DisplayMessageElement.builder()
                            .message(message)
                            .sender(sender + ":" + plcCommand)
                            .build());
            System.out.println("Found code (static display): '" + plcCommand + "'");
            return true;
        } else {
            System.out.println("Not found code (static display): '" + plcCommand + "'");
            return false;
        }
    }
}
