package com.youqd.kind.ckind.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.shizhefei.fragment.LazyFragment;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.adapter.CourseAdapter;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.ClassListBean;
import com.youqd.kind.ckind.bean.CourseBean;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import java.util.ArrayList;
import java.util.List;

public class CourseFragment extends LazyFragment {
	private String tabIndex;
	private ListView courseListView = null;
	private CourseAdapter courseAdapter = null;
	public static final String INTENT_INT_INDEX = "intent_int_index";

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_list);
		tabIndex = getArguments().getString(INTENT_INT_INDEX);

		courseListView = (ListView)findViewById(R.id.recodeList);
		courseListView.setDivider(new ColorDrawable(Color.rgb(242,242,242)));
		courseListView.setDividerHeight(1);
		HttpTool.getInstance().getClasslist(getBaby().getKindergartenID()+"",getBaby().getClassID()+"",tabIndex,
				new KingCallback<ClassListBean>(ClassListBean.class) {
					@Override
					public void onSucceed(ClassListBean data) {
						if(data.getResultData().size()==0){
							findViewById(R.id.nodata).setVisibility(View.VISIBLE);
						}
						List<CourseBean> list = new ArrayList<CourseBean>();
						for(int i = 0 ; i < data.getResultData().size(); i++){
							CourseBean courseBean  = new CourseBean();
							courseBean.setIndexCode(i);
							courseBean.setCourseName(data.getResultData().get(i).getCourseName());
							courseBean.setUserName(data.getResultData().get(i).getGardenerName());
							list.add(courseBean);
							findViewById(R.id.nodata).setVisibility(View.GONE);
						}
						courseAdapter = new CourseAdapter(getActivity(),list);
						courseListView.setAdapter(courseAdapter);
						courseListView.setDividerHeight(0);
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

	public List<CourseBean> getData(){
		String courseNames[] = {"美术","音乐","数学","语文"};

		List<CourseBean> list = new ArrayList<CourseBean>();
		CourseBean courseBean = null;

		for(int i = 0 ; i < 4; i++){
			courseBean = new CourseBean();
			courseBean.setIndexCode(i);
			courseBean.setCourseName(courseNames[Integer.parseInt(Math.round(Math.random() * 3) + "")]);
			list.add(courseBean);
		}

		return list;
	}
	public LoginBean.AllBaByBean getBaby() {
		return ((KingActivity)getActivity()).getBaby();
	}
}
