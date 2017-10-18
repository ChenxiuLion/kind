package com.youqd.kind.ckind.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shizhefei.fragment.LazyFragment;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.activity.BaiduActivity;
import com.youqd.kind.ckind.activity.ImageActivity;
import com.youqd.kind.ckind.activity.WorkActivity;
import com.youqd.kind.ckind.adapter.CourseAdapter;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.CheckList;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import java.io.Serializable;
import java.util.ArrayList;

import tm.dwcorephoto.PreviewImage;
import tm.model.ImageBDInfo;
import tm.model.ImageInfo;
import tm.model.MainInfo;

public class WorkFragment extends LazyFragment {
	private String tabIndex;
	private ListView courseListView = null;
	private CourseAdapter courseAdapter = null;
	public static final String INTENT_INT_INDEX = "intent_int_index";

	private ListView mListView;

	private CheckList mData;
	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_work_item);
		mListView = (ListView) findViewById(R.id.listview);
		tabIndex = getArguments().getString(INTENT_INT_INDEX);
		HttpTool.getInstance().getBabyQiandao(tabIndex, getBaby().getCardNO(), getBaby().getClassID() + "",
				getBaby().getID() + ":", new KingCallback<CheckList>(CheckList.class) {
					@Override
					public void onSucceed(final CheckList data) {

						if(data.getResultData().size() == 0){
							if(getBaby().getAttendanceMachineID()!=0) {
								CheckList.ResultDataBean bean = new CheckList.ResultDataBean();
								bean.setCheckType(100);
								data.getResultData().add(bean);
							}else {

								findViewById(R.id.nodata).setVisibility(View.VISIBLE);
							}
						}
						mData= data;
						if(data.getResultData().size()>0) {
							mListView.setAdapter(new BaseAdapter() {
								@Override
								public int getCount() {
									return data.getResultData().size();
								}

								@Override
								public Object getItem(int position) {
									return null;
								}

								@Override
								public long getItemId(int position) {
									return 0;
								}

								@Override
								public View getView(int position, View convertView, ViewGroup parent) {
									CheckList.ResultDataBean bean = data.getResultData().get(position);

									if (bean.getCheckType() == 3) {
										//校车
										convertView = LayoutInflater.from(getActivity()).inflate(R.layout.daka_bas_item, null);
										TextView dateTv = (TextView) convertView.findViewById(R.id.daka_item_date);
										convertView.findViewById(R.id.dingwei_item).setOnClickListener(new View.OnClickListener() {
											@Override
											public void onClick(View v) {
												startActivity(new Intent(getActivity(), BaiduActivity.class));
											}
										});
										dateTv.setText(bean.getCheckTime());
									}else if (bean.getCheckType() == 100) {
										//请假
										convertView = LayoutInflater.from(getActivity()).inflate(R.layout.daka_basno_item, null);
										convertView.findViewById(R.id.dingwei_item).setOnClickListener(new View.OnClickListener() {
											@Override
											public void onClick(View v) {
												startActivity(new Intent(getActivity(), BaiduActivity.class));
											}
										});
									}
									else if (bean.getCheckType() == 4) {
										//请假
										convertView = LayoutInflater.from(getActivity()).inflate(R.layout.daka_qingjia_item, null);
										TextView dateTv = (TextView) convertView.findViewById(R.id.date_tv);
										dateTv.setText(bean.getCheckTime());
									} else {
										//学校
										convertView = LayoutInflater.from(getActivity()).inflate(R.layout.daka_school_item, null);
										TextView dateTv = (TextView) convertView.findViewById(R.id.date_tv);
										TextView school_item_type = (TextView) convertView.findViewById(R.id.school_item_type);
										ImageView image = (ImageView) convertView.findViewById(R.id.image_view);
										ImageInfo imageInfo = new ImageInfo();
										imageInfo.url = Constant.IMAGE_URL + bean.getPhoto();
										image.setTag(imageInfo);
										image.setOnClickListener(new View.OnClickListener() {
											@Override
											public void onClick(View v) {
												ImageInfo imageInfo = (ImageInfo) v.getTag();
												Intent intent = new Intent(getActivity(), ImageActivity.class);
												intent.putExtra("image", imageInfo.url);
												startActivity(intent);
											}
										});
										dateTv.setText(bean.getCheckTime());
										school_item_type.setText(bean.getCheckType() == 2 ? "离园" : "入园");
										ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + bean.getPhoto(), image, KingActivity.getOptions());
									}

									return convertView;
								}
							});
						}else{
							findViewById(R.id.nodata).setVisibility(View.VISIBLE);
						}
					}

					@Override
					public void onErro() {

					}
				});
	}

	@Override
	public void onDestroyViewLazy() {
		super.onDestroyViewLazy();
	}

	public LoginBean.AllBaByBean getBaby() {
		return ((KingActivity)getActivity()).getBaby();
	}
}
