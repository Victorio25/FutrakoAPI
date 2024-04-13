package com.ecommerce.futrako.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items")
@Entity
@Getter
@Setter
@Builder
public class Item {

    @EmbeddedId
    private ItemId id;

    @Column(name = "amount")
    private Long amount;

    @Embeddable
    @Getter
    @Setter
    public static class ItemId implements Serializable {
        @Column(name = "order_id")
        private Long orderId;

        @Column(name = "product_id")
        private Long productId;


    }

}
