/*
 * @fileoverview    {VehiculoSobrepesoDTO}
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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Definición de {@code VehiculoSobrepesoDTO}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class VehiculoSobrepesoDTO {

    private Long intIdRepeso;
    private Integer intPesoMaximo;
    private Integer intDiferenciaPeso;
    private Long intIdDinamica;
    private String strPlacaVehiculo;
    private Boolean bitBorrado;

    /**
     * Obtiene el valor en {String} del objeto actual.
     *
     * @return un {String} con la representación del objeto.
     */
    @Override
    public String toString() {
        return "VehiculoSobrepesoDTO{"
                + "intIdRepeso=" + intIdRepeso
                + ", intPesoMaximo=" + intPesoMaximo
                + ", intDiferenciaPeso=" + intDiferenciaPeso
                + ", intIdDinamica=" + intIdDinamica
                + ", strPlacaVehiculo='" + strPlacaVehiculo + '\''
                + ", bitBorrado=" + bitBorrado
                + '}';
    }

}
