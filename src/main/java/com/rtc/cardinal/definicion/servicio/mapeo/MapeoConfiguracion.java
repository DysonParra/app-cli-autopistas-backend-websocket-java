/*
 * @fileoverview {FileName} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {FileName} fue realizada el 31/07/2022.
 * @Dev - La primera version de {FileName} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.definicion.servicio.mapeo;

import com.rtc.cardinal.definicion.dominio.Configuracion;
import com.rtc.cardinal.definicion.servicio.dto.ConfiguracionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TODO: Definición de {@code MapeoConfiguracion}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@Mapper(componentModel = "spring") //, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapeoConfiguracion extends MapeoEntidadesGenerico<ConfiguracionDTO, Configuracion> {

    @Mapping(source = "intIdConfiguracion", target = "intIdConfiguracion")
    //TODO: deben ser el campo clave de la base de datos ( la llave )
    @Override
    public ConfiguracionDTO obtenerDto(Configuracion entidad);

    @Mapping(source = "intIdConfiguracion", target = "intIdConfiguracion")
    @Override
    public Configuracion obtenerEntidad(ConfiguracionDTO entidadDTO);

    default Configuracion desdeId(String intId) {
        if (intId == null) {
            return null;
        }
        Configuracion entidad = new Configuracion();
        entidad.setIntIdConfiguracion(Long.parseLong(intId));
        return entidad;
    }
}
