/*
 * @fileoverview    {MapeoVehiculo} se encarga de realizar tareas específicas.
 *
 * @version         2.0
 *
 * @author          Dyson Arley Parra Tilano <dysontilano@gmail.com>
 *
 * @copyright       Dyson Parra
 * @see             github.com/DysonParra
 *
 * History
 * @version 1.0     Implementación realizada.
 * @version 2.0     Documentación agregada.
 */
package com.rtc.cardinal.definicion.servicio.mapeo;

import com.rtc.cardinal.definicion.dominio.Vehiculo;
import com.rtc.cardinal.definicion.servicio.dto.VehiculoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TODO: Definición de {@code MapeoVehiculo}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@Mapper(componentModel = "spring") //, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapeoVehiculo extends MapeoEntidadesGenerico<VehiculoDTO, Vehiculo> {

    @Mapping(source = "strPlacaVehiculo", target = "strPlacaVehiculo")
    //TODO: deben ser el campo clave de la base de datos ( la llave )
    @Override
    public VehiculoDTO obtenerDto(Vehiculo entidad);

    @Mapping(source = "strPlacaVehiculo", target = "strPlacaVehiculo")
    @Override
    public Vehiculo obtenerEntidad(VehiculoDTO entidadDTO);

    default Vehiculo desdeId(String intId) {
        if (intId == null) {
            return null;
        }
        Vehiculo entidad = new Vehiculo();
        entidad.setStrPlacaVehiculo(String.valueOf(intId));
        return entidad;
    }
}
