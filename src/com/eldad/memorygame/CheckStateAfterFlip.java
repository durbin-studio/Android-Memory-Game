package com.eldad.memorygame;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class CheckStateAfterFlip implements AnimationListener {

	@Override
	public void onAnimationEnd(Animation animation) {
		MemoryGameEngine engine = MemoryGameEngine.GetInstance();
		engine.CheckIfTwoCardAreSame();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		
	}

}
