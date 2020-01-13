package pl.martyna.catering.app.service.menu.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.entity.diet.Diet;
import pl.martyna.catering.app.entity.menu.Menu;
import pl.martyna.catering.app.entity.order.Order;
import pl.martyna.catering.app.repository.menu.IMenuRepository;
import pl.martyna.catering.app.repository.order.IOrderRepository;
import pl.martyna.catering.app.service.menu.IMenuEntryService;
import pl.martyna.catering.app.service.menu.IMenuService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MenuService implements IMenuService {

    private IMenuRepository menuRepository;
    private IMenuEntryService menuEntryService;
    private IOrderRepository orderRepository;

    @Autowired
    public MenuService(IMenuRepository menuRepository,
                       IMenuEntryService menuEntryService,
                       IOrderRepository orderRepository){

        this.menuRepository = menuRepository;
        this.menuEntryService = menuEntryService;
        this.orderRepository = orderRepository;
    }

    @Override
    public Menu save(Menu menuToSave) {
        return this.menuRepository.save(menuToSave);
    }

    @Override
    public List<Menu> getAll() {
        return this.menuRepository.findAll();
    }

    @Override
    public List<Menu> getClientsMenus(UUID userId) {

        List<Order> orders = this.orderRepository.findByUser(userId);
        List<Menu> menus = new ArrayList<>();
        orders.forEach( order -> {
            menus.addAll(this.menuRepository.findFromOrder(
                    order.getDiet().getId(), order.getStartDate(), order.getEndDate(),
                    order.getCaloricVersion()));
        });
        return menus;
    }
}
