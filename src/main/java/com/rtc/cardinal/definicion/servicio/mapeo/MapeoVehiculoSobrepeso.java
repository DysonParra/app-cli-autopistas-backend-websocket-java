/*
 * @fileoverview {MapeoVehiculoSobrepeso} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {MapeoVehiculoSobrepeso} fue realizada el 31/07/2022.
 * @Dev - La primera version de {MapeoVehiculoSobrepeso} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.definicion.servicio.mapeo;

import com.rtc.cardinal.definicion.dominio.VehiculoSobrepeso;
import com.rtc.cardinal.definicion.servicio.dto.VehiculoSobrepesoDTO;
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
