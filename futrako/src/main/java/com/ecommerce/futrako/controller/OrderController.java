package com.ecommerce.futrako.controller;


import com.ecommerce.futrako.dto.OrderDto;
import com.ecommerce.futrako.dto.OrderPatchDto;
import com.ecommerce.futrako.service.interfaces.IItemService;
import com.ecommerce.futrako.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IItemService iItemService;

    @GetMapping("/")
    public ResponseEntity<?> getOrders() {
        return iOrderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        return iOrderService.getOrder(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchOrder(@PathVariable Long id, @RequestBody OrderPatchDto order) {
        return iOrderService.patchOrder(id, order);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<?> getItemsByOrderId(@PathVariable Long id) {
        return iItemService.getItemsByOrder(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> postOrder(@RequestBody OrderDto order) {
        return iOrderService.postOrder(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        return iOrderService.deleteOrder(id);
    }
}