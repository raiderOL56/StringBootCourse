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
@RequestMapping("/customers")
public class CustomerController {
    private List<Customer> _customers = new ArrayList<>(Arrays.asList(
            new Customer(1, "Gerardo", "gerardo1", "1234"),
            new Customer(2, "Raul", "raul1", "1234")
    ));

    //@GetMapping
    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> GetCustomers(){
        return _customers;
    }

    @GetMapping("/{username}")
    public ResponseEntity<Customer> GetCustomerByUsername(@PathVariable String username){
        for (Customer customer : _customers){
            if(customer.getUsername().equals(username)) return ResponseEntity.ok(customer);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<List<Customer>> postCustomer(@RequestBody Customer customer){
        _customers.add(customer);

        return ResponseEntity.ok(_customers);
    }

    @PutMapping
    public ResponseEntity<List<Customer>> putCustomer(@RequestBody Customer customer){
        for (Customer c : _customers){
            if(c.getId() == customer.getId()){
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());

                return ResponseEntity.ok(_customers);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<List<Customer>> deleteCustomer(@RequestParam int id){
        for (Customer customer : _customers){
            if(customer.getId() == id){
                _customers.remove(customer);

                return ResponseEntity.ok(_customers);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping
    public ResponseEntity<List<Customer>> patchCustomer(@RequestBody Customer customer){
        for (Customer c : _customers){
            if (c.getId() == customer.getId()){
                if (customer.getName() != null) c.setName(customer.getName());
                if (customer.getUsername() != null) c.setUsername(customer.getUsername());
                if (customer.getPassword() != null) c.setPassword(customer.getPassword());

                return ResponseEntity.ok(_customers);
            }
        }

        return ResponseEntity.notFound().build();
    }
}
