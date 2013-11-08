package com.bionic.opengl;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;

public class DemoSurface extends openglObject {

	@Override
	void drawFrame(GL10 gl) {
		mShader.linkModelViewProjectionMatrix(modelViewProjectionMatrix);
		mShader.linkCamera(xCamera, yCamera, zCamera);
		mShader.linkLightSource(xLightPosition, yLightPosition, zLightPosition);
		getVertex();
		getNormal();
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

		GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, sizeIndex,
				GLES20.GL_UNSIGNED_SHORT, indexBuffer);
	}

	@Override
	public boolean initialisation() {
		return true;
	}

	@Override
	FloatBuffer getVertex() {
		if((vertex == null)||(vertex.length<(jmax+1)*(imax+1)*3)){
			vertex = new float[(jmax+1)*(imax+1)*3];
		}

		try{// заполним массив Y значениями функции
			for (int j=0; j<=jmax; j++){
				for (int i=0; i<=imax; i++){
					y[j][i]=(float)Math.exp(-3*(x[i]*x[i]+z[j]*z[j]));
				}
			}
		}catch(Exception e){
			//to do here
		}
		// заполним массив координат vertex
		int k=0;
		for (int j=0; j<jmax; j++){
			for (int i=0; i<imax; i++){
				vertex[k]=x[i];
				k++;
				vertex[k]=y[j][i];
				k++;
				vertex[k]=z[j];
				k++;
			}
		}
		//перепишем координаты вершин из массива vertex в буфер
		vertexBuffer = allocateByteBuffer((jmax+1)*(imax+1)*3*4).asFloatBuffer();		
		vertexBuffer.put(vertex);
		vertexBuffer.position(0);
		return vertexBuffer;
	}

}
