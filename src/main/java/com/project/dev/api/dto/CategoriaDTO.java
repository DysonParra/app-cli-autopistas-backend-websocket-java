/*
 * @fileoverview    {CategoriaDTO}
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
 * TODO: Description of {@code CategoriaDTO}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CategoriaDTO {

    private Long intIdCategoria;
    private String strCategoria;
    private Integer intPesoMaximo;
    private Integer intTolerancia;
    private String strDescripcion;
    private Integer intEjeSencillo;
    private Integer intEjeTandem;
    private Integer intTotalEjes;

    /**
     * Get the current {@code Object} as {@code String}.
     *
     * @return {@code String} representing this {@code Object}.
     */
    @Override
    public String toString() {
        return "CategoriaDTO{"
                + "intIdCategoria=" + intIdCategoria
                + ", strCategoria='" + strCategoria + '\''
                + ", intPesoMaximo=" + intPesoMaximo
                + ", intTolerancia=" + intTolerancia
                + ", strDescripcion='" + strDescripcion + '\''
                + ", intEjeSencillo=" + intEjeSencillo
                + ", intEjeTandem=" + intEjeTandem
                + ", intTotalEjes=" + intTotalEjes
                + '}';
    }

}
