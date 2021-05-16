package com.example.javaspringbootlessonfour.repositories;

import com.example.javaspringbootlessonfour.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}