package com.cgm.tmao.service;

import com.cgm.tmao.dao.ProductDAO;
import com.cgm.tmao.pojo.Category;
import com.cgm.tmao.pojo.Product;
import com.cgm.tmao.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired ProductDAO productDAO;
    @Autowired CategoryService categoryService;

    public void add(Product product){
        productDAO.save(product);
    }
    public void delete(int id){
        productDAO.deleteById(id);
    }
    public Product get(int id ){
        return productDAO.getOne(id);
    }
    public void update(Product product){
        productDAO.save(product);
    }
    public PageNavigator<Product> list(int cid,int start,int size,int navifatePages){
        Category category = categoryService.get(cid);
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Product> pageFromJPA = productDAO.findByCategory(category, pageable);
        return new PageNavigator<>(pageFromJPA,navifatePages);
    }
}
