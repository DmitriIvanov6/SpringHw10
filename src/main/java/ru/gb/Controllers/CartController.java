package ru.gb.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.Domains.Cart;
import ru.gb.Domains.Customer;
import ru.gb.Domains.Product;
import ru.gb.Repositories.CartRepository;
import ru.gb.Repositories.CustomerRepository;
import ru.gb.Repositories.ProductRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts/")
public class CartController {

    private CartRepository repository;
    private ProductRepository pR;
    private CustomerRepository custR;


    public CartController(CartRepository repository, ProductRepository pR, CustomerRepository custR) {
        this.repository = repository;
        this.custR = custR;
        this.pR = pR;
    }


    @GetMapping
    public ResponseEntity<List<Cart>> findAll() {
        List<Cart> carts = new ArrayList<>();
        repository.findAll().forEach(carts::add);
        return ResponseEntity.ok(carts);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cart> findById(@PathVariable long id) {
        Optional<Cart> cart = repository.findById(id);
        if (cart.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart.get());
    }

    @PostMapping
    public ResponseEntity<Cart> save(@RequestBody Cart cart) {
        Customer customer = cart.getCustomer();
        List<Product> products = cart.getProducts();
        if (custR.findById(customer.getId()).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        for (Product p : products) {
            if (pR.findById(p.getId()).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }
        Cart newCart = repository.save(cart);
        return ResponseEntity.created(URI.create("/carts/" + newCart.getId())).body(newCart);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cart> deleteById(@PathVariable long id) {
        Optional<Cart> cart = repository.findById(id);
        if (cart.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
