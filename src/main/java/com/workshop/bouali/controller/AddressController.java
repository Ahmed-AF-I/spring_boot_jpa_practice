package com.workshop.bouali.controller;

import com.workshop.bouali.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;


    @GetMapping("/search")
    public ResponseEntity<?> find(
            @RequestParam(required = false) String street,
            @RequestParam(required = false) String houseNumber,
            @RequestParam(required = false) String zipCode

    ){
        if((street == null || street.isBlank())&&
        (houseNumber == null || houseNumber.isBlank())&&
        (zipCode == null || zipCode.isBlank())){

            return ResponseEntity
                    .badRequest()
                    .body("you need to specify at least one parameter.");
        }

        return ResponseEntity.ok(
                addressService.searchAddresses(street, houseNumber, zipCode)
        );
    }

}
