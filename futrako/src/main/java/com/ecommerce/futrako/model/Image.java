package com.ecommerce.futrako.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dataBase64",columnDefinition = "longtext")
    private String dataBase64;

    @Column(name = "name")
    private String name;

    @Column(name = "isMain")
    private boolean isMain;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
