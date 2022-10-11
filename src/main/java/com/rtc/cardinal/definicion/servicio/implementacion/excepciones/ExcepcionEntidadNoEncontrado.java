/*
 * @fileoverview {ExcepcionEntidadNoEncontrado} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {ExcepcionEntidadNoEncontrado} fue realizada el 31/07/2022.
 * @Dev - La primera version de {ExcepcionEntidadNoEncontrado} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.definicion.servicio.implementacion.excepciones;

/**
 * TODO: Definición de {@code ExcepcionEntidadNoEncontrado}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class ExcepcionEntidadNoEncontrado extends RuntimeException {

    /**
     * TODO: Definición de {@code ExcepcionEntidadNoEncontrado}.
     *
     * @param id
     */
    public ExcepcionEntidadNoEncontrado(Long id) {
        super("No se puede encontrar la entidad " + id);
    }

    /**
     * TODO: Definición de {@code ExcepcionEntidadNoEncontrado}.
     *
     * @param usuario
     */
    public ExcepcionEntidadNoEncontrado(String usuario) {
        super("No se puede encontrar la entidad " + usuario);
    }

}
