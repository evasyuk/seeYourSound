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
import android.os.SystemClock;

public class MyClassRenderer implements GLSurfaceView.Renderer{
    // ��������� GLSurfaceView.Renderer ��������
    // ��� ������ onDrawFrame, onSurfaceChanged, onSurfaceCreated
    // ������� ������ ���� ��������������
	//
    // ������� ��������
    private Context context;
    //���������� ������
    private float x�amera, yCamera, zCamera;
    //���������� ��������� �����
    private float xLightPosition, yLightPosition, zLightPosition;
    //�������
    private float[] modelMatrix;
    private float[] viewMatrix;
    private float[] modelViewMatrix;
    private float[] projectionMatrix;
    private float[] modelViewProjectionMatrix;
    //������� �����    
    private int imax=49;
    private int jmax=49;
    //������ ���������� �������
    private int sizeindex;    
    //��������� ���������� x
    private float x0=-1f;
    //��������� ���������� z
    private float z0=-1f;
    //��� ����� �� ��� x
    private float dx=0.04f;
    //��� ����� �� ��� z
    private float dz=0.04f;
    // ������ ��� �������� ���������� x
    private float [] x;
    // ������ ��� �������� ���������� y
    private float [][] y;
    //������ ��� �������� ���������� z
    private float [] z;
    //������ ��� �������� ��������� ������ ��� ������ � �����
    private float [] vertex;
    //������� ��� �������� ��������� ������� �������
    private float [][] normalX;
    private float [][] normalY;
    private float [][] normalZ;
    //������ ��� �������� ��������� ������� ������� ��� ������ � �����
    private float [] normal;    
    //������ ��� ��������� ������ � �������� 
    private FloatBuffer vertexBuffer, normalBuffer;
    //����� ��������
    private ShortBuffer indexBuffer;    
    //��������� ������
    private Shader mShader;
        
