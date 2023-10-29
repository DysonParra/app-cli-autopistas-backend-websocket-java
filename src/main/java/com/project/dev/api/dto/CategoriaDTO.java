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
 * TODO: Definición de {@code CategoriaDTO}.
 *
 * @author Dyson Parra
 * @since 11
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
     * Obtiene el valor en {String} del objeto actual.
     *
     * @return un {String} con la representación del objeto.
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
