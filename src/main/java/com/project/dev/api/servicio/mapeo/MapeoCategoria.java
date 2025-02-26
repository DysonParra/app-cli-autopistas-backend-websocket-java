/*
 * @fileoverview    {MapeoCategoria}
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

import com.project.dev.api.dominio.Categoria;
import com.project.dev.api.dto.CategoriaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TODO: Description of {@code MapeoCategoria}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
@Mapper(componentModel = "spring") //, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapeoCategoria extends MapeoEntidadesGenerico<CategoriaDTO, Categoria> {

    /**
     * TODO: Description of {@code obtenerDto}.
     *
     * @param entidad
     * @return 
     */
    @Mapping(source = "intIdCategoria", target = "intIdCategoria")
    @Override
    public CategoriaDTO obtenerDto(Categoria entidad);

    /**
     * TODO: Description of {@code obtenerEntidad}.
     *
     * @param entidadDTO
     * @return 
     */
    @Mapping(source = "intIdCategoria", target = "intIdCategoria")
    @Override
    public Categoria obtenerEntidad(CategoriaDTO entidadDTO);

    /**
     * TODO: Description of {@code desdeId}.
     *
     * @param intId
     * @return 
     */
    public default Categoria desdeId(String intId) {
        if (intId == null) {
            return null;
        }
        Categoria entidad = new Categoria();
        entidad.setIntIdCategoria(Long.parseLong(intId));
        return entidad;
    }
}
