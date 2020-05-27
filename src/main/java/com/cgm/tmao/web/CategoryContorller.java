package com.cgm.tmao.web;

import com.cgm.tmao.pojo.Category;
import com.cgm.tmao.service.CategoryService;
import com.cgm.tmao.util.ImageUtil;
import com.cgm.tmao.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


//提供 RESTFUL 服务器控制器了
@RestController
public class CategoryContorller {

    @Autowired CategoryService categoryService;

    @GetMapping("/categories")
    public PageNavigator<Category> list(@RequestParam(value = "start",defaultValue = "0")int start,
                                        @RequestParam(value = "size",defaultValue = "5")int size){
        start = start<0?0:start;
        PageNavigator<Category> page = categoryService.list(start, size, 5);
        return page;
    }
    @PostMapping("/categories")
    public Object add(Category bean , MultipartFile image , HttpServletRequest request) throws IOException {
        categoryService.add(bean);
        if (image!=null){
            System.out.println(image+"image这里");
        }
        saveOrUpdateImageFile(bean, image, request);
        return bean;
    }

    public void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request) throws IOException {
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, bean.getId() + ".jpg");
        System.out.println("file1==="+file);
        if(!file.getParentFile().exists()) {
            System.out.println("file1===" + file);
            file.getParentFile().mkdirs();
        }
            //把内存图片写入磁盘中
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            boolean imgOk = ImageIO.write(img, "jpg", file);
            if(imgOk==true){
                System.out.println("图片写入成功");
            }else{
                System.out.println("图片写入失败");
            }

        }
    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable("id")int id,HttpServletRequest request){
        System.out.println("这里======================"+id);
        categoryService.delete(id);
        File imgFile = new File(request.getServletContext().getRealPath("imge/category"));
        File file = new File(imgFile, id + ".jpg");
        file.delete();
        return null;
    }

    @GetMapping("/categories/{id}")
    public Category get(@PathVariable("id") int id) throws Exception {
        System.out.println("服务端获得id："+id);
        Category bean=categoryService.get(id);
        System.out.println("bean:name="+bean.getName()+",id="+bean.getId());
        return bean;
    }

    @PutMapping("/categories/{id}")
    public Object put(Category bean ,MultipartFile image , HttpServletRequest request) throws IOException {
        String name = request.getParameter("name");
        bean.setName(name);
        categoryService.save(bean);

        if(image!=null) {
            saveOrUpdateImageFile(bean, image, request);
        }
        return bean;
    }
}