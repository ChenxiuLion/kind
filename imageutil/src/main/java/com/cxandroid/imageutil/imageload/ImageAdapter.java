package com.cxandroid.imageutil.imageload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cxandroid.imageutil.R;
import com.cxandroid.imageutil.imageloader.util.ImageDown;

public class ImageAdapter extends BaseAdapter {

	//public static Set<String> mSeletedImg=new HashSet<String>();
	public static  ArrayList<String> mImages = new ArrayList<String>();
	
	public Map<String, View> mImageViews = new HashMap<String, View>();
	public String mDirPath;
	public List<String> mImgPaths = new ArrayList<String>();
	public LayoutInflater mInflater;
	private TextView mDirCount;
	SelectImageActivity activity;
	private Hold mHold;
	public int maxCount = 5;
	
	public ImageAdapter(Context context,List<String> mDatas,String dirPath, TextView mDirCount)
	{
		activity = (SelectImageActivity) context;
	this.mDirPath=dirPath;
		if(mDatas!=null) {
			this.mImgPaths = mDatas;
		}
	this.mDirCount = mDirCount;
	mInflater=LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		
		
		return mImgPaths.size();
	}
	
	@Override
	public Object getItem(int position) {
		
		return mImgPaths.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {


			final Hold hold;
			convertView=mInflater.inflate(R.layout.item_gridview, null);
			hold=new Hold();
			hold.imageview=(ImageView) convertView.findViewById(R.id.selectimg_item_image);
			hold.imageButton=(ImageButton) convertView.findViewById(R.id.selectimg_item_imagebutton);
			convertView.setTag(hold);

		//重置状态
		hold.imageview.setImageResource(R.drawable.wl_chat_btn_sendimage);
		hold.imageButton.setImageResource(R.drawable.ck_false);
		hold.imageview.setColorFilter(null);
		
		ImageDown.getInstance(3, ImageDown.Type.LIFO).loadImage(mDirPath+"/"+mImgPaths.get(position), hold.imageview);
		 String filePatha=mDirPath+"/"+mImgPaths.get(position);
		hold.imageview.setTag(filePatha);
		hold.imageview.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				String filePath = (String) v.getTag();
			//已经被选择
			 if(mImages.contains(filePath))
				 {
/*				 	
				 	mSeletedImg.remove(filePath);*/
				 	romevImage(filePath);
				 	activity.reseImagesLin();
				 	hold.imageview.setColorFilter(null);
					hold.imageButton.setImageResource(R.drawable.ck_false);			
				 
				 }else//未被选择
					{
					 if(mImages.size()<maxCount )
					 {
						 Log.e("TAG", filePath);
						 
						 activity.addImage(filePath);
					 mImages.add(filePath);
					 hold.imageview.setColorFilter(Color.parseColor("#77000000"));
					 hold.imageButton.setImageResource(R.drawable.ck_true);
					 }
					 else	if(maxCount ==1 && mHold != null)
					 {
						 		mImages.clear();
						 		activity.addImage(filePath);
						 		mImages.add(filePath);
					 			hold.imageview.setColorFilter(Color.parseColor("#77000000"));
					 			hold.imageButton.setImageResource(R.drawable.ck_true);
							 	activity.reseImagesLin();
							 	mHold.imageview.setColorFilter(null);
							 	mHold.imageButton.setImageResource(R.drawable.ck_false);	
					 }
						}

				mDirCount.setText(mImages.size()+"/"+maxCount);
					mHold = hold;
			}

			
		});
		
		if(mImages.contains(filePatha))
		{
			hold.imageview.setColorFilter(Color.parseColor("#77000000"));
			hold.imageButton.setImageResource(R.drawable.ck_true);
		}
		return convertView;

	}
	private class Hold{
		ImageView imageview,isGif;
		ImageButton imageButton;
	}
	
	public void romevImage(String path)
	{
	 	for(int i = 0 ;i<mImages.size();i++)
	 	{
	 		if(mImages.get(i).equals(path))
	 		{
	 			mImages.remove(i);
	 		}
	 	}
		mDirCount.setText(mImages.size()+"/"+maxCount);
	}
}



