/*
 * @fileoverview {CustomCommadProcessor} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {CustomCommadProcessor} fue realizada el 31/07/2022.
 * @Dev - La primera version de {CustomCommadProcessor} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.websocket.commandprocessor;

import com.rtc.cardinal.websocket.config.DataBaseConfig;
import com.rtc.cardinal.websocket.model.Station;
import com.rtc.cardinal.websocket.model.element.DisplayMessageElement;
import com.rtc.cardinal.websocket.model.element.DisplaySpeedElement;
import com.rtc.cardinal.websocket.model.element.InternCommandElement;

/**
 * TODO: Definición de {@code CustomCommadProcessor}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class CustomCommadProcessor implements GenericCommandProcessor<Boolean> {

    /**
     * TODO: Definición de {@code processCommand}.
     *
     * @param plcCommand
     * @param station
     * @param sender
     * @return
     */
    @Override
    public Boolean processCommand(String plcCommand, String sender, Station station) {
        boolean result = true;
        System.out.println("Validating code (custom): '" + plcCommand + "'");
        try {
            if (plcCommand.matches("DIS_EST_M_.*?")) {
                station.getIdsStaticDisplay().forEach(id -> {
                    station.sendInfoToPeripheric(id,
                            DisplayMessageElement.builder()
                                    .message(plcCommand.replace("DIS_EST_M_", ""))
                                    .sender(sender + ":" + plcCommand)
                                    .build());
                });
            } else if (plcCommand.matches("DIS_EST_V_\\d\\d")) {
                station.getIdsStaticDisplay().forEach(id -> {
                    station.sendInfoToPeripheric(id,
                            DisplaySpeedElement.builder()
                                    .speed(Integer.parseInt(plcCommand.replace("DIS_EST_V_", "")))
                                    .sender(sender + ":" + plcCommand)
                                    .build());
                });
            } else if (plcCommand.matches("DIS_DIN_M_.*?")) {
                station.getIdsDynamicDisplay().forEach(id -> {
                    station.sendInfoToPeripheric(id,
                            DisplayMessageElement.builder()
                                    .message(plcCommand.replace("DIS_DIN_M_", ""))
                                    .sender(sender + ":" + plcCommand)
                                    .build());
                });
            } else if (plcCommand.matches("DIS_DIN_V_\\d\\d")) {
                station.getIdsDynamicDisplay().forEach(id -> {
                    station.sendInfoToPeripheric(id,
                            DisplaySpeedElement.builder()
                                    .speed(Integer.parseInt(plcCommand.replace("DIS_DIN_V_", "")))
                                    .sender(sender + ":" + plcCommand)
                                    .build());
                });
            } else if (plcCommand.matches("CAMERA_CL")) {
                station.getIdsCamera().forEach(id -> {
                    station.sendInfoToPeripheric(id,
                            InternCommandElement.builder()
                                    .sender("CustomProcessor")
                                    .internCommand(InternCommandElement.COMMAND_VALIDATE_AND_CLEAR)
                                    .build());
                });
            } else if (plcCommand.matches("CLEAR")) {
                station.validateAndClearPeripherics();
            } else if (plcCommand.matches("INIT")) {
                station.getIdsPlc().forEach(id -> {
                    station.sendInfoToPeripheric(id,
                            InternCommandElement.builder()
                                    .sender("CustomProcessor")
                                    .internCommand(InternCommandElement.COMMAND_INIT)
                                    .build());
                });
            } else if (plcCommand.matches("SCHED_\\d\\d\\d\\d+")) {
                DataBaseConfig.SCHEDULED_TIME = Integer.parseInt(plcCommand.replaceAll("SCHED_", ""));
                synchronized (DataBaseConfig.SYNCHRONIZER) {
                    DataBaseConfig.SYNCHRONIZER.notifyAll();
                }
            } else if (plcCommand.matches("UPDATE_CONFIG") || plcCommand.matches("UP_CONF")) {
                DataBaseConfig.getDataBaseConfig(station.getServicioConfiguracion());
            } else {
                System.out.println("Not found code (custom): '" + plcCommand + "'");
                result = false;
            }
            if (result)
                System.out.println("Found code (custom): '" + plcCommand + "'");
        } catch (Exception e) {
        }
        return result;
    }

}
