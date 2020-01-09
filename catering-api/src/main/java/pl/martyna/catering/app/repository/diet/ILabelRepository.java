package pl.martyna.catering.app.repository.diet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.martyna.catering.app.entity.diet.Label;

import java.util.UUID;

@Repository
public interface ILabelRepository extends JpaRepository<Label, UUID> {

}
