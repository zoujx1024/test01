package com.zoujx1024;

import com.github.pagehelper.PageHelper;
import com.zoujx1024.dao.Impl.StudentDaoImpl;
import com.zoujx1024.dao.StudentDao;
import com.zoujx1024.entity.MyStudent;
import com.zoujx1024.entity.Student;
import com.zoujx1024.util.JsonUtil;
import com.zoujx1024.util.mybatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author ：zoujx1024@qq.com
 * @date ：2021/9/19 18:18
 * @description：TODO
 */
//测试加入数据
public class TestInsertData {
    @Test
    public void testInsert() throws IOException {
        //1.定义mybatis主配置文件名称，从类路径开始，target/classes后面
        String config="mybatis.xml";
        //2.读取这个config表示的文件
        InputStream in = Resources.getResourceAsStream(config);
        //3.创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        //4.创建SqlSessionFactory对象
        SqlSessionFactory factory =builder.build(in);
        //5.[重要]从SqlSessionFactory获取SqlSession对象
        SqlSession sqlSession =factory.openSession();
        //6.[重要]指定要执行的SQL语句的标识。SQL映射文件中namespace+"."+标签的id值
        String sqlId="com.zoujx1024.dao.StudentDao"+"."+"insertStudent";
        //7.执行SQL语句，通过sqlId找到语句
        Student student = new Student();
        student.setId(3);
        student.setName("liubei");
        student.setEmail("liubei@qq.com");
        student.setAge(23);
        int num =sqlSession.insert(sqlId,student);
        //mybatis默认不是自动提交事务，所以在insert、update、delete后要手工提交事务
        //不提交事务，数据不会发生变化的
        sqlSession.commit();
        //8.输出结果
        /*for (Student student:studentList){
            //这里输出的是一个类名对象，
            //可以使用在实体类重写tostring，改变输出格式
            //或者解析为json数据。解析也有两种方法，一种反射，一种调用第三方json jar包
            System.out.println(JsonUtil.jsonObject(student));
            //利用JSONArray将集合内容转换成json数组格式字符串.这是使用fastjson插件
            //System.out.println(JSONArray.fromObject(student));
        }*/
        System.out.println("插入"+num+"条数据！");
        //9.关闭SqlSession对象
        sqlSession.close();
    }

    @Test
    public void implInsert(){
        Student student = new Student();
        student.setId(4);
        student.setName("tl");
        student.setEmail("tl@qq.com");
        student.setAge(18);
        //封装sqlSession对象的创建
        StudentDao s1 = new StudentDaoImpl();
        int num=s1.insertStudent(student);
        System.out.println("插入"+num+"条数据！");

    }

    @Test
    public void proxyInsert(){
        SqlSession sqlSession = mybatisUtil.getSqlSession();
        //mybatis的动态代理
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student = new Student();
        student.setId(6);
        student.setName("atm");
        student.setEmail("atm@qq.com");
        student.setAge(15);
        int num=dao.insertStudent(student);
        sqlSession.commit();
        System.out.println("插入"+num+"条数据！");
        System.out.println("查看dao对象的类型："+dao.getClass().getName());//com.sun.proxy.$Proxy2
        sqlSession.close();
    }

    @Test
    public void selectById(){
        SqlSession sqlSession=mybatisUtil.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        Student sById=dao.selectStudentById(3);
        System.out.println(JsonUtil.jsonObject(sById));
        sqlSession.close();
    }

    @Test
    public void selectMulitParam(){
        SqlSession sqlSession= mybatisUtil.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        List<Student> students=dao.selectMulitParam("lisi",21);
        for (Student student:students){
            System.out.println("---"+JsonUtil.jsonObject(student));
        }
        sqlSession.close();
    }

    @Test
    public void selectMulitStudent(){
        SqlSession sqlSession= mybatisUtil.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        Student st = new Student();
        st.setName("lisi");
        st.setAge(21);
        List<Student> students=dao.selectMulitStudent(st);
        for (Student student:students){
            System.out.println("==="+JsonUtil.jsonObject(student));
        }
        sqlSession.close();
    }

    @Test
    public void selectMulitByMaP(){
        SqlSession sqlSession= mybatisUtil.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        Map<String,Object> map = new HashMap<>();
        map.put("myname","lisi");
        map.put("myage",21);
        List<Student> students=dao.selectMulitByMaP(map);
        for (Student student:students){
            System.out.println("*-*-"+JsonUtil.jsonObject(student));
        }
        sqlSession.close();
    }

