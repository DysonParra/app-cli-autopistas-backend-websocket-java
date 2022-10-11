/*
 * @fileoverview {SemaphoreMessage} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {SemaphoreMessage} fue realizada el 31/07/2022.
 * @Dev - La primera version de {SemaphoreMessage} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.websocket.model.front;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Definición de {@code SemaphoreMessage}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SemaphoreMessage implements FrontMessage {

    private final String messageType = "SEMAFORO";
    private String idSemaforo;
    private String descripcionSemaforo;
    private String estadoSemaforo;

}
