/*
 * @overview        {ServicioVehiculoSobrepesoImpl}
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

import com.project.dev.api.dominio.VehiculoSobrepeso;
import com.project.dev.api.dto.VehiculoSobrepesoDTO;
import com.project.dev.api.repositorio.RepositorioVehiculoSobrepeso;
import com.project.dev.api.servicio.ServicioVehiculoSobrepeso;
import com.project.dev.api.servicio.excepcion.ExcepcionEntidadNoEncontrado;
import com.project.dev.api.servicio.mapeo.MapeoVehiculoSobrepeso;
import java.util.List;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO: Description of {@code ServicioVehiculoSobrepesoImpl}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
@Service
@Transactional
public class ServicioVehiculoSobrepesoImpl implements ServicioVehiculoSobrepeso {

    private final Logger log = LoggerFactory.getLogger(ServicioVehiculoSobrepesoImpl.class);

    private final RepositorioVehiculoSobrepeso repositorioEntidad;

    private final MapeoVehiculoSobrepeso mapeoEntidad = Mappers.getMapper(MapeoVehiculoSobrepeso.class);

    /**
     * TODO: Description of method {@code ServicioVehiculoSobrepesoImpl}.
     *
     * @param repositorioEntidad
     */
    public ServicioVehiculoSobrepesoImpl(RepositorioVehiculoSobrepeso repositorioEntidad) {
        this.repositorioEntidad = repositorioEntidad;
    }

    /**
     * TODO: Description of method {@code guardarActualizar}.
     *
     */
    @Override
    public VehiculoSobrepesoDTO guardarActualizar(VehiculoSobrepesoDTO entidadDTO) throws Exception {
        log.debug("Solicitud para guardar la entidad : {}", entidadDTO);

        //TODO: agregar validación específica del servicio.
        VehiculoSobrepeso entidad = mapeoEntidad.obtenerEntidad(entidadDTO);
        entidad = repositorioEntidad.save(entidad);

        VehiculoSobrepesoDTO entidadActual = mapeoEntidad.obtenerDto(entidad);
        return entidadActual;
    }

    /**
     * TODO: Description of method {@code obtenerEntidades}.
     *
     */
    @Override
    public List<VehiculoSobrepesoDTO> obtenerEntidades() throws Exception {
        log.debug("Solicitud para listar todas las Entidades");
        return mapeoEntidad.obtenerDto(repositorioEntidad.findAll());
    }

    /**
     * TODO: Description of method {@code buscarEntidad}.
     *
     */
    @Override
    public VehiculoSobrepesoDTO buscarEntidad(String id) throws Exception {
        log.debug("Solicitud para buscar la Entidad : {}", id);
        VehiculoSobrepeso entidadBuscada = repositorioEntidad.findById(Long.parseLong(id))
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
    public List<VehiculoSobrepesoDTO> obtenerEntidades(String strId) throws Exception {
        log.debug("Solicitud para listar todas las Entidades");
        return (List<VehiculoSobrepesoDTO>) mapeoEntidad.obtenerDto(repositorioEntidad.findByIntIdRepeso(Long.parseLong(strId)));
    }

    /**
     * TODO: Description of method {@code obtenerEntidades}.
     *
     * @param pageable
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public Page<VehiculoSobrepesoDTO> obtenerEntidades(Pageable pageable) throws Exception {
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
        repositorioEntidad.deleteById(Long.parseLong(id));
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
    public Page<VehiculoSobrepesoDTO> query(String query, Pageable pageable) {
        log.debug("Solicitud de búsqueda de una pagina de la entidad para consulta {}", query);
        return repositorioEntidad.buscarEntidades(query, pageable).map(mapeoEntidad::obtenerDto);
    }
}
