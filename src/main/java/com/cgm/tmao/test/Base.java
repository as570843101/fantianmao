package com.cgm.tmao.test;

class Base{
        Base()
        {
            System.out.println("Base1");
        }
    }
class Severn extends  Base{
    public static void main(String[] args) {
        Severn s = new Severn();
    }

    Severn(){
        System.out.println("Severn2");
    }
}
