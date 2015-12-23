package com.eldad.memorygame;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
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
		
		ImageView imageViewCovered = (ImageView)((FrameLayout)view).getChildAt(0);
		ImageView imageViewUncovered = (ImageView)((FrameLayout)view).getChildAt(1);
		
		imageViewCovered.setImageResource(R.drawable.covered);
		SetHeightForImageView(imageViewCovered, (GridView)parent);
		SetHeightForImageView(imageViewUncovered, (GridView)parent);
		
		return view;
	}

	private void SetHeightForImageView(ImageView _imageView, GridView parent) {
		_imageView.setMinimumHeight(parent.getHeight() / (getCount() / MainFragment.ColumnCount));
	}
}
