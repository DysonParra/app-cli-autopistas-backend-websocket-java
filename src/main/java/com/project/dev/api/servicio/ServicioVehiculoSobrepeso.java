/*
 * @fileoverview    {ServicioVehiculoSobrepeso}
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
package com.project.dev.api.servicio;

import com.project.dev.api.dto.VehiculoSobrepesoDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * TODO: Description of {@code ServicioVehiculoSobrepeso}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
public interface ServicioVehiculoSobrepeso extends ServicioGenerico<VehiculoSobrepesoDTO> {

    public List<VehiculoSobrepesoDTO> obtenerEntidades(String id) throws Exception;

    public Page<VehiculoSobrepesoDTO> obtenerEntidades(Pageable pageable) throws Exception;

    public Page<VehiculoSobrepesoDTO> query(String query, Pageable pageable);
}
