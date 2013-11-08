package com.bionic.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.Matrix;

public abstract class openglObject {
	protected ByteBuffer b1,b2;
	protected FloatBuffer vertexBuffer, normalBuffer;
	protected float[] vertexArray;	
	protected ShortBuffer indexBuffer;
	protected Shader mShader;
	//
	protected int imax = 49;
	protected int jmax = 49;
	//
	protected float xCamera, yCamera, zCamera;
    protected float xLightPosition, yLightPosition, zLightPosition;
	//
    //initialisation
    protected float[] modelMatrix;
    protected float[] viewMatrix;
    protected float[] modelViewMatrix;
    protected float[] projectionMatrix;
    protected float[] modelViewProjectionMatrix;
    //
	protected float[] vertex;//might be unused
	//
	protected float[][] y;
	protected float[] x;
	protected float[] z;
	protected float dx, dz;
	protected float x0, z0;
	protected short[]  index;
	protected int sizeIndex;
	//
	protected float[][] normalX;
	protected float[][] normalY;
	protected float[][] normalZ;
	protected float [] normal;
	//
	protected EGL10 egl;
	protected EGLDisplay dpy;
	protected GL10 gl ;
	protected EGLSurface surface;
	protected EGLContext context;
	
	openglObject(){
		initialisation();
		defaultSetup();
		getIndex();	

		//EGL10 egl = (EGL10)EGLContext.getEGL(); //#9
		//openglObject(egl);
	}
	
	private void defaultSetup(){
		// imax, jmax is set by default	
//		x0 = -1f;
//		z0 = -1f;
//		dx = 0.04f;		
//		dz = 0.04f;
		//presetup 
		float _tempStep = 0.04f;
		float _tempX0 = -1f;
		
		setDxDz(_tempStep, _tempStep);
		this.setX0Z0(_tempX0, _tempX0);		
		
		modelMatrix = new float[16];
        viewMatrix = new float[16];
        modelViewMatrix = new float[16];
        projectionMatrix = new float[16];
        modelViewProjectionMatrix = new float[16];
        
        Matrix.setIdentityM(modelMatrix, 0);
//        xCamera = 3f;
//        yCamera = 1.7f;
//        zCamera = 1.5f;                        
        setCamera(3f, 1.7f, 1.5f);
        
        Matrix.setLookAtM(viewMatrix, 0, xCamera, yCamera, zCamera, 0, 0, 0, 0, 1, 0);
        Matrix.multiplyMM(modelViewMatrix, 0, viewMatrix, 0, modelMatrix, 0);
	}
	
	boolean reinitialisation(){
		try{						
			x = new float[imax + 1];
	        z = new float[jmax + 1];
	        y = new float[jmax + 1][imax + 1];
	        vertex = new float[(jmax + 1) * (imax + 1) * 3];
	        normalX = new float[jmax + 1][imax + 1];
	        normalY = new float[jmax + 1][imax + 1];
	        normalZ = new float[jmax + 1][imax + 1];
	        normal = new float[(jmax + 1) * (imax + 1) * 3];
	        
	        axisLayout();
	        return true;
		}catch(Exception e){
			return false;
		}  
		
	}
	
	abstract void drawFrame(GL10 gl);
	
	protected ByteBuffer allocateByteBuffer(int _size){
		ByteBuffer bb = ByteBuffer.allocateDirect(_size);
		bb.order(ByteOrder.nativeOrder());
		return bb;
	}
	public void setCamera(float _x, float _y, float _z){
		xCamera = _x;
		yCamera = _y;
		zCamera = _z;
	}
	public void setLightPosition(float _x, float _y, float _z){
		xLightPosition = _x;
		yLightPosition = _y;
		zLightPosition = _z;
	}
//	private void openglObject(EGL10 egl) {
//		dpy = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
//		int[] version = new int[2];
//		egl.eglInitialize(dpy, version);
//		egl.eglInitialize(dpy, version);
//		int[] configSpec = { //#10
//				EGL10.EGL_RED_SIZE, 5,
//				EGL10.EGL_GREEN_SIZE, 6,
//				EGL10.EGL_BLUE_SIZE, 5,
//				EGL10.EGL_DEPTH_SIZE, 16,
//				EGL10.EGL_NONE
//		};
//		EGLConfig[] configs = new EGLConfig[1];
//		int[] num_config = new int[1];
//		egl.eglChooseConfig(dpy, configSpec, configs, 1, num_config);
//		EGLConfig config = configs[0];
//		context = egl.eglCreateContext(dpy, config, EGL10.EGL_NO_CONTEXT, null); //#11
//		surface = null;
//		gl = null;
//	}
	
