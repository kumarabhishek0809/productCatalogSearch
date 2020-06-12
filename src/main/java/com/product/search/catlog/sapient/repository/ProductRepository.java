package com.product.search.catlog.sapient.repository;

import com.product.search.catlog.sapient.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
import java.util.stream.Stream;

@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {

    Stream<Product> findByProductName(@Param("productName") String productName);

    Optional<Product> findBySku(String sku);
}
