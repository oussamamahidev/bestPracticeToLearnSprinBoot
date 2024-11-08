package com.pokemonreview.api.repository;

import com.pokemonreview.api.models.Role;  // Use your own Role entity here
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);
}
