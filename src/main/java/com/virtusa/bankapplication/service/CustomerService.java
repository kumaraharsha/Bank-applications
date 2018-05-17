package com.virtusa.bankapplication.service;

import com.virtusa.bankapplication.model.Customer;
import com.virtusa.bankapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer 123456789");
        headers.set("X-Request-ID", "68768768768");

        Map requestData = new HashMap();
        requestData.put("firstName", customer.getFirstName());
        requestData.put("lastName", customer.getLastName());
        requestData.put("ssn", customer.getSsn());
        HttpEntity<Map> request = new HttpEntity<Map>(requestData, headers);

        Map<String, String> map = restTemplate.postForObject("http://localhost:8888/credit-union-application/V1/financial-eligibility",
                request, Map.class);
        customer.setId(customer.getZipCode() + customer.getSsn().substring(customer.getSsn().length() - 4));
        customerRepository.add(customer);
        return customer;
    }

    public Customer findById(String id) {
        return customerRepository.findbyId(id);
    }


}
