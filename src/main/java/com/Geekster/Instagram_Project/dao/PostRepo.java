package com.Geekster.Instagram_Project.dao;

import com.Geekster.Instagram_Project.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Integer> {
}
