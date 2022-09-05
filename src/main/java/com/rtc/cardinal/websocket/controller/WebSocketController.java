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
package com.rtc.cardinal.websocket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtc.cardinal.definicion.servicio.dto.CategoriaDTO;
import com.rtc.cardinal.websocket.model.Station;
import com.rtc.cardinal.websocket.model.element.PlcCommandElement;
import com.rtc.dummy.printer.SocketPrinter;
import com.rtc.dummy.printer.objecttoprint.autopistasdelcafe.ticket.SocketTicket;
import java.util.List;
import lombok.NonNull;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import static com.rtc.cardinal.websocket.config.DataBaseConfig.*;

/**
 * TODO: Definición de {@code WebSocketController}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@Controller
public class WebSocketController {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static SocketPrinter socketPrinter = null;
    private static Station station = Station.builder().build();

    /**
     * TODO: Definición de {@code getStation}.
     *
     * @return
     */
    public static Station getStation() {
        return station;
    }

    /**
     * TODO: Definición de {@code setStation}.
     *
     * @param station
     */
    public static void setStation(@NonNull Station station) {
        WebSocketController.station = station;
    }

    /**
     * TODO: Definición de {@code messageToSend}.
     *
     * @param receivedMessage
     */
    @MessageMapping("/send/message")
    public void messageToSend(String receivedMessage) {
        station.sendInfoToPeripheric(Station.ID_PLC + 1,
                PlcCommandElement.builder()
                        .command(receivedMessage)
                        .sender("FrontEnd")
                        .build());
    }

    /**
     * TODO: Definición de {@code messageToPrint}.
     *
     * @param receivedInfo
     */
    @MessageMapping("/send/print")
    public void messageToPrint(String receivedInfo) {
        try {
            socketPrinter = new SocketPrinter();
            SocketTicket socketTicket = objectMapper.readValue(receivedInfo, SocketTicket.class);

            System.out.println("Trying to print..." + socketTicket);
            try {
                List<CategoriaDTO> categorias = station.getServicioCategoria().obtenerEntidades(socketTicket.getCategoria());
                if (categorias != null && !categorias.isEmpty()) {
                    CategoriaDTO categoria = categorias.get(0);
                    socketTicket.setPesoMaximo(String.valueOf(categoria.getIntPesoMaximo() + categoria.getIntTolerancia()));
                    int sobrepeso = Integer.parseInt(socketTicket.getPesoRegistrado()) - (categoria.getIntPesoMaximo() + categoria.getIntTolerancia());
                    sobrepeso = (sobrepeso < 0) ? 0 : sobrepeso;
                    socketTicket.setSobrePeso(String.valueOf(sobrepeso));
                    socketTicket.setCategoria(categoria.getStrDescripcion());
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }

            System.out.println("Printing: " + socketTicket);
            socketPrinter.printObject(IP_IMPRESORA, PUERTO_IMPRESORA, socketTicket);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

}
