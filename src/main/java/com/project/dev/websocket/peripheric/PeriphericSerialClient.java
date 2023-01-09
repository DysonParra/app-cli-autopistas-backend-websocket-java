/*
 * @fileoverview    {PeriphericSerialClient} se encarga de realizar tareas específicas.
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
package com.project.dev.websocket.peripheric;

import com.project.dev.websocket.model.Station;
import com.project.dev.dummy.serial.generic.client.GenericSerialClient;
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
