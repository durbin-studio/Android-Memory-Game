package com.eldad.memorygame;

import java.util.List;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class ImageViewAdapter extends ArrayAdapter<Integer> {

	public ImageViewAdapter(Context context, int resource, List<Integer> items) {
		super(context, resource, items);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = convertView;
		
		if (view == null){
			LayoutInflater vi = LayoutInflater.from(getContext());
			view = vi.inflate(R.layout.card_frame_layout, parent, false);
		}
		
		if (view instanceof FrameLayout == false)
			return null;
		
		ImageView imageView = (ImageView)((FrameLayout)view).getChildAt(1);
		
		imageView.setImageResource(R.drawable.covered);
		SetHeightForImageView(imageView);
		
		return view;
	}

	private void SetHeightForImageView(ImageView _imageView2) {
		DisplayMetrics metrics = new DisplayMetrics();
		GetCurrentWindowMetrics(metrics);
		_imageView2.setMinimumHeight((metrics.heightPixels - 30) / (getCount() / MainFragment.ColumnCount));
	}

	private void GetCurrentWindowMetrics(DisplayMetrics metrics) {
		WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(metrics);
	}
}
