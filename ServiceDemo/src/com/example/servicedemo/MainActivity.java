package com.example.servicedemo;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{
    private Button bind, unbind, getServiceStatus;
    
    private MyService.MyBinder binder;
    
    private static final String ACTION = "action";
    
    private ServiceConnection connection = new ServiceConnection()
    {
        
        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            System.out.println("！！Service Disconnected！！");
        }
        
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            System.out.println("！！Service Connected！！");
            binder = (MyService.MyBinder) service;
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        bind = (Button) findViewById(R.id.bind);
        unbind = (Button) findViewById(R.id.unbind);
        getServiceStatus = (Button) findViewById(R.id.status);
        
        final Intent intent = new Intent();
        intent.setAction(ACTION);
        
        OnClickListener listener = new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                if (bind == v)
                {
                    bindService(intent, connection, Service.BIND_AUTO_CREATE);
                }
                if (unbind == v)
                {
                    unbindService(connection);
                }
                if (getServiceStatus == v)
                {
                    Toast.makeText(MainActivity.this,
                        "Count = " + binder.getCount(),
                       Toast.LENGTH_SHORT).show();
                }
            }
        };
        
        bind.setOnClickListener(listener);
        unbind.setOnClickListener(listener);
        getServiceStatus.setOnClickListener(listener);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
