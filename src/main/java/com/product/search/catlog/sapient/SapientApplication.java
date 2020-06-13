package com.product.search.catlog.sapient;

import com.product.search.catlog.sapient.model.Product;
import com.product.search.catlog.sapient.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;
import java.util.function.Supplier;

import static java.util.Arrays.asList;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class SapientApplication {

    public static final String PRODUCT_CATLOG = "PRODUCT_CATLOG";

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

    @CacheEvict(allEntries = true, value = {PRODUCT_CATLOG})
    @Scheduled(fixedDelay = 2 * 60 * 60 * 1000 ,  initialDelay = 500)
    public void reportCacheEvict() {
        System.out.println("Flush Cache ");
    }


    @Bean
    CommandLineRunner runner(ProductRepository productRepository){
        List<Product> products = new
                ArrayList<>();
        for(int i = 1; i < 20; i++){
            products.add(Product.builder()
                    .brand(i/2 == 0 ? "Zodiac" : "Vimal")
                    .color( i/2 == 0 ? "White" : "Black")
                    .price(2000)
                    .productId(i)
                    .productName(i/2 == 0 ? "Zodiac" : "Vimal" + (i/2 == 0 ? "Business" : "Formal") +" Shirts")
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
