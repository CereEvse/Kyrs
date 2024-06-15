package ru.cereevse.kyrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.cereevse.kyrs.model.Order;

import java.time.LocalDateTime;

public interface OrderRepository extends JpaRepository<Order, Long> {

    //общее количество заказов за период
    @Query("SELECT COUNT(o) FROM order o WHERE o.dateAddedOrder BETWEEN :startDate AND :endDate")
    Long countOrdersBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    //сумма выручки за период
    @Query("SELECT SUM(o.product.price * o.countProductInOrder) FROM order o WHERE o.dateAddedOrder BETWEEN :startDate AND :endDate")
    Long totalRevenueBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
