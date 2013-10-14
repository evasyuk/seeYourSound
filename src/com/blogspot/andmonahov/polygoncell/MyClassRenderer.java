package com.blogspot.andmonahov.polygoncell;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.view.MotionEvent;
import android.os.SystemClock;

<<<<<<< HEAD
public class MyClassRenderer implements GLSurfaceView.Renderer{
    // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ GLSurfaceView.Renderer пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
    // пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ onDrawFrame, onSurfaceChanged, onSurfaceCreated
    // пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
	//
    // пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
=======
public class MyClassRenderer implements GLSurfaceView.Renderer {
    boolean isTimeOk = false;
    double time0;
    // интерфейс GLSurfaceView.Renderer содержит
    // три метода onDrawFrame, onSurfaceChanged, onSurfaceCreated
    // которые должны быть переопределены
    //
    // текущий контекст
>>>>>>> onTouchEvent handler added
    private Context context;
    //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
    private float xпїЅamera, yCamera, zCamera;
    //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ
    private float xLightPosition, yLightPosition, zLightPosition;
    //пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
    private float[] modelMatrix;
    private float[] viewMatrix;
    private float[] modelViewMatrix;
    private float[] projectionMatrix;
    private float[] modelViewProjectionMatrix;
<<<<<<< HEAD
    //пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ    
    private int imax=49;
    private int jmax=49;
    //пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
    private int sizeindex;    
    //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ x
    private float x0=-1f;
    //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ z
    private float z0=-1f;
    //пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅ x
    private float dx=0.04f;
    //пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅ z
    private float dz=0.04f;
    // пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ x
    private float [] x;
    // пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ y
    private float [][] y;
    //пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ z
    private float [] z;
    //пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅ
    private float [] vertex;
    //пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
    private float [][] normalX;
    private float [][] normalY;
    private float [][] normalZ;
    //пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅ
    private float [] normal;    
    //пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
    private FloatBuffer vertexBuffer, normalBuffer;
    //пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
    private ShortBuffer indexBuffer;    
    //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
    private Shader mShader;
        
