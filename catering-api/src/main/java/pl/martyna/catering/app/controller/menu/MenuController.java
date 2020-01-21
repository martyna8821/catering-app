package pl.martyna.catering.app.controller.menu;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.martyna.catering.app.dto.input.MenuInput;
import pl.martyna.catering.app.dto.resource.MenuResource;
import pl.martyna.catering.app.entity.menu.Menu;
import pl.martyna.catering.app.service.menu.IMenuService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/menus")
public class MenuController {

    private IMenuService menuService;
    private ModelMapper modelMapper;

    @Autowired
    public MenuController(IMenuService menuService, ModelMapper modelMapper){
        this.menuService = menuService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<?> addMenu(@RequestBody MenuInput menuInput){
        Menu createdMenu = this.menuService.save(modelMapper.map(menuInput, Menu.class));

       return new ResponseEntity<>( createdMenu, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){

        List<MenuResource> menuList = this.menuService.getAll()
                                                        .stream()
                                                        .map(menu ->
                                                                modelMapper.map(menu, MenuResource.class))
                                                        .collect(Collectors.toList());

        return new ResponseEntity<>(menuList, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getClientsMenus(@PathVariable UUID userId) {

           List<MenuResource> clientMenuList = this.menuService.getClientsMenus(userId)
                                                                .stream()
                                                                .map(menu ->
                                                                        modelMapper.map(menu, MenuResource.class))
                                                                .collect(Collectors.toList());

           return new ResponseEntity<>(clientMenuList, HttpStatus.OK);
    }
}
