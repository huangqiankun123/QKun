package com.kun.qian.baselibrary.bean;

import java.io.Serializable;

/**
 * 登录实体类
 * Created by fangs on 2017/12/22 0031.
 */
public class LoginBean implements Serializable {


    /**
     * student : {"id":34748,"xuejihao":"13191639","schoolname":"北京109小学","schoolcode":"bj109xx","schoolid":2,"nname":"五年级","ncode":"15","nid":21,"bcode":"1501","bname":"1班","bid":22,"name":"杨汉基","sex":"1","birthday":"2007-02-23","carid":null,"minzu":null,"address":null,"jiazhangphone":null,"xuehao":"13191639","studentid":34748,"touxiangurl":null,"height":154.7,"weight":68.4,"age":10,"createdtime":"2018-01-06 20:44:49","creater":1}
     * token : 73fae84e4ffa98f83d82f3bb8d650a94
     */

    private StudentBean student;
    private String token;

    public StudentBean getStudent() {
        return student;
    }

    public void setStudent(StudentBean student) {
        this.student = student;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class StudentBean implements Serializable {
        /**
         * id : 34748
         * xuejihao : 13191639
         * schoolname : 北京109小学
         * schoolcode : bj109xx
         * schoolid : 2
         * nname : 五年级
         * ncode : 15
         * nid : 21
         * bcode : 1501
         * bname : 1班
         * bid : 22
         * name : 杨汉基
         * sex : 1
         * birthday : 2007-02-23
         * carid : null
         * minzu : null
         * address : null
         * jiazhangphone : null
         * xuehao : 13191639
         * studentid : 34748
         * touxiangurl : null
         * height : 154.7
         * weight : 68.4
         * age : 10
         * createdtime : 2018-01-06 20:44:49
         * creater : 1
         */

        private int id;
        private String xuejihao;
        private String schoolname;
        private String schoolcode;
        private int schoolid;
        private String nname;
        private String ncode;
        private int nid;
        private String bcode;
        private String bname;
        private int bid;
        private String name;
        private String sex;
        private String birthday;
        private Object carid;
        private String minzu;
        private String address;
        private String jiazhangphone;
        private String xuehao;
        private int studentid;
        private String touxiangurl;
        private double height;
        private double weight;
        private int age;
        private String createdtime;
        private int creater;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getXuejihao() {
            return xuejihao;
        }

        public void setXuejihao(String xuejihao) {
            this.xuejihao = xuejihao;
        }

        public String getSchoolname() {
            return schoolname;
        }

        public void setSchoolname(String schoolname) {
            this.schoolname = schoolname;
        }

        public String getSchoolcode() {
            return schoolcode;
        }

        public void setSchoolcode(String schoolcode) {
            this.schoolcode = schoolcode;
        }

        public int getSchoolid() {
            return schoolid;
        }

        public void setSchoolid(int schoolid) {
            this.schoolid = schoolid;
        }

        public String getNname() {
            return nname;
        }

        public void setNname(String nname) {
            this.nname = nname;
        }

        public String getNcode() {
            return ncode;
        }

        public void setNcode(String ncode) {
            this.ncode = ncode;
        }

        public int getNid() {
            return nid;
        }

        public void setNid(int nid) {
            this.nid = nid;
        }

        public String getBcode() {
            return bcode;
        }

        public void setBcode(String bcode) {
            this.bcode = bcode;
        }

        public String getBname() {
            return bname;
        }

        public void setBname(String bname) {
            this.bname = bname;
        }

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public Object getCarid() {
            return carid;
        }

        public void setCarid(Object carid) {
            this.carid = carid;
        }

        public String getMinzu() {
            return minzu;
        }

        public void setMinzu(String minzu) {
            this.minzu = minzu;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getJiazhangphone() {
            return jiazhangphone;
        }

        public void setJiazhangphone(String jiazhangphone) {
            this.jiazhangphone = jiazhangphone;
        }

        public String getXuehao() {
            return xuehao;
        }

        public void setXuehao(String xuehao) {
            this.xuehao = xuehao;
        }

        public int getStudentid() {
            return studentid;
        }

        public void setStudentid(int studentid) {
            this.studentid = studentid;
        }

        public String getTouxiangurl() {
            return touxiangurl;
        }

        public void setTouxiangurl(String touxiangurl) {
            this.touxiangurl = touxiangurl;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(String createdtime) {
            this.createdtime = createdtime;
        }

        public int getCreater() {
            return creater;
        }

        public void setCreater(int creater) {
            this.creater = creater;
        }
    }
}
