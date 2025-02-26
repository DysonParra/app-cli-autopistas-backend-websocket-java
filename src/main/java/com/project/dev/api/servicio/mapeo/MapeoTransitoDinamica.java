/*
 * @fileoverview    {MapeoTransitoDinamica}
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

import com.project.dev.api.dominio.TransitoDinamica;
import com.project.dev.api.dto.TransitoDinamicaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TODO: Description of {@code MapeoTransitoDinamica}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
@Mapper(componentModel = "spring") //, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapeoTransitoDinamica extends MapeoEntidadesGenerico<TransitoDinamicaDTO, TransitoDinamica> {

    /**
     * TODO: Description of {@code obtenerDto}.
     *
     * @param entidad
     * @return 
     */
    @Mapping(source = "intIdDinamica", target = "intIdDinamica")
    @Override
    public TransitoDinamicaDTO obtenerDto(TransitoDinamica entidad);

    /**
     * TODO: Description of {@code obtenerEntidad}.
     *
     * @param entidadDTO
     * @return 
     */
    @Mapping(source = "intIdDinamica", target = "intIdDinamica")
    @Override
    public TransitoDinamica obtenerEntidad(TransitoDinamicaDTO entidadDTO);

    /**
     * TODO: Description of {@code desdeId}.
     *
     * @param intId
     * @return 
     */
    public default TransitoDinamica desdeId(String intId) {
        if (intId == null) {
            return null;
        }
        TransitoDinamica entidad = new TransitoDinamica();
        entidad.setIntIdDinamica(Long.parseLong(intId));
        return entidad;
    }
}
