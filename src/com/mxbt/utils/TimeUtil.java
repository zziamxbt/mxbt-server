package com.mxbt.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.mxbt.dao.ForTimmer;


public class TimeUtil {  
    public void count  () {  
        // TODO todo.generated by zoer  
        Timer timer = new Timer();  
        timer.schedule(new MyTask(), 1000, 20000);  
    }  
}  
  
class MyTask extends TimerTask {  
  
    @Override  
    public void run() {  
       
        ForTimmer ft = new ForTimmer();
        List<Integer> cidList = new ArrayList<>();
        cidList = ft.getChapter();
        System.out.println(cidList.toString());  
    }  
  
}  