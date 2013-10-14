package com.blogspot.andmonahov.polygoncell;

import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {
	// создадим ссылку на экземпл€р нашего класса MyClassSurfaceView
	private MyClassSurfaceView mGLSurfaceView;
	
	// переопределим метод onCreate
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
			//создадим экземпл€р нашего класса MyClassSurfaceView
	    	mGLSurfaceView = new MyClassSurfaceView(this);
	    	//вызовем экземпл€р нашего класса MyClassSurfaceView  
	    	setContentView(mGLSurfaceView);
	    	// на экране по€витс€ поверность дл€ рисовани€ в OpenGl ES
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
}
