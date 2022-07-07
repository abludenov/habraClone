package com.javamentor.backend.service;

import com.javamentor.backend.model.Address;

import java.util.List;

public interface AddressService {

    Address getAddressById(Long id);

    List<Address> getAllAddress();

    void saveOrUpdateAddress(Address address);

    void deleteAddress(Long id);

}
