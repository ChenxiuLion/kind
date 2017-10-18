package com.youqd.kind.skind.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shizhefei.fragment.LazyFragment;
import com.youqd.kind.skind.R;
import com.youqd.kind.skind.activity.JobDetailActivity;
import com.youqd.kind.skind.activity.NewDetailActivity;
import com.youqd.kind.skind.activity.NoticeDetailActivity;
import com.youqd.kind.skind.adapter.JobAdapter;
import com.youqd.kind.skind.bean.JobBean;

import java.util.ArrayList;
import java.util.List;

public class JobFragment extends LazyFragment implements AdapterView.OnItemClickListener{
	private int tabIndex;
	private ListView listView = null;
	private JobAdapter jobAdapter = null;
	public static final String INTENT_INT_INDEX = "intent_int_index";

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_list);
		tabIndex = getArguments().getInt(INTENT_INT_INDEX);
		listView = (ListView)findViewById(R.id.recodeList);
		listView.setDivider(new ColorDrawable(Color.rgb(242,242,242)));
		listView.setDividerHeight(5);
		jobAdapter = new JobAdapter(this.getActivity(),getData());
		listView.setAdapter(jobAdapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Bundle bundle = new Bundle();
		bundle.putString(NewDetailActivity.TITLE,getData().get(position).getName());

		Intent intent = new Intent();
		intent.setClass(this.getActivity(), JobDetailActivity.class);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	@Override
	public void onDestroyViewLazy() {
		super.onDestroyViewLazy();
	}

	public List<JobBean> getData(){
		String names[] = {"完成数学课本练习","学会分享","收集旧书"};
		String contents[] = {"完成课后的数学练题目","和至少一个朋友分享自己的成功","收集至少三本旧书"};

		List<JobBean> list = new ArrayList<JobBean>();
		JobBean jobBean = null;

		for(int i = 0 ; i < 3; i++){
			jobBean = new JobBean();
			jobBean.setId(i);
			jobBean.setIsFinish((i % 2 == 0 ? false : true));
			jobBean.setName(names[i]);
			jobBean.setContent(contents[i]);
			list.add(jobBean);
		}

		return list;
	}
}
