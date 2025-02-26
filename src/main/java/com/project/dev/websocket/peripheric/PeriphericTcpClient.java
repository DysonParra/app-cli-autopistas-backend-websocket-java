/*
 * @fileoverview    {PeriphericTcpClient}
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

import com.project.dev.dummy.websocket.tcp.generic.client.GenericClient;
import com.project.dev.websocket.model.Station;
import lombok.Data;

/**
 * TODO: Description of {@code PeriphericTcpClient}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
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
     * TODO: Description of method {@code PeriphericTcpClient}.
     *
     */
    public PeriphericTcpClient() {
        super();
    }

    /**
     * TODO: Description of method {@code PeriphericTcpClient}.
     *
     * @param serverIpAddress
     */
    public PeriphericTcpClient(String serverIpAddress) {
        super(serverIpAddress);
    }

    /**
     * TODO: Description of method {@code PeriphericTcpClient}.
     *
     * @param serverIpAddress
     * @param serverPort
     */
    public PeriphericTcpClient(String serverIpAddress, int serverPort) {
        super(serverIpAddress, serverPort);
    }

    /**
     * TODO: Description of method {@code PeriphericTcpClient}.
     *
     * @param serverIpAddress
     * @param retryTime
     * @param serverPort
     */
    public PeriphericTcpClient(String serverIpAddress, int serverPort, int retryTime) {
        super(serverIpAddress, serverPort, retryTime);
    }

    /**
     * TODO: Description of method {@code PeriphericTcpClient}.
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
     * TODO: Description of method {@code PeriphericTcpClient}.
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
     * TODO: Description of method {@code PeriphericTcpClient}.
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
