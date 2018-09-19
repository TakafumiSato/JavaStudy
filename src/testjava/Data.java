/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjava;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Data {
    private Integer id;
    private String name;
    private String gender;
    private int birth;
    private int age;
    private long myNumber;
    
    public Data(Integer id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    
    public Data(Integer id, String name, String gender, int birth) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        setBirth(birth);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
        
        Date now = new Date();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        age = (Integer.parseInt(sdf.format(now)) - this.birth) / 10000;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getMyNumber() {
        return myNumber;
    }

    public void setMyNumber(long myNumber) {
        this.myNumber = myNumber;
    }
    
    public boolean equals(Object obj) {
        
        // オブジェクトがNULLでないこと
        if (obj == null) {
            return false;
        }
        // オブジェクトが同じ型であること
        if (!(obj instanceof Data)) {
            return false;
        }
        // 同値性を比較
        if (this.id.equals(((Data)obj).id) &&
            this.name.equals(((Data)obj).name)) {
            return true;
        } else {
            return false;
        }
    }
}
