package com.ecommerce.futrako.repository;

import com.ecommerce.futrako.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderStatusRepository extends JpaRepository<OrderStatus, Long> {

}
