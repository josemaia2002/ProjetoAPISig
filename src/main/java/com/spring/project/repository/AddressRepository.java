package com.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.project.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
