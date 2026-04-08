package com.workshop.bouali.dto.addressdto;

import lombok.Builder;

@Builder
public record AddressResponseDTO(
        String streetName,
        String houseNumber,
        String zipCode
) {}
