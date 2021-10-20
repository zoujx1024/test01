package com.zoujx1024.dao;

import com.zoujx1024.entity.MyStudent;
import com.zoujx1024.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ：zoujx1024@qq.com
 * @date ：2021/9/18 16:10
 * @description：TODO
 */
//接口操作student表
public interface StudentDao {
    //查询student表中的所有数据
    public List<Student> selectStudents();

    //插入方法
    //参数：student，表示要插入到数据库的数据
    //返回值：int，表示执行insert操作后的影响数据库的行数
    public int insertStudent(Student student);

    //条件查询
    public Student selectStudentById(Integer id);

    //命名参数：多个参数传参，使用@Param命名
    public List<Student> selectMulitParam(@Param("myname") String name, @Param("myage") Integer age);

    //多个参数，使用Java对象作为接口中方法的参数
    List<Student> selectMulitStudent(Student student);

    //多个参数，使用Map存放多个值，可读性差
    List<Student> selectMulitByMaP(Map<String,Object> map);

    //定义方法返回Map
    Map<Object,Object> selectMapById(Integer id);

    //使用resultMap定义映射关系
    List<Student> selectAllStudents();

    //列名和属性名不一样，方法一
    List<MyStudent> selectMyStudent();

    //列名和属性名不一样，方法二
    List<MyStudent> selectDiffColProperty();

    //模糊查询，第一种，Java代码指定like的内容
    List<Student> selectLikeOne(@Param("likename") String name);

    //模糊查询，第二种，在mapper文件中拼接like的内容
    List<Student> selectLikeTwo(@Param("likename") String name);

    //动态SQL-if
    List<Student> selectStudentIf(Student student);

    //动态SQL-where
    List<Student> selectStudentWhere(Student student);

    //foreach使用一循环基本类型的值，List<Integer>
    List<Student> selectForeachOne(List<Integer> id);

    //foreach使用二循环对象中属性值，，List<Student>
    List<Student> selectForeachTwo(List<Student> id);

    //使用pagehelper分页插件
    List<Student> selectAllPageHelper();

}
