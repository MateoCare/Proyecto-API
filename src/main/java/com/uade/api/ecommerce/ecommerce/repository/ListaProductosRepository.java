package com.uade.api.ecommerce.ecommerce.repository;

import com.uade.api.ecommerce.ecommerce.models.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListaProductosRepository extends PagingAndSortingRepository<Producto, Long> {

    Page<Producto> findAllByStatus(Boolean status, Pageable pageable);

    @Query(value = "SELECT p FROM Producto p JOIN p.categoria pc WHERE pc.id IN :categorias and p.status = true GROUP BY p HAVING COUNT(pc.id) = :cantCategorias")
    Page<Producto> findByCategoriaFiltro(@Param("categorias") List<Long> categorias, @Param("cantCategorias") int cantCategorias, Pageable pageable);


    @Query(value = "select * from (select p.*, count(1) cont from producto p inner join favorito f on p.id = f.producto_id group by p.id order by cont desc LIMIT 10)", nativeQuery = true)
    Page<Producto> findProductosDestacados(@Param("top") Integer top, Pageable pageable);

    @Query("SELECT p FROM Producto p INNER JOIN HistorialProducto hp ON p.id = hp.productoId WHERE hp.usuarioId = :usuarioId order by hp.date desc")
    Page<Producto> findProductosVistosRecientemente(@Param("usuarioId") long usuarioId, Pageable pageable);

    @Query("SELECT p FROM Producto p INNER JOIN Favorito f ON p.id = f.productoId AND f.usuarioId = :usuarioId")
    Page<Producto> findProductosFavoritosPorUsuario(@Param("usuarioId") long usuarioId, Pageable pageable);
}
