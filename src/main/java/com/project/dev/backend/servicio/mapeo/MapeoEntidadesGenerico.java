/*
 * @fileoverview    {MapeoEntidadesGenerico} se encarga de realizar tareas específicas.
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

import java.util.List;

/**
 * FIXME: Definición de {@code MapeoEntidadesGenerico}.
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public interface MapeoEntidadesGenerico<D, E> {

    public E obtenerEntidad(D dto);

    public D obtenerDto(E entidad);

    public List<E> obtenerEntidad(List<D> listaDto);

    public List<D> obtenerDto(List<E> listaEntidades);
}
