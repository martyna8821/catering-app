package pl.martyna.catering.app.service.menu.impl;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.repository.menu.IMenuEntryRepository;
import pl.martyna.catering.app.service.menu.IMenuEntryService;

@Service
public class MenuEntryService implements IMenuEntryService {

    private IMenuEntryRepository menuEntryRepository;

    @Autowired
    public MenuEntryService(IMenuEntryRepository menuEntryRepository){
        this.menuEntryRepository = menuEntryRepository;
    }
}
