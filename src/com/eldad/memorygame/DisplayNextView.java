package com.eldad.memorygame;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class DisplayNextView implements AnimationListener {

	private ImageView _imageToRotate;
	private SwapView _swapView;

	public DisplayNextView(ImageView imageToRotate, SwapView swapView) {
		_imageToRotate = imageToRotate;
		_swapView = swapView;
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		_imageToRotate.post(_swapView);
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		
	}

	@Override
	public void onAnimationStart(Animation arg0) {
		
	}

}
