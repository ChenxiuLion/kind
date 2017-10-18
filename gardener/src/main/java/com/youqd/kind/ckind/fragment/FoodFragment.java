package com.youqd.kind.ckind.fragment;

import android.os.Bundle;
import android.widget.ListView;

import com.shizhefei.fragment.LazyFragment;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.adapter.FoodAdapter;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.FoodBean;
import com.youqd.kind.ckind.bean.FoodListBean;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UserManage;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends LazyFragment {
	private String tabIndex;
	private ListView foodListView = null;
	private FoodAdapter foodAdapter = null;
	public static final String INTENT_INT_INDEX = "intent_int_index";

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_list);
		tabIndex = getArguments().getString(INTENT_INT_INDEX);
		foodListView = (ListView)findViewById(R.id.recodeList);
		HttpTool.getInstance().getFoodlist(UserManage.getInstance().getUser().getKindergartenID()+"",tabIndex, new KingCallback<FoodListBean>(FoodListBean.class) {
			@Override
			public void onSucceed(FoodListBean data) {
				List<FoodBean> list = new ArrayList<FoodBean>();
				for(int i = 0 ; i < 5; i++){
					FoodBean foodBean = new FoodBean();
					foodBean.setIndexCode(i);

					String content = "";
					for(FoodListBean.ResultDataBean bean : data.getResultData()){
						if(bean.getPeriodsType() == i){
							content+=" "+bean.getRecipesName();
							foodBean.setPhoto(bean.getPhoto());
						}
					}

					foodBean.setFoodContent(content);
					list.add(foodBean);

				}
				foodAdapter = new FoodAdapter(getActivity(),list);
				foodListView.setAdapter(foodAdapter);
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
