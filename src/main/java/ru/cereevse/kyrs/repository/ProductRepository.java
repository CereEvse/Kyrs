package ru.cereevse.kyrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.cereevse.kyrs.model.Product;
import ru.cereevse.kyrs.model.ProductWithCount;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    @Query("SELECT new ru.cereevse.kyrs.model.ProductWithCount(p.idProduct, p.productName, COUNT(o.idOrder)) " +
            "FROM product p " +
            "JOIN p.orders o " +
            "GROUP BY p.idProduct, p.productName " +
            "ORDER BY COUNT(o.idOrder) DESC")
    List<ProductWithCount> findTop5Products();
}
