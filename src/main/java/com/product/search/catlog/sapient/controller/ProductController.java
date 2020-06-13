package com.product.search.catlog.sapient.controller;

import com.product.search.catlog.sapient.model.Product;
import com.product.search.catlog.sapient.model.ProductsCountByBrand;
import com.product.search.catlog.sapient.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<Product> getProductBySku(@RequestParam("sku") String sku) {
        Product product = productService.getProductBySku(sku);
        return new ResponseEntity<>(product, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/products/brand")
    public ResponseEntity<List<ProductsCountByBrand>> getProductByBrand() {
        List<ProductsCountByBrand> products = productService.getProductByBrand();
        return new ResponseEntity<>(products, new HttpHeaders(), HttpStatus.OK);
    }

}