/*
 * @overview        {RepositorioTransitoDinamica}
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
package com.project.dev.api.repositorio;

import com.project.dev.api.dominio.TransitoDinamica;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * TODO: Description of {@code RepositorioTransitoDinamica}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
@Repository
public interface RepositorioTransitoDinamica extends JpaRepository<TransitoDinamica, Long> {

    /**
     * TODO: Description of method {@code findByIntIdDinamica}.
     *
     * @param id
     * @return 
     */
    public List<TransitoDinamica> findByIntIdDinamica(Long id);

    /**
     * TODO: Description of method {@code findByStrPlacaVehiculo}.
     *
     * @param id
     * @return 
     */
    public TransitoDinamica findByStrPlacaVehiculo(String id);

    /**
     * TODO: Description of method {@code buscarEntidades}.
     *
     * @param strBusqueda
     * @param pageable
     */
    @Query("SELECT m FROM TransitoDinamica m WHERE m.intIdDinamica LIKE CONCAT('%', :strBusqueda, '%')")
    public Page<TransitoDinamica> buscarEntidades(@Param("strBusqueda") String strBusqueda, Pageable pageable);
}
