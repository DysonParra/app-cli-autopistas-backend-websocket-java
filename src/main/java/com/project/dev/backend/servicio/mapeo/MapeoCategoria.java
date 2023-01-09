/*
 * @fileoverview    {MapeoCategoria} se encarga de realizar tareas específicas.
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

import com.project.dev.backend.dominio.Categoria;
import com.project.dev.backend.servicio.dto.CategoriaDTO;
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
