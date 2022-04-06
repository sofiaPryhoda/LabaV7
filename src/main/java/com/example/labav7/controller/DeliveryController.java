package com.example.labav7.controller;

import com.example.labav7.crud.DeliveryRepository;
import com.example.labav7.entity.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DeliveryController {
    @Autowired
    DeliveryRepository deliveryRepository;

    @GetMapping("/deliveries")
    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }

    @GetMapping("/deliveries/{id}")
    public ResponseEntity<Delivery> getById(@PathVariable("id") long id) {
        Optional<Delivery> deliveryData = deliveryRepository.findById(id);
        if (deliveryData.isPresent()) {
            return new ResponseEntity<>(deliveryData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/deliveries")
    public ResponseEntity<Delivery> create(@RequestBody Delivery delivery) {
        try {
            deliveryRepository.save(delivery);
            return new ResponseEntity<>(delivery, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deliveries/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
        try {
            deliveryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deliveries")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            deliveryRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