    //����������� 
    public MyClassRenderer(Context context) {
            // �������� ��������
            // �� ��� ����������� � ������� ��� �������� �������
            this.context=context;
            //���������� ��������� ��������� �����            
            xLightPosition=5f;
            yLightPosition=30f;
            zLightPosition=5f;                        
            //�������
            modelMatrix=new float[16];
            viewMatrix=new float[16];
            modelViewMatrix=new float[16];
            projectionMatrix=new float[16];
            modelViewProjectionMatrix=new float[16];
            //�� �� ����� ������� �������
            //������� ���������� ��������� ������� �� ���������
            Matrix.setIdentityM(modelMatrix, 0);
            //���������� ������          
            x�amera=0.3f;
            yCamera=1.7f;
            zCamera=1.5f;                        
            //����� ������ ������� �� ������ ���������
            //� ���� � ������ ����� ����� ��� Y
            //���� ���������� ������ �������� ������� ����
            Matrix.setLookAtM(
                   viewMatrix, 0, x�amera, yCamera, zCamera, 0, 0, 0, 0, 1, 0);           
            // ������� ������� ���� �� ������� ������
            // �������� ������� ������-����
            Matrix.multiplyMM(modelViewMatrix, 0, viewMatrix, 0, modelMatrix, 0);
            // ������� �������                           
            x=new float [imax+1];
            z=new float [jmax+1];
            y=new float [jmax+1][imax+1];
            vertex=new float[(jmax+1)*(imax+1)*3];            
            normalX=new float[jmax+1][imax+1];
            normalY=new float[jmax+1][imax+1];
            normalZ=new float[jmax+1][imax+1];
            normal=new float[(jmax+1)*(imax+1)*3];               
            //�������� ������� x � z ������������ �����            
            for (int i=0; i<=imax; i++){
            	x[i]=x0+i*dx;  
            }
            for (int j=0; j<=jmax; j++){
              	z[j]=z0+j*dz;           
            }      
            //�������� ����� ��� �������� ��������� ������
            // �� ����������� � ������ getVertex()
            ByteBuffer vb = ByteBuffer.allocateDirect((jmax+1)*(imax+1)*3*4);
            vb.order(ByteOrder.nativeOrder());
            vertexBuffer = vb.asFloatBuffer();
            vertexBuffer.position(0);
            //�������� ����� ��� �������� ��������� �������� ��������
            // �� ����������� � ������ getNormal()
            ByteBuffer nb = ByteBuffer.allocateDirect((jmax+1)*(imax+1)*3*4);
            nb.order(ByteOrder.nativeOrder());
            normalBuffer = nb.asFloatBuffer();
            normalBuffer.position(0);
            //�������
    		// ��������� ������ ��������
    		short[] index;
    		// 2*(imax+1) - ���������� �������� � �����
    		// jmax - ���������� ����
    		// (jmax-1) - ����������� ������� ��� ������ ���� 
    		sizeindex=2*(imax+1)*jmax + (jmax-1);
    		index = new short[sizeindex];
    		// ������ ������� �������� ��� ������
    		int k=0;
    		int j=0;
    		while (j < jmax) {
    			// ����� ����� �������
    			for (int i = 0; i <= imax; i++) {
    				index[k] = chain(j,i);
    				k++;
    				index[k] = chain(j+1,i);
    				k++;				
    			}
    			if (j < jmax-1){
    				// ������� ��������� ������ ��� ������
    				index[k] = chain(j+1,imax);
    				k++;
    			}
    			// ��������� ���
    			j++;
    			//
    			// ��������� ���������� �����
    			if (j < jmax){
    				// ����� ������ ������
    				for (int i = imax; i >= 0; i--) {
    					index[k] = chain(j,i);
    					k++;
    					index[k] = chain(j+1,i);
    					k++;				
    				}
    				if (j < jmax-1){
    					// ������� ��������� ������ ��� ������
    					index[k] = chain(j+1,0);
    					k++;
    				}
    				// ��������� ���
    				j++;
    			}
    		}            
    		// ����� �������� - ��� short �������� 2 �����
    		ByteBuffer bi = ByteBuffer.allocateDirect(sizeindex * 2);
    		bi.order(ByteOrder.nativeOrder());
    		indexBuffer = bi.asShortBuffer();
    		// ��������� ����� ��������
    		indexBuffer.put(index);
    		indexBuffer.position(0);
    		// ���������� ��������� ������ ��������,
    		// �.�. � ���������� ����� ������ ����� ��������
    		index = null;
    		//��������� ���������� ������� ������ � ��������
            getVertex();
            getNormal();      
    }//����� ������������

    
    // ��������������� �������
    // ���������� ���������� ����� ������� �� ��������� j � i 
	private short chain(int j, int i){
		return (short) (i+j*(imax+1));
	}
    
