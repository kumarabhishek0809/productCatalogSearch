package com.product.search.catlog.sapient.cache;

import com.product.search.catlog.sapient.SapientApplication;
import com.product.search.catlog.sapient.model.Product;
import org.springframework.cache.CacheManager;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class ReadingEventHandler {

    public final CacheManager cacheManager;

    public ReadingEventHandler(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    @HandleAfterCreate
    @HandleAfterDelete
    @HandleAfterSave
    public void evictCache(Product product){
        int productId = product.getProductId();
        cacheManager.getCache(SapientApplication.PRODUCT_CATLOG).evict(productId);
    }
}
