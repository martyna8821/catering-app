package pl.martyna.catering.app.repository.diet;

import pl.martyna.catering.app.entity.diet.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDietRepository extends JpaRepository<Diet, UUID> {
}
