package com.zoujx1024;

import com.zoujx1024.entity.Student;
import com.zoujx1024.util.JsonUtil;
import net.sf.json.JSONArray;
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
public class mybatisTest01 {
    public static void main(String[] args) throws IOException {
        //访问mybatis读取student数据
        //1.定义mybatis主配置文件名称，从类路径开始，target/classes后面
        String config="mybatis.xml";
        //2.读取这个config表示的文件
        InputStream in =Resources.getResourceAsStream(config);
        //3.创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        //4.（执行一次就好，避免浪费资源）创建SqlSessionFactory对象
        SqlSessionFactory factory =builder.build(in);
        //5.[重要]从SqlSessionFactory获取SqlSession对象
        SqlSession sqlSession =factory.openSession();
        //6.[重要]指定要执行的SQL语句的标识。SQL映射文件中namespace+"."+标签的id值
        String sqlId="com.zoujx1024.dao.StudentDao"+"."+"selectStudents";
        //7.执行SQL语句，通过sqlId找到语句
        List<Student> studentList =sqlSession.selectList(sqlId);
        //8.输出结果
        for (Student student:studentList){
            //这里输出的是一个类名对象，
            //可以使用在实体类重写tostring，改变输出格式
            //或者解析为json数据。解析也有两种方法，一种反射，一种调用第三方json jar包
            System.out.println(JsonUtil.jsonObject(student));
            //利用JSONArray将集合内容转换成json数组格式字符串.这是使用fastjson插件
            //System.out.println(JSONArray.fromObject(student));
        }
        //9.关闭SqlSession对象
        sqlSession.close();
    }
}
