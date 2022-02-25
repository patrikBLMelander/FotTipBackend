package com.fotboll.tips.repository;

import com.fotboll.tips.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by Patrik Melander
 * Date: 2022-02-15
 * Time: 12:48
 * Project: GoNorth2
 * Copyright: MIT
 */
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    Optional<AppUser> findByEmail(String email);
    Boolean existsByEmail(String email);
}
