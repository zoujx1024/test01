package com.zoujx1024.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ：zoujx1024@qq.com
 * @date ：2021/9/19 23:06
 * @description：TODO
 */
public class mybatisUtil {
    private static SqlSessionFactory factory=null;
    static {
        String config="mybatis.xml";
        try {
            InputStream in =Resources.getResourceAsStream(config);
            factory=new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*//实现每一个线程都有自己的共享变量（解决并发）
    private static ThreadLocal<SqlSession> t = new ThreadLocal<>();

    public static SqlSession getSqlSession(){
        SqlSession sqlSession=t.get();
        if (sqlSession==null){
            if (factory!=null){
                sqlSession=factory.openSession();
                t.set(sqlSession);
            }
        }
        return sqlSession;
    }

    public static void myClose(SqlSession sqlSession){
        if (sqlSession!=null){
            sqlSession.close();

            //关闭当前线程设置，必须加，否则下一个线程可能会使用上一个线程共享变量的风险
            t.remove();
        }
    }*/

    public static SqlSession getSqlSession(){
        SqlSession sqlSession=null;
        if (factory!=null){
            sqlSession=factory.openSession();
        }
        return sqlSession;
    }
}
