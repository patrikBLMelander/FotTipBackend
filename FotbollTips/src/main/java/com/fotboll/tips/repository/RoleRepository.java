package com.fotboll.tips.repository;

import com.fotboll.tips.config.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by Patrik Melander
 * Date: 2022-02-15
 * Time: 12:47
 * Project: GoNorth2
 * Copyright: MIT
 */
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(String name);
    Role getByName(Role.RoleConstant name);
}
