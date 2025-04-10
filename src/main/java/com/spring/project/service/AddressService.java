package com.spring.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.model.Address;
import com.spring.project.model.AddressNotFoundException;
import com.spring.project.repository.AddressRepository;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAllAddresses() {
        return addressRepository.findAll();
    }

    public Address findAddressById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if(address.isEmpty()) {
            throw new AddressNotFoundException();
        }

        return address.get();
    }

    public void createAddress(Address address) {
        addressRepository.save(address);
    }

    public void updateAddress(Address address, Long id) {
        Address updatedAddress = findAddressById(id);

        updatedAddress.setStreet(address.getStreet());
        updatedAddress.setHouseNumber(address.getHouseNumber());
        updatedAddress.setZipCode(address.getZipCode());

        addressRepository.save(updatedAddress);
    }

    public void deleteAddress(Long id) {
        Address address = findAddressById(id);

        addressRepository.delete(address);
    }
}