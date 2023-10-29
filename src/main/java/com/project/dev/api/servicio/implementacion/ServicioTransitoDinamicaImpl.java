/*
 * @fileoverview    {ServicioTransitoDinamicaImpl}
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
package com.project.dev.api.servicio.implementacion;

import com.project.dev.api.dominio.TransitoDinamica;
import com.project.dev.api.dto.TransitoDinamicaDTO;
import com.project.dev.api.repositorio.RepositorioTransitoDinamica;
import com.project.dev.api.servicio.ServicioTransitoDinamica;
import com.project.dev.api.servicio.excepcion.ExcepcionEntidadNoEncontrado;
import com.project.dev.api.servicio.mapeo.MapeoTransitoDinamica;
import java.util.List;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO: Description of {@code ServicioTransitoDinamicaImpl}.
 *
 * @author Dyson Parra
 * @since 11
 */
@Service
@Transactional
public class ServicioTransitoDinamicaImpl implements ServicioTransitoDinamica {

    private final Logger log = LoggerFactory.getLogger(ServicioTransitoDinamicaImpl.class);

    private final RepositorioTransitoDinamica repositorioEntidad;

    private final MapeoTransitoDinamica mapeoEntidad = Mappers.getMapper(MapeoTransitoDinamica.class);

    /**
     * TODO: Description of {@code ServicioTransitoDinamicaImpl}.
     *
     * @param repositorioEntidad
     */
    public ServicioTransitoDinamicaImpl(RepositorioTransitoDinamica repositorioEntidad) {
        this.repositorioEntidad = repositorioEntidad;
    }

    /**
     * TODO: Description of {@code guardarActualizar}.
     *
     */
    @Override
    public TransitoDinamicaDTO guardarActualizar(TransitoDinamicaDTO entidadDTO) throws Exception {
        log.debug("Solicitud para guardar la entidad : {}", entidadDTO);

        //TODO: agregar validacion especifica del servicio.
        TransitoDinamica entidad = mapeoEntidad.obtenerEntidad(entidadDTO);
        entidad = repositorioEntidad.save(entidad);

        TransitoDinamicaDTO entidadActual = mapeoEntidad.obtenerDto(entidad);
        return entidadActual;
    }

    /**
     * TODO: Description of {@code obtenerEntidades}.
     *
     */
    @Override
    public List<TransitoDinamicaDTO> obtenerEntidades() throws Exception {
        log.debug("Solicitud para listar todas las Entidades");
        return mapeoEntidad.obtenerDto(repositorioEntidad.findAll());
    }

    /**
     * TODO: Description of {@code buscarEntidad}.
     *
     */
    @Override
    public TransitoDinamicaDTO buscarEntidad(String id) throws Exception {
        log.debug("Solicitud para buscar la Entidad : {}", id);
        TransitoDinamica entidadBuscada = repositorioEntidad.findById(Long.parseLong(id))
                .orElseThrow(() -> new ExcepcionEntidadNoEncontrado(id));
        return mapeoEntidad.obtenerDto(entidadBuscada);
    }

    /**
     * TODO: Description of {@code obtenerEntidades}.
     *
     * @param strId
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public List<TransitoDinamicaDTO> obtenerEntidades(String strId) throws Exception {
        log.debug("Solicitud para listar todas las Entidades");
        return (List<TransitoDinamicaDTO>) mapeoEntidad.obtenerDto(repositorioEntidad.findByIntIdDinamica(Long.parseLong(strId)));
    }

    /**
     * TODO: Description of {@code obtenerEntidades}.
     *
     * @param pageable
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public Page<TransitoDinamicaDTO> obtenerEntidades(Pageable pageable) throws Exception {
        log.debug("Solicitud para listar todas las Entidades");
        return repositorioEntidad.findAll(pageable).map(mapeoEntidad::obtenerDto);
    }

    /**
     * TODO: Description of {@code eliminarEntidad}.
     *
     */
    @Override
    public void eliminarEntidad(String id) throws Exception {
        log.debug("Solicitud para eliminar la entidad : {}", id);
        repositorioEntidad.deleteById(Long.parseLong(id));
    }

    /**
     * TODO: Description of {@code query}.
     *
     * @param query
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public Page<TransitoDinamicaDTO> query(String query, Pageable pageable) {
        log.debug("Solicitud de búsqueda de una pagina de la entidad para consulta {}", query);
        return repositorioEntidad.buscarEntidades(query, pageable).map(mapeoEntidad::obtenerDto);
    }
}
