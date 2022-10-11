/*
 * @fileoverview {PeriphericSerialClient} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {PeriphericSerialClient} fue realizada el 31/07/2022.
 * @Dev - La primera version de {PeriphericSerialClient} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.websocket.peripheric;

import com.rtc.cardinal.websocket.model.Station;
import com.rtc.dummy.serial.generic.client.GenericSerialClient;
import lombok.Data;

/**
 * TODO: Definición de {@code PeriphericSerialClient}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public abstract class PeriphericSerialClient extends GenericSerialClient implements Peripheric {

    protected String id = "UNDEFINED";
    protected String type = "UNKNOWN_CLIENT_TYPE";
    protected int numberOfThisType;
    protected Station station = Station.builder().build();

    /**
     * TODO: Definición de {@code PeriphericSerialClient}.
     *
     * @param portName
     * @param parity
     * @param baudRate
     * @param stopBits
     * @param dataBits
     */
    public PeriphericSerialClient(String portName, int baudRate, int dataBits, int stopBits, int parity) {
        super(portName, baudRate, dataBits, stopBits, parity);
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
