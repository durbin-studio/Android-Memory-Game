package com.eldad.memorygame;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class mainFragment extends Fragment {
	
	private View _view;
	private GridView _gridView;
	private ArrayAdapter<String> _arrayAdapter;
	private ArrayList<String> _arrayList;

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
		
		_gridView = (GridView)_view.findViewById(R.id.mainGridView);
		_gridView.setNumColumns(3);
		
		_arrayList = new ArrayList<String>();
		for (int index = 0; index < 9; index++) {
			_arrayList.add("item id " + index);
		}
		
		_arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, _arrayList);
		_gridView.setAdapter(_arrayAdapter);
		
		_gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), _arrayList.get(arg2), Toast.LENGTH_SHORT).show();
			}
		});
	}

}
