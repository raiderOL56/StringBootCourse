package com.corporativoX.cursoSpringBoot.service;

import com.corporativoX.cursoSpringBoot.models.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

// @Primary // si no se especifica algún Qualifer, este será el que se va a ejecutar por defecto. Es el que tiene la prioridad
/// @Service("jsonResourceService")
@Service
@ConditionalOnProperty(name = "service.products", havingValue = "jsonResourceService")
public class ProductsServiceJSONImpl implements ProductsService{
    public ProductsServiceJSONImpl(){
        System.out.println("Instancia de la clase ProductsServiceJSONImpl");
    }

    @Override
    public List<Product> getProducts() {
        try {
            return new ObjectMapper().readValue(this.getClass().getResourceAsStream("/Products.json"), new TypeReference<List<Product>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
