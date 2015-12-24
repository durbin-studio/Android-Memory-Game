package com.eldad.memorygame;

import java.util.ArrayList;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

public class MainFragment extends Fragment implements IHaveSetup {
	
	public static Integer ColumnCount = 3;
	
	private View _view;
	private GridView _gridView;
	private ImageViewAdapter _arrayAdapter;
	private ArrayList<Integer> _arrayList;
	private MemoryGameEngine _engine;
	
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
		
		_engine = MemoryGameEngine.GetInstance();
		Setup();
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

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

				if (_engine.GetSecondCard() != null)
					return;
				
				FrameLayout container = (FrameLayout)arg1;
				ImageView covered = (ImageView)container.getChildAt(0);
				ImageView uncovered = (ImageView)container.getChildAt(1);
				Integer cardInteger = _arrayList.get(arg2);
				
				GameCard gameCard = _engine.CreateIfNotExists(covered, uncovered, cardInteger);
				if (gameCard.GetIsCovered() == false)
					return;
				
				if (_engine.GetFirstCard() == null)
					_engine.SetFirstCard(covered);
				else
					_engine.SetSecondCard(covered);
				
				gameCard.SetCoveredUncoveredState(false);
				covered.setVisibility(View.VISIBLE);
				uncovered.setVisibility(View.GONE);
				
				_engine.ApplyAnimation(covered, uncovered, true, new SwapView(covered, uncovered, true));
			}
		});
	}
}
