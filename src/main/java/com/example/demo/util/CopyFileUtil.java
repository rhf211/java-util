package com.example.demo.util;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class CopyFileUtil {
    public static void main(String[] args) throws Exception {

        List<String> list = Arrays.asList("en", "es", "ja", "ko", "vi", "zh", "zh-Hant");
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        for (String s : list) {
            File file = new File("C:\\work\\bind\\templates\\email\\" + "zh" + "\\header.php");
            File outFile = new File("C:\\work\\bind\\invite2\\" + "invite_friends_success_email-" + s + ".html");
            bufferedReader = new BufferedReader(new FileReader(file));
            bufferedWriter = new BufferedWriter(new FileWriter(outFile, true));
            //加入header
            String info;
            while ((info = bufferedReader.readLine()) != null) {
                bufferedWriter.write(info);
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
            File email = new File("C:\\work\\bind\\templates\\email\\"+s+"\\system_message_notification.php");
            //File email = new File("C:\\work\\bind\\templates\\email\\refer_friends_success.html");
            bufferedReader = new BufferedReader(new FileReader(email));

            String ems;
            //加入body
            while ((ems = bufferedReader.readLine()) != null) {
                bufferedWriter.write(ems);
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();

            /*File style = new File("C:\\work\\bind\\templates\\email\\refer_friends_success_style.php");

            bufferedReader = new BufferedReader(new FileReader(style));

            String sts;
            //加入style
            while ((sts = bufferedReader.readLine()) != null) {
                bufferedWriter.write(sts);
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();*/

            File foot = new File("C:\\work\\bind\\templates\\email\\" + s + "\\footer.php");
            bufferedReader = new BufferedReader(new FileReader(foot));

            String fos;
            //加入foot
            while ((fos = bufferedReader.readLine()) != null) {
                bufferedWriter.write(fos);
            }
            bufferedWriter.flush();


        }


    }
}
