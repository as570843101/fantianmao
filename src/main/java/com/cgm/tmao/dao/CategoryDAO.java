package com.cgm.tmao.dao;

import com.cgm.tmao.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDAO extends JpaRepository<Category,Integer>{

    @Override
    List<Category> findAll();

}