	//����� ��������� ������ ��������� ������
	private void getVertex(){		
		double time=System.currentTimeMillis();
        // �������� ������ Y ���������� �������
        for (int j=0; j<=jmax; j++){
                for (int i=0; i<=imax; i++){                	
                    y[j][i]=0.2f*(float)Math.cos(0.005*time+5*(z[j]+x[i]))*(float)Math.sin(0.005*time+5*(z[j]+x[i]));
                }
        }
        // �������� ������ ��������� vertex
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
        }
        //��������� ���������� ������ �� ������� vertex � �����
        vertexBuffer.put(vertex);
        vertexBuffer.position(0);
	}

	//����� ��������� ������ �������� �������� 
	//�� ��������� ����������� ������ 
   	private void getNormal(){
        for (int j=0; j<jmax; j++){
                for (int i=0; i<imax; i++){
                        normalX [j] [i] = - ( y [j] [i+1] - y [j] [i] ) * dz;
                        normalY [j] [i] = dx * dz;
                        normalZ [j] [i] = - dx * ( y [j+1] [i] - y [j] [i] );
                }
        }
        //������� ��� i=imax
        for (int j=0; j<jmax; j++){
                normalX [j] [imax] = ( y [ j ] [ imax -1] - y [ j ] [ imax] ) * dz;
                normalY [j] [imax] = dx * dz;
                normalZ [j] [imax] = - dx * ( y [ j+1 ] [ imax] - y [ j ] [ imax ] );
        }
        //������� ��� j=jmax
        for (int i=0; i<imax; i++){
                normalX [jmax] [ i ] = - ( y [ jmax ] [ i+1 ] - y [ jmax ] [ i ] ) * dz;
                normalY [jmax] [ i ] = dx * dz;
                normalZ [jmax] [ i ] = dx * ( y [ jmax-1 ] [ i ] - y [ jmax ] [ i ] );
        }
        //������� ��� i=imax � j=jmax
        normalX [jmax] [ imax ]= (y [ jmax] [ imax-1] - y [ jmax] [imax]) * dz;
        normalY [jmax] [ imax ] = dx * dz;
        normalZ [jmax] [ imax ] = dx * (y [jmax-1] [imax] - y[jmax ] [imax]);
        //������������ ���������� ������� ������� � ���������� ������ normal
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
        }
        //���������� ���������� ������ normal � ����� 
        normalBuffer.put(normal);
        normalBuffer.position(0); 
   	} // ����� ������ 
    

    //�����, ������� ����������� ��� ��������� �������� ������
    //� ��� �� ������� ������� �������� � ������� ������-����-��������
    public void onSurfaceChanged(GL10 unused, int width, int height) {
            // ������������� glViewport
            GLES20.glViewport(0, 0, width, height);
            float ratio = (float) width / height;
            float k=0.055f;
            float left = -k*ratio;
            float right = k*ratio;
            float bottom = -k;
            float top = k;
            float near = 0.1f;
            float far = 10.0f;
            // �������� ������� ��������
            Matrix.frustumM(projectionMatrix, 0, left, right, bottom, top, near, far);
            // ������� �������� ����������,
            // ������� ����� ����������� ������� ������-����-��������
            Matrix.multiplyMM(
                    modelViewProjectionMatrix, 0, projectionMatrix, 0, modelViewMatrix, 0);
    }
     
    //�����, ������� ����������� ��� �������� ������
    //����� �� ������� ��������� ������
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
       //�������� ���� �������
       GLES20.glEnable(GLES20.GL_DEPTH_TEST);
       //�������� ��������� ��������� ������
       GLES20.glEnable(GLES20.GL_CULL_FACE); 
       //�������� ����������� �������, ��� ���������� � �������
       GLES20.glHint(GLES20.GL_GENERATE_MIPMAP_HINT, GLES20.GL_NICEST);
 
      //������� ������� ��� ��������� 
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
            //�������� ��������� ������ 
            mShader=new Shader(vertexShaderCode, fragmentShaderCode);
            //������ ����� ������ � ��������� a_vertex � ��������� �������
            mShader.linkVertexBuffer(vertexBuffer);            
            //������ ����� �������� � ��������� a_normal � ��������� �������
            mShader.linkNormalBuffer(normalBuffer);
            //����� ��������� � �������� ����������� �� ��� ���,
            //���� �� ����� ��������� ��������� ������            
   }

    //�����, � ������� ����������� ��������� �����
    public void onDrawFrame(GL10 unused) { 
            //�������� � ��������� ������ ������� ������-����-�������� 
            mShader.linkModelViewProjectionMatrix(modelViewProjectionMatrix);
            //�������� � ��������� ������ ���������� ������
            mShader.linkCamera(x�amera, yCamera, zCamera);
           //�������� � ��������� ������ ���������� ��������� �����
            mShader.linkLightSource(xLightPosition, yLightPosition, zLightPosition);
            //��������� ���������� ������
            getVertex();
            //��������� ���������� ��������
            getNormal();
            //������� ����
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
            //������ �����������
    		GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, sizeindex,
    				GLES20.GL_UNSIGNED_SHORT, indexBuffer);	
    		
    }//����� ������
}//����� ������
