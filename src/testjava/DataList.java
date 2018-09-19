/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjava;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DataList {
    
    private ArrayList<Data> list;
    public static final int SORT_MODE_SELECT = 1;
    public static final int SORT_MODE_BUCKET = 2;
    
    public DataList() {
        list = new ArrayList<Data>();
    }
    
    public DataList(String fileName) {
        list = new ArrayList<Data>();
        
        int size;
        char[] cbuf = new char[2];
        
        try {
            FileInputStream fs = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(fs,"UTF-8");
            StringWriter sWriter = new StringWriter();
            
            //InputStreamReaderクラスのreadメソッドでファイルを1文字ずつ読み込む
            while((size = isr.read(cbuf)) != -1) {
                //System.out.println((char)data);
                sWriter.write(cbuf, 0, size);
            }
            
            System.out.println(sWriter.toString());
            String s = sWriter.toString();
            String[] list = s.split("[,\n]", 0);
            
            for (int i = 4; i < list.length; i+=4) {
                addData(Integer.parseInt(list[i]), list[i+1], list[i+2], Integer.parseInt(list[i+3]));
            }
            outputList();
            
            isr.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void addData(int id, String name, String gender, int birth) {
        list.add(new Data(id, name, gender, birth));
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
    
    public void insertData(int num, int id, String name, String gender, int age) {
        list.add(num, new Data(id, name, gender, age));
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
    
    public int searchID(int id) {
        
        int index = 99999;
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                System.out.println("発見");
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    public void sortAge(int sortMode) {
        
        switch (sortMode) {
            case SORT_MODE_SELECT:
                selectSortAge();
                break;
            case SORT_MODE_BUCKET:
                bucketSortAge();
                break;
            default:
                System.out.println("そんなモードはありません");
                break;
        }
    }
    
    private void selectSortAge() {
        
        System.out.println("*選択ソート*");
        
        int[] array = new int[list.size()];
        
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).getAge();
        }

        for(int i = 0; i < list.size(); i++ ){
            int index = i;
            for(int j = i + 1; j < list.size(); j++){
                if(list.get(j).getAge() < list.get(index).getAge()){
                    index = j;
                }
            }
            if(i != index){
                Data tmp = list.get(index);
                list.set(index, list.get(i));
                list.set(i,tmp);
            }
        }
    }
    
    private void bucketSortAge() {
        
        System.out.println("*バケットソート*");
        
        List<List> bucket = new ArrayList<List>();
        for (int i = 0; i < 150; i++) {
            bucket.add(new ArrayList<Data>());
        }
        
        for (int i = 0; i < list.size(); i++) {
            bucket.get(list.get(i).getAge()).add(list.get(i));
        }
        
        int j = 0;
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i) != null) {
                while (bucket.get(i).size() > 0) {
                    Data tmp = (Data)bucket.get(i).get(0);
                    bucket.get(i).remove(0);
                    list.set(j, tmp);
                    j++;
                }
            }
        }
    }
    
    public void outputList() {
        for (Data data: list) {
            System.out.println("ID: " + data.getId()
                            + "\n名前: " + data.getName()
                            + "\n性別: " + data.getGender()
                            + "\n年齢: " + data.getAge()
                            + "\nマイナンバー: " + data.getMyNumber());
        }
        System.out.println("データ数: " + list.size());
    }
}
