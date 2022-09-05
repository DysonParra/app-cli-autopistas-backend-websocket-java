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
package com.rtc.cardinal.definicion.servicio;

import java.util.List;

/**
 * TODO: Definición de {@code ServicioGenerico}.
 *
 * @author Dyson Parra
 * @param <T>
 * @since 1.8
 */
public interface ServicioGenerico<T> {

    /**
     * Guarda o actualiza los datos de un actividad.
     *
     * @param entidadDTO entidad que sera almacenada
     * @return entidad almacenada en la base de datos.
     * @throws java.lang.Exception
     */
    public T guardarActualizar(T entidadDTO) throws Exception;

    /**
     * Lista todos las entidades (actividad) existentes.
     *
     * @return lista de entidades almacenadas en la base de datos.
     * @throws java.lang.Exception
     */
    public List<T> obtenerEntidades() throws Exception;

    /**
     * Obtiene el actividad segun el id suministrado.
     *
     * @param id entidad
     * @return entidad almacenada en la base de datos.
     * @throws java.lang.Exception
     */
    public T buscarEntidad(String id) throws Exception;

    /**
     * Guarda o actualiza los datos de un actividad.
     *
     * @param id entidad que sera eliminada.
     * @throws java.lang.Exception
     */
    public void eliminarEntidad(String id) throws Exception;
}
