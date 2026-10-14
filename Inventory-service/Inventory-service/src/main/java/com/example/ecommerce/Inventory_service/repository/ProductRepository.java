package com.example.ecommerce.Inventory_service.repository;

import com.example.ecommerce.Inventory_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long >{


}
