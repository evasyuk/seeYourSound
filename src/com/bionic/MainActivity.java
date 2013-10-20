package com.bionic;

import android.os.Bundle;
import android.app.Activity;
import android.view.WindowManager;
import android.widget.TextView;
import com.bionic.opengl.*;

public class MainActivity extends Activity {
    // �������� ������ �� ��������� ������ ������ MyClassSurfaceVi mGLSurfaceView;
    MyClassSurfaceView mGLSurfaceView;
    // ������������� ����� onCreate
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  // ������������� ������������� �����
        TextView textView = new TextView(this); // TextView c ScrollView ��� ����������� ����������

        //�������� ��������� ������ ������ MyClassSurfaceView
        mGLSurfaceView = new MyClassSurfaceView(this, textView);
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
