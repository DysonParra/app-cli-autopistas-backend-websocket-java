/*
 * @fileoverview {MapeoTransitoDinamica} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {MapeoTransitoDinamica} fue realizada el 31/07/2022.
 * @Dev - La primera version de {MapeoTransitoDinamica} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.definicion.servicio.mapeo;

import com.rtc.cardinal.definicion.dominio.TransitoDinamica;
import com.rtc.cardinal.definicion.servicio.dto.TransitoDinamicaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TODO: Definición de {@code MapeoTransitoDinamica}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@Mapper(componentModel = "spring") //, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapeoTransitoDinamica extends MapeoEntidadesGenerico<TransitoDinamicaDTO, TransitoDinamica> {

    @Mapping(source = "intIdDinamica", target = "intIdDinamica")
    //TODO: deben ser el campo clave de la base de datos ( la llave )
    @Override
    public TransitoDinamicaDTO obtenerDto(TransitoDinamica entidad);

    @Mapping(source = "intIdDinamica", target = "intIdDinamica")
    @Override
    public TransitoDinamica obtenerEntidad(TransitoDinamicaDTO entidadDTO);

    default TransitoDinamica desdeId(String intId) {
        if (intId == null) {
            return null;
        }
        TransitoDinamica entidad = new TransitoDinamica();
        entidad.setIntIdDinamica(Long.parseLong(intId));
        return entidad;
    }
}
