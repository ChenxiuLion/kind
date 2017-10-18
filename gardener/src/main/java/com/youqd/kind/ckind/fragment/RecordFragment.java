package com.youqd.kind.ckind.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cosfund.library.util.LogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.activity.BBInfoActivity;
import com.youqd.kind.ckind.activity.LeaveActivity;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.AllBoby;
import com.youqd.kind.ckind.bean.CheckList;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.model.RecordInfo;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UserManage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class RecordFragment extends KingFragment {

	private String tabIndex;

	private GridView mGridviewl;
	private AllBoby mAllboby;
	public static final String INTENT_INT_INDEX = "intent_int_index";

	@Override
	public int initLayout() {
		return R.layout.fragment_record;
	}

	@Override
	public void initData() {
		tabIndex = getArguments().getString(INTENT_INT_INDEX);
		Logger.e("RecordFragment= "+tabIndex);
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simple2 = new SimpleDateFormat("M个月d天");
		mAllboby = UserManage.getInstance().getAllBoby();
		GetRecord getRecord = new GetRecord();

		mGridviewl = (GridView) findViewById(R.id.chen_grid);
		getRecord.setCheckDay(tabIndex);
		HttpTool.getInstance().getBabyQiandao(tabIndex, UserManage.getInstance().getUser().getClassID()+"",
				new KingCallback<CheckList>(CheckList.class) {
					@Override
					public void onSucceed(CheckList data) {
						tv1 = 0;
						tv2 = 0;
						tv3 =0;
						for(CheckList.ResultDataBean dataBean : data.getResultData()){
							updata(dataBean);
						}
						((TextView)findViewById(R.id.tv3_record)).setText(tv3+"");
						((TextView)findViewById(R.id.tv2_record)).setText(tv2+"");
						((TextView)findViewById(R.id.tv1_record)).setText(tv1+"");
						mGridviewl.setAdapter(mAdapter);
					}

					@Override
					public void onErro() {

					}
				});
	}
	private int tv1,tv2,tv3;
	public void updata(CheckList.ResultDataBean bean){
		for(AllBoby.ResultDataBean boby : mAllboby.getResultData()){
			if(bean.getCheckType()==1){
				if (boby.getID() == bean.getBabyID()) {
					boby.setTiwen(bean.getTemperature()+"");
					boby.setJiankang(bean.getHealthStatus());
					if(boby.getJiankang() == 1){
						tv1++;
					}
					if(boby.getJiankang() == 2){
						tv2++;
					}
					if(boby.getJiankang() == 3){
						tv3++;
					}
					return;
				}
			}
		}
	}
	private BaseAdapter mAdapter = new BaseAdapter() {
		@Override
		public int getCount() {
			return mAllboby.getResultData().size();
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			convertView= LayoutInflater.from(getActivity()).inflate(R.layout.henjian_item,null);

			TextView name = (TextView) convertView.findViewById(R.id.tiwen_chen);
			TextView image = (TextView) convertView.findViewById(R.id.name_chen);
			image.setText(mAllboby.getResultData().get(position).getUserName());
			name.setText(mAllboby.getResultData().get(position).getTiwen());

			if(mAllboby.getResultData().get(position).getJiankang() == 1){
				name.setBackgroundResource(R.drawable.shape_yuan_lv);
			}
			if(mAllboby.getResultData().get(position).getJiankang() == 2){
				name.setBackgroundResource(R.drawable.shape_yuan_huang);
			}
			if(mAllboby.getResultData().get(position).getJiankang() == 3){
				name.setBackgroundResource(R.drawable.shape_yuan_red);
				name.setTextColor(Color.WHITE);
			}
			return convertView;
		}
	};
	public class GetRecord{

		/**
		 * CheckDay : 2016-08-21
		 * CheckType : 1
		 * CheckTypeList : [1]
		 * CardNO : 1952480340
		 * BabyID : 1
		 */

		private String CheckDay;
		private int CheckType =1;
		private String CardNO;
		private int BabyID;
		private List<Integer> CheckTypeList = new ArrayList<>();

		public GetRecord(){
			CheckTypeList.add(1);
		}

		public String getCheckDay() {
			return CheckDay;
		}

		public void setCheckDay(String CheckDay) {
			this.CheckDay = CheckDay;
		}

		public int getCheckType() {
			return CheckType;
		}

		public void setCheckType(int CheckType) {
			this.CheckType = CheckType;
		}

		public String getCardNO() {
			return CardNO;
		}

		public void setCardNO(String CardNO) {
			this.CardNO = CardNO;
		}

		public int getBabyID() {
			return BabyID;
		}

		public void setBabyID(int BabyID) {
			this.BabyID = BabyID;
		}

		public List<Integer> getCheckTypeList() {
			return CheckTypeList;
		}

		public void setCheckTypeList(List<Integer> CheckTypeList) {
			this.CheckTypeList = CheckTypeList;
		}
	}

}
