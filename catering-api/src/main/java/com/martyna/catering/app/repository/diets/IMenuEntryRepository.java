package com.martyna.catering.app.repository.diets;

import com.martyna.catering.app.entity.diets.MenuEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IMenuEntryRepository extends JpaRepository<MenuEntry, UUID> {
}
