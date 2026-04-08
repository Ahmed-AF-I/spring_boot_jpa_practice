package com.workshop.bouali.services;

import com.workshop.bouali.dto.addressdto.AddressMapper;
import com.workshop.bouali.dto.addressdto.AddressResponseDTO;
import com.workshop.bouali.models.Address;
import com.workshop.bouali.repositories.addressrepo.AddressRepository;
import com.workshop.bouali.specification.AddressSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<AddressResponseDTO> searchAddresses(
            String street,
            String houseNumber,
            String zipCode
    ){
        Specification<Address> spec = Specification
                .where(AddressSpecifications.hasStreet(street))
                .and(AddressSpecifications.hasHouseNumber(houseNumber))
                .and(AddressSpecifications.hasZipCode(zipCode));

        List<Address> addresses = addressRepository.findAll(spec);

        return addresses.stream()
                .map(AddressMapper::addressToResponceDTO)
                .toList();
    }
}
