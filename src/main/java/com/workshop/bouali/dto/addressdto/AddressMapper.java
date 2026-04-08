package com.workshop.bouali.dto.addressdto;

import com.workshop.bouali.models.Address;

public class AddressMapper {

    private AddressMapper() {}

    public static AddressResponseDTO addressToResponceDTO(Address address) {
        return AddressResponseDTO.builder()
                .streetName(address.getStreetName())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .build();
    }
}
