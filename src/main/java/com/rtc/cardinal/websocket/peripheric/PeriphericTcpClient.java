/*
 * @fileoverview    {PeriphericTcpClient} se encarga de realizar tareas específicas.
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
package com.rtc.cardinal.websocket.peripheric;

import com.rtc.cardinal.websocket.model.Station;
import com.rtc.dummy.websocket.tcp.generic.client.GenericClient;
import lombok.Data;

/**
 * TODO: Definición de {@code PeriphericTcpClient}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public abstract class PeriphericTcpClient extends GenericClient implements Peripheric {

    protected String id = "UNDEFINED";
    protected String type = "UNKNOWN_CLIENT_TYPE";
    protected int numberOfThisType;
    protected Station station = Station.builder().build();

    /**
     * TODO: Definición de {@code PeriphericTcpClient}.
     *
     */
    public PeriphericTcpClient() {
        super();
    }

    /**
     * TODO: Definición de {@code PeriphericTcpClient}.
     *
     * @param serverIpAddress
     */
    public PeriphericTcpClient(String serverIpAddress) {
        super(serverIpAddress);
    }

    /**
     * TODO: Definición de {@code PeriphericTcpClient}.
     *
     * @param serverIpAddress
     * @param serverPort
     */
    public PeriphericTcpClient(String serverIpAddress, int serverPort) {
        super(serverIpAddress, serverPort);
    }

    /**
     * TODO: Definición de {@code PeriphericTcpClient}.
     *
     * @param serverIpAddress
     * @param retryTime
     * @param serverPort
     */
    public PeriphericTcpClient(String serverIpAddress, int serverPort, int retryTime) {
        super(serverIpAddress, serverPort, retryTime);
    }

    /**
     * TODO: Definición de {@code PeriphericTcpClient}.
     *
     * @param serverIpAddress
     * @param maxTries
     * @param serverPort
     * @param retryTime
     */
    public PeriphericTcpClient(String serverIpAddress, int serverPort, int retryTime, int maxTries) {
        super(serverIpAddress, serverPort, retryTime, maxTries);
    }

    /**
     * TODO: Definición de {@code PeriphericTcpClient}.
     *
     * @param serverIpAddress
     * @param autoRetryConnection
     * @param serverPort
     * @param maxTries
     * @param retryTime
     */
    public PeriphericTcpClient(String serverIpAddress, int serverPort, int retryTime, int maxTries, boolean autoRetryConnection) {
        super(serverIpAddress, serverPort, retryTime, maxTries, autoRetryConnection);
    }

    /**
     * TODO: Definición de {@code PeriphericTcpClient}.
     *
     * @param serverIpAddress
     * @param processListenersOnOtherThread
     * @param serverPort
     * @param autoRetryConnection
     * @param retryTime
     * @param maxTries
     */
    public PeriphericTcpClient(String serverIpAddress, int serverPort, int retryTime, int maxTries, boolean autoRetryConnection, boolean processListenersOnOtherThread) {
        super(serverIpAddress, serverPort, retryTime, maxTries, autoRetryConnection, processListenersOnOtherThread);
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
