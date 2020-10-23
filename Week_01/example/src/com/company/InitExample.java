package com.company;

public class InitExample {
    public static int var = 1;
    static {
         var = 2;
         System.out.println("static print");
    }

    InitExample() {
        System.out.println("init print");
    }

    public static void main(String[] args) {
        InitExample example = new InitExample();
        System.out.println(InitExample.var);
    }
}
