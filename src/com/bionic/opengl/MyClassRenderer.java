package com.bionic.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.os.SystemClock;
import android.widget.TextView;
import com.bionic.opengl.utils.*;
import android.view.View;
public class MyClassRenderer implements GLSurfaceView.Renderer {
    Button btn1;
    TextView text_info;
    TexFont textFont;
    boolean isTimeOk = false;
    double time0;
    //
    //
    private Context context;
    private float xCamera, yCamera, zCamera;
    private float xLightPosition, yLightPosition, zLightPosition;

    private float[] modelMatrix;
    private float[] viewMatrix;
    private float[] modelViewMatrix;
    private float[] projectionMatrix;
    private float[] modelViewProjectionMatrix;
    //                 
    private int imax = 49;
    private int jmax = 49;
    //                         
    private int sizeindex;
    //
    private float x0 = -1f;
    private float z0 = -1f;
    //
    private float dx = 0.04f;
    private float dz = 0.04f;
    // x
    private float[] x;
    private float[][] y;
    private float[] z;
    //                                                       
    private float[] vertex;
    //                                              
    private float[][] normalX;
    private float[][] normalY;
    private float[][] normalZ;
    //                                                                
    private float[] normal;
    //
    private FloatBuffer vertexBuffer, normalBuffer;
    //              
    private ShortBuffer indexBuffer;
    //                
    private Shader mShader;

    public void setCameraX(float _x){
        xCamera = _x;
    }
    public void setCameraY(float _y){
        yCamera = _y;
    }
    public void setCameraZ(float _z){
        zCamera = _z;
    }

    //            
    public MyClassRenderer(Context context, TextView _text) {
        text_info = _text;                            
        this.context = context;
        //                                    
        xLightPosition = 5f;
        yLightPosition = 30f;
        zLightPosition = 5f;
        //       
        modelMatrix = new float[16];
        viewMatrix = new float[16];
        modelViewMatrix = new float[16];
        projectionMatrix = new float[16];
        modelViewProjectionMatrix = new float[16];
        //                                                 
        Matrix.setIdentityM(modelMatrix, 0);
        //                 
        xCamera = 3f;
        yCamera = 1.7f;
        zCamera = 1.5f;
                          
        Matrix.setLookAtM(viewMatrix, 0, xCamera, yCamera, zCamera, 0, 0, 0, 0, 1, 0);
        Matrix.multiplyMM(modelViewMatrix, 0, viewMatrix, 0, modelMatrix, 0);
        //                
        x = new float[imax + 1];
        z = new float[jmax + 1];
        y = new float[jmax + 1][imax + 1];
        vertex = new float[(jmax + 1) * (imax + 1) * 3];
        normalX = new float[jmax + 1][imax + 1];
        normalY = new float[jmax + 1][imax + 1];
        normalZ = new float[jmax + 1][imax + 1];
        normal = new float[(jmax + 1) * (imax + 1) * 3];
        //                 x   z                   
        for (int i = 0; i <= imax; i++) {
            x[i] = x0 + i * dx;
        }
        for (int j = 0; j <= jmax; j++) {
            z[j] = z0 + j * dz;
        }
        
        ByteBuffer vb = ByteBuffer.allocateDirect((jmax + 1) * (imax + 1) * 3 * 4);
        vb.order(ByteOrder.nativeOrder());
        vertexBuffer = vb.asFloatBuffer();
        vertexBuffer.position(0);

        ByteBuffer nb = ByteBuffer.allocateDirect((jmax + 1) * (imax + 1) * 3 * 4);
        nb.order(ByteOrder.nativeOrder());
        normalBuffer = nb.asFloatBuffer();
        normalBuffer.position(0);

        short[] index;                                 
        sizeindex = 2 * (imax + 1) * jmax + (jmax - 1);
        index = new short[sizeindex];
     
        int k = 0;
        int j = 0;
        while (j < jmax) {     
            for (int i = 0; i <= imax; i++) {
                index[k] = chain(j, i);
                k++;
                index[k] = chain(j + 1, i);
                k++;
            }
            if (j < jmax - 1) {                
                index[k] = chain(j + 1, imax);
                k++;
            }            
            j++;                        
            if (j < jmax) {  
                for (int i = imax; i >= 0; i--) {
                    index[k] = chain(j, i);
                    k++;
                    index[k] = chain(j + 1, i);
                    k++;
                }
                if (j < jmax - 1) {                    
                    index[k] = chain(j + 1, 0);
                    k++;
                }             
                j++;
            }
        }
        ByteBuffer bi = ByteBuffer.allocateDirect(sizeindex * 2);
        bi.order(ByteOrder.nativeOrder());
        indexBuffer = bi.asShortBuffer();
        //                         
        indexBuffer.put(index);
        indexBuffer.position(0);
      
        index = null;
        //                                              
        getVertex();
        getNormal();

        btn1 = new Button(this);

    }
    
