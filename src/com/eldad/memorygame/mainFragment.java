package com.eldad.memorygame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainFragment extends Fragment implements IHaveSetup {
	
	public static Integer ColumnCount = 3;
	
	private View _view;
	private GridView _gridView;
	private ImageViewAdapter _arrayAdapter;
	private ArrayList<Integer> _arrayList;

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
		
		Setup();
	}

	@Override
	public void Setup() {
		_gridView = (GridView)_view.findViewById(R.id.mainGridView);
		_gridView.setNumColumns(ColumnCount);
		
		_arrayList = new ArrayList<Integer>();
		PopulateList();
		ShuffleList();
		
		_arrayAdapter = new ImageViewAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line, _arrayList);
		_gridView.setAdapter(_arrayAdapter);
		
		_gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				ImageView imageView = (ImageView)arg1;
				if (imageView != null){
					imageView.setImageResource(_arrayList.get(arg2));
				}
			}
		});
	}

	private void ShuffleList() {
		// TODO Auto-generated method stub
		Collections.shuffle(_arrayList, new Random(System.nanoTime()));
		Collections.shuffle(_arrayList, new Random(System.nanoTime()));
		Collections.shuffle(_arrayList, new Random(System.nanoTime()));
	}

	private void PopulateList() {
		_arrayList.add(R.drawable.ball);
		_arrayList.add(R.drawable.ball);

		_arrayList.add(R.drawable.boat);
		_arrayList.add(R.drawable.boat);
		
		_arrayList.add(R.drawable.castle);
		_arrayList.add(R.drawable.castle);

		_arrayList.add(R.drawable.crab);
		_arrayList.add(R.drawable.crab);
		
		_arrayList.add(R.drawable.fish);
		_arrayList.add(R.drawable.fish);

		_arrayList.add(R.drawable.kite);
		_arrayList.add(R.drawable.kite);
		
		_arrayList.add(R.drawable.rocket);
		_arrayList.add(R.drawable.rocket);

		_arrayList.add(R.drawable.slippers);
		_arrayList.add(R.drawable.slippers);

		_arrayList.add(R.drawable.star);
		_arrayList.add(R.drawable.star);
	}

}
