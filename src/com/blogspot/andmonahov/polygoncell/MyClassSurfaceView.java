package com.blogspot.andmonahov.polygoncell;


import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.TextView;

//ќпишем наш класс MyClassSurfaceView расшир€ющий GLSurfaceView
public class MyClassSurfaceView extends GLSurfaceView {
    //создадим ссылку дл€ хранени€ экземпл€ра нашего класса рендерера
    private MyClassRenderer renderer;

    // конструктор
    public MyClassSurfaceView(Context context, TextView _tv) {
        // вызовем конструктор родительского класса GLSurfaceView
        super(context);
        setEGLContextClientVersion(2);
        // создадим экземпл€р нашего класса MyClassRenderer
        renderer = new MyClassRenderer(context, _tv);
        // запускаем рендерер
        setRenderer(renderer);
        // установим режим циклического запуска метода onDrawFrame
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        // при этом запускаетс€ отдельный поток
        // в котором циклически вызываетс€ метод onDrawFrame
        // т.е. бесконечно происходит перерисовка кадров
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

