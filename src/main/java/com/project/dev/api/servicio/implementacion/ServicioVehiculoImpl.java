/*
 * @fileoverview    {ServicioVehiculoImpl}
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

import com.project.dev.api.dominio.Vehiculo;
import com.project.dev.api.dto.VehiculoDTO;
import com.project.dev.api.repositorio.RepositorioVehiculo;
import com.project.dev.api.servicio.ServicioVehiculo;
import com.project.dev.api.servicio.excepcion.ExcepcionEntidadNoEncontrado;
import com.project.dev.api.servicio.mapeo.MapeoVehiculo;
import java.util.List;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO: Description of {@code ServicioVehiculoImpl}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
@Service
@Transactional
public class ServicioVehiculoImpl implements ServicioVehiculo {

    private final Logger log = LoggerFactory.getLogger(ServicioVehiculoImpl.class);

    private final RepositorioVehiculo repositorioEntidad;

    private final MapeoVehiculo mapeoEntidad = Mappers.getMapper(MapeoVehiculo.class);

    /**
     * TODO: Description of method {@code ServicioVehiculoImpl}.
     *
     * @param repositorioEntidad
     */
    public ServicioVehiculoImpl(RepositorioVehiculo repositorioEntidad) {
        this.repositorioEntidad = repositorioEntidad;
    }

    /**
     * TODO: Description of method {@code guardarActualizar}.
     *
     */
    @Override
    public VehiculoDTO guardarActualizar(VehiculoDTO entidadDTO) throws Exception {
        log.debug("Solicitud para guardar la entidad : {}", entidadDTO);

        //TODO: agregar validacion especifica del servicio.
        Vehiculo entidad = mapeoEntidad.obtenerEntidad(entidadDTO);
        entidad = repositorioEntidad.save(entidad);

        VehiculoDTO entidadActual = mapeoEntidad.obtenerDto(entidad);
        return entidadActual;
    }

    /**
     * TODO: Description of method {@code obtenerEntidades}.
     *
     */
    @Override
    public List<VehiculoDTO> obtenerEntidades() throws Exception {
        log.debug("Solicitud para listar todas las Entidades");
        return mapeoEntidad.obtenerDto(repositorioEntidad.findAll());
    }

    /**
     * TODO: Description of method {@code buscarEntidad}.
     *
     */
    @Override
    public VehiculoDTO buscarEntidad(String id) throws Exception {
        log.debug("Solicitud para buscar la Entidad : {}", id);
        Vehiculo entidadBuscada = repositorioEntidad.findById(String.valueOf(id))
                .orElseThrow(() -> new ExcepcionEntidadNoEncontrado(id));
        return mapeoEntidad.obtenerDto(entidadBuscada);
    }

    /**
     * TODO: Description of method {@code obtenerEntidades}.
     *
     * @param strId
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public List<VehiculoDTO> obtenerEntidades(String strId) throws Exception {
        log.debug("Solicitud para listar todas las Entidades");
        return (List<VehiculoDTO>) mapeoEntidad.obtenerDto(repositorioEntidad.findByStrPlacaVehiculo(String.valueOf(strId)));
    }

    /**
     * TODO: Description of method {@code obtenerEntidades}.
     *
     * @param pageable
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public Page<VehiculoDTO> obtenerEntidades(Pageable pageable) throws Exception {
        log.debug("Solicitud para listar todas las Entidades");
        return repositorioEntidad.findAll(pageable).map(mapeoEntidad::obtenerDto);
    }

    /**
     * TODO: Description of method {@code eliminarEntidad}.
     *
     */
    @Override
    public void eliminarEntidad(String id) throws Exception {
        log.debug("Solicitud para eliminar la entidad : {}", id);
        repositorioEntidad.deleteById(String.valueOf(id));
    }

    /**
     * TODO: Description of method {@code query}.
     *
     * @param query
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public Page<VehiculoDTO> query(String query, Pageable pageable) {
        log.debug("Solicitud de búsqueda de una pagina de la entidad para consulta {}", query);
        return repositorioEntidad.buscarEntidades(query, pageable).map(mapeoEntidad::obtenerDto);
    }
}
