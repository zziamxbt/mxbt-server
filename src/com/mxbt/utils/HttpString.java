package com.mxbt.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpString {
	 public  String getHttpString(String path) { 
	        // 存放获取到的数据 
	        String info = ""; 
	        // 网络请求所需变量 
	        InputStream in = null; 
	        InputStreamReader reader = null; 
	        BufferedReader bufferedReader = null; 
	        try { 
	            URL url = new URL(path); 
	            // 根据Url打开地址，以utf-8编码的形式返回输入流 
	            in = url.openStream(); 
	            reader = new InputStreamReader(in, "utf-8"); 
	            bufferedReader = new BufferedReader(reader); 
	            // 临时接受数据变量 
	            String temp = null; 
	            while ((temp = bufferedReader.readLine()) != null) { 
	                info += temp; 
	            } 
	            return info; 
	        } catch (MalformedURLException e) { 
	            e.printStackTrace(); 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } finally { 
	            try { 
	                in.close(); 
	                reader.close(); 
	                bufferedReader.close(); 
	            } catch (IOException e) { 
	                e.printStackTrace(); 
	            } 
	        } 
	        return null; 
	    }

}
