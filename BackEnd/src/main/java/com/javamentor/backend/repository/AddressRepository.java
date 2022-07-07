package com.javamentor.backend.repository;

import com.javamentor.backend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address getAddressById (Long id);
}