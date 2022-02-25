package com.fotboll.tips.repository;

import com.fotboll.tips.model.Tips;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by Patrik Melander
 * Date: 2022-02-22
 * Time: 13:43
 * Project: FotbollTips
 * Copyright: MIT
 */
public interface TipsRepository extends JpaRepository<Tips, UUID> {
}
