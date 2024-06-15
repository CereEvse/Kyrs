package ru.cereevse.kyrs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.cereevse.kyrs.exceptions.ExceptionHandler;
import ru.cereevse.kyrs.model.Buyer;
import ru.cereevse.kyrs.model.Employee;
import ru.cereevse.kyrs.model.Order;
import ru.cereevse.kyrs.model.Product;
import ru.cereevse.kyrs.repository.BuyerRepository;
import ru.cereevse.kyrs.repository.EmployeeRepository;
import ru.cereevse.kyrs.repository.OrderRepository;
import ru.cereevse.kyrs.repository.ProductRepository;
import ru.cereevse.kyrs.servive.OrderServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BuyerRepository buyerRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ExceptionHandler exceptionHandler;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addOrder_success() {
        Order order = new Order();
        Product product = new Product();
        Buyer buyer = new Buyer();
        Employee employee = new Employee();

        order.setProduct(product);
        order.setBuyer(buyer);
        order.setEmployee(employee);

        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        when(buyerRepository.findById(any())).thenReturn(Optional.of(buyer));
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));

        orderService.addOrder(order);

        verify(orderRepository, times(1)).save(order);
    }


    @Test
    void deleteOrderById_success() {
        Long orderId = 1L;

        orderService.deleteOrderById(orderId);

        verify(orderRepository, times(1)).deleteById(orderId);
    }
}
