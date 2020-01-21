package pl.martyna.catering.app.repository.user;

import pl.martyna.catering.app.entity.auth.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAddressRepository extends JpaRepository<Address, UUID> {

}
