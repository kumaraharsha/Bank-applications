package com.virtusa.bankapplication;

import com.virtusa.bankapplication.model.Customer;
import com.virtusa.bankapplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.addCustomer(customer);
        HashMap<Object, Object> object = new HashMap<>();
        object.put("id", newCustomer.getId());
        return new ResponseEntity(object, HttpStatus.CREATED);
    }

    @GetMapping("/customers/{id}")
    public Customer findCustomer(@PathVariable("id") String id) {
        return customerService.findById(id);
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map<Object, Object> exceptionHandler(Exception e){
        e.printStackTrace();
        HashMap<Object, Object> object = new HashMap<>();
        object.put("error",  e.getMessage());
        return object;
    }
}
