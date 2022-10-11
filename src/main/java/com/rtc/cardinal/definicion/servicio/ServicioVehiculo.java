/*
 * @fileoverview {ServicioVehiculo} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {ServicioVehiculo} fue realizada el 31/07/2022.
 * @Dev - La primera version de {ServicioVehiculo} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.definicion.servicio;

import com.rtc.cardinal.definicion.servicio.dto.VehiculoDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * TODO: Definición de {@code ServicioVehiculo}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public interface ServicioVehiculo extends ServicioGenerico<VehiculoDTO> {

    public List<VehiculoDTO> obtenerEntidades(String id) throws Exception;

    public Page<VehiculoDTO> obtenerEntidades(Pageable pageable) throws Exception;

    public Page<VehiculoDTO> query(String query, Pageable pageable);
}
