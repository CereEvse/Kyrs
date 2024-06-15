package ru.cereevse.kyrs.servive;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cereevse.kyrs.exceptions.ExceptionHandler;
import ru.cereevse.kyrs.model.Buyer;
import ru.cereevse.kyrs.model.Employee;
import ru.cereevse.kyrs.model.Order;
import ru.cereevse.kyrs.model.Product;
import ru.cereevse.kyrs.repository.BuyerRepository;
import ru.cereevse.kyrs.repository.EmployeeRepository;
import ru.cereevse.kyrs.repository.OrderRepository;
import ru.cereevse.kyrs.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ExceptionHandler exceptionHandler;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final BuyerRepository buyerRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void addOrder(Order order) {
        Product product = order.getProduct();
        Buyer buyer = order.getBuyer();
        Employee employee = order.getEmployee();

        if (product != null && product.getIdProduct() != null) {
            product = productRepository.findById(product.getIdProduct()).orElse(null);
        }
        if (buyer != null && buyer.getIdBuyer() != null) {
            buyer = buyerRepository.findById(buyer.getIdBuyer()).orElse(null);
        }
        if (employee != null && employee.getIdEmployee() != null) {
            employee = employeeRepository.findById(employee.getIdEmployee()).orElse(null);
        }

        order.setProduct(product);
        order.setBuyer(buyer);
        order.setEmployee(employee);

        orderRepository.save(order);

    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<Order> putOrderById(Long id, Order updatedOrder) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if(existingOrder.isPresent()){

            Order orderToUpdate = existingOrder.get();
            if(updatedOrder.getProduct() != null && updatedOrder.getProduct().getIdProduct() != null) {
                Product product = productRepository.findById(updatedOrder.getProduct().getIdProduct()).orElse(null);
                orderToUpdate.setProduct(product);
            }

            if(updatedOrder.getCountProductInOrder() != null) {
                orderToUpdate.setCountProductInOrder(updatedOrder.getCountProductInOrder());
            }

            if(updatedOrder.getBuyer() != null && updatedOrder.getBuyer().getIdBuyer() != null) {
                Buyer buyer = buyerRepository.findById(updatedOrder.getBuyer().getIdBuyer()).orElse(null);
                orderToUpdate.setBuyer(buyer);
            }

            if(updatedOrder.getEmployee() != null && updatedOrder.getEmployee().getIdEmployee() != null) {
                Employee employee = employeeRepository.findById(updatedOrder.getEmployee().getIdEmployee()).orElse(null);
                orderToUpdate.setEmployee(employee);
            }
            orderRepository.save(orderToUpdate);
        }
        return existingOrder;
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);

    }
    
}
