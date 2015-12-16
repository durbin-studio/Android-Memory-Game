package com.eldad.memorygame;

import java.util.List;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageViewAdapter extends ArrayAdapter<Integer> {

	public ImageViewAdapter(Context context, int resource, List<Integer> items) {
		super(context, resource, items);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View view = convertView;
		
		if (view == null){
			LayoutInflater vi = LayoutInflater.from(getContext());
			view = vi.inflate(R.layout.grid_item, parent, false);
		}
		
		ImageView imageView = (ImageView)view.findViewById(R.id.gridItem);
		
		imageView.setImageResource(R.drawable.uncovered);
		SetHeightForImageView(imageView);
		
		return view;
	}

	private void SetHeightForImageView(ImageView _imageView2) {
		DisplayMetrics metrics = new DisplayMetrics();
		GetCurrentWindowMetrics(metrics);
		_imageView2.setMinimumHeight(metrics.heightPixels / (getCount() / MainFragment.ColumnCount));
	}

	private void GetCurrentWindowMetrics(DisplayMetrics metrics) {
		WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(metrics);
	}
}
