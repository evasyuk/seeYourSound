package com.bionic.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.TextView;

public class MyClassSurfaceView extends GLSurfaceView {
    private MyClassRenderer renderer;
    public MyClassSurfaceView(Context context, TextView _tv) {
        super(context);
        setEGLContextClientVersion(2);

        renderer = new MyClassRenderer(context, _tv);// shitty arguments
        setRenderer(renderer);

        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
      	int W = getWidth(); 
      	int H = getHeight();
      	float rotation;
      	
      	if( x > H/2){
      		rotation = 3f;
      	}else{
      		rotation = -3f;
      	}
        renderer.changeMyView(rotation);
        return true;
    }
}