    public void changeMyView(float rotation){
    	Matrix.setIdentityM(modelMatrix, 0);
        Matrix.setLookAtM(viewMatrix, 0, xCamera, yCamera, zCamera, 0, -10, 0, 0, 1, 0);
//        viewMatrix = gldTranslatef(viewMatrix, xCamera, yCamera, zCamera);
//        viewMatrix = gldRotatef(viewMatrix, 1, xCamera, yCamera, zCamera);
        Matrix.multiplyMM(modelViewMatrix, 0, viewMatrix, 0, modelMatrix, 0);
        Matrix.rotateM(modelViewProjectionMatrix, 0, rotation, 0.0f, 0.0f, 1.0f);
        
    }
//    float PI = 3.1415926535897932384626433832795f;
//    float PI_OVER_180 = 0.017453292519943295769236907684886f;
//    float PI_OVER_360 = 0.0087266462599716478846184538424431f;
    
//    float[] gldRotatef(float[] m, float a, float x,float y, float z)
//    {
//    	float angle=a*PI_OVER_180;
//    	float[] m2 = new float[16];
//
//    	m2[0] = (float) (1+(1-Math.cos(angle))*(x*x-1));
//    	m2[1] = (float) (-z*Math.sin(angle)+(1-Math.cos(angle))*x*y);
//    	m2[2] = (float) (y*Math.sin(angle)+(1-Math.cos(angle))*x*z);
//    	m2[3] = 0;
//
//    	m2[4] = (float) (z*Math.sin(angle)+(1-Math.cos(angle))*x*y);
//    	m2[5] = (float) (1+(1-Math.cos(angle))*(y*y-1));
//    	m2[6] = (float) (-x*Math.sin(angle)+(1-Math.cos(angle))*y*z);
//    	m2[7] = 0;
//
//    	m2[8] = (float) (-y*Math.sin(angle)+(1-Math.cos(angle))*x*z);
//    	m2[9] = (float) (x*Math.sin(angle)+(1-Math.cos(angle))*y*z);
//    	m2[10] = (float) (1+(1-Math.cos(angle))*(z*z-1));
//    	m2[11] = 0;
//
//    	m2[12] = 0;
//    	m2[13] = 0;
//    	m2[14] = 0;
//    	m2[15] = 1;
//
//    	return gldMultMatrix(m,m2);
//    }
//    
//    float[] gldMultMatrix(float[] MatrixB,float[] MatrixA)
//    {
//    	float[] NewMatrix = new float[16];
//    	int i; 
//
//    	for(i = 0; i < 4; i++){ //Cycle through each vector of first matrix.
//    		NewMatrix[i*4] = MatrixA[i*4] * MatrixB[0] + MatrixA[i*4+1] * MatrixB[4] + MatrixA[i*4+2] * MatrixB[8] + MatrixA[i*4+3] * MatrixB[12];
//    		NewMatrix[i*4+1] = MatrixA[i*4] * MatrixB[1] + MatrixA[i*4+1] * MatrixB[5] + MatrixA[i*4+2] * MatrixB[9] + MatrixA[i*4+3] * MatrixB[13];
//    		NewMatrix[i*4+2] = MatrixA[i*4] * MatrixB[2] + MatrixA[i*4+1] * MatrixB[6] + MatrixA[i*4+2] * MatrixB[10] + MatrixA[i*4+3] * MatrixB[14];
//    		NewMatrix[i*4+3] = MatrixA[i*4] * MatrixB[3] + MatrixA[i*4+1] * MatrixB[7] + MatrixA[i*4+2] * MatrixB[11] + MatrixA[i*4+3] * MatrixB[15];
//    	}
//    	return NewMatrix;
////    	memcpy(MatrixB,NewMatrix,64);
//    }
//    
//    float[] gldTranslatef(float[] m,float x,float y, float z)
//    {
//    	float[] m2 = new float[16];
//    	float[] m3 = new float[16];
//
//    	m2[0] = 1;
//    	m2[1] = 0;
//    	m2[2] = 0;
//    	m2[3] = 0;
//
//    	m2[4] = 0;
//    	m2[5] = 1;
//    	m2[6] = 0;
//    	m2[7] = 0;
//
//    	m2[8] = 0;
//    	m2[9] = 0;
//    	m2[10] = 1;
//    	m2[11] = 0;
//
//    	m2[12] = x;
//    	m2[13] = y;
//    	m2[14] = z;
//    	m2[15] = 1;
//
//    	return gldMultMatrix(m,m2);
//    }
    
    private short chain(int j, int i) {
        return (short) (i + j * (imax + 1));
    }
                                     
    private void getVertex() {
        double time = System.currentTimeMillis();
        for (int j = 0; j <= jmax; j++) {
            for (int i = 0; i <= imax; i++) {
                y[j][i] = 0.1f * (float) Math.cos(0.005 * time + 5 * (z[j] + x[i])) * (float) Math.sin(0.005 * time + 5 * (z[j] + x[i]));
            }
        }

        int k = 0;
        for (int j = 0; j <= jmax; j++) {
            for (int i = 0; i <= imax; i++) {
                vertex[k] = x[i];
                k++;
                vertex[k] = y[j][i];
                k++;
                vertex[k] = z[j];
                k++;
            }
        }
        vertexBuffer.put(vertex);
        vertexBuffer.position(0);
    }
                      
