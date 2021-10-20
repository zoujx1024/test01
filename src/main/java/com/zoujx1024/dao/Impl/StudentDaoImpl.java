package com.zoujx1024.dao.Impl;

import com.zoujx1024.dao.StudentDao;
import com.zoujx1024.entity.MyStudent;
import com.zoujx1024.entity.Student;
import com.zoujx1024.util.mybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * @author ：zoujx1024@qq.com
 * @date ：2021/9/20 15:51
 * @description：TODO
 */
public class StudentDaoImpl implements StudentDao {
    @Override
    public int insertStudent(Student student) {
        SqlSession sqlSession =mybatisUtil.getSqlSession();
        String sqlId = "com.zoujx1024.dao.StudentDao.insertStudent";
        int nums=sqlSession.insert(sqlId,student);
        //提交事务
        sqlSession.commit();
        return nums;
    }

    @Override
    public List<Student> selectStudents() {
        SqlSession sqlSession =mybatisUtil.getSqlSession();
        String sqlId = "com.zoujx1024.dao.StudentDao.selectStudents";
        List<Student> listSelect=sqlSession.selectList(sqlId);
        return listSelect;
    }

    @Override
    public Student selectStudentById(Integer id) {
        return null;
    }

    @Override
    public List<Student> selectMulitParam(String name, Integer age) {
        return null;
    }

    @Override
    public List<Student> selectMulitStudent(Student student) {
        return null;
    }

    @Override
    public List<Student> selectMulitByMaP(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<Object, Object> selectMapById(Integer id) {
        return null;
    }

    @Override
    public List<Student> selectAllStudents() {
        return null;
    }

    @Override
    public List<MyStudent> selectMyStudent() {
        return null;
    }

    @Override
    public List<MyStudent> selectDiffColProperty() {
        return null;
    }

    @Override
    public List<Student> selectLikeOne(String name) {
        return null;
    }

    @Override
    public List<Student> selectLikeTwo(String name) {
        return null;
    }

    @Override
    public List<Student> selectStudentIf(Student student) {
        return null;
    }

    @Override
    public List<Student> selectStudentWhere(Student student) {
        return null;
    }

    @Override
    public List<Student> selectForeachOne(List<Integer> id) {
        return null;
    }

    @Override
    public List<Student> selectForeachTwo(List<Student> id) {
        return null;
    }

    @Override
    public List<Student> selectAllPageHelper() {
        return null;
    }
}
