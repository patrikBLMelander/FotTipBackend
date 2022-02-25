package com.fotboll.tips.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by Patrik Melander
 * Date: 2022-02-22
 * Time: 13:38
 * Project: FotbollTips
 * Copyright: MIT
 */
@Entity
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Slf4j
public class Tips {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "home_team")
    private String homeTeam;
    @Column(name = "away_team")
    private String awayTeam;
    @Column(name = "placedTips")
    private String placedTips;
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;


}
