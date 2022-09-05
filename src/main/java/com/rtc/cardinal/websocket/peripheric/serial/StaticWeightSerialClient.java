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
package com.rtc.cardinal.websocket.peripheric.serial;

import com.rtc.cardinal.websocket.model.element.GenericElement;
import com.rtc.cardinal.websocket.model.element.InternCommandElement;
import com.rtc.cardinal.websocket.model.front.StaticWeightMessage;
import com.rtc.cardinal.websocket.peripheric.PeriphericSerialClient;
import com.rtc.dummy.serial.generic.client.GenericSerialMessageListener;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import static com.rtc.cardinal.websocket.peripheric.Peripheric.*;

/**
 * TODO: Definición de {@code StaticWeightSerialClient}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public class StaticWeightSerialClient extends PeriphericSerialClient {

    @Setter(AccessLevel.NONE)
    protected boolean sendWeightToFront = true;
    protected int lastTotalWeight = 0;

    /**
     * TODO: Definición de {@code StaticWeightSerialClient}.
     *
     * @param portName
     * @param parity
     * @param baudRate
     * @param stopBits
     * @param dataBits
     */
    public StaticWeightSerialClient(String portName, int baudRate, int dataBits, int stopBits, int parity) {
        super(portName, baudRate, dataBits, stopBits, parity);
        super.setType(TYPE_STATIC_WEIGHT);
        super.setName("Static weight");
        super.setOnMessageListener(new GenericSerialMessageListener() {
            @Override
            public void onMessage(String message) {
            }

            @Override
            public void onResponse(String response) {
                int scaleNumber = 3;
                try {
                    int weight = Integer.parseInt(response.substring(3).replaceAll("[a-zA-Z]|\\s", ""));
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
            }
        });
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
