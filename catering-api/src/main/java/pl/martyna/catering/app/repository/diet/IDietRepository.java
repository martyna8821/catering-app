package pl.martyna.catering.app.repository.diet;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.martyna.catering.app.entity.diet.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface IDietRepository extends JpaRepository<Diet, UUID> {

    @Modifying
    @Transactional
    @Query(value = "update diets set published = ?2 where diet_id = ?1", nativeQuery = true)
    void changeStatus(UUID id, boolean published);

    @Modifying
    @Transactional
    @Query(value = "insert into diets_caloric_version (diet_id, caloric_version) values (:diet_id, :caloricVersion)",
            nativeQuery = true)
    void addCaloricVersion(@Param("diet_id")UUID id,@Param("caloricVersion") String caloricVersion);

}
