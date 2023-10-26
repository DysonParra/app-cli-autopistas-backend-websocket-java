/*
 * @fileoverview    {Configuracion}
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
 * TODO: Definición de {@code Configuracion}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@Entity
//@Table(name = "Configuracion", schema = "cardinal", catalog = "cardinal")
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Configuracion implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Long intIdConfiguracion;
    @Column(length = 50)
    private String strParametro;
    @Column(length = 2147483647)
    private String strValor;

}
