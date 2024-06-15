package ru.cereevse.kyrs.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.cereevse.kyrs.model.Order;
import ru.cereevse.kyrs.repository.OrderRepository;
import ru.cereevse.kyrs.servive.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @PostMapping
    ResponseEntity<Void> addOrder(@RequestBody @Valid Order order,
                                     BindingResult bindingResult){
        orderService.addOrder(order);
        return ResponseEntity.ok().build();

    }
    
    @GetMapping
    ResponseEntity<List<Order>> getAllOrder(){
        return ResponseEntity.ok(orderService.getAllOrder());

    }
    
    @GetMapping("/{id}")
    ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Optional<Order> orderOptional = orderService.getOrderById(id);
        return orderOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    ResponseEntity<Order> updateOrderById(@PathVariable Long id, @RequestBody @Validated Order updatedOrder) {
        Optional<Order> updatedOrderOptional = orderService.putOrderById(id, updatedOrder);
        return updatedOrderOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteOrderById(@PathVariable Long id){
        orderService.deleteOrderById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/count")
    ResponseEntity<Long> countOrdersBetweenDates(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        Long count = orderRepository.countOrdersBetweenDates(startDate, endDate);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/revenue")
    ResponseEntity<Long> totalRevenueBetweenDates(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        Long revenue = orderRepository.totalRevenueBetweenDates(startDate, endDate);
        return ResponseEntity.ok(revenue);
    }
    
}
