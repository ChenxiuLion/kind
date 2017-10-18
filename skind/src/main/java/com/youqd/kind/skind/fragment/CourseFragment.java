package com.youqd.kind.skind.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shizhefei.fragment.LazyFragment;
import com.youqd.kind.skind.R;
import com.youqd.kind.skind.adapter.CourseAdapter;
import com.youqd.kind.skind.bean.CourseBean;
import com.youqd.kind.skind.evaluation.utils.GlobalEditDialog;

import java.util.ArrayList;
import java.util.List;

public class CourseFragment extends LazyFragment implements AdapterView.OnItemClickListener {
	private int tabIndex;
	private ListView courseListView = null;
	private CourseAdapter courseAdapter = null;
	public static final String INTENT_INT_INDEX = "intent_int_index";

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_list);
		tabIndex = getArguments().getInt(INTENT_INT_INDEX);
		courseListView = (ListView)findViewById(R.id.recodeList);
		courseAdapter = new CourseAdapter(this.getActivity(),getData());
		courseListView.setAdapter(courseAdapter);

		courseListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		final GlobalEditDialog delDialog = new GlobalEditDialog(this.getActivity());
		delDialog.setCanceledOnTouchOutside(true);
		delDialog.getTitle().setText("课程名称");
		delDialog.getContent().setText(getData().get(position).getCourseName());
		delDialog.setLeftBtnText("取消");
		delDialog.setRightBtnText("确定");
		delDialog.setLeftOnclick(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				delDialog.dismiss();
			}
		});
		delDialog.setRightOnclick(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				delDialog.dismiss();
			}
		});
		delDialog.show();
	}

	@Override
	public void onDestroyViewLazy() {
		super.onDestroyViewLazy();
	}

	public List<CourseBean> getData(){
		String courseNames[] = {"美术","音乐","无","无"};

		List<CourseBean> list = new ArrayList<CourseBean>();
		CourseBean courseBean = null;

		for(int i = 0 ; i < 4; i++){
			courseBean = new CourseBean();
			courseBean.setIndexCode(i);
			courseBean.setCourseName(courseNames[i]);
			list.add(courseBean);
		}

		return list;
	}
}
