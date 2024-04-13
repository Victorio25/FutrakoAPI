package com.ecommerce.futrako.model;

import com.ecommerce.futrako.listing.RoleName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated
    @Column(name = "name")
    private RoleName name;

    @Column(name = "description")
    private String description;


}