    private void getNormal() {
        for (int j = 0; j < jmax; j++) {
            for (int i = 0; i < imax; i++) {
                normalX[j][i] = -(y[j][i + 1] - y[j][i]) * dz;
                normalY[j][i] = dx * dz;
                normalZ[j][i] = -dx * (y[j + 1][i] - y[j][i]);
            }
        }

        for (int j = 0; j < jmax; j++) {
            normalX[j][imax] = (y[j][imax - 1] - y[j][imax]) * dz;
            normalY[j][imax] = dx * dz;
            normalZ[j][imax] = -dx * (y[j + 1][imax] - y[j][imax]);
        }

        for (int i = 0; i < imax; i++) {
            normalX[jmax][i] = -(y[jmax][i + 1] - y[jmax][i]) * dz;
            normalY[jmax][i] = dx * dz;
            normalZ[jmax][i] = dx * (y[jmax - 1][i] - y[jmax][i]);
        }

        normalX[jmax][imax] = (y[jmax][imax - 1] - y[jmax][imax]) * dz;
        normalY[jmax][imax] = dx * dz;
        normalZ[jmax][imax] = dx * (y[jmax - 1][imax] - y[jmax][imax]);

        int k = 0;
        for (int j = 0; j <= jmax; j++) {
            for (int i = 0; i <= imax; i++) {
                normal[k] = normalX[j][i];
                k++;
                normal[k] = normalY[j][i];
                k++;
                normal[k] = normalZ[j][i];
                k++;
            }
        }        
        normalBuffer.put(normal);
        normalBuffer.position(0);
    }
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        float k = 0.055f;
        float left = -k * ratio;
        float right = k * ratio;
        float bottom = -k;
        float top = k;
        float near = 0.1f;
        float far = 10.0f;
        //                          
        Matrix.frustumM(projectionMatrix, 0, left, right, bottom, top, near, far);        
        Matrix.multiplyMM(modelViewProjectionMatrix, 0, projectionMatrix, 0, modelViewMatrix, 0);
    }
                    
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glEnable(GLES20.GL_CULL_FACE);               
        GLES20.glHint(GLES20.GL_GENERATE_MIPMAP_HINT, GLES20.GL_NICEST);

        String vertexShaderCode =
                "uniform mat4 u_modelViewProjectionMatrix;" +
                        "attribute vec3 a_vertex;" +
                        "attribute vec3 a_normal;" +
                        "varying vec3 v_vertex;" +
                        "varying vec3 v_normal;" +
                        "void main() {" +
                        "v_vertex=a_vertex;" +
                        "vec3 n_normal=normalize(a_normal);" +
                        "v_normal=n_normal;" +
                        "gl_Position = u_modelViewProjectionMatrix * vec4(a_vertex,1.0);" +
                        "}";
        String fragmentShaderCode =
                "precision mediump float;" +
                        "uniform vec3 u_camera;" +
                        "uniform vec3 u_lightPosition;" +
                        "varying vec3 v_vertex;" +
                        "varying vec3 v_normal;" +
                        "void main() {" +
                        "vec3 n_normal=normalize(v_normal);" +
                        "vec3 lightvector = normalize(u_lightPosition - v_vertex);" +
                        "vec3 lookvector = normalize(u_camera - v_vertex);" +
                        "float ambient=0.1;" +
                        "float k_diffuse=0.7;" +
                        "float k_specular=0.3;" +
                        "float diffuse = k_diffuse * max(dot(n_normal, lightvector), 0.0);" +
                        "vec3 reflectvector = reflect(-lightvector, n_normal);" +
                        "float specular = k_specular * pow( max(dot(lookvector,reflectvector),0.0), 40.0 );" +
                        "vec4 one=vec4(1.0,1.0,1.0,1.0);" +
                        "vec4 lightColor=(ambient+diffuse+specular)*one;" +
                        "gl_FragColor=lightColor;" +
                        "}";
    
        mShader = new Shader(vertexShaderCode, fragmentShaderCode); 
        mShader.linkVertexBuffer(vertexBuffer);               
        mShader.linkNormalBuffer(normalBuffer);
    }
                               
    public void onDrawFrame(GL10 gl) {  	
        mShader.linkModelViewProjectionMatrix(modelViewProjectionMatrix);
        mShader.linkCamera(xCamera, yCamera, zCamera);
        mShader.linkLightSource(xLightPosition, yLightPosition, zLightPosition);
        getVertex();
        getNormal();
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        
        GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, sizeindex,
                GLES20.GL_UNSIGNED_SHORT, indexBuffer);
    }
   
	public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }


    public static int loadShader(int type, String shaderCode){
        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}//            
