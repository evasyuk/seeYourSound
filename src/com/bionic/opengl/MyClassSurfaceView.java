package com.bionic.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.TextView;

//������ ��� ����� MyClassSurfaceView ����������� GLSurfaceView
public class MyClassSurfaceView extends GLSurfaceView {
    //�������� ������ ��� �������� ���������� ������ ������ ���������
    private MyClassRenderer renderer;

    // �����������
    public MyClassSurfaceView(Context context, TextView _tv) {
        // ������� ����������� ������������� ������ GLSurfaceView
        super(context);
        setEGLContextClientVersion(2);
        // �������� ��������� ������ ������ MyClassRenderer
        renderer = new MyClassRenderer(context, _tv);
        // ��������� ��������
        setRenderer(renderer);
        // ��������� ����� ������������ ������� ������ onDrawFrame
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        // ��� ���� ����������� ��������� �����
        // � ������� ���������� ���������� ����� onDrawFrame
        // �.�. ���������� ���������� ����������� ������
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float eventX = event.getX();
        float eventY = event.getY();

        renderer.setCameraX(eventX);
        renderer.setCameraX(eventY);
        renderer.changeMyView();

//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                renderer.setCameraX(eventX);
//                renderer.setCameraX(eventY);
////                path.moveTo(eventX, eventY);
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                renderer.setCameraX(eventX);
//                renderer.setCameraX(eventY);
////                path.lineTo(eventX, eventY);
//                break;
//            case MotionEvent.ACTION_UP:
//                // nothing to do
//                break;
//            default:
//                return false;
//        }

        return true;
    }
}

