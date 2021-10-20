package com.zoujx1024;

import com.zoujx1024.entity.Student;
import com.zoujx1024.util.JsonUtil;
import com.zoujx1024.util.mybatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author ：zoujx1024@qq.com
 * @date ：2021/9/18 22:38
 * @description：TODO
 */
public class mybatisTest02 {
    public static void main(String[] args) throws IOException {
        //[重要]从SqlSessionFactory获取SqlSession对象
        SqlSession sqlSession = mybatisUtil.getSqlSession();
        //[重要]指定要执行的SQL语句的标识。SQL映射文件中namespace+"."+标签的id值
        String sqlId="com.zoujx1024.dao.StudentDao"+"."+"selectStudents";
        //执行SQL语句，通过sqlId找到语句
        List<Student> studentList =sqlSession.selectList(sqlId);
        //输出结果
        for (Student student:studentList){
            //这里输出的是一个类名对象，
            //可以使用在实体类重写tostring，改变输出格式
            //或者解析为json数据。解析也有两种方法，一种反射，一种调用第三方json jar包
            System.out.println(JsonUtil.jsonObject(student));
            //利用JSONArray将集合内容转换成json数组格式字符串.这是使用fastjson插件
            //System.out.println(JSONArray.fromObject(student));
        }
        //关闭SqlSession对象
        sqlSession.close();
    }
}