    @Test
    public void selectMapById(){
        SqlSession sqlSession=mybatisUtil.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        Map<Object,Object> mapById=dao.selectMapById(1);
        System.out.println("map=="+mapById);
    }

    @Test
    public void selectAllStudents(){
        SqlSession sqlSession= mybatisUtil.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        List<Student> students=dao.selectAllStudents();
        for (Student student:students){
            System.out.println("*-*-"+JsonUtil.jsonObject(student));
        }
        sqlSession.close();
    }

    @Test
    public void selectMyStudent(){
        SqlSession sqlSession=mybatisUtil.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        List<MyStudent> students=dao.selectMyStudent();
        for (MyStudent student:students){
            System.out.println("0.0."+student);
        }
        sqlSession.close();
    }

    @Test
    public void selectDiffColProperty(){
        SqlSession sqlSession=mybatisUtil.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        List<MyStudent> students=dao.selectDiffColProperty();
        for (MyStudent student:students){
            System.out.println("!!!"+student);
        }
        sqlSession.close();
    }

    @Test
    public void selectLikeOne(){
        SqlSession sqlSession=mybatisUtil.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        String name="%li%";
        List<Student> students=dao.selectLikeOne(name);
        for (Student student:students){
            System.out.println("@@@"+JsonUtil.jsonObject(student));
        }
        sqlSession.close();
    }

    @Test
    public void selectLikeTwo(){
        SqlSession sqlSession=mybatisUtil.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        String name="li";
        List<Student> students=dao.selectLikeTwo(name);
        for (Student student:students){
            System.out.println("###"+JsonUtil.jsonObject(student));
        }
        sqlSession.close();
    }

    @Test
    public void selectStudentIf(){
        SqlSession sqlSession=mybatisUtil.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        Student stu = new Student();
        stu.setName("lisi");
        stu.setAge(21);
        List<Student> students=dao.selectStudentIf(stu);
        for (Student student:students){
            System.out.println("###"+JsonUtil.jsonObject(student));
        }
        sqlSession.close();
    }

    @Test
    public void selectStudentWhere(){
        SqlSession sqlSession=mybatisUtil.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        Student stu = new Student();
        stu.setName("lisi");
        stu.setAge(21);
        List<Student> students=dao.selectStudentWhere(stu);
        for (Student student:students){
            System.out.println("###123"+JsonUtil.jsonObject(student));
        }
        sqlSession.close();
    }

    @Test
    //原理：自己编写动态SQL-foreach
    public void ownForeach(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        //编写目标，String sql ="select * from student where id in(1,2,3)";
        String sql ="select * from student where id in";
        //拼接字符串
        StringBuilder builder = new StringBuilder();
        int init=0;
        int len=list.size();
        //添加开始的（
        builder.append("(");
        for (Integer i:list){
            builder.append(i).append(",");
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append(")");
        sql=sql+builder.toString();
        System.out.println("sql=="+sql);
    }

    @Test
    public void selectForeachOne(){
        SqlSession sqlSession=mybatisUtil.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Student> stu=dao.selectForeachOne(list);
        for (Student student:stu){
            System.out.println("###123"+JsonUtil.jsonObject(student));
        }
        sqlSession.close();
    }

    @Test
    public void selectForeachTwo(){
        SqlSession sqlSession=mybatisUtil.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        List<Student> stulist = new ArrayList<>();
        Student s1 = new Student();
        s1.setId(1);
        stulist.add(s1);
        //再来一个对象
        s1=new Student();
        s1.setId(2);
        stulist.add(s1);

        List<Student> stu = dao.selectForeachTwo(stulist);
        for (Student student:stu){
            System.out.println("###123"+JsonUtil.jsonObject(student));
        }
        sqlSession.close();
    }

    @Test
    public void selectAllPageHelper(){
        SqlSession sqlSession=mybatisUtil.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        //分页的话，需要在查询语句前面定义。
        // pageNum：第几页，从1开始
        //pageSize：一页有多少行数据
        PageHelper.startPage(1,3);
        List<Student> list=dao.selectAllPageHelper();
        for (Student student:list){
            System.out.println("分页数据："+JsonUtil.jsonObject(student));
        }
    }
}
