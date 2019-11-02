package com.martyna.catering.app.repository.diets;

import com.martyna.catering.app.entity.diets.Diet;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDietRepository extends JpaRepository<Diet, UUID> {
}