    //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
    public MyClassRenderer(Context context) {
            // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
            // пїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
            this.context=context;
            //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ            
            xLightPosition=5f;
            yLightPosition=30f;
            zLightPosition=5f;                        
            //пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
            modelMatrix=new float[16];
            viewMatrix=new float[16];
            modelViewMatrix=new float[16];
            projectionMatrix=new float[16];
            modelViewProjectionMatrix=new float[16];
            //пїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
            //пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
            Matrix.setIdentityM(modelMatrix, 0);
            //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ          
            xпїЅamera=0.3f;
            yCamera=1.7f;
            zCamera=1.5f;                        
            //пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
            //пїЅ пїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ Y
            //пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ
            Matrix.setLookAtM(
                   viewMatrix, 0, xпїЅamera, yCamera, zCamera, 0, 0, 0, 0, 1, 0);           
            // пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
            // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ-пїЅпїЅпїЅпїЅ
            Matrix.multiplyMM(modelViewMatrix, 0, viewMatrix, 0, modelMatrix, 0);
            // пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ                           
            x=new float [imax+1];
            z=new float [jmax+1];
            y=new float [jmax+1][imax+1];
            vertex=new float[(jmax+1)*(imax+1)*3];            
            normalX=new float[jmax+1][imax+1];
            normalY=new float[jmax+1][imax+1];
            normalZ=new float[jmax+1][imax+1];
            normal=new float[(jmax+1)*(imax+1)*3];               
            //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ x пїЅ z пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ            
            for (int i=0; i<=imax; i++){
            	x[i]=x0+i*dx;  
            }
            for (int j=0; j<=jmax; j++){
              	z[j]=z0+j*dz;           
            }      
            //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
            // пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅ getVertex()
            ByteBuffer vb = ByteBuffer.allocateDirect((jmax+1)*(imax+1)*3*4);
            vb.order(ByteOrder.nativeOrder());
            vertexBuffer = vb.asFloatBuffer();
            vertexBuffer.position(0);
            //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
            // пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅ getNormal()
            ByteBuffer nb = ByteBuffer.allocateDirect((jmax+1)*(imax+1)*3*4);
            nb.order(ByteOrder.nativeOrder());
            normalBuffer = nb.asFloatBuffer();
            normalBuffer.position(0);
            //пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
    		// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
    		short[] index;
    		// 2*(imax+1) - пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅ
    		// jmax - пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ
    		// (jmax-1) - пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ 
    		sizeindex=2*(imax+1)*jmax + (jmax-1);
    		index = new short[sizeindex];
    		// пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
    		int k=0;
    		int j=0;
    		while (j < jmax) {
    			// пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
    			for (int i = 0; i <= imax; i++) {
    				index[k] = chain(j,i);
    				k++;
    				index[k] = chain(j+1,i);
    				k++;				
    			}
    			if (j < jmax-1){
    				// пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
    				index[k] = chain(j+1,imax);
    				k++;
    			}
    			// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ
    			j++;
    			//
    			// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ
    			if (j < jmax){
    				// пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
    				for (int i = imax; i >= 0; i--) {
    					index[k] = chain(j,i);
    					k++;
    					index[k] = chain(j+1,i);
    					k++;				
    				}
    				if (j < jmax-1){
    					// пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
    					index[k] = chain(j+1,0);
    					k++;
    				}
    				// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ
    				j++;
    			}
    		}            
    		// пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ - пїЅпїЅпїЅ short пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 2 пїЅпїЅпїЅпїЅпїЅ
    		ByteBuffer bi = ByteBuffer.allocateDirect(sizeindex * 2);
    		bi.order(ByteOrder.nativeOrder());
    		indexBuffer = bi.asShortBuffer();
    		// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
    		indexBuffer.put(index);
    		indexBuffer.position(0);
    		// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ,
    		// пїЅ.пїЅ. пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
    		index = null;
    		//пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
            getVertex();
            getNormal();      
    }//пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ

    
    // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
    // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ j пїЅ i 
	private short chain(int j, int i){
		return (short) (i+j*(imax+1));
	}
    
