package com.workshop.bouali.specification;

import com.workshop.bouali.models.Address;
import org.springframework.data.jpa.domain.Specification;

public class AddressSpecifications {

    public static Specification<Address> hasHouseNumber(String houseNumber) {
        return (root, _, cb) ->
                (houseNumber == null || houseNumber.isBlank()) ? null : cb.equal(root.get("houseNumber"), houseNumber);
    }

    public static Specification<Address> hasZipCode(String zipCode) {
        return (root, _, cb) ->
                (zipCode == null || zipCode.isBlank()) ? null : cb.equal(root.get("zipCode"), zipCode);
    }

    public static Specification<Address> hasStreet(String street) {
        return (root, _, cb) ->
                (street == null || street.isBlank()) ? null : cb.equal(root.get("street"), street);
    }
}
