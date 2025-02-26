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
 * @version 1.0     Implementation done.
 * @version 2.0     Documentation added.
 */
package com.project.dev.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Description of {@code VehiculoSobrepesoDTO}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
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
     * Get the current {@code Object} as {@code String}.
     *
     * @return {@code String} representing this {@code Object}.
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
