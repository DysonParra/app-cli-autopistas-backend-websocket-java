/*
 * @overview        {CameraServer}
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
package com.project.dev.websocket.peripheric.tcp.server;

import com.project.dev.websocket.model.element.CameraPlateElement;
import com.project.dev.websocket.model.element.GenericElement;
import com.project.dev.websocket.model.element.InternCommandElement;
import com.project.dev.websocket.model.front.CameraMessage;
import com.project.dev.websocket.peripheric.PeriphericTcpServer;
import java.util.LinkedList;
import java.util.Queue;
import lombok.Data;

/**
 * TODO: Description of {@code CameraServer}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public class CameraServer extends PeriphericTcpServer {

    protected final Queue<CameraPlateElement> staticPlatesQueue = new LinkedList<>();
    protected final Queue<CameraPlateElement> dynamicPlatesQueue = new LinkedList<>();
    protected String lastStaticPlates = "";
    protected String lastDynamPlates = "";

    /**
     * TODO: Description of method {@code CameraServer}.
     *
     * @param port
     */
    public CameraServer(int port) {
        super(port);
        super.setType(TYPE_CAMERA);
        super.setName("CameraServer");
        super.setOnMessageListener((message, receiver) -> {
            if (message != null) {
                System.out.println("Got '" + message + "' in server " + "'" + name + "'"
                        + " from client '" + receiver.getSender().getPort() + "'");
                System.out.println(message);
                String[] messageSplits = message.split("<EOF>");

                if (messageSplits[1] != null && !messageSplits[1].isEmpty()) {
                    if (messageSplits[1].length() > 6)
                        messageSplits[1] = messageSplits[1].substring(0, 6);

                    station.sendMessageToFront(CameraMessage.builder()
                            .idCamara(messageSplits[2])
                            .frame(messageSplits[0])
                            .plate(messageSplits[1])
                            .build());

                    System.out.println("Plate '" + messageSplits[1] + "' Resended to front.");
                    if (messageSplits[2].equals("estatica")) {
                        staticPlatesQueue.clear();
                        staticPlatesQueue.add(CameraPlateElement.builder()
                                .txtPlate(messageSplits[1])
                                .imgPlate(messageSplits[0])
                                .build());
                        System.out.println("Static plates in queue (" + staticPlatesQueue.size() + "): [");
                        staticPlatesQueue.forEach((e) -> System.out.println("  " + e));
                        System.out.println("]");
                    } else {
                        dynamicPlatesQueue.clear();
                        dynamicPlatesQueue.add(CameraPlateElement.builder()
                                .txtPlate(messageSplits[1])
                                .imgPlate(messageSplits[0])
                                .build());
                        System.out.println("Dynamic plates in queue (" + dynamicPlatesQueue.size() + "): [");
                        dynamicPlatesQueue.forEach((e) -> System.out.println("  " + e));
                        System.out.println("]");
                    }
                } else {
                    System.out.println("Plate '" + messageSplits[1] + "' discarded.");
                }
            }
        });
    }

    /**
     * TODO: Description of method {@code validateAndClear}.
     *
     */
    @Override
    public void validateAndClear() {
        String dynamicPlates = dynamicPlatesQueue.toString();
        if (!dynamicPlates.equals("[]")) {
            System.out.println("Found dynamic plates in " + "'" + name + "'" + ":");
            System.out.println("Dynamic plates (" + dynamicPlatesQueue.size() + ") in " + "'" + name + "'" + ": [");
            dynamicPlatesQueue.forEach((e) -> System.out.println("  " + e));
            System.out.println("]");
            if (lastDynamPlates.equals(dynamicPlates)) {
                dynamicPlatesQueue.clear();
                System.out.println("dynamic plates in " + "'" + name + "'" + " cleaned.");
            } else
                System.out.println("Dynamic plates in " + "'" + name + "'" + " not cleaned.");
            lastDynamPlates = dynamicPlates;
        } else
            System.out.println("Dynamic plates in " + "'" + name + "'" + ": []");

        String staticPlates = staticPlatesQueue.toString();
        if (!staticPlates.equals("[]")) {
            System.out.println("Found static plates in " + "'" + name + "'" + ":");
            System.out.println("Static plates (" + staticPlatesQueue.size() + ") in " + "'" + name + "'" + ": [");
            staticPlatesQueue.forEach((e) -> System.out.println("  " + e));
            System.out.println("]");
            if (lastStaticPlates.equals(staticPlates)) {
                staticPlatesQueue.clear();
                System.out.println("static plates in " + "'" + name + "'" + " cleaned.");
            } else
                System.out.println("static plates in " + "'" + name + "'" + " not cleaned.");
            lastStaticPlates = staticPlates;
        } else
            System.out.println("Static plates in " + "'" + name + "'" + ": []");
    }

    /**
     * TODO: Description of method {@code getInfo}.
     *
     * @param type
     * @return
     */
    @Override
    public Object getInfo(String type) {
        switch (type) {

            case GET_DYNAMIC_PLATES_QUEUE:
                return dynamicPlatesQueue;

            case GET_DYNAMIC_PLATES_QUEUE_SIZE:
                return dynamicPlatesQueue.size();

            case GET_DYNAMIC_PLATES_QUEUE_FIRST_ELEMENT:
                return dynamicPlatesQueue.peek();

            case GET_DYNAMIC_PLATES_QUEUE_FIRTS_ELEMENT_AND_POLL:
                return dynamicPlatesQueue.poll();

            case GET_STATIC_PLATES_QUEUE:
                return staticPlatesQueue;

            case GET_STATIC_PLATES_QUEUE_SIZE:
                return staticPlatesQueue.size();

            case GET_STATIC_PLATES_QUEUE_FIRST_ELEMENT:
                return staticPlatesQueue.peek();

            case GET_STATIC_PLATES_QUEUE_FIRTS_ELEMENT_AND_POLL:
                return staticPlatesQueue.poll();

            default:
                return null;
        }
    }

    /**
     * TODO: Description of method {@code sendInfo}.
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

                        case InternCommandElement.COMMAND_PRINT:
                            System.out.println("Dynamic plates (" + dynamicPlatesQueue.size() + "): [");
                            dynamicPlatesQueue.forEach((e) -> System.out.println("  " + e));
                            System.out.println("]");
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
