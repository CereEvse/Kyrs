package ru.cereevse.kyrs.servive;

import ru.cereevse.kyrs.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void addOrder( Order Order);

    List<Order> getAllOrder();

    Optional<Order> getOrderById(Long id);

    Optional<Order> putOrderById(Long id, Order updatedOrder);

    void deleteOrderById(Long id);
}
