package com.eldad.memorygame;

import android.widget.ImageView;

public class SwapViewNoListener extends SwapView {

	public SwapViewNoListener(ImageView imageToRotate, ImageView secondImage, boolean isCoveredImage) {
		super(imageToRotate, secondImage, isCoveredImage);
		
	}

	@Override
	protected void SetAnimationListener(Flip3dAnimation rotation) {
		// do no add animation listener here
	}
}
