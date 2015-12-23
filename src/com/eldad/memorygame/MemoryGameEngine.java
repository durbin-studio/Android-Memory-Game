package com.eldad.memorygame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

public class MemoryGameEngine {

	private static MemoryGameEngine _instance;
	
	private Map<ImageView, GameCard> _mapping = new HashMap<ImageView, GameCard>();

	private ImageView _imageViewFirstCard;
	private ImageView _imageViewSecondCard;

	public static MemoryGameEngine GetInstance() {
		if (_instance == null)
			_instance = new MemoryGameEngine();
		return _instance;
	}
	
	protected MemoryGameEngine() {
		
		ResetCards();
	}
	
	public void CreateIfNotExists(ImageView imageViewCovered, ImageView imageViewUnCovered, Integer imageToDisplayResource){
		
		if (!_mapping.containsKey(imageViewCovered)){
			new GameCard(imageViewCovered, imageViewUnCovered, imageToDisplayResource, true);
		}
	}

	public void CheckWinState() {
		
		
	}

	public void HideAndResetCards() {
		CoverOpenCards();
		ResetCards();
	}

	public void ResetCards() {
		_imageViewFirstCard = null;
		_imageViewSecondCard = null;
	}

	private void CoverOpenCards() {
		GameCard firstCard = GetFirstCard();
		ApplyAnimation(firstCard.GetUnCoveredImage(), firstCard.GetCoveredImage(), firstCard.GetIsCovered());
		GameCard secondCard = GetSecondCard();
		ApplyAnimation(secondCard.GetUnCoveredImage(), secondCard.GetCoveredImage(), secondCard.GetIsCovered());
	}
	
	public void SetSecondCard(ImageView imageView) {
		_imageViewFirstCard = imageView;
	}

	public void SetFirstCard(ImageView imageView) {
		_imageViewSecondCard = imageView;
	}

	public GameCard GetFirstCard() {
		return _mapping.get(_imageViewFirstCard);
	}

	public GameCard GetSecondCard() {
		return _mapping.get(_imageViewSecondCard);
	}

	public void ApplyAnimation(ImageView imageToRotate, ImageView secondImage, boolean isCoveredImage){
		
		final float centerX = imageToRotate.getWidth() / 2.0f;
		final float centerY = secondImage.getHeight() / 2.0f;
		
		Flip3dAnimation rotation = new Flip3dAnimation(0, 90, centerX, centerY);
		rotation.setDuration(500);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(imageToRotate, secondImage, isCoveredImage));
		
		imageToRotate.startAnimation(rotation);
	}
	
	public boolean IsFirstCardUnCovered(){
		return GetFirstCard().GetIsCovered();
	}
	
	public boolean ValidateTwoCardAerSame(){
		return GetFirstCard().GetCurrentCardImageNumber().intValue() == GetSecondCard().GetCurrentCardImageNumber().intValue();
	}
	
	public List<Integer> GetCardsImageReference(){
		List<Integer> list = new ArrayList<Integer>();

		list.add(R.drawable.ball);
		list.add(R.drawable.ball);

		list.add(R.drawable.boat);
		list.add(R.drawable.boat);
		
		list.add(R.drawable.castle);
		list.add(R.drawable.castle);

		list.add(R.drawable.crab);
		list.add(R.drawable.crab);
		
		list.add(R.drawable.fish);
		list.add(R.drawable.fish);

		list.add(R.drawable.kite);
		list.add(R.drawable.kite);
		
		list.add(R.drawable.rocket);
		list.add(R.drawable.rocket);

		list.add(R.drawable.slippers);
		list.add(R.drawable.slippers);

		list.add(R.drawable.star);
		list.add(R.drawable.star);
		
		Collections.shuffle(list);
		
		return list;
	}
	
}