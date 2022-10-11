/*
 * @fileoverview {ServicioCategoriaImpl} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {ServicioCategoriaImpl} fue realizada el 31/07/2022.
 * @Dev - La primera version de {ServicioCategoriaImpl} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.definicion.servicio.implementacion;

import com.rtc.cardinal.definicion.dominio.Categoria;
import com.rtc.cardinal.definicion.repositorio.RepositorioCategoria;
import com.rtc.cardinal.definicion.servicio.ServicioCategoria;
import com.rtc.cardinal.definicion.servicio.dto.CategoriaDTO;
import com.rtc.cardinal.definicion.servicio.mapeo.MapeoCategoria;
import java.util.List;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO: Definición de {@code ServicioCategoriaImpl}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@Service
@Transactional
public class ServicioCategoriaImpl implements ServicioCategoria {

    private final Logger log = LoggerFactory.getLogger(ServicioCategoriaImpl.class);

    private final RepositorioCategoria repositorioEntidad;

    private final MapeoCategoria mapeoEntidad = Mappers.getMapper(MapeoCategoria.class);

    /**
     * TODO: Definición de {@code ServicioCategoriaImpl}.
     *
     * @param repositorioEntidad
     */
    public ServicioCategoriaImpl(RepositorioCategoria repositorioEntidad) {
        this.repositorioEntidad = repositorioEntidad;
    }

    /**
     * TODO: Definición de {@code guardarActualizar}.
     *
     */
    @Override
    public CategoriaDTO guardarActualizar(CategoriaDTO entidadDTO) throws Exception {
        log.debug("Solicitud para guardar la entidad : {}", entidadDTO);

        //TODO: agregar validacion especifica del servicio.
        Categoria entidad = mapeoEntidad.obtenerEntidad(entidadDTO);
        entidad = repositorioEntidad.save(entidad);

        CategoriaDTO entidadActual = mapeoEntidad.obtenerDto(entidad);
        return entidadActual;
    }

    /**
     * TODO: Definición de {@code obtenerEntidades}.
     *
     */
    @Override
    public List<CategoriaDTO> obtenerEntidades() throws Exception {
        log.debug("Solicitud para listar todas las Entidades");
        return mapeoEntidad.obtenerDto(repositorioEntidad.findAll());
    }

    /**
     * TODO: Definición de {@code buscarEntidad}.
     *
     */
    @Override
    public CategoriaDTO buscarEntidad(String id) throws Exception {
        log.debug("Solicitud para buscar la Entidad : {}", id);
        Categoria entidadBuscada = repositorioEntidad.findFirstByStrCategoria((id));
        return mapeoEntidad.obtenerDto(entidadBuscada);
    }

    /**
     * TODO: Definición de {@code obtenerEntidades}.
     *
     * @param strId
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public List<CategoriaDTO> obtenerEntidades(String strId) throws Exception {
        log.debug("Solicitud para listar todas las Entidades");
        return mapeoEntidad.obtenerDto(repositorioEntidad.findByIntIdCategoria(Long.parseLong(strId)));
    }

    /**
     * TODO: Definición de {@code obtenerEntidades}.
     *
     * @param pageable
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public Page<CategoriaDTO> obtenerEntidades(Pageable pageable) throws Exception {
        log.debug("Solicitud para listar todas las Entidades");
        return repositorioEntidad.findAll(pageable).map(mapeoEntidad::obtenerDto);
    }

    /**
     * TODO: Definición de {@code eliminarEntidad}.
     *
     */
    @Override
    public void eliminarEntidad(String id) throws Exception {
        log.debug("Solicitud para eliminar la entidad : {}", id);
        repositorioEntidad.deleteById(Long.parseLong(id));
    }

    /**
     * TODO: Definición de {@code query}.
     *
     * @param query
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public Page<CategoriaDTO> query(String query, Pageable pageable) {
        log.debug("Solicitud de búsqueda de una pagina de la entidad para consulta {}", query);
        return repositorioEntidad.buscarEntidades(query, pageable).map(mapeoEntidad::obtenerDto);
    }
}