	//пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
	private void getVertex(){		
		double time=System.currentTimeMillis();
        // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ Y пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
        for (int j=0; j<=jmax; j++){
                for (int i=0; i<=imax; i++){                	
                    y[j][i]=0.2f*(float)Math.cos(0.005*time+5*(z[j]+x[i]))*(float)Math.sin(0.005*time+5*(z[j]+x[i]));
                }
        }
        // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ vertex
        int k=0;
        for (int j=0; j<=jmax; j++){
                for (int i=0; i<=imax; i++){
                        vertex[k]=x[i];
                        k++;
                        vertex[k]=y[j][i];
                        k++;
                        vertex[k]=z[j];
                        k++;
                }
=======
    //размеры сетки    
    private int imax = 49;
    private int jmax = 49;
    //размер индексного массива
    private int sizeindex;
    //начальная координата x
    private float x0 = -1f;
    //начальная координата z
    private float z0 = -1f;
    //шаг сетки по оси x
    private float dx = 0.04f;
    //шаг сетки по оси z
    private float dz = 0.04f;
    // массив для хранения координаты x
    private float[] x;
    // массив для хранения координаты y
    private float[][] y;
    //массив для хранения координаты z
    private float[] z;
    //массив для хранения координат вершин для записи в буфер
    private float[] vertex;
    //массивы для хранения координат вектора нормали
    private float[][] normalX;
    private float[][] normalY;
    private float[][] normalZ;
    //массив для хранения координат вектора нормали для записи в буфер
    private float[] normal;
    //буферы для координат вершин и нормалей 
    private FloatBuffer vertexBuffer, normalBuffer;
    //буфер индексов
    private ShortBuffer indexBuffer;
    //шейдерный объект
    private Shader mShader;

    public void setCameraX(float _x){
        xСamera = _x;
    }
    public void setCameraY(float _y){
        yCamera = _y;
    }
    public void setCameraZ(float _z){
        zCamera = _z;
    }

    //конструктор 
    public MyClassRenderer(Context context) {
        // запомним контекст
        // он нам понадобится в будущем для загрузки текстур
        this.context = context;
        //координаты точечного источника света
        xLightPosition = 5f;
        yLightPosition = 30f;
        zLightPosition = 5f;
        //матрицы
        modelMatrix = new float[16];
        viewMatrix = new float[16];
        modelViewMatrix = new float[16];
        projectionMatrix = new float[16];
        modelViewProjectionMatrix = new float[16];
        //мы не будем двигать объекты
        //поэтому сбрасываем модельную матрицу на единичную
        Matrix.setIdentityM(modelMatrix, 0);
        //координаты камеры
        xСamera = 0.3f;
        yCamera = 1.7f;
        zCamera = 1.5f;
        //пусть камера смотрит на начало координат
        //и верх у камеры будет вдоль оси Y
        //зная координаты камеры получаем матрицу вида
        Matrix.setLookAtM(
                viewMatrix, 0, xСamera, yCamera, zCamera, 0, 0, 0, 0, 1, 0);
        // умножая матрицу вида на матрицу модели
        // получаем матрицу модели-вида
        Matrix.multiplyMM(modelViewMatrix, 0, viewMatrix, 0, modelMatrix, 0);
        // создаем массивы
        x = new float[imax + 1];
        z = new float[jmax + 1];
        y = new float[jmax + 1][imax + 1];
        vertex = new float[(jmax + 1) * (imax + 1) * 3];
        normalX = new float[jmax + 1][imax + 1];
        normalY = new float[jmax + 1][imax + 1];
        normalZ = new float[jmax + 1][imax + 1];
        normal = new float[(jmax + 1) * (imax + 1) * 3];
        //заполним массивы x и z координатами сетки
        for (int i = 0; i <= imax; i++) {
            x[i] = x0 + i * dx;
        }
        for (int j = 0; j <= jmax; j++) {
            z[j] = z0 + j * dz;
        }
        //создадим буфер для хранения координат вершин
        // он заполняется в методе getVertex()
        ByteBuffer vb = ByteBuffer.allocateDirect((jmax + 1) * (imax + 1) * 3 * 4);
        vb.order(ByteOrder.nativeOrder());
        vertexBuffer = vb.asFloatBuffer();
        vertexBuffer.position(0);
        //создадим буфер для хранения координат векторов нормалей
        // он заполняется в методе getNormal()
        ByteBuffer nb = ByteBuffer.allocateDirect((jmax + 1) * (imax + 1) * 3 * 4);
        nb.order(ByteOrder.nativeOrder());
        normalBuffer = nb.asFloatBuffer();
        normalBuffer.position(0);
        //индексы
        // временный массив индексов
        short[] index;
        // 2*(imax+1) - количество индексов в ленте
        // jmax - количество лент
        // (jmax-1) - добавленные индексы для связки лент
        sizeindex = 2 * (imax + 1) * jmax + (jmax - 1);
        index = new short[sizeindex];
        // расчет массива индексов для буфера
        int k = 0;
        int j = 0;
        while (j < jmax) {
            // лента слева направо
            for (int i = 0; i <= imax; i++) {
                index[k] = chain(j, i);
                k++;
                index[k] = chain(j + 1, i);
                k++;
            }
            if (j < jmax - 1) {
                // вставим хвостовой индекс для связки
                index[k] = chain(j + 1, imax);
                k++;
            }
            // переводим ряд
            j++;
            //
            // проверяем достижение конца
            if (j < jmax) {
                // лента справа налево
                for (int i = imax; i >= 0; i--) {
                    index[k] = chain(j, i);
                    k++;
                    index[k] = chain(j + 1, i);
                    k++;
                }
                if (j < jmax - 1) {
                    // вставим хвостовой индекс для связки
                    index[k] = chain(j + 1, 0);
                    k++;
                }
                // переводим ряд
                j++;
            }
        }
        // буфер индексов - тип short содержит 2 байта
        ByteBuffer bi = ByteBuffer.allocateDirect(sizeindex * 2);
        bi.order(ByteOrder.nativeOrder());
        indexBuffer = bi.asShortBuffer();
        // заполняем буфер индексов
        indexBuffer.put(index);
        indexBuffer.position(0);
        // уничтожаем временный массив индексов,
        // т.к. в дальнейшем нужен только буфер индексов
        index = null;
        //начальное заполнение буферов вершин и нормалей
        getVertex();
        getNormal();
    }//конец конструктора


    // вспомогательная функция
    // возвращает порядковый номер вершины по известным j и i 
    private short chain(int j, int i) {
        return (short) (i + j * (imax + 1));
    }

    //метод выполняет расчет координат вершин
    private void getVertex() {
        double time = System.currentTimeMillis();
        // заполним массив Y значениями функции
        for (int j = 0; j <= jmax; j++) {
            for (int i = 0; i <= imax; i++) {
                y[j][i] = 0.1f * (float) Math.cos(0.005 * time + 5 * (z[j] + x[i])) * (float) Math.sin(0.005 * time + 5 * (z[j] + x[i]));
            }
        }
        // заполним массив координат vertex
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
>>>>>>> onTouchEvent handler added
        }
        //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ vertex пїЅ пїЅпїЅпїЅпїЅпїЅ
        vertexBuffer.put(vertex);
        vertexBuffer.position(0);
    }

<<<<<<< HEAD
	//пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
	//пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
   	private void getNormal(){
        for (int j=0; j<jmax; j++){
                for (int i=0; i<imax; i++){
                        normalX [j] [i] = - ( y [j] [i+1] - y [j] [i] ) * dz;
                        normalY [j] [i] = dx * dz;
                        normalZ [j] [i] = - dx * ( y [j+1] [i] - y [j] [i] );
                }
        }
        //пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ i=imax
        for (int j=0; j<jmax; j++){
                normalX [j] [imax] = ( y [ j ] [ imax -1] - y [ j ] [ imax] ) * dz;
                normalY [j] [imax] = dx * dz;
                normalZ [j] [imax] = - dx * ( y [ j+1 ] [ imax] - y [ j ] [ imax ] );
        }
        //пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ j=jmax
        for (int i=0; i<imax; i++){
                normalX [jmax] [ i ] = - ( y [ jmax ] [ i+1 ] - y [ jmax ] [ i ] ) * dz;
                normalY [jmax] [ i ] = dx * dz;
                normalZ [jmax] [ i ] = dx * ( y [ jmax-1 ] [ i ] - y [ jmax ] [ i ] );
        }
        //пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ i=imax пїЅ j=jmax
        normalX [jmax] [ imax ]= (y [ jmax] [ imax-1] - y [ jmax] [imax]) * dz;
        normalY [jmax] [ imax ] = dx * dz;
        normalZ [jmax] [ imax ] = dx * (y [jmax-1] [imax] - y[jmax ] [imax]);
        //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ normal
        int k=0;
        for (int j=0; j<=jmax; j++){
                for (int i=0; i<=imax; i++){
                        normal[k]=normalX[j][i];
                        k++;
                        normal[k]=normalY[j][i];
                       k++;
                        normal[k]=normalZ[j][i];
                        k++;
                }
=======
    //метод выполняет расчет векторов нормалей
    //по известным координатам вершин
    private void getNormal() {
        for (int j = 0; j < jmax; j++) {
            for (int i = 0; i < imax; i++) {
                normalX[j][i] = -(y[j][i + 1] - y[j][i]) * dz;
                normalY[j][i] = dx * dz;
                normalZ[j][i] = -dx * (y[j + 1][i] - y[j][i]);
            }
        }
        //нормаль для i=imax
        for (int j = 0; j < jmax; j++) {
            normalX[j][imax] = (y[j][imax - 1] - y[j][imax]) * dz;
            normalY[j][imax] = dx * dz;
            normalZ[j][imax] = -dx * (y[j + 1][imax] - y[j][imax]);
        }
        //нормаль для j=jmax
        for (int i = 0; i < imax; i++) {
            normalX[jmax][i] = -(y[jmax][i + 1] - y[jmax][i]) * dz;
            normalY[jmax][i] = dx * dz;
            normalZ[jmax][i] = dx * (y[jmax - 1][i] - y[jmax][i]);
        }
        //нормаль для i=imax и j=jmax
        normalX[jmax][imax] = (y[jmax][imax - 1] - y[jmax][imax]) * dz;
        normalY[jmax][imax] = dx * dz;
        normalZ[jmax][imax] = dx * (y[jmax - 1][imax] - y[jmax][imax]);
        //переписываем координаты вектора нормали в одномерный массив normal
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
>>>>>>> onTouchEvent handler added
        }
        //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ normal пїЅ пїЅпїЅпїЅпїЅпїЅ 
        normalBuffer.put(normal);
<<<<<<< HEAD
        normalBuffer.position(0); 
   	} // пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
    
=======
        normalBuffer.position(0);
    } // конец метода

>>>>>>> onTouchEvent handler added

    //пїЅпїЅпїЅпїЅпїЅ, пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
    //пїЅ пїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ-пїЅпїЅпїЅпїЅ-пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
    public void onSurfaceChanged(GL10 unused, int width, int height) {
<<<<<<< HEAD
            // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ glViewport
            GLES20.glViewport(0, 0, width, height);
            float ratio = (float) width / height;
            float k=0.055f;
            float left = -k*ratio;
            float right = k*ratio;
            float bottom = -k;
            float top = k;
            float near = 0.1f;
            float far = 10.0f;
            // пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
            Matrix.frustumM(projectionMatrix, 0, left, right, bottom, top, near, far);
            // пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ,
            // пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ-пїЅпїЅпїЅпїЅ-пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
            Matrix.multiplyMM(
                    modelViewProjectionMatrix, 0, projectionMatrix, 0, modelViewMatrix, 0);
    }
     
    //пїЅпїЅпїЅпїЅпїЅ, пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
    //пїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
       //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
       GLES20.glEnable(GLES20.GL_DEPTH_TEST);
       //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
       GLES20.glEnable(GLES20.GL_CULL_FACE); 
       //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ, пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
       GLES20.glHint(GLES20.GL_GENERATE_MIPMAP_HINT, GLES20.GL_NICEST);
 
      //пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
      String vertexShaderCode=
              "uniform mat4 u_modelViewProjectionMatrix;"+
              "attribute vec3 a_vertex;"+
              "attribute vec3 a_normal;"+
              "varying vec3 v_vertex;"+
              "varying vec3 v_normal;"+
              "void main() {"+
                      "v_vertex=a_vertex;"+
                      "vec3 n_normal=normalize(a_normal);"+
                      "v_normal=n_normal;"+
                      "gl_Position = u_modelViewProjectionMatrix * vec4(a_vertex,1.0);"+
              "}";              
       String fragmentShaderCode=
              "precision mediump float;"+
              "uniform vec3 u_camera;"+
              "uniform vec3 u_lightPosition;"+
              "varying vec3 v_vertex;"+
              "varying vec3 v_normal;"+
              "void main() {"+
                     "vec3 n_normal=normalize(v_normal);"+
                     "vec3 lightvector = normalize(u_lightPosition - v_vertex);"+
                     "vec3 lookvector = normalize(u_camera - v_vertex);"+
                     "float ambient=0.1;"+
                     "float k_diffuse=0.7;"+
                     "float k_specular=0.3;"+
                     "float diffuse = k_diffuse * max(dot(n_normal, lightvector), 0.0);"+
                     "vec3 reflectvector = reflect(-lightvector, n_normal);"+
                     "float specular = k_specular * pow( max(dot(lookvector,reflectvector),0.0), 40.0 );"+
                    "vec4 one=vec4(1.0,1.0,1.0,1.0);"+
                    "vec4 lightColor=(ambient+diffuse+specular)*one;"+              
                    "gl_FragColor=lightColor;"+
              "}";
            //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ 
            mShader=new Shader(vertexShaderCode, fragmentShaderCode);
            //пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ a_vertex пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
            mShader.linkVertexBuffer(vertexBuffer);            
            //пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ a_normal пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ
            mShader.linkNormalBuffer(normalBuffer);
            //пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅ,
            //пїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ            
   }

