package com.eldad.memorygame;

import java.util.ArrayList;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;

public class MainFragment extends Fragment implements IHaveSetup {
	
	public static Integer ColumnCount = 3;
	
	private View _view;
	private GridView _gridView;
	private ImageViewAdapter _arrayAdapter;
	private ArrayList<Integer> _arrayList;
	private MemoryGameEngine _engine;

	private Handler _handler;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		_view = inflater.inflate(R.layout.main_fragment, container, false);
		
		return _view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		_engine = new MemoryGameEngine();
		Setup();
		
		_handler = new Handler();
	}

	@Override
	public void Setup() {
		_gridView = (GridView)_view.findViewById(R.id.mainGridView);
		_gridView.setNumColumns(ColumnCount);
		
		_arrayList = new ArrayList<Integer>();
		_arrayList.addAll(_engine.GetCardsImageReference());
		
		_arrayAdapter = new ImageViewAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line, _arrayList);
		_gridView.setAdapter(_arrayAdapter);
		
		_gridView.setOnItemClickListener(new OnItemClickListener() {

			private ImageView _firstImageView;
			private ImageView _secondImageView;

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

				final ImageView imageView = (ImageView)arg1;
				final Integer cardInteger = _arrayList.get(arg2);
				
				Runnable runnableOffMain = new Runnable(){

					@Override
					public void run() {
						if (imageView != null){
							
							_handler.post(new Runnable() {
								
								@Override
								public void run() {
									imageView.setImageResource(cardInteger);
								}
							});
							
							if (_engine.IsFirstCardFlipped() == false){
								
								_engine.SetFirstCardVisible(cardInteger);
								_firstImageView = imageView;
							}
							else{
								
								_engine.SetSecondCardVisible(cardInteger);
								_secondImageView = imageView;
								
								if (_engine.ValidateTwoCardAerSame()){

									_engine.ResetCards();
									return;
								}
								else{

									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									_engine.ResetCards();
									
									_handler.post(new Runnable() {
										
										@Override
										public void run() {
											_firstImageView.setImageResource(R.drawable.covered);
											_secondImageView.setImageResource(R.drawable.covered);
										}
									});
								}
							}
						}
					}
				};
				new Thread(runnableOffMain).start();
			}
		});
	}
}
