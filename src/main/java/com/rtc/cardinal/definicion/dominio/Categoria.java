/*
 * @fileoverview {Categoria} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {Categoria} fue realizada el 31/07/2022.
 * @Dev - La primera version de {Categoria} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.definicion.dominio;

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
 * TODO: Definición de {@code Categoria}.
 *
 * @author Dyson Parra
 * @since 1.8
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
