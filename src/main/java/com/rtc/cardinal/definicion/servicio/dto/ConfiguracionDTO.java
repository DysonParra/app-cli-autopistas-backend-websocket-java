/*
 * @fileoverview {ConfiguracionDTO} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {ConfiguracionDTO} fue realizada el 31/07/2022.
 * @Dev - La primera version de {ConfiguracionDTO} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.definicion.servicio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Definición de {@code ConfiguracionDTO}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ConfiguracionDTO {

    private Long intIdConfiguracion;
    private String strParametro;
    private String strValor;

    /**
     * Obtiene el valor en {String} del objeto actual.
     *
     * @return un {String} con la representación del objeto.
     */
    @Override
    public String toString() {
        return strValor;
    }

}
