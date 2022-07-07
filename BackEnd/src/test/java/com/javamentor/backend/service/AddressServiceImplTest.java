package com.javamentor.backend.service;

import com.javamentor.backend.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AddressServiceImplTest {

    @Autowired
    private AddressService addressService;

    @Test
    void getAllAddressTest() {
        List <Address> addressList =  addressService.getAllAddress();
        assertEquals(3, addressList.size());
    }

    @Test
    void negativeGetAllAddressTest() {
        List <Address> addressList =  addressService.getAllAddress();
        assertNotEquals(0, addressList.size());
    }

    @Test
    void getAddressByIdTest(){
        Address address = addressService.getAddressById(2L);
        assertEquals(2, address.getId());
    }

    @Test
    void negativeGetAddressByIdTest(){
        addressService.getAddressById(150L);
        assertThrows(NullPointerException.class, ()->{
            throw new NullPointerException();
        });
    }



    @Test
    void addNewAddressTest(){
        List <Address> addressListBefore =  addressService.getAllAddress();
        Address address = new Address();
        address.setCountry("Country");
        address.setRegion("Region");
        address.setCity("City");
        addressService.saveOrUpdateAddress(address);
        List <Address> addressListAfter =  addressService.getAllAddress();

        assertEquals(addressListBefore.size()+1, addressListAfter.size());

    }
    @Test
    void updateAddressTest(){
        Address address = addressService.getAddressById(2L);
        address.setCountry("Japan");
        address.setRegion("Tokyo");
        address.setCity("Tokyo");
        addressService.saveOrUpdateAddress(address);
        assertEquals(address, addressService.getAddressById(2L));
    }

    @Test
    void deleteAddressTest(){
        List <Address> addressListBefore =  addressService.getAllAddress();
        addressService.deleteAddress(2L);
        List <Address> addressListAfter =  addressService.getAllAddress();
        assertEquals(addressListBefore.size()-1, addressListAfter.size());
    }
}