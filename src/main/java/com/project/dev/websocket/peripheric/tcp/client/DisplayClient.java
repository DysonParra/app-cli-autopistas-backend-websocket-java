/*
 * @fileoverview    {DisplayClient}
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
package com.project.dev.websocket.peripheric.tcp.client;

import com.project.dev.dummy.websocket.tcp.generic.client.ClientConnectionListener;
import com.project.dev.dummy.websocket.tcp.generic.client.ClientMessageListener;
import com.project.dev.dummy.websocket.tcp.generic.client.GenericClient;
import com.project.dev.websocket.model.element.DisplayElement;
import com.project.dev.websocket.model.element.DisplayMessageElement;
import com.project.dev.websocket.model.element.DisplaySpeedElement;
import com.project.dev.websocket.model.element.GenericElement;
import com.project.dev.websocket.model.element.InternCommandElement;
import com.project.dev.websocket.model.front.DisplayMessage;
import com.project.dev.websocket.peripheric.PeriphericTcpClient;
import java.net.Socket;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import lombok.Data;

/**
 * TODO: Description of {@code DisplayClient}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public class DisplayClient extends PeriphericTcpClient {

    private static final String SPEED_PATTERN = "\\$VEL\\d\\d%";
    private static final String MESSAGE_PATTERN = "\\$MSJ\\d\\d\\d#.*?%";
    private static final String DISPLAY_MESSAGE_DUMMY = "$MSJ313#MESSAGE%";
    private static final String DISPLAY_SPEED_DUMMY = "$VEL##%";

    private final String displayNumber;
    private final String displayType;

    protected String currentMessage;
    protected int currentSpeed;
    protected final Deque<DisplayElement> displayQueue = new LinkedList<>();

    /**
     * TODO: Description of method {@code DisplayClient}.
     *
     * @param serverIpAddress
     * @param isDynamic
     * @param serverPort
     */
    public DisplayClient(String serverIpAddress, int serverPort, boolean isDynamic) {
        super(serverIpAddress, serverPort, 2000, -1, true);
        if (isDynamic) {
            super.setType(TYPE_DYNAMIC_DISPLAY);
            super.setName("Dynamic display");
            this.displayNumber = "1";
            this.displayType = "dinamica";
        } else {
            super.setType(TYPE_STATIC_DISPLAY);
            super.setName("Static display");
            this.displayNumber = "2";
            this.displayType = "estatica";
        }
        super.setOnMessageListener(new ClientMessageListener() {
            @Override
            public void onMessage(GenericClient client, Socket server, String sentMessage) {
                if (sentMessage.matches(SPEED_PATTERN)) {
                    currentSpeed = Integer.parseInt(sentMessage.substring(4, sentMessage.length() - 1));
                    System.out.println("New speed of " + "'" + name + "'" + " is: " + currentSpeed);
                } else if (sentMessage.matches(MESSAGE_PATTERN)) {
                    String frontMessage = sentMessage.substring(8, sentMessage.length() - 1);
                    System.out.println("New message of " + "'" + name + "'" + " is: " + "'" + frontMessage + "'");
                    station.sendMessageToFront(DisplayMessage.builder()
                            .idDisplay(displayNumber)
                            .message(frontMessage)
                            .tipoDisplay(displayType)
                            .build());
                } else
                    System.out.println("Message " + "'" + sentMessage + "'" + " sent to display is not valid.");
            }

            @Override
            public void onResponse(GenericClient client, Socket server, String response) {
            }
        });

        super.setOnConnectionListener(new ClientConnectionListener() {
            @Override
            public void onConnectedClient(GenericClient client, Socket server) {
                sendDisplayInfo(DisplayMessageElement.builder()
                        .message("")
                        .sender("Connection")
                        .build());
                sendDisplayInfo(DisplaySpeedElement.builder()
                        .speed(0)
                        .sender("Connection")
                        .build());
            }

            @Override
            public void onDisconnectedClient(GenericClient client, Socket server) {
            }

            @Override
            public void onErrorConnectingToServer(GenericClient client, String serverIpAddress, int serverPort, int tries) {
            }
        });
    }

    /**
     * TODO: Description of method {@code sendDisplayInfo}.
     *
     * @param display
     * @return
     */
    private boolean sendDisplayInfo(DisplayElement display) {
        if (display != null && display.getMessage() != null) {
            displayQueue.add(display);
            if (display.isSpeed()) {
                return sendMessage(DISPLAY_SPEED_DUMMY.replaceAll("##", display.getMessage()));
            } else {
                return sendMessage(DISPLAY_MESSAGE_DUMMY.replaceAll("MESSAGE", display.getMessage()));
            }
        } else {
            System.out.println("It tried to send null message to " + "'" + name + "'");
            return false;
        }
    }

    /**
     * TODO: Description of method {@code getLastDisplayMessage}.
     *
     * @return
     */
    private DisplayElement getLastDisplayMessage() {
        DisplayElement last = null;
        Iterator iterator = displayQueue.descendingIterator();
        while (iterator.hasNext()) {
            last = (DisplayElement) iterator.next();
            if (!last.isSpeed())
                break;
        }
        return last;
    }

    /**
     * TODO: Description of method {@code validateAndClear}.
     *
     */
    @Override
    public void validateAndClear() {
        if (!displayQueue.isEmpty()) {
            System.out.println("'" + name + "'" + " messages (" + displayQueue.size() + "): [");
            displayQueue.forEach((e) -> System.out.println("  " + e));
            System.out.println("]");

            if (getLastDisplayMessage() == null || getLastDisplayMessage().getMessage().isEmpty()) {
                System.out.println("'" + name + "'" + " is probably clear.");
                displayQueue.clear();
                System.out.println("'" + name + "'" + " queue cleared.");
            } else {
                System.out.println("'" + name + "'" + " is probably not clear.");
                sendDisplayInfo(DisplayMessageElement.builder()
                        .message("")
                        .sender("ValidateAndClear")
                        .build());
                sendDisplayInfo(DisplaySpeedElement.builder()
                        .speed(0)
                        .sender("ValidateAndClear")
                        .build());
                System.out.println("'" + name + "'" + " manually cleaned.");
            }
        } else {
            System.out.println("'" + name + "'" + " messages: " + displayQueue);
            System.out.println("'" + name + "'" + " is probably clean.");
        }
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

            case GET_LAST_DISPLAY_MESSAGE:
                return getLastDisplayMessage();

            case GET_DISPLAY_QUEUE:
                return displayQueue;

            case GET_DISPLAY_QUEUE_SIZE:
                return displayQueue.size();

            case GET_DISPLAY_QUEUE_FIRST_ELEMENT:
                return displayQueue.getFirst();

            case GET_DISPLAY_QUEUE_FIRTS_ELEMENT_AND_POLL:
                return displayQueue.pollFirst();

            case GET_DISPLAY_QUEUE_LAST_ELEMENT:
                return displayQueue.getLast();

            case GET_DISPLAY_QUEUE_LAST_ELEMENT_AND_POLL:
                return displayQueue.pollLast();

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
                    }
                    break;

                case GenericElement.TYPE_DISPLAY_MESSAGE:
                case GenericElement.TYPE_DISPLAY_SPEED:
                    sendDisplayInfo((DisplayElement) info);
                    result = RESULT_SEND_SUCCESS_PROCESSED;
                    break;
            }
        } catch (Exception e) {
        }
        return result;
    }

}
