/*
 * @fileoverview {MapeoCategoria} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {MapeoCategoria} fue realizada el 31/07/2022.
 * @Dev - La primera version de {MapeoCategoria} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.definicion.servicio.mapeo;

import com.rtc.cardinal.definicion.dominio.Categoria;
import com.rtc.cardinal.definicion.servicio.dto.CategoriaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TODO: Definición de {@code MapeoCategoria}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@Mapper(componentModel = "spring") //, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapeoCategoria extends MapeoEntidadesGenerico<CategoriaDTO, Categoria> {

    @Mapping(source = "intIdCategoria", target = "intIdCategoria")
    //TODO: deben ser el campo clave de la base de datos ( la llave )
    @Override
    public CategoriaDTO obtenerDto(Categoria entidad);

    @Mapping(source = "intIdCategoria", target = "intIdCategoria")
    @Override
    public Categoria obtenerEntidad(CategoriaDTO entidadDTO);

    default Categoria desdeId(String intId) {
        if (intId == null) {
            return null;
        }
        Categoria entidad = new Categoria();
        entidad.setIntIdCategoria(Long.parseLong(intId));
        return entidad;
    }
}
