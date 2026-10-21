package com.example.ecommerce.order_service.controller;

import com.example.ecommerce.order_service.Clients.InventoryOpenFeignClient;
import com.example.ecommerce.order_service.config.FeaturesEnableConfig;
import com.example.ecommerce.order_service.dto.OrderRequestDto;
import com.example.ecommerce.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
@Slf4j
@RefreshScope
public class OrdersController {
    private final OrderService ordersService;

    @Value("${my.variable}")
    private String myVariable;

    private final FeaturesEnableConfig featuresEnableConfig;

    @GetMapping("/helloOrders")
    public String helloOrders() {

        if (featuresEnableConfig.isUserTrackingEnable()) {
            return "User tracking enabled wohooo, my variable is : " + myVariable;
        }
        else {
            return "User tracking disable awww, my variable is : " + myVariable;


        }

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
