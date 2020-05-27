package com.cgm.tmao.service;

import com.cgm.tmao.dao.CategoryDAO;
import com.cgm.tmao.pojo.Category;
import com.cgm.tmao.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    public List<Category> list() {
        //Sort 对象，表示通过 id 倒排序
        Sort id = Sort.by(Sort.Direction.DESC, "id");

        return categoryDAO.findAll(id);
    }

    public PageNavigator<Category> list(int start,int size ,int navigatePages){
        Sort id = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start,size,id);
        Page pageFormJPA =categoryDAO.findAll(pageable);
        System.out.println("pageFormJPA:"+pageFormJPA);
        return new PageNavigator<>(pageFormJPA,navigatePages);
    }

    public void add(Category bean){
        categoryDAO.save(bean);
    }

    public void delete(int id ){
        categoryDAO.deleteById(id);
    }
    public Category get(int id){
        Category one = categoryDAO.getOne(id);
        return one;
    }

    public void save(Category bean) {
        categoryDAO.save(bean);
    }
}