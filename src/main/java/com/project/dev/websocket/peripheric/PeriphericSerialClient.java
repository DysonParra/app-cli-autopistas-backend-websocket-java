/*
 * @overview        {PeriphericSerialClient}
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
package com.project.dev.websocket.peripheric;

import com.project.dev.dummy.serial.generic.client.GenericSerialClient;
import com.project.dev.websocket.model.Station;
import lombok.Data;

/**
 * TODO: Description of {@code PeriphericSerialClient}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
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
     * TODO: Description of method {@code PeriphericSerialClient}.
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
     * TODO: Description of method {@code setNumberOfThisType}.
     *
     * @param numberOfThisType
     */
    @Override
    public void setNumberOfThisType(int numberOfThisType) {
        this.numberOfThisType = numberOfThisType;
        this.setName(name + " #" + numberOfThisType);
    }

}
