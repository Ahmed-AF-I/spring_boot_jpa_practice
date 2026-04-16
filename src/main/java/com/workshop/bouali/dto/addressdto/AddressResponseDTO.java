package com.workshop.bouali.dto.addressdto;

import lombok.Builder;

@Builder
public record AddressResponseDTO(
        Integer id,
        String streetName,
        String houseNumber,
        String zipCode
) {}
