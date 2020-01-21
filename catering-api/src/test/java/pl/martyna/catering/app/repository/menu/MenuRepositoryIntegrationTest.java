package pl.martyna.catering.app.repository.menu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.martyna.catering.app.entity.auth.User;
import pl.martyna.catering.app.entity.diet.Diet;
import pl.martyna.catering.app.entity.menu.Menu;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MenuRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IMenuRepository menuRepository;

    @Test
    public void testGettingOrderMenus(){
        //given
        LocalDate orderStartDate = LocalDate.of(2020, 1, 3);
        LocalDate orderEndDate = LocalDate.of(2020, 1, 8);

        Diet diet = new Diet(UUID.randomUUID(), "test diet","description",
                            100, true, Set.of("1000", "1500"));
        entityManager.persist(diet);

        Menu menuFromOrder = new Menu(UUID.randomUUID(), LocalDate.of(2020,1,5),
                            diet, "1000");
        entityManager.persist(menuFromOrder);

        Menu menuNotFromOrder = new Menu(UUID.randomUUID(), LocalDate.of(2020,1,2),
                diet, "1500");
        entityManager.persist(menuNotFromOrder);
        entityManager.flush();

        //when
        List<Menu> foundMenu = this.menuRepository.findFromOrder(diet.getId(),
                                orderStartDate, orderEndDate, "1000");

        //then
        assertThat(foundMenu.contains(menuFromOrder));
        assertThat(!foundMenu.contains(menuNotFromOrder));

    }

}
