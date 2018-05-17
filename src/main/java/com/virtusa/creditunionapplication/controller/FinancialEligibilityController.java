package com.virtusa.creditunionapplication.controller;

import com.virtusa.creditunionapplication.model.FinancialEligibilityRequest;
import com.virtusa.creditunionapplication.model.FinancialEligibilityResponse;
import com.virtusa.creditunionapplication.model.Person;
import com.virtusa.creditunionapplication.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class FinancialEligibilityController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping(value = "/financial-eligibility", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity checkEligibility(@RequestBody FinancialEligibilityRequest financialEligibilityRequest) {
        if (!personRepository.isEligible(new Person(financialEligibilityRequest.getFirstName(),
                financialEligibilityRequest.getLastName(), financialEligibilityRequest.getSsn()))) {
            HashMap<Object, Object> object = new HashMap<>();
            object.put("error", "DECLINED");
            return new ResponseEntity(object, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(new FinancialEligibilityResponse(financialEligibilityRequest.getFirstName(),
                financialEligibilityRequest.getLastName(), "APPROVED"), HttpStatus.OK);
    }
}
