package com.ra.repository;

import com.ra.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReponsitory extends JpaRepository<User,Long> {
    Boolean existsByUserName(String userName);
}
