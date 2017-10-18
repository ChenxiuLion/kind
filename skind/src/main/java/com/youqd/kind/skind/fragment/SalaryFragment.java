package com.youqd.kind.skind.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.shizhefei.fragment.LazyFragment;
import com.youqd.kind.skind.R;
import com.youqd.kind.skind.activity.NewDetailActivity;
import com.youqd.kind.skind.activity.NoticeDetailActivity;
import com.youqd.kind.skind.adapter.SalaryAdapter;
import com.youqd.kind.skind.bean.SalaryBean;
import com.youqd.kind.skind.evaluation.utils.GlobalDialog;
import com.youqd.kind.skind.evaluation.utils.GlobalEditDialog;

import java.util.ArrayList;
import java.util.List;

public class SalaryFragment extends LazyFragment implements AdapterView.OnItemClickListener {
	private int tabIndex;
	private ListView listView = null;
	private SalaryAdapter salaryAdapter = null;
	public static final String INTENT_INT_INDEX = "intent_int_index";

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_salary_list);
		tabIndex = getArguments().getInt(INTENT_INT_INDEX);
		listView = (ListView)findViewById(R.id.recodeList);
		listView.setDivider(new ColorDrawable(Color.rgb(242,242,242)));
		listView.setDividerHeight(1);
		salaryAdapter = new SalaryAdapter(this.getActivity(),getData());
		listView.setAdapter(salaryAdapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		final GlobalEditDialog delDialog = new GlobalEditDialog(this.getActivity());
		delDialog.setCanceledOnTouchOutside(true);
		delDialog.getTitle().setText("编写原因");
		delDialog.getContent().setText(getData().get(position).getSalaryRemark());
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

	public List<SalaryBean> getData(){
		String salaryRemark[] = {"病假","没有理由","病假"};
		String salaryName[] = {"张小红","李小明","赵二蛋"};
		List<SalaryBean> list = new ArrayList<SalaryBean>();
		SalaryBean salaryBean = null;

		for(int i = 0 ; i < 3; i++){
			salaryBean = new SalaryBean();
			salaryBean.setId(i);
			salaryBean.setSalaryName(salaryName[i]);
			salaryBean.setSalaryRemark(salaryRemark[i]);
			list.add(salaryBean);
		}

		return list;
	}
}
