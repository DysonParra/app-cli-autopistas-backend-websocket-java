/*
 * @fileoverview    {ExcepcionEntidadNoEncontrado} se encarga de realizar tareas específicas.
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
