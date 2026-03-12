package com.corporativoX.cursoSpringBoot.service;

import com.corporativoX.cursoSpringBoot.models.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// @Service("listResourceService") // Esto convierte a la clase en un bean de servicio. Es decir, Spring gestionará automáticamente la creación de instancias de esta clase
@Lazy
@Service
@ConditionalOnProperty(name = "service.products", havingValue = "listResourceService")
public class ProductsServiceImpl implements ProductsService {
    public ProductsServiceImpl(){
        System.out.println("Instancia de la clase ProductsServiceImpl");
    }

    List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "producto1", 38.5, 20),
            new Product(2, "producto2", 21.90, 10)
    ));

    @Override
    public List<Product> getProducts(){
        return products;
    }
}