package com.blogspot.andmonahov.polygoncell;

import android.os.Bundle;
import android.app.Activity;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity {
    // создадим ссылку на экземпл€р нашего класса MyClassSurfaceView
    private MyClassSurfaceView mGLSurfaceView;

    // переопределим метод onCreate
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  // ”станавливаем полноэкранный режим
        TextView textView = new TextView(this); // TextView c ScrollView дл€ отображени€ результата

        //создадим экземпл€р нашего класса MyClassSurfaceView
        mGLSurfaceView = new MyClassSurfaceView(this, textView);
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
