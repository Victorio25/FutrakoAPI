package com.ecommerce.futrako.service.interfaces;

import com.ecommerce.futrako.dto.OrderDto;
import com.ecommerce.futrako.dto.OrderPatchDto;
import org.springframework.http.ResponseEntity;

public interface IOrderService {

    ResponseEntity<?> getOrder(Long id);

    ResponseEntity<?> getOrdersByUser(Long id);

    ResponseEntity<?> getAllOrders();

    ResponseEntity<?> postOrder(OrderDto orderDto);

    ResponseEntity<?> patchOrder(Long id, OrderPatchDto order);

    ResponseEntity<?> deleteOrder(Long id);
}
