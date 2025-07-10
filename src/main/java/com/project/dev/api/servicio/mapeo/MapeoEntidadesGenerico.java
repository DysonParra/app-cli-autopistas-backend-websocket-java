/*
 * @overview        {MapeoEntidadesGenerico}
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

import java.util.List;

/**
 * TODO: Description of {@code MapeoEntidadesGenerico}.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
public interface MapeoEntidadesGenerico<D, E> {

    /**
     * TODO: Description of method {@code obtenerEntidad}.
     *
     * @param dto
     * @return 
     */
    public E obtenerEntidad(D dto);

    /**
     * TODO: Description of method {@code obtenerDto}.
     *
     * @param entidad
     * @return 
     */
    public D obtenerDto(E entidad);

    /**
     * TODO: Description of method {@code obtenerEntidad}.
     *
     * @param listaDto
     * @return 
     */
    public List<E> obtenerEntidad(List<D> listaDto);

    /**
     * TODO: Description of method {@code obtenerDto}.
     *
     * @param listaEntidades
     * @return 
     */
    public List<D> obtenerDto(List<E> listaEntidades);
}
