package com.youqd.kind.ckind.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shizhefei.fragment.LazyFragment;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.activity.JobInfoActivity;
import com.youqd.kind.ckind.activity.JobOkInfoActivity;
import com.youqd.kind.ckind.adapter.JobAdapter;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.GetJob;
import com.youqd.kind.ckind.bean.GetJobInfo;
import com.youqd.kind.ckind.bean.JobBean;
import com.youqd.kind.ckind.bean.JobList;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UserManage;

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
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
							}
		});


		jobAdapter = new JobAdapter(getActivity(),list);
		jobAdapter.setOnJobLiseten(new JobAdapter.OnJobLiseten() {
			@Override
			public void onJob(int position) {
				startActivityForResult(new Intent(getActivity(),JobInfoActivity.class).putExtra("data",list.get(position)),222);

			}
		});
		listView.setAdapter(jobAdapter);

	}

	@Override
	protected void onResumeLazy() {
		mIndex = 0;
		loding();
		super.onResumeLazy();
	}

	private int mIndex = 0;

	public void loding(){
		HttpTool.getInstance().getJoblist(UserManage.getInstance().getUser().getID()+"",
				UserManage.getInstance().getUser().getKindergartenID()+"",
				UserManage.getInstance().getUser().getClassID()+"",
				tabIndex, new KingCallback<GetJob>(GetJob.class) {
					@Override
					public void onSucceed(GetJob data) {
						if(data.getResultData().size() != list.size()) {
							list.clear();
							if (data.getResultData().size() > 0) {
								getJobUser(data);
							}
						}



					}

					@Override
					public void onErro() {

					}
				});
	}


	public void getJobUser(final GetJob data){
		if(mIndex<data.getResultData().size()) {
			final JobBean foodBean = new JobBean();
			foodBean.setContent(data.getResultData().get(mIndex).getContent());
			foodBean.setIsFinish(false);
			foodBean.setId(data.getResultData().get(mIndex).getID());
			foodBean.setName(data.getResultData().get(mIndex).getHomeWorkTitle());
			foodBean.setDate(data.getResultData().get(mIndex).getCreateTime().replace("T", " "));
			foodBean.setImage(data.getResultData().get(mIndex).getPhoto());
			HttpTool.getInstance().getErroJobListg(data.getResultData().get(mIndex).getID() + "", new KingCallback<JobList>(JobList.class) {
				@Override
				public void onSucceed(JobList adata) {
					foodBean.setUsers(adata.getResultData());
					list.add(foodBean);
					jobAdapter.notifyDataSetChanged();
					mIndex++;
					getJobUser(data);
				}

				@Override
				public void onErro() {

				}
			});
		}else{
			jobAdapter.notifyDataSetChanged();
		}
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
		super.onActivityResult(requestCode, resultCode, data);
	}

}
