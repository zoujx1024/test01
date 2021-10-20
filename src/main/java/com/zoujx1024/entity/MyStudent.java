package com.zoujx1024.entity;

/**
 * @author ：zoujx1024@qq.com
 * @date ：2021/9/24 15:12
 * @description：TODO
 */
public class MyStudent {
    private Integer stuId;
    private String stuName;
    private String stuEmail;
    private Integer stuAge;

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    @Override
    public String toString() {
        return "MyStudent{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", stuEmail='" + stuEmail + '\'' +
                ", stuAge=" + stuAge +
                '}';
    }
}
