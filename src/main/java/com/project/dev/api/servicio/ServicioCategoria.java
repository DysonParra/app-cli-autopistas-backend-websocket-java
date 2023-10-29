/*
 * @fileoverview    {ServicioCategoria}
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

import com.project.dev.api.dto.CategoriaDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * TODO: Definición de {@code ServicioCategoria}.
 *
 * @author Dyson Parra
 * @since 11
 */
public interface ServicioCategoria extends ServicioGenerico<CategoriaDTO> {

    public List<CategoriaDTO> obtenerEntidades(String id) throws Exception;

    public Page<CategoriaDTO> obtenerEntidades(Pageable pageable) throws Exception;

    public Page<CategoriaDTO> query(String query, Pageable pageable);
}
