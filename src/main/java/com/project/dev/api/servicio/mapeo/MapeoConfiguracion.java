/*
 * @overview        {MapeoConfiguracion}
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
package com.project.dev.api.servicio.mapeo;

import com.project.dev.api.dominio.Configuracion;
import com.project.dev.api.dto.ConfiguracionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TODO: Description of {@code MapeoConfiguracion}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
@Mapper(componentModel = "spring")
public interface MapeoConfiguracion extends MapeoEntidadesGenerico<ConfiguracionDTO, Configuracion> {

    /**
     * TODO: Description of method {@code obtenerDto}.
     *
     * @param entidad
     * @return 
     */
    @Mapping(source = "intIdConfiguracion", target = "intIdConfiguracion")
    @Override
    public ConfiguracionDTO obtenerDto(Configuracion entidad);

    /**
     * TODO: Description of method {@code obtenerEntidad}.
     *
     * @param entidadDTO
     * @return 
     */
    @Mapping(source = "intIdConfiguracion", target = "intIdConfiguracion")
    @Override
    public Configuracion obtenerEntidad(ConfiguracionDTO entidadDTO);

    /**
     * TODO: Description of method {@code desdeId}.
     *
     * @param intId
     * @return 
     */
    public default Configuracion desdeId(String intId) {
        if (intId == null) {
            return null;
        }
        Configuracion entidad = new Configuracion();
        entidad.setIntIdConfiguracion(Long.parseLong(intId));
        return entidad;
    }
}
