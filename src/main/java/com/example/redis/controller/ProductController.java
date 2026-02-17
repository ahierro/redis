package com.example.redis.controller;

import com.example.redis.entity.Product;
import com.example.redis.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Product CRUD operations")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<Product> create(@RequestBody ProductRequest request) {
        Product product = new Product(UUID.randomUUID(), request.name());
        Product saved = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public ResponseEntity<Product> getById(@PathVariable UUID id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "List all products")
    public Iterable<Product> listAll() {
        return productRepository.findAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product by ID")
    public ResponseEntity<Product> update(@PathVariable UUID id, @RequestBody ProductRequest request) {
        return productRepository.findById(id)
                .map(existing -> {
                    existing.setName(request.name());
                    return ResponseEntity.ok(productRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product by ID")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public record ProductRequest(String name) {
    }
}
