package com.ecommerce.futrako.service;

import com.ecommerce.futrako.dto.ItemDto;
import com.ecommerce.futrako.dto.OrderDto;
import com.ecommerce.futrako.dto.OrderPatchDto;
import com.ecommerce.futrako.dto.ResponseOrderDto;
import com.ecommerce.futrako.exception.ResourceNotFoundException;
import com.ecommerce.futrako.model.*;
import com.ecommerce.futrako.repository.*;
import com.ecommerce.futrako.service.interfaces.IOrderService;
import com.ecommerce.futrako.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository iOrderRepository;

    @Autowired
    private IOrderStatusRepository iOrderStatusRepository;

    @Autowired
    private IProductRepository iProductRepository;

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IItemRepository iItemRepository;

    @Autowired
    private Mapper mapper;

    public ResponseEntity<?> getOrder(Long id) {
        Order order = iOrderRepository.findById(id).orElseThrow();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.getMapper().map(order, ResponseOrderDto.class));
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(order);
    }


    @Override
    public ResponseEntity<?> getOrdersByUser(Long id) {
        List<Order> orders = iOrderRepository.findByUser_id(id);
        List<ResponseOrderDto> ordersDto = orders.stream().map(order -> mapper.getMapper().map(order, ResponseOrderDto.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ordersDto);
    }


    @Override
    public ResponseEntity<?> getAllOrders() {
        List<Order> orders = iOrderRepository.findAll();
        List<ResponseOrderDto> ordersDto = orders.stream().map(order -> mapper.getMapper().map(order, ResponseOrderDto.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ordersDto);
    }


    @Override
    public ResponseEntity<?> postOrder(OrderDto orderDto) {
        Order order = mapper.getMapper().map(orderDto, Order.class);
        OrderStatus orderStatus = iOrderStatusRepository.findById(order.getOrderStatus().getId()).orElseThrow(() -> new ResourceNotFoundException("State not found"));
        order.setOrderStatus(orderStatus);
        Product product;
        try {
            for (ItemDto itemDto : orderDto.getItems()) {
                product = iProductRepository.findById(itemDto.getProduct().getId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
                product.setStock(product.getStock() - itemDto.getAmount());
                order.getProducts().add(product);
                product.getOrders().add(order);
                Item item = new Item();
                Item.ItemId itemId = new Item.ItemId();
                item.setId(itemId);
                item.setAmount(itemDto.getAmount());
                order = iOrderRepository.save(order);
                itemId.setOrderId(order.getId());
                itemId.setProductId(product.getId());
                iItemRepository.save(item);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.getMapper().map(order, ResponseOrderDto.class));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> patchOrder(Long id, OrderPatchDto order) {
        Order orderToUpdate = iOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        OrderStatus state = iOrderStatusRepository.findById(order.getOrderStatus().getId()).orElseThrow(() -> new ResourceNotFoundException("State not found"));
        orderToUpdate.setOrderStatus(state);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.getMapper().map(iOrderRepository.save(orderToUpdate), ResponseOrderDto.class));
    }

    @Override
    public ResponseEntity<?> deleteOrder(Long id) {
        Order orderToDelete = iOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        iOrderRepository.delete(orderToDelete);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Order deleted");
    }

}