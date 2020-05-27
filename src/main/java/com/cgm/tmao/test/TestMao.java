package com.cgm.tmao.test;

import sun.misc.CharacterEncoder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;

public class TestMao {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tmao_springboot?useUnicode=true&" +
                    "characterEncoding=utf-8", "root", "root");
            Statement s = c.createStatement();
        )
        {
            for (int i = 0 ; i < 10 ; i++ ){
                String sqlFormat = "insert into category value (null,'测试分类%d')";
                String sql = String.format(sqlFormat,i);
                System.out.println(sqlFormat);
                System.out.println(sql);
                s.execute(sql);
            }
            System.out.println("ok");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
