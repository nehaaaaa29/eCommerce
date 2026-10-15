package com.example.ecommerce.Inventory_service.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name ="order-service" )
public class OrderClient {
}
