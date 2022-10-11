/*
 * @fileoverview {PeriphericTcpServer} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {PeriphericTcpServer} fue realizada el 31/07/2022.
 * @Dev - La primera version de {PeriphericTcpServer} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.websocket.peripheric;

import com.rtc.cardinal.websocket.model.Station;
import com.rtc.dummy.websocket.tcp.generic.server.GenericServer;
import lombok.Data;

/**
 * TODO: Definición de {@code PeriphericTcpServer}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public abstract class PeriphericTcpServer extends GenericServer implements Peripheric {

    protected String id = "UNDEFINED";
    protected String type = "UNKNOWN_SERVER_TYPE";
    protected int numberOfThisType;
    protected Station station = Station.builder().build();

    /**
     * TODO: Definición de {@code PeriphericTcpServer}.
     *
     */
    public PeriphericTcpServer() {
        super();
    }

    /**
     * TODO: Definición de {@code PeriphericTcpServer}.
     *
     * @param serverPort
     */
    public PeriphericTcpServer(int serverPort) {
        super(serverPort);
    }

    /**
     * TODO: Definición de {@code PeriphericTcpServer}.
     *
     * @param serverPort
     * @param processListenersOnOtherThread
     */
    public PeriphericTcpServer(int serverPort, boolean processListenersOnOtherThread) {
        super(serverPort, processListenersOnOtherThread);
    }

    /**
     * TODO: Definición de {@code setNumberOfThisType}.
     *
     * @param numberOfThisType
     */
    @Override
    public void setNumberOfThisType(int numberOfThisType) {
        this.numberOfThisType = numberOfThisType;
        this.setName(name + " #" + numberOfThisType);
    }
}
