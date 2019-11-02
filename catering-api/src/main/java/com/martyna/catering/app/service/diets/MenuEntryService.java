package com.martyna.catering.app.service.diets;

import com.martyna.catering.app.repository.diets.IMenuEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuEntryService implements IMenuEntryService {

    IMenuEntryRepository menuEntryRepository;

    @Autowired
    public MenuEntryService(IMenuEntryRepository menuEntryRepository){

        this.menuEntryRepository = menuEntryRepository;
    }

}

