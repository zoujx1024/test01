package com.zoujx1024;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author ：zoujx1024@qq.com
 * @date ：2021/9/16 22:47
 * @description：TODO
 */
public class TestHelloMaven {
    @Test
    public void TestAdd(){
        System.out.println("成功......");
        HelloMaven helloMaven = new HelloMaven();
        int res=helloMaven.add(5,6);
        Assert.assertEquals(11,res);
    }
}
