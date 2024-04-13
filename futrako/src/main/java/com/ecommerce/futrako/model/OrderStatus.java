package com.ecommerce.futrako.model;


import com.ecommerce.futrako.listing.OrderStatusName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private OrderStatusName name;

    @Column(name = "description")
    private String description;

}
