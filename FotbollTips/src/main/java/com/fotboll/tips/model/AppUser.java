package com.fotboll.tips.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fotboll.tips.config.security.Role;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Patrik Melander
 * Date: 2022-02-22
 * Time: 13:23
 * Project: FotbollTips
 * Copyright: MIT
 */
@Entity
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Slf4j
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "first_name", length = 200, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 200, nullable = false)
    private String lastName;
    @Column(name = "email", length = 325, unique = true, nullable = false)
    private String email;
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    @Column(name = "points")
    private double points;
    @Column(name = "is_admin")
    private Boolean isAdmin;

    @OneToMany
    private List<Tips> tips;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roleList;
}
