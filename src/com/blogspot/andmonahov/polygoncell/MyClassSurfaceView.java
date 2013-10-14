package com.blogspot.andmonahov.polygoncell;


import android.content.Context;
import android.opengl.GLSurfaceView;

//������ ��� ����� MyClassSurfaceView ����������� GLSurfaceView
public class MyClassSurfaceView extends GLSurfaceView{
	//�������� ������ ��� �������� ���������� ������ ������ ���������
	private MyClassRenderer renderer;
	
	// �����������
	public MyClassSurfaceView(Context context) {
		// ������� ����������� ������������� ������ GLSurfaceView
		super(context);
		setEGLContextClientVersion(2);
		// �������� ��������� ������ ������ MyClassRenderer
		renderer = new MyClassRenderer(context);
		// ��������� ��������
		setRenderer(renderer);
		// ��������� ����� ������������ ������� ������ onDrawFrame 
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
		// ��� ���� ����������� ��������� �����
		// � ������� ���������� ���������� ����� onDrawFrame
		// �.�. ���������� ���������� ����������� ������
	}	
}

