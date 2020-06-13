package com.product.search.catlog.sapient.service;

import com.product.search.catlog.sapient.SapientApplication;
import com.product.search.catlog.sapient.exception.ProductCatalogException;
import com.product.search.catlog.sapient.model.Product;
import com.product.search.catlog.sapient.model.ProductsCountByBrand;
import com.product.search.catlog.sapient.repository.ProductRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.groupingBy;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Cacheable(value = SapientApplication.PRODUCT_CATLOG, key = "#sku")
    public Product getProductBySku(String sku) {
        return productRepository.findBySku(sku).orElseThrow(() -> new ProductCatalogException("Product not found for SKU " + sku));
    }

    @Cacheable(value = SapientApplication.PRODUCT_CATLOG, key = "#id")
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductCatalogException("Product not found for ID " + id));
    }

    @Cacheable(value = SapientApplication.PRODUCT_CATLOG, key = " 'products' ")
    public List<Product> findAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Cacheable(value = SapientApplication.PRODUCT_CATLOG, key = " 'getProductByBrand' ")
    public List<ProductsCountByBrand> getProductByBrand() {
        return StreamSupport.stream(productRepository.findByBrands().spliterator(), false).collect(Collectors.toList());
    }

    @Cacheable(value = SapientApplication.PRODUCT_CATLOG, key = " 'getProductByPrice' ")
    public Map<Double, List<Product>> getProductByPrice() {
        return findAll().stream().collect(groupingBy(Product::getPrice));
    }
}
