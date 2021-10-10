package ru.gb.Repositories;

import org.springframework.data.repository.CrudRepository;
import ru.gb.Domains.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
