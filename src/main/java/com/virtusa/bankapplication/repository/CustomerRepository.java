package com.virtusa.bankapplication.repository;

import com.virtusa.bankapplication.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {

    private List<Customer> customers = new ArrayList<>();

    public void add(Customer customer) {
        customers.add(customer);
    }

    public Customer findbyId(String id) {
        System.out.println(customers);
        System.out.println(id);
        return customers.stream().filter(customer -> customer.getId().equals(id)).findAny().orElseThrow(() ->
                new RuntimeException("Customer not found"));
    }
}
