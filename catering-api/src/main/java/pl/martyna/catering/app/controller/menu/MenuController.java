package pl.martyna.catering.app.controller.menu;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.martyna.catering.app.dto.input.MenuInput;
import pl.martyna.catering.app.dto.resource.MenuResource;
import pl.martyna.catering.app.entity.menu.Menu;
import pl.martyna.catering.app.entity.menu.MenuEntry;
import pl.martyna.catering.app.service.menu.IMenuEntryService;
import pl.martyna.catering.app.service.menu.IMenuService;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/menus")
public class MenuController {

    private IMenuService menuService;
    private IMenuEntryService menuEntryService;
    private ModelMapper modelMapper;

    @Autowired
    public MenuController(IMenuService menuService, ModelMapper modelMapper,
                          IMenuEntryService menuEntryService){
        this.menuService = menuService;
        this.modelMapper = modelMapper;
        this.menuEntryService = menuEntryService;
    }

    @PostMapping
    public ResponseEntity<?> addMenu(@RequestBody MenuInput menuInput){

        Menu menu = modelMapper.map(menuInput, Menu.class);
        menu.setMenuEntries(new HashSet<>());
        menuInput.getMenuEntries().forEach(menuEntryInput ->{
            menu.getMenuEntries().add(
                    menuEntryService.save(modelMapper.map(menuEntryInput, MenuEntry.class)));
        });
       return ResponseEntity.ok( this.menuService.save(menu));
    }

    @GetMapping("/{dietId}")
    public List<MenuResource> getDietsMenuList(@PathVariable UUID dietId) {
        return null;
    }
}