    //пїЅпїЅпїЅпїЅпїЅ, пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ
    public void onDrawFrame(GL10 unused) { 
            //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ-пїЅпїЅпїЅпїЅ-пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ 
            mShader.linkModelViewProjectionMatrix(modelViewProjectionMatrix);
            //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
            mShader.linkCamera(xпїЅamera, yCamera, zCamera);
           //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ
            mShader.linkLightSource(xLightPosition, yLightPosition, zLightPosition);
            //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
            getVertex();
            //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
            getNormal();
            //пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
            //пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
    		GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, sizeindex,
    				GLES20.GL_UNSIGNED_SHORT, indexBuffer);	
    		
    }//пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
}//пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
=======
        // устанавливаем glViewport
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        float k = 0.055f;
        float left = -k * ratio;
        float right = k * ratio;
        float bottom = -k;
        float top = k;
        float near = 0.1f;
        float far = 10.0f;
        // получаем матрицу проекции
        Matrix.frustumM(projectionMatrix, 0, left, right, bottom, top, near, far);
        // матрица проекции изменилась,
        // поэтому нужно пересчитать матрицу модели-вида-проекции
        Matrix.multiplyMM(
                modelViewProjectionMatrix, 0, projectionMatrix, 0, modelViewMatrix, 0);
    }

    //метод, который срабатывает при создании экрана
    //здесь мы создаем шейдерный объект
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        //включаем тест глубины
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        //включаем отсечение невидимых граней
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        //включаем сглаживание текстур, это пригодится в будущем
        GLES20.glHint(GLES20.GL_GENERATE_MIPMAP_HINT, GLES20.GL_NICEST);

        //простые шейдеры для освещения
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
        //создадим шейдерный объект
        mShader = new Shader(vertexShaderCode, fragmentShaderCode);
        //свяжем буфер вершин с атрибутом a_vertex в вершинном шейдере
        mShader.linkVertexBuffer(vertexBuffer);
        //свяжем буфер нормалей с атрибутом a_normal в вершинном шейдере
        mShader.linkNormalBuffer(normalBuffer);
        //связь атрибутов с буферами сохраняется до тех пор,
        //пока не будет уничтожен шейдерный объект
    }

    //метод, в котором выполняется рисование кадра
    public void onDrawFrame(GL10 unused) {
        //передаем в шейдерный объект матрицу модели-вида-проекции
        mShader.linkModelViewProjectionMatrix(modelViewProjectionMatrix);
        //передаем в шейдерный объект координаты камеры
        mShader.linkCamera(xСamera, yCamera, zCamera);
        //передаем в шейдерный объект координаты источника света
        mShader.linkLightSource(xLightPosition, yLightPosition, zLightPosition);
        //вычисляем координаты вершин
        getVertex();
        //вычисляем координаты нормалей
        getNormal();
        //очищаем кадр
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        //рисуем поверхность
        GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, sizeindex,
                GLES20.GL_UNSIGNED_SHORT, indexBuffer);

    }//конец метода

}//конец класса
>>>>>>> onTouchEvent handler added
