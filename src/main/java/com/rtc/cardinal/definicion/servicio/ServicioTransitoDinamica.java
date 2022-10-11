/*
 * @fileoverview {ServicioTransitoDinamica} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {ServicioTransitoDinamica} fue realizada el 31/07/2022.
 * @Dev - La primera version de {ServicioTransitoDinamica} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.definicion.servicio;

import com.rtc.cardinal.definicion.servicio.dto.TransitoDinamicaDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * TODO: Definición de {@code ServicioTransitoDinamica}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public interface ServicioTransitoDinamica extends ServicioGenerico<TransitoDinamicaDTO> {

    public List<TransitoDinamicaDTO> obtenerEntidades(String id) throws Exception;

    public Page<TransitoDinamicaDTO> obtenerEntidades(Pageable pageable) throws Exception;

    public Page<TransitoDinamicaDTO> query(String query, Pageable pageable);
}
