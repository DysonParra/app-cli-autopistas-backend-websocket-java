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

    public List<Categoria> findByIntIdCategoria(Long id);

    public Categoria findFirstByStrCategoria(String id);

    @Query("SELECT m FROM Categoria m WHERE m.intIdCategoria LIKE CONCAT('%', :strBusqueda, '%')")
    public Page<Categoria> buscarEntidades(@Param("strBusqueda") String strBusqueda, Pageable pageable);
}
