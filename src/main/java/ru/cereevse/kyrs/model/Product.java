package ru.cereevse.kyrs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="product")
@Table(name="product")
public class Product {

    @Id
    @Column(name="id_product")
    @GeneratedValue(generator = "id_prod_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id_prod_seq", sequenceName = "id_prod_seq", initialValue = 1, allocationSize = 1)
    private Long idProduct;

    @NotEmpty(message = "Sorry, empty")
    @Column(name="product_name")
    private String productName;

    @NotEmpty(message = "Sorry, empty")
    @Column(name="product_available_in_stock")
    private Long countAvailable;

    @NotEmpty(message = "Sorry, empty")
    @Column(name="price")
    private Long price;

    @Column(name="discount")
    private Long discount;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Order> orders;

}
