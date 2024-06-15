package ru.cereevse.kyrs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order")
@Entity(name = "order")
public class Order {

    @Id
    @Column(name = "id_order")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_order_seq")
    @SequenceGenerator(name = "id_order_seq", sequenceName = "id_order_seq", initialValue = 1, allocationSize = 1)
    private Long idOrder;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @NotEmpty(message = "Sorry, empty")
    @Column(name="count_product_in_order")
    private Long countProductInOrder;

    @NotEmpty(message = "Sorry, empty")
    @ManyToOne
    @JoinColumn(name = "id_buyer")
    private Buyer buyer;

    @NotEmpty(message = "Sorry, empty")
    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @NotEmpty(message = "Sorry, empty")
    @CreationTimestamp
    @Column(name = "date_added_order")
    private LocalDateTime dateAddedOrder;
}
