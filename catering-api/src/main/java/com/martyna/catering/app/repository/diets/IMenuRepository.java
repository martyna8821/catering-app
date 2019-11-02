package com.martyna.catering.app.repository.diets;

import com.martyna.catering.app.entity.diets.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IMenuRepository extends JpaRepository<Menu, UUID> {
}
