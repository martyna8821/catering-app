package pl.martyna.catering.app.repository.menu;

import pl.martyna.catering.app.entity.menu.MenuEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IMenuEntryRepository extends JpaRepository<MenuEntry, UUID> {
}
