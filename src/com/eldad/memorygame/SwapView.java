package com.eldad.memorygame;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class SwapView implements Runnable {

	private ImageView _imageToRotate;
	private ImageView _secondImage;
	private boolean _isCoveredImage;

	public SwapView(ImageView imageToRotate, ImageView secondImage, boolean isCoveredImage) {
		_imageToRotate = imageToRotate;
		_secondImage = secondImage;
		_isCoveredImage = isCoveredImage;
	}

	@Override
	public void run() {
		final float centerX = _imageToRotate.getWidth() / 2.0f;
		final float centerY = _secondImage.getWidth() / 2.0f;
		
		Flip3dAnimation rotation;
		
		_secondImage.setVisibility(View.GONE);
		_imageToRotate.setVisibility(View.VISIBLE);
		_imageToRotate.requestFocus();
		
		if (_isCoveredImage)
			rotation = new Flip3dAnimation(-90, 0, centerX, centerY);
		else
			rotation = new Flip3dAnimation(90, 0, centerX, centerY);
		
		rotation.setStartOffset(500);
		rotation.setDuration(500);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new DecelerateInterpolator());
		SetAnimationListener(rotation);
		
		_secondImage.startAnimation(rotation);
		
		MemoryGameEngine engine = MemoryGameEngine.GetInstance();
		if (engine.GetFirstCard() == null)
			engine.SetFirstCard(_imageToRotate);
		else
			engine.SetSecondCard(_imageToRotate);
	}

	protected void SetAnimationListener(Flip3dAnimation rotation) {
		
		rotation.setAnimationListener(new CheckStateAfterFlip());
	}

}
