package com.bionic.opengl;


import java.nio.FloatBuffer;

import android.opengl.GLES20;

public class Shader {
    //����� ������� ������ �� ��������� ���������
    //������ ������ ��� ��������� ����
    private int program_Handle;

    //��� �������� ������� ������ �������� � �����������
    //������ ���� ���������� � ������������ �������
    public Shader(String vertexShaderCode, String fragmentShaderCode) {
        //�������� �����, ��������� ��������� ���������
        //��� ���� ����������� ���� program_Handle
        createProgram(vertexShaderCode, fragmentShaderCode);
    }

    // �����, ������� ������� ��������� ���������, ���������� � ������������
    private void createProgram(String vertexShaderCode, String fragmentShaderCode) {
        //�������� ������ �� ��������� ������
        int vertexShader_Handle =
                GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        //������������ � ���������� ������� ��� ���
        GLES20.glShaderSource(vertexShader_Handle, vertexShaderCode);
        //����������� ��������� ������
        GLES20.glCompileShader(vertexShader_Handle);
        //�������� ������ �� ����������� ������
        int fragmentShader_Handle =
                GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        //������������ � ������������ ������� ��� ���
        GLES20.glShaderSource(fragmentShader_Handle, fragmentShaderCode);
        //����������� ����������� ������
        GLES20.glCompileShader(fragmentShader_Handle);
        //�������� ������ �� ��������� ���������
        program_Handle = GLES20.glCreateProgram();
        //������������ � ��������� ��������� ��������� ������
        GLES20.glAttachShader(program_Handle, vertexShader_Handle);
        //������������ � ��������� ��������� ����������� ������
        GLES20.glAttachShader(program_Handle, fragmentShader_Handle);
        //����������� ��������� ���������
        GLES20.glLinkProgram(program_Handle);
    }

    //�����, ������� ���������
    //����� ��������� ������ vertexBuffer � ��������� a_vertex
    public void linkVertexBuffer(FloatBuffer vertexBuffer) {
        //������������� �������� ���������
        GLES20.glUseProgram(program_Handle);
        //�������� ������ �� ������� a_vertex
        int a_vertex_Handle = GLES20.glGetAttribLocation(program_Handle, "a_vertex");
        //�������� ������������� �������� a_vertex
        GLES20.glEnableVertexAttribArray(a_vertex_Handle);
        //��������� ����� ��������� ������ vertexBuffer � ��������� a_vertex
        GLES20.glVertexAttribPointer(
                a_vertex_Handle, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer);
    }

    //�����, ������� ���������
    //����� ��������� �������� �������� normalBuffer � ��������� a_normal
    public void linkNormalBuffer(FloatBuffer normalBuffer) {
        //������������� �������� ���������
        GLES20.glUseProgram(program_Handle);
        //�������� ������ �� ������� a_normal
        int a_normal_Handle = GLES20.glGetAttribLocation(program_Handle, "a_normal");
        //�������� ������������� �������� a_normal
        GLES20.glEnableVertexAttribArray(a_normal_Handle);
        //��������� ����� �������� normalBuffer � ��������� a_normal
        GLES20.glVertexAttribPointer(
                a_normal_Handle, 3, GLES20.GL_FLOAT, false, 0, normalBuffer);
    }


    //�����, ������� ��������� ������� ������-����-��������
    // modelViewProjectionMatrix � ��������� u_modelViewProjectionMatrix
    public void linkModelViewProjectionMatrix(float[] modelViewProjectionMatrix) {
        //������������� �������� ���������
        GLES20.glUseProgram(program_Handle);
        //�������� ������ �� �������� u_modelViewProjectionMatrix
        int u_modelViewProjectionMatrix_Handle =
                GLES20.glGetUniformLocation(program_Handle, "u_modelViewProjectionMatrix");
        //��������� ������ modelViewProjectionMatrix
        //� ��������� u_modelViewProjectionMatrix
        GLES20.glUniformMatrix4fv(
                u_modelViewProjectionMatrix_Handle, 1, false, modelViewProjectionMatrix, 0);
    }

    //���������
    public void linkModelMatrix(float[] modelMatrix) {
        //������������� �������� ���������
        GLES20.glUseProgram(program_Handle);
        //�������� ������ �� �������� u_modelMatrix
        int u_modelMatrix_Handle =
                GLES20.glGetUniformLocation(program_Handle, "u_modelMatrix");
        //��������� ������ modelMatrix
        //� ��������� u_modelMatrix
        GLES20.glUniformMatrix4fv(
                u_modelMatrix_Handle, 1, false, modelMatrix, 0);
    }

    // �����, ������� ��������� ���������� ������ � ��������� u_camera
    public void linkCamera(float xCamera, float yCamera, float zCamera) {
        //������������� �������� ���������
        GLES20.glUseProgram(program_Handle);
        //�������� ������ �� �������� u_camera
        int u_camera_Handle = GLES20.glGetUniformLocation(program_Handle, "u_camera");
        // ��������� ���������� ������ � ��������� u_camera
        GLES20.glUniform3f(u_camera_Handle, xCamera, yCamera, zCamera);
    }

    // �����, ������� ��������� ���������� ��������� �����
    // � ��������� u_lightPosition
    public void linkLightSource(float xLightPosition, float yLightPosition, float zLightPosition) {
        //������������� �������� ���������
        GLES20.glUseProgram(program_Handle);
        //�������� ������ �� �������� u_lightPosition
        int u_lightPosition_Handle = GLES20.glGetUniformLocation(program_Handle, "u_lightPosition");
        // ��������� ���������� ��������� ����� � ��������� u_lightPosition
        GLES20.glUniform3f(u_lightPosition_Handle, xLightPosition, yLightPosition, zLightPosition);
    }


    // �����, ������� ������ ��������� ��������� ������� ������ ��������
    public void useProgram() {
        GLES20.glUseProgram(program_Handle);
    }
    // ����� ������
}

