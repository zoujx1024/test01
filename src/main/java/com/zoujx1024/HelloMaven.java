package com.zoujx1024;

/**
 * @author ：zoujx1024@qq.com
 * @date ：2021/9/16 22:34
 * @description：TODO
 */
public class HelloMaven {
    public int add(int n1,int n2){
        return n1+n2;
    }

    public static void main(String[] args) {
        HelloMaven hello = new HelloMaven();
        int res=hello.add(1,2);
        System.out.println("结果："+res);
    }
}
