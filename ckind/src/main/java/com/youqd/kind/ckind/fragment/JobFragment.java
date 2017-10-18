package com.youqd.kind.ckind.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.shizhefei.fragment.LazyFragment;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.activity.JobInfoActivity;
import com.youqd.kind.ckind.activity.JobOkInfoActivity;
import com.youqd.kind.ckind.adapter.FoodAdapter;
import com.youqd.kind.ckind.adapter.JobAdapter;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.FoodBean;
import com.youqd.kind.ckind.bean.FoodListBean;
import com.youqd.kind.ckind.bean.GetJob;
import com.youqd.kind.ckind.bean.GetJobInfo;
import com.youqd.kind.ckind.bean.JobBean;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import java.util.ArrayList;
import java.util.List;

public class JobFragment extends LazyFragment {
	private String tabIndex;
	private ListView listView = null;
	private JobAdapter jobAdapter = null;
	public static final String INTENT_INT_INDEX = "intent_int_index";
	List<JobBean> list = new ArrayList<JobBean>();
	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_list);
		tabIndex = getArguments().getString(INTENT_INT_INDEX);
		listView = (ListView)findViewById(R.id.recodeList);
		listView.setDivider(new ColorDrawable(Color.rgb(242,242,242)));
		listView.setDividerHeight(5);
		((TextView)findViewById(R.id.nodata)).setText("未布置亲子作业");
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(list.get(position).isFinish()){
					startActivityForResult(new Intent(getActivity(),JobOkInfoActivity.class).putExtra("data",list.get(position)),223);

				}else{
					startActivityForResult(new Intent(getActivity(),JobInfoActivity.class).putExtra("data",list.get(position)),222);

				}
			}
		});


		jobAdapter = new JobAdapter(getActivity(),list);
		listView.setAdapter(jobAdapter);
		loding();
	}


	public void loding(){

		HttpTool.getInstance().getJoblist(getBaby().getGradeID()+"",
				getBaby().getKindergartenID()+"",
				getBaby().getClassID()+"",
				tabIndex, new KingCallback<GetJob>(GetJob.class) {
					@Override
					public void onSucceed(GetJob data) {
						list.clear();

						if(data.getResultData().size()==0){
							findViewById(R.id.nodata).setVisibility(View.VISIBLE);
						}
						for(GetJob.ResultDataBean bean : data.getResultData()){
							JobBean foodBean = new JobBean();
							foodBean.setContent(bean.getContent());
							foodBean.setIsFinish(false);
							foodBean.setId(bean.getID());
							foodBean.setName(bean.getHomeWorkTitle());
							foodBean.setDate(bean.getCreateTime().replace(" "," "));
							foodBean.setImage(bean.getPhoto());
							list.add(foodBean);

						}
						HttpTool.getInstance().getJobInfolist(getBaby().getID()+"",tabIndex,tabIndex, new KingCallback<GetJobInfo>(GetJobInfo.class) {
							@Override
							public void onSucceed(GetJobInfo data) {
								if(data!=null){
									if(data.getResultData().size()>0){
										for(GetJobInfo.ResultDataBean bean : data.getResultData()){
											for(JobBean job : list){
												if(job.getId() == bean.getHomeWorkID()){
													job.setIsFinish(true);
													job.setDate(bean.getOperationTime().replace(" "," "));
													job.setOkImagePath(bean.getPhoto());
													job.setOkVideoPath(bean.getVideo());
													if(!TextUtils.isEmpty(bean.getEvaluationByName())) {
														job.setOkLaoshi(bean.getEvaluation());
														job.setOkLaoshiName(bean.getEvaluationByName());
														job.setOkLaoshiDate(bean.getEvaluationByCreateTime());
													}
													job.setOkText(bean.getProfile());
												}
											}
										}
									}
								}
								jobAdapter.notifyDataSetChanged();
							}

							@Override
							public void onErro() {

							}
						});
						//jobAdapter.notifyDataSetChanged();


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

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		loding();
		super.onActivityResult(requestCode, resultCode, data);
	}

	public LoginBean.AllBaByBean getBaby() {
		return ((KingActivity)getActivity()).getBaby();
	}
}
