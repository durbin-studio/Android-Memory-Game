package com.eldad.memorygame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryGameEngine {

	private Integer _firstCard;
	private Integer _secondCard;
	
	public MemoryGameEngine() {
		// TODO Auto-generated constructor stub
		ResetCards();
	}

	public void ResetCards() {
		_firstCard = -1;
		_secondCard = -1;
	}
	
	public boolean IsFirstCardFlipped(){
		return _firstCard != -1;
	}
	
	public boolean ValidateTwoCardAerSame(){
		return _firstCard.intValue() == _secondCard.intValue();
	}
	
	public void SetFirstCardVisible(Integer cardVisible){
		_firstCard = cardVisible;
	}

	public void SetSecondCardVisible(Integer cardVisible){
		_secondCard = cardVisible;
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