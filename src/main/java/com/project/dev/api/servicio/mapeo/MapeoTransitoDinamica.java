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
 * TODO: Definición de {@code MapeoTransitoDinamica}.
 *
 * @author Dyson Parra
 * @since 11
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
