package ru.gb.Domains;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "customer")
    private List<Cart> carts;

    public Customer() {
    }

    public Customer(String name, List<Cart> carts) {
        this.name = name;
        this.carts = carts;

    }

    public Customer(long id, String name, List<Cart> carts) {
        this.id = id;
        this.name = name;
        this.carts = carts;

    }

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}



