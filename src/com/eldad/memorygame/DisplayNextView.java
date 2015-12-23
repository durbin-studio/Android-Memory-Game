package com.eldad.memorygame;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class DisplayNextView implements AnimationListener {

	private ImageView _imageToRotate;
	private ImageView _secondImage;
	private boolean _isCoveredImage;

	public DisplayNextView(ImageView imageToRotate, ImageView secondImage, boolean isCoveredImage) {
		_imageToRotate = imageToRotate;
		_secondImage = secondImage;
		_isCoveredImage = isCoveredImage;
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		_imageToRotate.post(new SwapView(_imageToRotate, _secondImage, _isCoveredImage));
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		
	}

	@Override
	public void onAnimationStart(Animation arg0) {
		
	}

}
