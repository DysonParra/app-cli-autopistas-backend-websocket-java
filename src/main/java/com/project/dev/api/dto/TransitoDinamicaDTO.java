/*
 * @fileoverview    {TransitoDinamicaDTO}
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
package com.project.dev.api.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Definición de {@code TransitoDinamicaDTO}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TransitoDinamicaDTO {

    private Long intIdDinamica;
    private Integer intIdCategoria;
    private Date dtFechaHoraTransito;
    private Integer intPesoGeneral;
    private String strPesoEjes;
    private Float fltVelocidad;
    private String strPlacaVehiculo;
    private String strBase64Placa;
    private Boolean bitBorrado;

    /**
     * Obtiene el valor en {String} del objeto actual.
     *
     * @return un {String} con la representación del objeto.
     */
    @Override
    public String toString() {
        return "TransitoDinamicaDTO{"
                + "intIdDinamica='" + intIdDinamica
                + "', intIdCategoria='" + intIdCategoria
                + "', dtFechaHoraTransito='" + dtFechaHoraTransito
                + "', intPesoGeneral='" + intPesoGeneral
                + "', strPesoEjes='" + strPesoEjes
                + "', fltVelocidad='" + fltVelocidad
                + "', strPlacaVehiculo='" + strPlacaVehiculo
                + "', strBase64Placa='" + ((strBase64Placa != null && strBase64Placa.length() > 12) ? "%image%" : strBase64Placa)
                + "', bitBorrado='" + bitBorrado + '}';
    }

}
