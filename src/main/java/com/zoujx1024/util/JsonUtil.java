package com.zoujx1024.util;

import java.lang.reflect.Field;

/**
 * @author ：zoujx1024@qq.com
 * @date ：2021/9/19 14:04
 * @description：TODO
 */
//通过反射机制，将类对象解析为json数据
public class JsonUtil {
    //
    public static String jsonObject(Object obj){
        Field[] fieldArray = null;
        Class classFile = null;
        //拼接字符串
        StringBuffer str=new StringBuffer("{");
        //1.获取当前对象的class文件
        classFile=obj.getClass();
        //2.获得class文件所有属性
        fieldArray=classFile.getDeclaredFields();
        //3.获得当前对象所有属性的值
        try {
            for (int i=0;i<fieldArray.length;i++){
                //获得单个属性
                Field field=fieldArray[i];
                //打破封装，获取属性名及属性值
                field.setAccessible(true);
                String fieldName=field.getName();//属性名
                Object value=field.get(obj);//属性值
                //将属性名、属性值拼接为json字符串形式
                str.append("\"");
                str.append(fieldName);
                str.append("\":");
                str.append("\"");
                str.append(value);
                str.append("\"");
                //保证最后一个无逗号
                if (i<fieldArray.length-1){
                    str.append(",");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            str.append("}");
        }
        return str.toString();
    }
}
