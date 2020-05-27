package com.cgm.tmao.web;


import com.cgm.tmao.pojo.Property;
import com.cgm.tmao.service.PropertyService;
import com.cgm.tmao.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PropertyController {
    @Autowired
    PropertyService propertyService;
    @GetMapping("/categories/{cid}/properties")
    public PageNavigator<Property> list(@PathVariable("cid") int cid, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start<0?0:start;
        PageNavigator<Property> page =propertyService.list(cid, start, size,5);
        System.out.println("page======="+page);
        return page;
    }

    @GetMapping("/properties/{id}")
    public Property get(@PathVariable("id") int id ){
        Property bean = propertyService.get(id);
        return bean;
    }
    @PutMapping("/properties")
    public Object update(@RequestBody Property property){
        propertyService.update(property);
        return property;
    }
    @PostMapping("/properties")
    public Object add(@RequestBody Property property){
        propertyService.add(property);
        return property;
    }

    @DeleteMapping("/properties/{id}")
    public String delete(@PathVariable("id")int id ){
        propertyService.delete(id);
        return null;
    }
}
