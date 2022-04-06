package com.example.labav7.controller;

import com.example.labav7.crud.ProviderRepository;
import com.example.labav7.entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProviderController {
    @Autowired
    ProviderRepository providerRepository;

    @GetMapping("/providers")
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    @GetMapping("/providers/{id}")
    public ResponseEntity<Provider> getById(@PathVariable("id") long id) {
        Optional<Provider> providerData = providerRepository.findById(id);
        if (providerData.isPresent()) {
            return new ResponseEntity<>(providerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/providers")
    public ResponseEntity<Provider> create(@RequestBody Provider provider) {
        try {
            providerRepository.save(provider);
            return new ResponseEntity<>(provider, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/providers/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
        try {
            providerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/providers")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            providerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
