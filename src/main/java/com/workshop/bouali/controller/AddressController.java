package com.workshop.bouali.controller;

import com.workshop.bouali.dto.addressdto.AddressRequestDTO;
import com.workshop.bouali.dto.addressdto.AddressResponseDTO;
import com.workshop.bouali.services.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody AddressRequestDTO request
    ){
        return ResponseEntity.ok(
                addressService.updateAddress(id, request));
    }

    public ResponseEntity<AddressResponseDTO> create(
            @Valid @RequestBody AddressRequestDTO request
    ){
        return new ResponseEntity<>(addressService.createAddress(request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> findById(
            @PathVariable Integer id
    ){
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

}
