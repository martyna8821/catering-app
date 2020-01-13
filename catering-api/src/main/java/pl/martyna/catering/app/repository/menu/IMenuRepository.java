package pl.martyna.catering.app.repository.menu;

import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.repository.Query;
import pl.martyna.catering.app.entity.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IMenuRepository extends JpaRepository<Menu, UUID> {

    List<Menu> findByDiet_Id(UUID dietId);

    @Transactional
    @Query(value = "select * from menu m where m.diet_id = ?1 " +
                        "and m.menu_date BETWEEN ?2 AND ?3 AND m.caloric_version = ?4",
        nativeQuery = true)
    List<Menu> findFromOrder(UUID dietId, LocalDate start, LocalDate end, String caloricVersion);

    @Transactional
    @Query(value = "select * from menu m where m.diet_id in ?1 ",
            nativeQuery = true)
    List<Menu> findAllByDiets(List<UUID> dietsId);
}
