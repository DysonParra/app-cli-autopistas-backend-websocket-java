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

import java.util.List;

/**
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
