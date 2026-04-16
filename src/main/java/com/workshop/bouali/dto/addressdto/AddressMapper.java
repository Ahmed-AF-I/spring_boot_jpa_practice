package com.workshop.bouali.dto.addressdto;

import com.workshop.bouali.models.Address;

public class AddressMapper {

    private AddressMapper() {}

    public static AddressResponseDTO toDTO(Address address) {
        return AddressResponseDTO.builder()
                .id(address.getId())
                .streetName(address.getStreetName())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .build();
    }

    public static Address toEntity(AddressRequestDTO response) {
        if(response == null) return null;

        Address address = new Address();
        address.setStreetName(response.streetName());
        address.setHouseNumber(response.houseNumber());
        address.setZipCode(response.zipCode());
        return address;
    }
}
