/*
 * @fileoverview    {PeriphericTcpServer}
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

import com.project.dev.dummy.websocket.tcp.generic.server.GenericServer;
import com.project.dev.websocket.model.Station;
import lombok.Data;

/**
 * TODO: Description of {@code PeriphericTcpServer}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
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
     * TODO: Description of method {@code PeriphericTcpServer}.
     *
     */
    public PeriphericTcpServer() {
        super();
    }

    /**
     * TODO: Description of method {@code PeriphericTcpServer}.
     *
     * @param serverPort
     */
    public PeriphericTcpServer(int serverPort) {
        super(serverPort);
    }

    /**
     * TODO: Description of method {@code PeriphericTcpServer}.
     *
     * @param serverPort
     * @param processListenersOnOtherThread
     */
    public PeriphericTcpServer(int serverPort, boolean processListenersOnOtherThread) {
        super(serverPort, processListenersOnOtherThread);
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
