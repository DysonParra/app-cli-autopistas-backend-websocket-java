/*
 * @fileoverview    {StaticWeightClient} se encarga de realizar tareas específicas.
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
package com.project.dev.websocket.peripheric.tcp.client;

import com.project.dev.dummy.websocket.tcp.generic.client.ClientMessageListener;
import com.project.dev.dummy.websocket.tcp.generic.client.GenericClient;
import com.project.dev.websocket.model.element.GenericElement;
import com.project.dev.websocket.model.element.InternCommandElement;
import com.project.dev.websocket.model.front.StaticWeightMessage;
import com.project.dev.websocket.peripheric.PeriphericTcpClient;
import java.net.Socket;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import static com.project.dev.websocket.peripheric.Peripheric.*;

/**
 * TODO: Definición de {@code StaticWeightClient}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public class StaticWeightClient extends PeriphericTcpClient {

    @Setter(AccessLevel.NONE)
    protected boolean sendWeightToFront = true;
    protected int lastTotalWeight = 0;

    /**
     * TODO: Definición de {@code StaticWeightClient}.
     *
     * @param serverIpAddress
     * @param serverPort
     */
    public StaticWeightClient(String serverIpAddress, int serverPort) {
        super(serverIpAddress, serverPort, 2000, -1, true);
        super.setType(TYPE_STATIC_WEIGHT);
        super.setName("Static weight");
        super.setOnMessageListener(new ClientMessageListener() {
            @Override
            public void onMessage(GenericClient client, Socket server, String sentMessage) {
            }

            @Override
            public void onResponse(GenericClient client, Socket server, String response) {
                int scaleNumber = getScaleNumber(response);
                if (scaleNumber != -1) {
                    try {
                        int weight = Integer.parseInt(response.substring(2).replaceAll("[a-zA-Z]|\\s", ""));
                        if (scaleNumber == 3)
                            lastTotalWeight = weight;
                        if (sendWeightToFront) {
                            station.sendMessageToFront(StaticWeightMessage.builder()
                                    .nroBascula(scaleNumber)
                                    .peso(weight)
                                    .build());
                            //System.out.println("<" + response + ">" + " found in " + "'" + name + "'" + " (Sent to front)");
                        } else if (weight != 0) {
                            sendWeightToFront = true;
                            station.sendMessageToFront(StaticWeightMessage.builder()
                                    .nroBascula(scaleNumber)
                                    .peso(weight)
                                    .build());
                            //System.out.println("<" + response + ">" + " found in " + "'" + name + "'" + " (Sent to front)");
                            System.out.println("Will send static weight '0 KG' from " + "'" + name + "'" + " to front for the moment");
                        } else {
                            //System.out.println("<" + response + ">" + " found in " + "'" + name + "'" + " (Not sent to front)");
                        }
                    } catch (Exception ignored) {
                        System.out.println("Invalid message get in " + "'" + name + "'" + " from " + "'" + response + "'");
                    }
                } else
                    System.out.println("Invalid scale number get in " + "'" + name + "'" + " from " + "'" + response + "'");
            }
        });
    }

    /**
     * TODO: Definición de {@code getScaleNumber}.
     *
     * @param message
     * @return
     */
    private int getScaleNumber(String message) {
        int result = -1;
        try {
            switch (message.charAt(1)) {
                case '0':
                case '1':
                case '2':
                case '3':
                    result = Integer.parseInt(message.charAt(1) + "");
                    break;
            }
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * TODO: Definición de {@code validateAndClear}.
     *
     */
    @Override
    public void validateAndClear() {
        if (sendWeightToFront) {
            this.sendWeightToFront = false;
            System.out.println("Won't send static weight '0 KG' from " + "'" + name + "'" + " to front for the moment");
        } else {
            System.out.println("System is not sending weight '0 KG' from " + "'" + name + "'" + " to front");
        }
    }

    /**
     * TODO: Definición de {@code getInfo}.
     *
     * @param type
     * @return
     */
    @Override
    public Object getInfo(String type) {
        switch (type) {

            case GET_LAST_WEIGHT:
                return getLastTotalWeight();

            default:
                return null;
        }
    }

    /**
     * TODO: Definición de {@code sendInfo}.
     *
     * @param info
     * @return
     */
    @Override
    public int sendInfo(GenericElement info) {
        int result = RESULT_SEND_INVALID_MESSAGE;
        try {
            switch (info.getType()) {
                case GenericElement.TYPE_PLC_INTERN_COMMAND:
                    InternCommandElement internCommandElement = (InternCommandElement) info;
                    switch (internCommandElement.getInternCommand()) {
                        case InternCommandElement.COMMAND_VALIDATE_AND_CLEAR:
                            this.validateAndClear();
                            result = RESULT_SEND_SUCCESS_PROCESSED;
                            break;
                    }
                    break;
            }
        } catch (Exception e) {
        }
        return result;
    }

}
