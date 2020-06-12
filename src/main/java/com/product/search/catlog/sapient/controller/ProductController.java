package com.product.search.catlog.sapient.controller;

import com.product.search.catlog.sapient.model.Product;
import com.product.search.catlog.sapient.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> findAll() {
		List<Product> products = productService.findAll();
		return new ResponseEntity<List<Product>>(products, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
		Product product = productService.getProductById(id);
		return new ResponseEntity<Product>(product,new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/product")
	public ResponseEntity<Product> getProductBySku(@RequestParam("sku") String sku) {
		Product product = productService.getProductBySku(sku);
		return new ResponseEntity<Product>(product,new HttpHeaders(), HttpStatus.OK);
	}

}