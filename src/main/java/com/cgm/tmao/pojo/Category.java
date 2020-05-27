package com.cgm.tmao.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "category")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Category {
    // 标注用于声明一个实体类的属性映射为数据库的主键列
    @Id
    //用于标注主键的生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY：采用数据库ID自增长的方式来自增主键字段
    @Column(name = "id")
    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category(){

    }
    public Category(String name) {
        this.name = name;
        this.id = id;
    }
}
