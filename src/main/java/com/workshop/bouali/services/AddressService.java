package com.workshop.bouali.services;

import com.workshop.bouali.dto.addressdto.AddressMapper;
import com.workshop.bouali.dto.addressdto.AddressRequestDTO;
import com.workshop.bouali.dto.addressdto.AddressResponseDTO;
import com.workshop.bouali.models.Address;
import com.workshop.bouali.repositories.addressrepo.AddressRepository;
import com.workshop.bouali.specification.AddressSpecifications;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .map(AddressMapper::toDTO)
                .toList();
    }

    @Transactional
    public AddressResponseDTO updateAddress(
            Integer id,
            AddressRequestDTO request
    ){
        Address existingAddress = addressRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Address with id " + id + " not found"));
        existingAddress.setStreetName(request.streetName());
        existingAddress.setHouseNumber(request.houseNumber());
        existingAddress.setZipCode(request.zipCode());

        Address updatedAddress = addressRepository.save(existingAddress);
        return AddressMapper.toDTO(updatedAddress);
    }

    @Transactional
    public AddressResponseDTO createAddress(
            AddressRequestDTO request
    ){
        Address address = AddressMapper.toEntity(request);
        return AddressMapper.toDTO(addressRepository.save(address));
    }

    @Transactional
    public void deleteAddress(Integer id){
        if (!addressRepository.existsById(id)){
            throw new EntityNotFoundException("Address with id " + id + " not found");
        }
        addressRepository.deleteById(id);
    }

    public AddressResponseDTO getAddressById(Integer id){
        return addressRepository.findById(id)
                .map(AddressMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Address with id " + id + " not found"));
    }
}
