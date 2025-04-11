package com.spring.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.model.Address;
import com.spring.project.service.AddressService;

@RestController
@RequestMapping("/api/adresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    public List<Address> findAllAddresses() {
        return addressService.findAllAddresses();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Address findAddressById(Long id) {
        return addressService.findAddressById(id);
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public void createAdress(Address address) {
        addressService.createAddress(address);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}")
    public void updateAddress(Address address, Long id) {
        addressService.updateAddress(address, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteAddress(Long id) {
        addressService.deleteAddress(id);
    }
}