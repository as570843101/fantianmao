package com.cgm.tmao.service;

import com.cgm.tmao.dao.PropertyDAO;
import com.cgm.tmao.pojo.Category;
import com.cgm.tmao.pojo.Property;
import com.cgm.tmao.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {
    @Autowired
    PropertyDAO propertyDAO;
    @Autowired
    CategoryService categoryService;
    public void add(Property bean) {
        propertyDAO.save(bean);
    }

    public void delete(int id) {
        propertyDAO.deleteById(id);
    }

    public Property get(int id) {
        Property one = propertyDAO.getOne(id);
        return one;
    }

    public void update(Property bean) {
        propertyDAO.save(bean);
    }

    public PageNavigator<Property> list(int cid, int start, int size, int navigatePages) {
        Category category = categoryService.get(cid);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Property> pageFromJPA =propertyDAO.findByCategory(category,pageable);
        return new PageNavigator<>(pageFromJPA,navigatePages);

    }

}
