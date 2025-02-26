/*
 * @fileoverview    {RepositorioCategoria}
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

import com.project.dev.api.dominio.Categoria;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * TODO: Description of {@code RepositorioCategoria}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria, Long> {

    /**
     * TODO: Description of {@code findByIntIdCategoria}.
     *
     * @param id
     * @return 
     */
    public List<Categoria> findByIntIdCategoria(Long id);

    /**
     * TODO: Description of {@code findFirstByStrCategoria}.
     *
     * @param id
     * @return 
     */
    public Categoria findFirstByStrCategoria(String id);

    /**
     * TODO: Description of {@code buscarEntidades}.
     *
     * @param strBusqueda
     * @param pageable
     */
    @Query("SELECT m FROM Categoria m WHERE m.intIdCategoria LIKE CONCAT('%', :strBusqueda, '%')")
    public Page<Categoria> buscarEntidades(@Param("strBusqueda") String strBusqueda, Pageable pageable);
}
