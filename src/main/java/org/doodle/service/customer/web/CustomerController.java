package org.doodle.service.customer.web;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.doodle.service.customer.entity.Customer;
import org.doodle.service.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository repository;


    @PostMapping("/customers")
    public ResponseEntity<Void> create(@RequestBody @NonNull Customer customer) throws URISyntaxException {
        log.info("create called");
        log.info("Creating customer: {}",customer);
        Customer saved = repository.save(customer);
        log.info("Created");
        return ResponseEntity.created(new URI("/customers/" + saved.getId())).build();
    }

    @GetMapping("/customers")
    public ResponseEntity<Iterable> findAll() {
        log.info("findAll called");
        List<Customer> customers = repository.findAll();
        log.info("Returning customers: {}",customers);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Integer id) {
        log.info("findById called");
        Customer customer = repository.findById(id).orElse(null);
        log.info("Found customer: {}",customer);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customer);
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        log.info("deleteById called");
        if (repository.existsById(id)) {
            repository.deleteById(id);
            log.info("Deleted customer with Id: {}",id);
            return ResponseEntity.noContent().build();
        } else {
            log.info("Not found customer with Id: {}",id);
            return ResponseEntity.notFound().build();
        }
    }
}
