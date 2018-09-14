/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjava;


public class TestJava {
    
    public static void main(String[] args) {
        
        DataList dataList = new DataList();
        dataList.addData(1, "さとう", 31);
        dataList.addData(new Data(2,"たかふみ",62));
        dataList.insertData(1, new Data(3,"hoge",10));
        dataList.outputList();
        
        Data d = dataList.getData(1);
        System.out.println("\n取得したデータ\n" + d.getId() + d.getName() + d.getAge() + "\n");
        
        dataList.removeData(d);
        dataList.removeData(1);
        dataList.outputList();
        
        String s = Integer.toString(dataList.getData(0).getAge());
        int i = Integer.parseInt(s);
        Integer integer = Integer.valueOf(s);
        System.out.println(integer);
    }
}
