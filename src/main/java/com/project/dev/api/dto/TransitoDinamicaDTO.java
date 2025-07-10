/*
 * @overview        {TransitoDinamicaDTO}
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

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Description of {@code TransitoDinamicaDTO}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
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
     * Get the current {@code Object} as {@code String}.
     *
     * @return {@code String} representing this {@code Object}.
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
