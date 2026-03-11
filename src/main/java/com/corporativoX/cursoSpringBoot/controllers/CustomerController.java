package com.corporativoX.cursoSpringBoot.controllers;

import com.corporativoX.cursoSpringBoot.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CustomerController {
    private List<Customer> _customers = new ArrayList<>(Arrays.asList(
            new Customer(1, "Gerardo", "gerardo1", "1234"),
            new Customer(2, "Raul", "raul1", "1234")
    ));

    @GetMapping("/getCustomers")
    public List<Customer> GetCustomers(){
        return _customers;
    }

    @GetMapping("/getCustomers/{username}")
    public ResponseEntity<Customer> GetCustomerByUsername(@PathVariable String username){
        for (Customer customer : _customers){
            if(customer.getUsername().equals(username)) return ResponseEntity.ok(customer);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/postCustomer")
    public Customer postCustomer(@RequestBody Customer customer){
        _customers.add(customer);

        return customer;
    }
}
