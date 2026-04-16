package com.workshop.bouali.dto.addressdto;

import jakarta.validation.constraints.NotBlank;

public record AddressRequestDTO(
        @NotBlank(message = "Street name is required") String streetName,
        @NotBlank(message = "House number is required") String houseNumber,
        @NotBlank(message = "Zip code is required") String zipCode
) {}
