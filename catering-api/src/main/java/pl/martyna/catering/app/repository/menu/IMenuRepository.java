package pl.martyna.catering.app.repository.menu;

import pl.martyna.catering.app.entity.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IMenuRepository extends JpaRepository<Menu, UUID> {

    List<Menu> findByDiet_Id(UUID dietId);
}
