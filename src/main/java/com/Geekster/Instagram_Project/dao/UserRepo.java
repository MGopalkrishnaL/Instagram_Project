package com.Geekster.Instagram_Project.dao;

import com.Geekster.Instagram_Project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
