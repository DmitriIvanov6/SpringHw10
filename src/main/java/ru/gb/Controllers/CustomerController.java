package ru.gb.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.Domains.Customer;
import ru.gb.Repositories.CustomerRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers/")
public class CustomerController {
    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        List<Customer> customers = new ArrayList<>();
        repository.findAll().forEach(customers::add);
        return ResponseEntity.ok(customers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> findById(@PathVariable long id) {
        Optional<Customer> customer = repository.findById(id);
        if (customer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Customer> deleteById(@PathVariable long id) {
        Optional<Customer> customer = repository.findById(id);
        if (customer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        Customer customerNew = repository.save(customer);
        return ResponseEntity.created(URI.create("/customers/" + customerNew.getId())).body(customerNew);
    }
}
