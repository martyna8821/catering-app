package pl.martyna.catering.app.service.menu.impl;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.entity.menu.MenuEntry;
import pl.martyna.catering.app.repository.menu.IMenuEntryRepository;
import pl.martyna.catering.app.service.menu.IMenuEntryService;

import java.util.UUID;

@Service
public class MenuEntryService implements IMenuEntryService {

    private IMenuEntryRepository menuEntryRepository;

    @Autowired
    public MenuEntryService(IMenuEntryRepository menuEntryRepository){
        this.menuEntryRepository = menuEntryRepository;
    }

    @Override
    public MenuEntry save(MenuEntry entryToSave) {
        entryToSave.setId(UUID.randomUUID());
        return this.menuEntryRepository.save(entryToSave);
    }
}
