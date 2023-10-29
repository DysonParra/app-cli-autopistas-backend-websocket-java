/*
 * @fileoverview    {VehiculoDTO}
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
package com.project.dev.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Definición de {@code VehiculoDTO}.
 *
 * @author Dyson Parra
 * @since 11
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class VehiculoDTO {

    private String strPlacaVehiculo;
    private Long intIdCategoria;
    private String strObservaciones;

    /**
     * Obtiene el valor en {String} del objeto actual.
     *
     * @return un {String} con la representación del objeto.
     */
    @Override
    public String toString() {
        return "VehiculoDTO{"
                + "strPlacaVehiculo='" + strPlacaVehiculo + '\''
                + ", intIdCategoria=" + intIdCategoria
                + ", strObservaciones='" + strObservaciones + '\''
                + '}';
    }

}
