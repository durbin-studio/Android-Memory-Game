package com.eldad.memorygame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

public class MemoryGameEngine {

	private static MemoryGameEngine _instance;
	
	private Map<ImageView, GameCard> _mapping;
	private ImageView _imageViewFirstCard;
	private ImageView _imageViewSecondCard;

	public static MemoryGameEngine GetInstance() {
		if (_instance == null)
			_instance = new MemoryGameEngine();
		return _instance;
	}
	
	protected MemoryGameEngine() {
		
		_mapping = new HashMap<ImageView, GameCard>();
		ResetCards();
	}
	
	public GameCard CreateIfNotExists(ImageView imageViewCovered, ImageView imageViewUnCovered, Integer imageToDisplayResource){
		
		if (!_mapping.containsKey(imageViewCovered)){
			GameCard gameCard = new GameCard(imageViewCovered, imageViewUnCovered, imageToDisplayResource);
			_mapping.put(imageViewCovered, gameCard);
		}
		
		GameCard gameCard = _mapping.get(imageViewCovered);
		gameCard.SetCoveredUncoveredState(true);
		return gameCard;
	}

	private boolean CheckWinState(){
		for (Map.Entry<ImageView, GameCard> item : _mapping.entrySet()) {
			if (item.getValue().GetIsCovered())
				return false;
		}
		return true;
	}
	
	public void CheckIfTwoCardAreSame() {
		GameCard firstCard = GetFirstCard();
		GameCard secondCard = GetSecondCard();
		
		if (firstCard == null || secondCard == null)
			return;
		
		if (AreTwoCardSame() == false){
			CoverOpenCards();
		}
		if (CheckWinState()){
			Toast.makeText(MainActivity.GetApplicationContext(), "You win!!!", Toast.LENGTH_SHORT).show();
		}

		ResetCards();
		return;
	}

	public void HideAndResetCards() {
		CoverOpenCards();
		ResetCards();
	}

	private void ResetCards() {
		_imageViewFirstCard = null;
		_imageViewSecondCard = null;
	}

	private void CoverOpenCards() {
		
		GameCard firstCard = GetFirstCard();
		ImageView firstCardUncoveredImage = firstCard.GetUnCoveredImage();
		ImageView firstCardCoveredImage = firstCard.GetCoveredImage();
		boolean firstCardIsCovered = firstCard.GetIsCovered();
		ApplyAnimation(firstCard.GetUnCoveredImage(), firstCard.GetCoveredImage(), firstCard.GetIsCovered(), new SwapViewNoListener(firstCardUncoveredImage, firstCardCoveredImage, firstCardIsCovered));
		firstCard.SetCoveredUncoveredState(true);
		
		GameCard secondCard = GetSecondCard();
		ImageView secondCardUncoveredImage = secondCard.GetUnCoveredImage();
		ImageView secondCardCoveredImage = secondCard.GetCoveredImage();
		boolean secondCardIsCovered = secondCard.GetIsCovered();
		ApplyAnimation(secondCard.GetUnCoveredImage(), secondCard.GetCoveredImage(), secondCard.GetIsCovered(), new SwapViewNoListener(secondCardUncoveredImage, secondCardCoveredImage, secondCardIsCovered));
		secondCard.SetCoveredUncoveredState(true);
	}

	public void SetFirstCard(ImageView imageView) {
		_imageViewFirstCard = imageView;
	}

	public void SetSecondCard(ImageView imageView) {
		_imageViewSecondCard = imageView;
	}

	public GameCard GetFirstCard() {
		return _mapping.get(_imageViewFirstCard);
	}

	public GameCard GetSecondCard() {
		return _mapping.get(_imageViewSecondCard);
	}

	public void ApplyAnimation(ImageView imageToRotate, ImageView secondImage, boolean isCoveredImage, SwapView swapView){
		
		final float centerX = imageToRotate.getWidth() / 2.0f;
		final float centerY = secondImage.getHeight() / 2.0f;
		
		Flip3dAnimation rotation = new Flip3dAnimation(0, 90, centerX, centerY);
		rotation.setDuration(500);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(imageToRotate, swapView));
		
		imageToRotate.startAnimation(rotation);
	}
	
	private boolean AreTwoCardSame(){
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