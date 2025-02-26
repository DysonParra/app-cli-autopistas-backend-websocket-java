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
 * TODO: Description of {@code ServicioTransitoDinamica}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
public interface ServicioTransitoDinamica extends ServicioGenerico<TransitoDinamicaDTO> {

    /**
     * TODO: Description of {@code obtenerEntidades}.
     *
     * @param id
     * @return 
     * @throws java.lang.Exception 
     */
    public List<TransitoDinamicaDTO> obtenerEntidades(String id) throws Exception;

    /**
     * TODO: Description of {@code obtenerEntidades}.
     *
     * @param pageable
     * @throws java.lang.Exception
     */
    public Page<TransitoDinamicaDTO> obtenerEntidades(Pageable pageable) throws Exception;

    /**
     * TODO: Description of {@code query}.
     *
     * @param query
     * @param pageable
     */
    public Page<TransitoDinamicaDTO> query(String query, Pageable pageable);
}
