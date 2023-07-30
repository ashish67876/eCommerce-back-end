package com.example.inventoryservice.controllers;

import com.example.inventoryservice.dtos.IProductMapper;
import com.example.inventoryservice.dtos.ProductCreateDto;
import com.example.inventoryservice.dtos.ProductStockQuantityDto;
import com.example.inventoryservice.services.IProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = {"api/v1/products"})
public class ProductController {

    private final IProductService productService;
    private final IProductMapper productMapper;

    @PostMapping("/add")
    public ResponseEntity<Object> add(
            HttpServletRequest request,
            @RequestHeader("CorrelationID") String correlationId,
            @Valid @RequestBody ProductCreateDto productCreateDto) {

        log.info("{} - {} - {} - {} - {}", request.getMethod(), request.getRequestURI(), correlationId, null, productCreateDto);

        var product = productService.add(productCreateDto);

        var productDto = productMapper.toDto(product);

//        return ResponseEntity.created(URI.create("/" + productDto.id())).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneById(
            HttpServletRequest request,
            @RequestHeader("CorrelationID") String correlationId,
            @PathVariable Long id) {

        log.info("{} - {} - {} - {} - {}", request.getMethod(), request.getRequestURI(), correlationId, null, null);

        var product = productService.getOneBy(id);

        var productDto = productMapper.toDto(product);

        return ResponseEntity.ok(productDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(
            HttpServletRequest request,
            @RequestHeader("CorrelationID") String correlationId) {

        log.info("{} - {} - {} - {} - {}", request.getMethod(), request.getRequestURI(), correlationId, null, null);

        var products = productService.getAll();

        var productDtos = productMapper.toDtos(products);

        return ResponseEntity.ok(productDtos);
    }


    @PatchMapping("/{productId}/increase-stock")
    public ResponseEntity<Object> increaseStock(
            HttpServletRequest request,
            @RequestHeader("CorrelationID") String correlationId,
            @PathVariable final Long productId,
            @Valid @RequestBody ProductStockQuantityDto productStockQuantityDto) {

        log.info("{} - {} - {} - {} - {}", request.getMethod(), request.getRequestURI(), correlationId, null, productStockQuantityDto);

        productService.increaseStock(productId, productStockQuantityDto);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{productId}/decrease-stock")
    public ResponseEntity<Object> decreaseStock(
            HttpServletRequest request,
            @RequestHeader("CorrelationID") String correlationId,
            @PathVariable final Long productId,
            @Valid @RequestBody ProductStockQuantityDto productStockQuantityDto) {

        log.info("{} - {} - {} - {} - {}", request.getMethod(), request.getRequestURI(), correlationId, null, productStockQuantityDto);

        productService.decreaseStock(productId, productStockQuantityDto);

        return ResponseEntity.noContent().build();
    }
}