/*
 * @fileoverview {FileName} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {FileName} fue realizada el 31/07/2022.
 * @Dev - La primera version de {FileName} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.websocket.peripheric.tcp.server;

import com.rtc.cardinal.websocket.commandprocessor.GenericCommandProcessor;
import com.rtc.cardinal.websocket.commandprocessor.PlcCommandProcessor;
import com.rtc.cardinal.websocket.config.DataBaseConfig;
import com.rtc.cardinal.websocket.model.element.GenericElement;
import com.rtc.cardinal.websocket.model.element.InternCommandElement;
import com.rtc.cardinal.websocket.model.element.PlcCommandElement;
import com.rtc.cardinal.websocket.peripheric.PeriphericTcpServer;
import com.rtc.dummy.websocket.tcp.generic.server.ServerConnectionListener;
import com.rtc.dummy.websocket.tcp.generic.server.ThreadServer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * TODO: Definición de {@code PlcServer}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PlcServer extends PeriphericTcpServer {

    protected GenericCommandProcessor commandProcessor;
    protected final Queue<PlcCommandElement> plcCommandsQueue = new LinkedList<>();
    protected final List<PlcCommandElement> plcRequests = new ArrayList<>();

    /**
     * TODO: Definición de {@code PlcServer}.
     *
     * @param plcPort
     */
    public PlcServer(int plcPort) {
        super(plcPort, true);
        super.setType(TYPE_PLC);
        super.setName("PLC");
        super.setOnMessageListener((message, receiver) -> {
            if (receiver != null && receiver.getSender() != null) {
                System.out.println("Got '" + message + "' in server " + "'" + name + "'"
                        + " from client '" + receiver.getSender().getPort() + "'");
                PlcCommandElement plcRequest
                        = PlcCommandElement.builder()
                                .command(message)
                                .sender("Plc")
                                .build();
                plcCommandsQueue.add(plcRequest);

                if (!message.matches(".?99")) {
                    System.out.println("'" + name + "'" + " get command from 'Plc' " + "(" + message + ")");
                    if (commandProcessor != null)
                        commandProcessor.processCommand(message, "Plc", station);
                    String response = message.substring(0, 1) + "99";
                    PlcCommandElement smartWimResponse
                            = PlcCommandElement.builder()
                                    .command(response)
                                    .sender("SmartWim")
                                    .build();
                    plcCommandsQueue.add(smartWimResponse);
                    //receiver.sendMessageToClient(message.substring(0, 1) + "99", 3);
                    sendMessageToAllClients(response, null, 3);
                } else {
                    System.out.println("'" + name + "'" + " get response from 'Plc' " + "(" + message + ")");
                    if (DataBaseConfig.WAIT_PLC_RESPONSE == 1) {
                        for (PlcCommandElement request : plcRequests) {
                            String key = request.getCommand();
                            if (key.charAt(0) == message.charAt(0)) {
                                System.out.println("Found " + request + " for response " + plcRequest);
                                plcRequests.remove(request);
                                synchronized (request) {
                                    System.out.println("Waking up the thread");
                                    request.notifyAll();
                                }
                                break;
                            }
                        }
                    }
                }
            } else {
                String clientName = (receiver == null) ? "FrontEnd" : receiver.getName();
                System.out.println("Got '" + message + "' in server " + "'" + name + "'"
                        + " from client " + "'" + clientName + "'");
                PlcCommandElement frontRequest
                        = PlcCommandElement.builder()
                                .command(message)
                                .sender(clientName)
                                .build();
                plcCommandsQueue.add(frontRequest);

                if (!message.matches(".?99")) {
                    System.out.println("'" + name + "'" + " get command from " + "'" + clientName + "'" + " " + "(" + message + ")");
                    if (commandProcessor != null)
                        commandProcessor.processCommand(message, clientName, station);
                    if (DataBaseConfig.WAIT_PLC_RESPONSE != 1)
                        sendMessageToAllClients(message, null, 3);
                    else {
                        plcRequests.add(frontRequest);
                        for (int i = 1; i <= DataBaseConfig.RESEND_TRIES; i++) {
                            try {
                                sendMessageToAllClients(message, null, 3);
                                synchronized (frontRequest) {
                                    System.out.println("Waiting response of: " + frontRequest);
                                    frontRequest.wait(DataBaseConfig.RESEND_TIME);
                                    if (!plcRequests.contains(frontRequest)) {
                                        System.out.println("Got response of:  " + frontRequest);
                                        break;
                                    } else if (i != DataBaseConfig.RESEND_TRIES)
                                        System.out.println("Could not get response of " + frontRequest + " (Trie " + i + ") retrying...");
                                    else
                                        System.out.println("Could not get response of " + frontRequest + " (Trie " + i + ") and max tries reached.");
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                } else {
                    System.out.println("'" + name + "'" + " get response from " + "'" + clientName + "'" + " " + "(" + message + ")");
                    sendMessageToAllClients(message, null, 3);
                }
            }
            station.sendMessageToFront(message);
        });
        super.setOnConnectionListener(new ServerConnectionListener() {
            @Override
            public void onConnectedClient(ThreadServer connected) {
                sendInitCommands(connected, "Connection init");
            }

            @Override
            public void onDisconnectedClient(ThreadServer disconnected) {
            }
        });
        commandProcessor = new PlcCommandProcessor();
    }

    /**
     * TODO: Definición de {@code sendInitCommands}.
     *
     * @param client
     * @param sender
     */
    private void sendInitCommands(ThreadServer client, @NonNull String sender) {
        try {
            if (DataBaseConfig.PLC_INIT_COMMAND != null) {
                if (!DataBaseConfig.PLC_INIT_COMMAND.isEmpty()) {
                    Thread.sleep(1500);
                    System.out.println("Sendind init commands to PLC...");
                    for (String initCommand : DataBaseConfig.PLC_INIT_COMMAND) {
                        System.out.println(initCommand);
                        if (client == null)
                            sendMessageToAllClients(initCommand, null, 3);
                        else
                            client.sendMessageToClient(initCommand, 3);
                        if (commandProcessor != null)
                            commandProcessor.processCommand(initCommand, sender, station);
                        plcCommandsQueue.add(PlcCommandElement.builder()
                                .command(initCommand)
                                .sender(sender)
                                .build());
                        station.sendMessageToFront(initCommand);
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }
                    }
                    System.out.println("Init commands sent to PLC...");
                } else
                    System.out.println("Init commands is empty.");
            } else
                System.out.println("Init commands is null.");
        } catch (Exception ignored) {
        }
    }

    /**
     * TODO: Definición de {@code validateAndClear}.
     *
     */
    @Override
    public void validateAndClear() {
        if (!plcCommandsQueue.isEmpty()) {
            System.out.println("Plc Commands (" + plcCommandsQueue.size() + ") in " + "'" + name + "'" + ": [");
            plcCommandsQueue.forEach((e) -> System.out.println("  " + e));
            System.out.println("]");
            plcCommandsQueue.clear();
            System.out.println("Plc Commands in " + "'" + name + "'" + " cleaned");
        } else {
            System.out.println("Plc Commands in " + "'" + name + "'" + ": []");
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

            case GET_PLC_COMMANDS_QUEUE:
                return plcCommandsQueue;

            case GET_PLC_COMMANDS_QUEUE_SIZE:
                return plcCommandsQueue.size();

            case GET_PLC_COMMANDS_QUEUE_FIRST_ELEMENT:
                return plcCommandsQueue.peek();

            case GET_PLC_COMMANDS_QUEUE_FIRTS_ELEMENT_AND_POLL:
                return plcCommandsQueue.poll();

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

                        case InternCommandElement.COMMAND_INIT:
                            DataBaseConfig.updateInitList(station.getServicioConfiguracion());
                            sendInitCommands(null, "INIT command");
                            result = RESULT_SEND_SUCCESS_PROCESSED;
                            break;
                    }
                    break;

                case GenericElement.TYPE_PLC_COMMAND:
                    PlcCommandElement plcCommand = (PlcCommandElement) info;
                    //clients.add(new Socket());
                    ThreadServer threadServer = new ThreadServer(null, null, null);
                    threadServer.setName(plcCommand.getSender());
                    onMessageListener.onRequest(plcCommand.getCommand(), threadServer);
                    result = RESULT_SEND_SUCCESS_PROCESSED;
                    break;
            }
        } catch (Exception e) {
        }
        return result;
    }

}
