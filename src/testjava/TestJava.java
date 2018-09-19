/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjava;

import java.io.*;


public class TestJava {
    
    public static void main(String[] args) {
        
        arrayListTest();
        
        integerTest();
        
        stringTest();
        
        equalsTest();
        
        loadTextFile();
    }
    
    static void arrayListTest() {
        
        System.out.println("\n--- ArrayListTest ---\n");
        
        DataList dataList = new DataList();
        dataList.addData(1, "さとう", "男", 31);
        dataList.addData(new Data(2,"たかふみ",62));
        dataList.insertData(1, new Data(3,"hoge",10));
        dataList.outputList();
        
        Data d = dataList.getData(1);
        System.out.println("\n取得したデータ\n" + d.getId() + d.getName() + d.getAge() + "\n");
        
        dataList.removeData(d);
        dataList.removeData(1);
        dataList.outputList();
    }
    
    static void integerTest() {
        
        System.out.println("\n--- IntegerTest ---\n");
        
        DataList dataList = new DataList();
        dataList.addData(new Data(123, "てすと", 321));
        dataList.addData(new Data(124, "てすとてすと", 321));
        
        String s = Integer.toString(dataList.getData(0).getAge());
        try {
            // Integer で型変換
            int i = Integer.parseInt(s);
            Integer integerObj = Integer.valueOf(s);
            System.out.println(integerObj);
            
            int intValue = integerObj.intValue();
            byte byteValue = integerObj.byteValue();
            double doubleValue = integerObj.doubleValue();
            float floatValue = integerObj.floatValue();
            System.out.println("\n" + intValue + "\n" + byteValue + "\n" + doubleValue + "\n" + floatValue + "\n");
            
            // Integer で比較
            Integer a = Integer.valueOf(dataList.getData(0).getAge());
            Integer b = Integer.valueOf(dataList.getData(1).getAge());
            switch (a.compareTo(b)) {
                
                // 引数のほうが大きい場合
                case -1:
                    System.out.println("Bのほうが大きい");
                    break;
                case 1:
                    System.out.println("Bのほうが小さい");
                    break;
                case 0:
                    System.out.println("AとBは同じ");
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException numberFormatException) {
            // 不正な文字列を検出
            System.out.println("NumberFormatException発生");
        }
    }
    
    static void stringTest() {
        
        System.out.println("\n--- StringTest ---\n");
        
        DataList dataList = new DataList();
        dataList.addData(new Data(1000, "namae myouji", 10));
        
        String name = dataList.getData(0).getName();
        System.out.println(name.length());
        
        if (name.equals("namae")) {
            System.out.println("名前は一緒");
        } else {
            System.out.println("名前が違う");
        }
        
        System.out.println(name.charAt(3));
        
        int res = name.indexOf("ma");
        if (res != -1) {
            System.out.println(res);
        } else {
            System.out.println("その文字列はない");
        }
        
        String[] str = name.split(" ");
        for (String s: str) {
            System.out.println(s);
        }
    }
    
    static void equalsTest() {
        
        System.out.println("\n--- EqualsTest ---\n");
        
        Data a = new Data(new Integer(123), "オーガスタ", 3);
        Data b = new Data(new Integer(123), "オーガスタ", 3);
        //String a = "あいう";
        //String b = "あいう";
        //b = a;
        
        if (a == b) {
            System.out.println("同じ");
        } else {
            System.out.println("違う");
        }
        if (a.equals(b)) {
            System.out.println("同じ");
        } else {
            System.out.println("違う");
        }
        
        // equalsメソッドが実装されていないため意図した結果にならない
        // また == は参照先を比較してしまうため同値であっても"違う"の結果となる
        // なのでDataクラス内でequalsメソッドをオーバライドする必要がある
    }
    
    static void loadTextFile() {
        
        System.out.println("\n--- LoadFileTest ---\n");
        
        // インスタンス生成時、データファイル読込登録
        DataList dataList = new DataList("C:/Users/佐藤孝史/Documents/NetBeansProjects/TestJava/src/testjava/TextFile/従業員マスタ.txt");
        
        // ファイルのマージ
        mergeFile(dataList);
    }
    
    static void mergeFile(DataList dataList) {
        
        int size;
        char[] cbuf = new char[2];
        
        try {
            // マージするファイルを読込
            FileInputStream fs = new FileInputStream("C:/Users/佐藤孝史/Documents/NetBeansProjects/TestJava/src/testjava/TextFile/個人番号マスタ.txt");
            InputStreamReader isr = new InputStreamReader(fs,"UTF-8");
            StringWriter sWriter = new StringWriter();
            
            //InputStreamReaderクラスのreadメソッドでファイルを1文字ずつ読み込む
            while((size = isr.read(cbuf)) != -1) {
                sWriter.write(cbuf, 0, size);
            }
            
            System.out.println(sWriter.toString());
            String s = sWriter.toString();
            // テキストファイル内にある特殊文字を","に変換
            s = s.replace("\r\n", ",");
            s = s.replace("\r", ",");
            s = s.replace("\n", ",");
            // ","で切り分け
            String[] list = s.split("[,\n\r]", 0);
            
            // マージ
            for (int i = 2; i < list.length; i+=2) {
                dataList.getData(dataList.searchID(Integer.parseInt(list[i]))).setMyNumber(Long.parseLong(list[i+1]));
            }
            
            dataList.outputList();
            
            System.out.println("\n--- SortFileTest ---\n");
            
            // 年齢順でソート
            dataList.sortAge(DataList.SORT_MODE_SELECT);
            
            dataList.outputList();

            isr.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
