package com.blogspot.andmonahov.polygoncell;

import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {
	// �������� ������ �� ��������� ������ ������ MyClassSurfaceView
	private MyClassSurfaceView mGLSurfaceView;
	
	// ������������� ����� onCreate
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
			//�������� ��������� ������ ������ MyClassSurfaceView
	    	mGLSurfaceView = new MyClassSurfaceView(this);
	    	//������� ��������� ������ ������ MyClassSurfaceView  
	    	setContentView(mGLSurfaceView);
	    	// �� ������ �������� ���������� ��� ��������� � OpenGl ES
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
