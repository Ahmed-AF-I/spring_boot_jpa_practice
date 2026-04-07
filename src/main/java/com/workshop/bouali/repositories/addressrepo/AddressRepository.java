package com.workshop.bouali.repositories.addressrepo;

import com.workshop.bouali.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {

}
