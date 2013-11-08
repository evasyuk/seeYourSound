package com.bionic;

import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;
import android.widget.TextView;
import com.bionic.opengl.*;

public class MainActivity extends Activity {
    MyClassSurfaceView mGLSurfaceView;
    WakeLock mWakeLock;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        PowerManager mPowerManager = (PowerManager)getSystemService(Context.POWER_SERVICE); 
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "UMSE PowerTest");
        if(mWakeLock != null){
        	mWakeLock.acquire();
        }
        		
        TextView textView = new TextView(this);

        mGLSurfaceView = new MyClassSurfaceView(this, textView);

        setContentView(mGLSurfaceView);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }
    
    @Override
    protected void onDestroy(){
    	if(mWakeLock != null){
    		mWakeLock.release();
    		mWakeLock = null;
    	}
    }
}
