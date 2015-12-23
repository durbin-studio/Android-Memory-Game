package com.eldad.memorygame;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class Flip3dAnimation extends Animation {

	private int _fromDegrees;
	private int _toDegrees;
	private float _centerX;
	private float _centerY;
	private Camera _camera;

	public Flip3dAnimation(int fromDegrees, int toDegrees, float centerX, float centerY) {
		_fromDegrees = fromDegrees;
		_toDegrees = toDegrees;
		_centerX = centerX;
		_centerY = centerY;
	}
	
	@Override
	public void initialize(int width, int height, int parentWidth, int parentHeight) {
		
		super.initialize(width, height, parentWidth, parentHeight);
		_camera = new Camera();
	}
	
	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		
		final float fromDegrees = _fromDegrees;
		float degrees = fromDegrees + ((_toDegrees - _fromDegrees) * interpolatedTime);
		final float centerX = _centerX;
		final float centerY = _centerY;
		final Camera camera = _camera;
		
		final Matrix matrix = t.getMatrix();
		
		camera.save();
		
		camera.rotateY(degrees);
		
		camera.getMatrix(matrix);
		camera.restore();
		
		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);
		
	}
}
