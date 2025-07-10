/*
 * @overview        {Categoria}
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
package com.project.dev.api.dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Description of {@code Categoria}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
@Entity
//@Table(name = "Categoria", schema = "cardinal", catalog = "cardinal")
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Categoria implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Long intIdCategoria;
    @Column(length = 6)
    private String strCategoria;
    private Integer intPesoMaximo;
    private Integer intTolerancia;
    @Column(length = 200)
    private String strDescripcion;
    private Integer intEjeSencillo;
    private Integer intEjeTandem;
    private Integer intTotalEjes;

}
