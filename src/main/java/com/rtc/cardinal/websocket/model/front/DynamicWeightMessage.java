/*
 * @fileoverview {DynamicWeightMessage} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {DynamicWeightMessage} fue realizada el 31/07/2022.
 * @Dev - La primera version de {DynamicWeightMessage} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.websocket.model.front;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Definición de {@code DynamicWeightMessage}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DynamicWeightMessage implements FrontMessage {

    private final String messageType = "BASCULA";
    private final String tipoBascula = "dinamica";
    private String recoveryDate;
    private String[] axles;
    private String total;
    private String nroClass;
    private String numAxles;
    private String speed;

}
