package com.youqd.kind.ckind.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shizhefei.fragment.LazyFragment;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.activity.BaiduActivity;
import com.youqd.kind.ckind.activity.ImageActivity;
import com.youqd.kind.ckind.activity.LeaveActivity;
import com.youqd.kind.ckind.adapter.CourseAdapter;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.AllBoby;
import com.youqd.kind.ckind.bean.CheckList;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UserManage;

import java.util.ArrayList;
import java.util.List;

import tm.model.ImageInfo;

public class WorkFragment extends LazyFragment {
	private String tabIndex;
	private ListView courseListView = null;
	private CourseAdapter courseAdapter = null;
	public static final String INTENT_INT_INDEX = "intent_int_index";

	private ListView mListView;

	private CheckList mData;

	private AllBoby mAllboby;

	private int mYiqian = 0;

	private List<CheckList> mDatas = new ArrayList<>();
	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_salary_list);
		mListView = (ListView) findViewById(R.id.recodeList);
		tabIndex = getArguments().getString(INTENT_INT_INDEX);
		mAllboby = UserManage.getInstance().getAllBoby();
	}

	@Override
	public void onDestroyViewLazy() {
		super.onDestroyViewLazy();
	}

	@Override
	protected void onResumeLazy() {
		mAllboby = UserManage.getInstance().getAllBoby();
		HttpTool.getInstance().getBabyQiandao(tabIndex, UserManage.getInstance().getUser().getClassID()+"",
				new KingCallback<CheckList>(CheckList.class) {
					@Override
					public void onSucceed(CheckList data) {
						mYiqian = 0;
						for(CheckList.ResultDataBean bean : data.getResultData()){
							updata(bean);
						}
						((TextView)findViewById(R.id.qiandao_yi)).setText(mYiqian+"");
						((TextView)findViewById(R.id.qiandao_wei)).setText(mAllboby.getResultData().size()+"");
						mListView.setAdapter(mAdapter);
					}

					@Override
					public void onErro() {

					}
				});
		super.onResumeLazy();
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
			convertView= LayoutInflater.from(getActivity()).inflate(R.layout.fragment_salary_item,null);

			TextView name = (TextView) convertView.findViewById(R.id.salary_name);
			TextView desc = (TextView)convertView.findViewById(R.id.salary_remark);
			ImageView image = (ImageView) convertView.findViewById(R.id.salary_img);
			name.setText(mAllboby.getResultData().get(position).getUserName());
			if(!TextUtils.isEmpty(mAllboby.getResultData().get(position).getDesc())){
				desc.setText(mAllboby.getResultData().get(position).getDesc());
				desc.setBackgroundColor(Color.WHITE);
				desc.setTextColor(Color.BLACK);
			}else{
				desc.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Bundle bundle = new Bundle();
						bundle.putSerializable("DATABABY",mAllboby.getResultData().get(position));
						bundle.putString("time",tabIndex);
						Intent intent = new Intent(getActivity(), LeaveActivity.class);
						intent.putExtras(bundle);
						startActivity(intent);
					}
				});
			}
			ImageLoader.getInstance().displayImage(Constant.IMAGE_URL+mAllboby.getResultData().get(position).getPhoto(),image,KingActivity.getOptions(R.drawable.icon_girl));
			return convertView;
		}
	};

	public void updata(CheckList.ResultDataBean bean){
		for(AllBoby.ResultDataBean boby : mAllboby.getResultData()){

			if(bean.getCheckType()==4){
				if (boby.getID() == bean.getBabyID()) {
					boby.setDesc(bean.getReason());
					return;
				}
			}else if(bean.getCheckType()==1){
				if (boby.getID() == bean.getBabyID()) {
					mYiqian++;
					mAllboby.getResultData().remove(boby);
					return;
				}
			}
		}
	}
}
