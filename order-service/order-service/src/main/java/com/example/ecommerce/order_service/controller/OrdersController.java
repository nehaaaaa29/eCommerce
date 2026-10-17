package com.example.ecommerce.order_service.controller;

import com.example.ecommerce.order_service.Clients.InventoryOpenFeignClient;
import com.example.ecommerce.order_service.dto.OrderRequestDto;
import com.example.ecommerce.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
@Slf4j
public class OrdersController {
    private final OrderService ordersService;


    @GetMapping("/helloOrders")
    public String helloOrders(@RequestHeader("X-User-Id") Long userId){
        return "Hello from, Orders Service,user id is : " +userId;
    }

    @PostMapping("/create-order")
    public ResponseEntity<OrderRequestDto>createOrder(@RequestBody OrderRequestDto orderRequestDto){
     OrderRequestDto orderRequestDto1 =ordersService.createOrder(orderRequestDto);
     return ResponseEntity.ok(orderRequestDto1);
    }

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders() {
        log.info("REST request to get all orders");
        return ResponseEntity.ok(ordersService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id) {
        log.info("REST request to get order by id: {}", id);
        return ResponseEntity.ok(ordersService.getOrderById(id));
    }

}
