package com.youqd.kind.ckind.fragment;


import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.activity.BBInfoActivity;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.model.RecordInfo;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class RecordFragment extends KingFragment {

	private String tabIndex;

	public static final String INTENT_INT_INDEX = "intent_int_index";

	@Override
	public int initLayout() {
		return R.layout.fragment_record;
	}

	@Override
	public void initData() {
		tabIndex = getArguments().getString(INTENT_INT_INDEX);
		Logger.e("RecordFragment= "+tabIndex);
		ImageView mUserIv = (ImageView) findViewById(R.id.baby_image);
		ImageLoader.getInstance().displayImage(Constant.IMAGE_URL+getBaby().getPhoto(),
				mUserIv,getOptions(R.drawable.icon_phone_image));
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simple2 = new SimpleDateFormat("M个月d天");
		String date = getBaby().getEntryDate().split(" ")[0];
		String newData = BBInfoActivity.getCurrentDate();
		try {
			long cTiem = simple.parse(newData).getTime() - simple.parse(date).getTime();
			String str = (BBInfoActivity.compareDate(date,null,2)>0?
					BBInfoActivity.compareDate(date,null,2)+"岁":"")+simple2.format(cTiem);
			((TextView)findViewById(R.id.date_tv)).setText(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		((TextView)findViewById(R.id.boby_info_name)).setText(getBaby().getUserName());
		GetRecord getRecord = new GetRecord();
		getRecord.setBabyID(getBaby().getID());
		getRecord.setCardNO(getBaby().getCardNO());
		getRecord.setCheckDay(tabIndex);
		HttpTool.getInstance().GetRecord(getRecord,
				new KingCallback<RecordInfo>(RecordInfo.class) {
					@Override
					public void onSucceed(RecordInfo data) {
						if(data!=null){
							if(data.getResultCode()==1){
								if(data.getResultData().size()>0) {
									if(data.getResultData().get(0).getHeight()!=0) {
										findViewById(R.id.shengao_layout).setVisibility(View.VISIBLE);
										findViewById(R.id.shengao_layoutv).setVisibility(View.VISIBLE);
										((TextView) findViewById(R.id.item_height_tv)).
												setText(data.getResultData().get(0).getHeight() + "CM");
									}
									if(data.getResultData().get(0).getWeight()!=0) {
										findViewById(R.id.tizhong_layout).setVisibility(View.VISIBLE);
										findViewById(R.id.tizhong_layoutv).setVisibility(View.VISIBLE);
										((TextView) findViewById(R.id.item_wihgt_tv)).
												setText(data.getResultData().get(0).getWeight() + "KG");
									}
									if(!TextUtils.isEmpty(data.getResultData().get(0).getCheckTime())) {
										findViewById(R.id.date_layout).setVisibility(View.VISIBLE);
										findViewById(R.id.date_layoutv).setVisibility(View.VISIBLE);
										((TextView) findViewById(R.id.item_date_tv))
												.setText(data.getResultData().get(0).getCheckTime());
									}
									if(data.getResultData().get(0).getTemperature()!=0) {
										findViewById(R.id.tiwen_layout).setVisibility(View.VISIBLE);
										findViewById(R.id.tiwen_layoutv).setVisibility(View.VISIBLE);
										((TextView) findViewById(R.id.item_tiwen_tv))
												.setText(data.getResultData().get(0).getTemperature()+"℃");
									}



									if(data.getResultData().get(0).getHealthStatus() == 1){
										((TextView) findViewById(R.id.content_item))
												.setText("健康");
									}
									if(data.getResultData().get(0).getHealthStatus() == 2){
										((TextView) findViewById(R.id.content_item))
												.setText("需注意");
									}
									if(data.getResultData().get(0).getHealthStatus() == 3){
										((TextView) findViewById(R.id.content_item))
												.setText("特别注意");
									}

								}else{
									((TextView) findViewById(R.id.content_item))
											.setText("未晨检");
									((TextView) findViewById(R.id.content_item)).setTextColor(Color.parseColor("#989898"));
								}
							}
						}
					}

					@Override
					public void onErro() {

					}
				});
	}


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

	public LoginBean.AllBaByBean getBaby() {
		return ((KingActivity)getActivity()).getBaby();
	}
}
