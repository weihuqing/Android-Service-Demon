package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service
{
    private int count;
    
    private Boolean quit = false;
    
    private MyBinder binder = new MyBinder();
    
    public class MyBinder extends Binder
    {
        public int getCount()
        {
            return count;
        }
    }
    
    @Override
    public IBinder onBind(Intent intent)
    {
        Toast.makeText(MyService.this, "Service is Binded", Toast.LENGTH_SHORT)
            .show();
        System.out.println("Service is Binded");
        return binder;
    }
    
    @Override
    public void onCreate()
    {
        System.out.println("Service is Created");
        
        Thread thread = new Thread(new Runnable()
        {
            
            @Override
            public void run()
            {
                // TODO Auto-generated method stub
                
            }
        });
//        new Thread()
//        {
//            public void run()
//            {
//                while (!quit)
//                {
//                    try
//                    {
//                        Thread.sleep(1000);
//                    }
//                    catch (InterruptedException e)
//                    {
//                        
//                    }
//                    count++;
//                    System.out.println("count = " + count);
//                }
//            }
//        }.start();
        super.onCreate();
    }
    
    @Override
    public boolean onUnbind(Intent intent)
    {
        System.out.println("Service is Unbinded");
        return super.onUnbind(intent);
    }
    
    @Override
    public void onDestroy()
    {
        this.quit = true;
        super.onDestroy();
        System.out.println("Service is Destroyed");
    }
    
}
