package com.martyna.catering.app.service.diets.impl;

import com.martyna.catering.app.repository.diets.IMenuEntryRepository;
import com.martyna.catering.app.repository.diets.IMenuRepository;
import com.martyna.catering.app.service.diets.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService implements IMenuService {

    IMenuRepository menuRepository;

    @Autowired
    public MenuService(IMenuRepository menuRepository){
        this.menuRepository = menuRepository;

    }

}
