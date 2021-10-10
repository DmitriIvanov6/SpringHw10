package ru.gb.Repositories;

import org.springframework.data.repository.CrudRepository;
import ru.gb.Domains.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {

}
