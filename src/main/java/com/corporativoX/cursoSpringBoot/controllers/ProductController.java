package com.corporativoX.cursoSpringBoot.controllers;

import com.corporativoX.cursoSpringBoot.configurations.AppConfiguration;
import com.corporativoX.cursoSpringBoot.models.Product;
import com.corporativoX.cursoSpringBoot.service.ProductsService;
import com.corporativoX.cursoSpringBoot.service.ProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    @Lazy
    // @Qualifier("listResourceService")
    private ProductsService productsService;

    @Autowired
    private AppConfiguration appConfiguration;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        System.out.println(appConfiguration.toString());
        return ResponseEntity.ok(productsService.getProducts());
     }
}
