/*
 * @overview        {MapeoVehiculo}
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

import com.project.dev.api.dominio.Vehiculo;
import com.project.dev.api.dto.VehiculoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TODO: Description of {@code MapeoVehiculo}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
@Mapper(componentModel = "spring")
public interface MapeoVehiculo extends MapeoEntidadesGenerico<VehiculoDTO, Vehiculo> {

    /**
     * TODO: Description of method {@code obtenerDto}.
     *
     * @param entidad
     * @return 
     */
    @Mapping(source = "strPlacaVehiculo", target = "strPlacaVehiculo")
    @Override
    public VehiculoDTO obtenerDto(Vehiculo entidad);

    /**
     * TODO: Description of method {@code obtenerEntidad}.
     *
     * @param entidadDTO
     * @return 
     */
    @Mapping(source = "strPlacaVehiculo", target = "strPlacaVehiculo")
    @Override
    public Vehiculo obtenerEntidad(VehiculoDTO entidadDTO);

    /**
     * TODO: Description of method {@code desdeId}.
     *
     * @param intId
     * @return 
     */
    public default Vehiculo desdeId(String intId) {
        if (intId == null) {
            return null;
        }
        Vehiculo entidad = new Vehiculo();
        entidad.setStrPlacaVehiculo(String.valueOf(intId));
        return entidad;
    }
}
