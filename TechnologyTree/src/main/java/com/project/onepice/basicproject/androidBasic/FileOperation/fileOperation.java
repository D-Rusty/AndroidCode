package com.project.onepice.basicproject.androidBasic.FileOperation;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by onepice2015 on 16/6/23.
 */
public class fileOperation {

    /**
     * 创建文件
     * */
    public static boolean createFile(String filePath){
        boolean result = false;
        File file = new File(filePath);
        if (!file.exists()){
            try {
                result = file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 创建文件夹
     * */
    public static boolean createDirectory(String directory){
        boolean result = false;
        File folder = new File(directory);
        if (!folder.exists()){
            result = folder.mkdir();
        }
        return result;
    }

    /**
     * 删除文件
     * */

    public static boolean deleteFile(String filePath){
        boolean result = false;
        File file = new File(filePath);
        if (file.exists() && file.isFile()){
            result = file.delete();
        }
        return result;
    }

    /**
     * 递归删除文件夹
     * */

    public static void deleteDirctory(String filePath){
        File file = new File(filePath);
        if (!file.exists()){
            return;
        }

        if (file.isFile()){
            file.delete();
        }else if (file.isDirectory()){
           for (File files:file.listFiles()){
               deleteDirctory(filePath+"/"+files.getName());
           }
        }
    }

    /**
     * 读文件
     * 以字节为单元读取文件，常用于二进制文件，如图片，声音，影像等文件
     * */

    public static  String readFileByBytes(String filePath){
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()){
            return null;
        }

        StringBuffer content = new StringBuffer();


        try {
            byte[] temp = new byte[1024];
            FileInputStream fileInputStream = new FileInputStream(file);
            while (fileInputStream.read(temp) !=-1){
                 content.append(new String(temp));
                temp = new byte[1024];
            }

            fileInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

       return content.toString();
    }

/**
 * 以字符为单位读取文件，常用于读文本，数字等类型的文件，支持读取中文
 * */

 public static String readFileByChars(String filePath){
     File file = new File(filePath);
     if (!file.exists() || ! file.isFile()){
         return null;
     }

     StringBuffer content = new StringBuffer();
     try {
         char[] temp = new char[1024];
         FileInputStream fileInputStream = new FileInputStream(file);
         InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
         while (inputStreamReader.read(temp)!=-1){
             content.append(new String(temp));
             temp = new char[1024];
         }

         fileInputStream.close();
         inputStreamReader.close();

     } catch (FileNotFoundException e) {
         e.printStackTrace();
     } catch (IOException e) {
         e.printStackTrace();
     }

     return content.toString();
 }

/**
 * 以行为单位读取文件，常用于读面向行的格式化文件
 * */
    public static List<String> readFileByLines(String filePath){
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()){
            return null;
        }

        List<String> content = new ArrayList<String>();

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String lineContent = "";
            while ((lineContent = reader.readLine())!=null){
             content.add(lineContent);
             System.out.println(lineContent);
            }

            fileInputStream.close();
            inputStreamReader.close();
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

 /**
  * 写文件
  *
  * 字符串写入文件的几个类中，FileWriter效率最高，BufferedOutputStream次之，FileOutputStream最差。
  * */

  //通过FileOutputStream文件
    public static void  writeFileByFileOutputStream(String filePath,String content){
        File file = new File(filePath);
        synchronized (file){
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                fileOutputStream.write(content.getBytes("GBK"));
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

   //通过BufferedOutPutStream写入文件
    public static void writeFileByBufferedOutPutStream(String filePath,String content){
        File file = new File(filePath);
        synchronized (file){
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath));
                bufferedOutputStream.write(content.getBytes("GBK"));
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //通过FileWriter将字符串写入文件
    public static void  writeFileByFileWriter(String filePath,String content){
        File file = new File(filePath);
        synchronized (file){
            try {
                FileWriter fw =new FileWriter(filePath);
                fw.write(content);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

























