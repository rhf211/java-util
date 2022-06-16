package com.example.demo.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UnicodeSwitch {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\test\\unico1.txt");
        File outFile = new File("C:\\test\\enc.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFile));
        String str;
        String tmp;
        List<String> list = new ArrayList<>();

        while ((str = bufferedReader.readLine()) != null) {

            int i, j;
            char c;
            StringBuffer sb = new StringBuffer(1000);
            for (i = 0; i < str.length(); i++) {
                c = str.charAt(i);
                sb.append("\\u");
                j = (c >>> 8); //取出高8位
                tmp = Integer.toHexString(j);
                if (tmp.length() == 1) {
                    sb.append("0");
                }
                sb.append(tmp);
                j = (c & 0xFF); //取出低8位
                tmp = Integer.toHexString(j);
                if (tmp.length() == 1) {
                    sb.append("0");
                }
                sb.append(tmp);

            }
            String s = sb.toString();
            s = "CSC50044" + "=" + s;

            bufferedWriter.write(s);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }
}
