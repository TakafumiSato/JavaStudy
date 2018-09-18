/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjava;

import java.util.ArrayList;
import java.util.Iterator;


public class DataList {
    private ArrayList<Data> list;
    
    public DataList() {
        list = new ArrayList<Data>();
    }

    public void addData(int id, String name, int age) {
        list.add(new Data(id, name, age));
    }
    
    public void addData(Data data) {
        list.add(data);
    }
    
    public ArrayList getDataList() {
        return list;
    }
    
    public Data getData(int index) {
        return list.get(index);
    }
    
    public void insertData(int num, int id, String name, int age) {
        list.add(num, new Data(id, name, age));
    }
    
    public void insertData(int num, Data data) {
        list.add(num, data);
    }
    
    public void removeData(Data data) {
        Iterator<Data> i = list.iterator();
        
        while (i.hasNext()) {
            
            Data d = i.next();

            // == だと参照型の場合、アドレスを比較する
            // なので値を比較したい場合はequalsで比較する
            if (data.equals(d)) {
            //if (data == d) {
                i.remove();
                System.out.println("aとbは同じ");
            } else {
                System.out.println("aとbは違う");
            }
        }
    }
    
    public void removeData(int id) {
        for (Data data: list) {
            if (data.getId() == id) {
                list.remove(data);
            }
        }
    }
    
    public void removeData(String name) {
        for (Data data: list) {
            if (data.getName() == name) {
                list.remove(data);
            }
        }
    }
    
    public void outputList() {
        for (Data data: list) {
            System.out.println("ID: " + data.getId()
                            + "\n名前: " + data.getName()
                            + "\n年齢: " + data.getAge());
        }
        System.out.println("データ数: " + list.size());
    }
}
