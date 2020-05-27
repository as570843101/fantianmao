package com.cgm.tmao.test;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VueTest {

    @GetMapping(value = "/vueTest")
    public String vueTest(){

        return "admin/vueTest";
    }
    @GetMapping(value = "/vueTest2")
    public String vueTest2(){

        return "admin/vueTest2";
    }

    @GetMapping(value = "/vueTest3")
    public String vueTest3(){

        return "admin/vueTest3";
    }
}
