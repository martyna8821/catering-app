package com.martyna.catering.app.repository;

import com.martyna.catering.app.entity.users.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAddressRepository extends JpaRepository<Address, UUID> {

    //Optional<Address> getAddress(Address);
}
