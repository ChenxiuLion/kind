package com.youqd.kind.skind.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListView;

import com.shizhefei.fragment.LazyFragment;
import com.youqd.kind.skind.R;
import com.youqd.kind.skind.adapter.FoodAdapter;
import com.youqd.kind.skind.bean.FoodBean;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends LazyFragment {
	private int tabIndex;
	private ListView foodListView = null;
	private FoodAdapter foodAdapter = null;
	public static final String INTENT_INT_INDEX = "intent_int_index";

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_list);
		tabIndex = getArguments().getInt(INTENT_INT_INDEX);
		foodListView = (ListView)findViewById(R.id.recodeList);
		foodListView.setDivider(new ColorDrawable(Color.rgb(242,242,242)));
		foodListView.setDividerHeight(1);
		foodAdapter = new FoodAdapter(this.getActivity(),getData());
		foodListView.setAdapter(foodAdapter);
	}

	@Override
	public void onDestroyViewLazy() {
		super.onDestroyViewLazy();
	}

	public List<FoodBean> getData(){
		String courseNames[] = {"松子大米粥,煮鸡蛋,豆沙包","牛奶","米饭,红烧狮子头,素炒佛手瓜,莲藕棒骨汤","香蕉","三鲜陷饼,小米粥"};

		List<FoodBean> list = new ArrayList<FoodBean>();
		FoodBean foodBean = null;

		for(int i = 0 ; i < 5; i++){
			foodBean = new FoodBean();
			foodBean.setIndexCode(i);
			foodBean.setFoodContent(courseNames[i]);
			list.add(foodBean);
		}

		return list;
	}
}
