package com.youqd.kind.ckind.fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cosfund.library.widget.BadgeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.activity.MainActivity;
import com.youqd.kind.ckind.bean.ClassBean;
import com.youqd.kind.ckind.bean.GetUser;
import com.youqd.kind.ckind.bean.KindBean;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.bean.NewBean;
import com.youqd.kind.ckind.bean.NewsModel;
import com.youqd.kind.ckind.bean.PostNew;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UserManage;

import java.util.List;

public class MainFragment extends KingFragment  {

	private TextView mKindNameTv;

	private PopupWindow popupWindow;

	BadgeView badgeView1;
	BadgeView badgeView2;
	private ImageView mH1,mH2;
	@Override
	public int initLayout() {
		return R.layout.activity_main;
	}

	@Override
	public void initData() {

		mKindNameTv = (TextView) findViewById(R.id.kind_name);
		mH1 = (ImageView) findViewById(R.id.main_item_h_1);
		mH2 = (ImageView) findViewById(R.id.main_item_h_2);
		if(UserManage.getInstance().getKind()!=null) {
			mKindNameTv.setText(UserManage.getInstance().getKind().getResultData().getName());
		}
		initBaby();

		HttpTool.getInstance().getKindInfo(getUser().getKindergartenID() + "",
				new KingCallback<KindBean>(KindBean.class) {
					@Override
					public void onSucceed(final KindBean sdata) {
						if(sdata.getResultData()!=null) {
							UserManage.getInstance().setKind(sdata);
						}
						ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + sdata.getResultData().getLogo(),
								((ImageView) findViewById(R.id.main_image)), getOptions());
						mKindNameTv.setText(sdata.getResultData().getName());
						MainActivity.mActivity.initBoby(sdata.getResultData().getLogo());


						HttpTool.getInstance().getClassName(getUser().getClassID()+"",
								new KingCallback<ClassBean>(ClassBean.class) {
									@Override
									public void onSucceed(ClassBean data) {
										try {
											((TextView)findViewById(R.id.baby_name))
													.setText(data.getResultData().getName());
											UserManage.getInstance().setClassName(data.getResultData().getName());
											LoginBean loginBean = getUser();

											loginBean.setGradeID(data.getResultData().getGradeID());
											setUser(loginBean);
										}catch (Exception e){

										}

									}

									@Override
									public void onErro() {

									}
								});
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
	@Override
	protected void onResumeLazy() {
		final PostNew mBean = new PostNew();
		mBean.setKindergartenID(getUser().getKindergartenID());
		mBean.setPageIndex(0);
		mBean.setOrderBy("id");
		mBean.setPageSize(5);
		mBean.setNoticeType(1);
		mBean.setSubNoticeType(1);
		HttpTool.getInstance().doNews(mBean, new KingCallback<NewsModel>(NewsModel.class) {
			@Override
			public void onSucceed(NewsModel data) {

				String json = mACache.getAsString("SchoolNewActivity_"+getUser().getUserAccount()+"5");
				if(!TextUtils.isEmpty(json)) {
					List<NewsModel.ResultDataBean> temp = new Gson().fromJson(json,
							new TypeToken<List<NewsModel.ResultDataBean>>() {
							}.getType());
					if (temp.size() > 0) {
						if(data.getResultData().size()>0) {
							if (temp.get(0).getID() !=data.getResultData().get(0).getID()){
								mH1.setVisibility(View.VISIBLE);
							}else{
								mH1.setVisibility(View.GONE);
							}
						}
					}
				}
				mBean.setSubNoticeType(2);
				mBean.setNoticeType(6);
				HttpTool.getInstance().doNews(mBean, new KingCallback<NewsModel>(NewsModel.class) {
					@Override
					public void onSucceed(NewsModel data) {
						String json = mACache.getAsString("SchoolNewActivity_"+getUser().getUserAccount()+"6");
						if(!TextUtils.isEmpty(json)) {
							List<NewsModel.ResultDataBean> temp = new Gson().fromJson(json,
									new TypeToken<List<NewsModel.ResultDataBean>>() {
									}.getType());
							if (temp.size() > 0) {
								if(data.getResultData().size()>0) {
									if (temp.get(0).getID() !=data.getResultData().get(0).getID()){
										mH2.setVisibility(View.VISIBLE);
									}else{
										mH2.setVisibility(View.GONE);
									}
								}
							}
						}
					}

					@Override
					public void onErro() {
					}
				});

			}

			@Override
			public void onErro() {
			}
		});
		super.onResumeLazy();
	}
	public void initBaby(){
	}
}
