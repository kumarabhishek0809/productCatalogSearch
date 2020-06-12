package com.product.search.catlog.sapient;

import com.product.search.catlog.sapient.model.Product;
import com.product.search.catlog.sapient.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static java.util.Arrays.asList;

@SpringBootApplication
@EnableCaching
public class SapientApplication {

    public static final String PRODUCT_CATLOG = "productCatlog";

    public static void main(String[] args) {
        SpringApplication.run(SapientApplication.class, args);
    }

    @Bean
    public CacheManager cacheManager(){
        Cache productCatlog = new ConcurrentMapCache(PRODUCT_CATLOG);
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(asList(productCatlog));

        return manager;
    }


    @Bean
    CommandLineRunner runner(ProductRepository productRepository){
        List<Product> products = new ArrayList<>();
        for(int i = 1; i < 20; i++){
            products.add(Product.builder()
                    .brand("Zodiac")
                    .color( i/2 == 0 ? "White" : "Black")
                    .price(2000)
                    .productId(i)
                    .productName("Zodiac "+ (i/2 == 0 ? "Business" : "Formal") +" Shirts")
                    .size(i/2 == 0 ? 40 : 42)
                    .sku(""+i)
                    .build());
        }
        return args -> {
          productRepository.saveAll(
                  products
          );
      };
    }

}
