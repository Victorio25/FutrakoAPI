package com.ecommerce.futrako.repository;


import com.ecommerce.futrako.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_id(Long id);
}
