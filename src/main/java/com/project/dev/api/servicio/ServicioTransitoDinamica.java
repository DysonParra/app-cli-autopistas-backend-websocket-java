/*
 * @fileoverview    {ServicioTransitoDinamica}
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
package com.project.dev.api.servicio;

import com.project.dev.api.dto.TransitoDinamicaDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * TODO: Definición de {@code ServicioTransitoDinamica}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public interface ServicioTransitoDinamica extends ServicioGenerico<TransitoDinamicaDTO> {

    public List<TransitoDinamicaDTO> obtenerEntidades(String id) throws Exception;

    public Page<TransitoDinamicaDTO> obtenerEntidades(Pageable pageable) throws Exception;

    public Page<TransitoDinamicaDTO> query(String query, Pageable pageable);
}