	public EGLSurface getSurface(){
		return surface;
	}
	
 	public FloatBuffer getVertexBuffer(){
		if(vertexBuffer == null){
			getVertex();
		}
		return vertexBuffer;
	}
	
	public ShortBuffer getIndexBufferShort(){
		return indexBuffer;
	}
	
	public int getSizeIndex(){
		return sizeIndex;
	}
	
	public abstract boolean initialisation();
	/**
	 * 	use setDxDz insted
	 * @param _dx
	 */
	public void setDxDz(float _dx, float _dz){
		dx = _dx;
		dz = _dz;	
		axisLayout();
	}
	
	public void axisLayout(){
		try{
			for (int i=0; i<=imax; i++){
				x[i]=x0+i*dx; 
			}
			for (int j=0; j<=jmax; j++){
				z[j]=z0+j*dz;
			}
		}catch(Exception e){
			System.exit(-1);
		}
	}
	
	@Deprecated
	public void setDx(float _dx){
		dx = _dx;
	}
	@Deprecated
	public void setDz(float _dz){
		dz = _dz;
	}
	
	public void setX0Z0(float _x0, float _z0){
		x0 = _x0;
		z0 = _z0;
		axisLayout();
	}
	@Deprecated
	public void setX0(int _num){
		x0 = _num;
	}
	@Deprecated
	public void setZ0(int _num){
		z0 = _num;
	}
	
	public void setIJmax(int _imax, int _jmax){
		imax = _imax;
		jmax = _jmax;
		
		try{
			if(reinitialisation()){				
			}else{
				throw new Exception();// out of memory exception
			}
		}catch(Exception e){
			
		}
	}
	/**
	 * 	use setIJmax insted
	 * 
	 * @param _jmax
	 */
	@Deprecated
	public void setJmax(int _jmax){
		jmax = _jmax;
	}

	abstract FloatBuffer getVertex();

	protected short[] getIndex(){ 
		//			Например, для i=3, j=2 функция chain возвратит 13, т.к. в нашем примере imax=4.
		//			Приступим к расчету массива индексов:
		int k=0;
		int j=0;				

		sizeIndex = 2*(imax+1)*jmax + (jmax-1);
		indexBuffer = allocateByteBuffer(sizeIndex*2).asShortBuffer();
		index = new short[sizeIndex];
		//			index = new short[imax*jmax];
		while (j < jmax) {
			// лента слева направо
			for (int i = 0; i <= imax; i++) {
				index[k] = chain(j,i);
				k++;
				index[k] = chain(j+1,i);
				k++;
			}
			if (j < jmax-1){
				// вставим хвостовой индекс для связки
				index[k] = chain(j+1,imax);
				k++;
			}
			// переводим ряд
			j++;
			// проверяем достижение конца
			if (j < jmax){
				// лента справа налево
				for (int i = imax; i >= 0; i--) {
					index[k] = chain(j,i);
					k++;
					index[k] = chain(j+1,i);
					k++;
				}
				if (j < jmax-1){
					// вставим хвостовой индекс для связки
					index[k] = chain(j+1,0);
					k++;
				}
				// переводим ряд
				j++;
			}
		}
		return index;
	}
	
	private short chain(int j, int i){
		return (short) (i+j*(imax+1));
	}
	protected void getNormal() {
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
}
