/*
 * @fileoverview    {MapeoVehiculoSobrepeso}
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

import com.project.dev.api.dominio.VehiculoSobrepeso;
import com.project.dev.api.dto.VehiculoSobrepesoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TODO: Description of {@code MapeoVehiculoSobrepeso}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
@Mapper(componentModel = "spring") //, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapeoVehiculoSobrepeso extends MapeoEntidadesGenerico<VehiculoSobrepesoDTO, VehiculoSobrepeso> {

    /**
     * TODO: Description of method {@code obtenerDto}.
     *
     * @param entidad
     * @return 
     */
    @Mapping(source = "intIdRepeso", target = "intIdRepeso")
    @Override
    public VehiculoSobrepesoDTO obtenerDto(VehiculoSobrepeso entidad);

    /**
     * TODO: Description of method {@code obtenerEntidad}.
     *
     * @param entidadDTO
     * @return 
     */
    @Mapping(source = "intIdRepeso", target = "intIdRepeso")
    @Override
    public VehiculoSobrepeso obtenerEntidad(VehiculoSobrepesoDTO entidadDTO);

    /**
     * TODO: Description of method {@code desdeId}.
     *
     * @param intId
     * @return 
     */
    public default VehiculoSobrepeso desdeId(String intId) {
        if (intId == null) {
            return null;
        }
        VehiculoSobrepeso entidad = new VehiculoSobrepeso();
        entidad.setIntIdRepeso(Long.parseLong(intId));
        return entidad;
    }
}
