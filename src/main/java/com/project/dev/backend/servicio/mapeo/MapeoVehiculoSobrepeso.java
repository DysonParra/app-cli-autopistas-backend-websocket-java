/*
 * @fileoverview    {MapeoVehiculoSobrepeso} se encarga de realizar tareas específicas.
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
package com.project.dev.backend.servicio.mapeo;

import com.project.dev.backend.dominio.VehiculoSobrepeso;
import com.project.dev.backend.servicio.dto.VehiculoSobrepesoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TODO: Definición de {@code MapeoVehiculoSobrepeso}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@Mapper(componentModel = "spring") //, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapeoVehiculoSobrepeso extends MapeoEntidadesGenerico<VehiculoSobrepesoDTO, VehiculoSobrepeso> {

    @Mapping(source = "intIdRepeso", target = "intIdRepeso")
    //TODO: deben ser el campo clave de la base de datos ( la llave )
    @Override
    public VehiculoSobrepesoDTO obtenerDto(VehiculoSobrepeso entidad);

    @Mapping(source = "intIdRepeso", target = "intIdRepeso")
    @Override
    public VehiculoSobrepeso obtenerEntidad(VehiculoSobrepesoDTO entidadDTO);

    default VehiculoSobrepeso desdeId(String intId) {
        if (intId == null) {
            return null;
        }
        VehiculoSobrepeso entidad = new VehiculoSobrepeso();
        entidad.setIntIdRepeso(Long.parseLong(intId));
        return entidad;
    }
}
