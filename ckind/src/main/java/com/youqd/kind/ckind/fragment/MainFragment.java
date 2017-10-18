package com.youqd.kind.ckind.fragment;

import android.os.Bundle;
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
import android.widget.SeekBar;
import android.widget.TextView;

import com.cosfund.library.widget.BadgeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.pili.pldroid.player.AVOptions;
import com.shizhefei.fragment.LazyFragment;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.activity.AccountActivity;
import com.youqd.kind.ckind.activity.BBInfoActivity;
import com.youqd.kind.ckind.activity.BabyActivity;
import com.youqd.kind.ckind.activity.ChatActivity;
import com.youqd.kind.ckind.activity.ContactActivity;
import com.youqd.kind.ckind.activity.CourseActivity;
import com.youqd.kind.ckind.activity.FoodActivity;
import com.youqd.kind.ckind.activity.IntroActivity;
import com.youqd.kind.ckind.activity.JobActivity;
import com.youqd.kind.ckind.activity.MainActivity;
import com.youqd.kind.ckind.activity.MsgActivity;
import com.youqd.kind.ckind.activity.MyActivity;
import com.youqd.kind.ckind.activity.SchoolNewActivity;
import com.youqd.kind.ckind.activity.SeeActivity;
import com.youqd.kind.ckind.activity.WorkActivity;
import com.youqd.kind.ckind.bean.GetParent;
import com.youqd.kind.ckind.bean.GetUser;
import com.youqd.kind.ckind.bean.GetUserName;
import com.youqd.kind.ckind.bean.KindBean;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.bean.NewBean;
import com.youqd.kind.ckind.bean.NewsModel;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UserManage;

import net.simonvt.menudrawer.MenuDrawer;

import java.util.List;

public class MainFragment extends KingFragment  {

	private TextView mKindNameTv;

	private PopupWindow popupWindow;

	private ImageView mH1,mH2;


	BadgeView badgeView1;
	BadgeView badgeView2;

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
						}else{
							return;
						}
						if(!TextUtils.isEmpty(sdata.getResultData().getName())){
							mKindNameTv.setText(sdata.getResultData().getName());
						}
						HttpTool.getInstance().getUserInfo(getUser().getID() + "", new KingCallback<GetUser>(GetUser.class) {
							@Override
							public void onSucceed(GetUser data) {
								setUser(data.getResultData());
								if(data.getResultData().getAllBaBy()!=null){
									LoginBean.AllBaByBean baByBean = data.getResultData().getAllBaBy().get(0);
									baByBean.setSchoolName(sdata.getResultData().getName());
									setBaby(data.getResultData().getAllBaBy().get(0));
								}
								MainActivity.mActivity.initBoby();
								initBaby();
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

	public void initBaby(){
		if(getBaby()!=null) {


			if(getActivity()!=null) {
				ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + getBaby().getPhoto(),
						((ImageView) findViewById(R.id.main_image)), getOptions());
				//((TextView)findViewById(R.id.baby_class_name)).setText(getBaby().get);
				findViewById(R.id.showpoplin).setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						showPopupWindow(v);
					}
				});
				((TextView) findViewById(R.id.baby_name)).setText(getBaby().getUserName());
				((TextView) findViewById(R.id.baby_class_name)).setText(getBaby().getClassName());
				((ImageView) findViewById(R.id.qiehuantv)).setImageResource(R.drawable.icon_jiantou_1);
			}
		}else{
			((ImageView) findViewById(R.id.qiehuantv)).setVisibility(View.GONE);
			findViewById(R.id.showpoplin).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//showPopupWindow(v);
				}
			});
		}
	}

	private void showPopupWindow(View view) {
		// 一个自定义的布局，作为显示的内容
		((ImageView)findViewById(R.id.qiehuantv)).setImageResource(R.drawable.icon_jiantou_2);
		final View contentView = LayoutInflater.from(getActivity()).inflate(
				R.layout.pop_window, null);
		GridView mList = (GridView) contentView.findViewById(R.id.pop_list);
		mList.setAdapter(new BaseAdapter() {
			@Override
			public int getCount() {
				return getUser().getAllBaBy().size();
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
			public View getView(int position, View convertView, ViewGroup parent) {
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.user_baby_item,null);
				TextView textView = (TextView) convertView.findViewById(R.id.item_baby_name);
				ImageView image = (ImageView) convertView.findViewById(R.id.item_baby_image);
				textView.setText(getUser().getAllBaBy().get(position).getUserName());
				ImageLoader.getInstance().displayImage(Constant.IMAGE_URL+getUser().getAllBaBy().get(position).getPhoto(),
						image,getOptions());
				return convertView;
			}
		});
		mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				if(getBaby()!=null){
					setBaby(getUser().getAllBaBy().get(position));
				}
				initBaby();
				popupWindow.dismiss();
			}
		});
		popupWindow = new PopupWindow(contentView,
				ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
		popupWindow.setTouchable(true);
		popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {

			}
		});
		popupWindow.setTouchInterceptor(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return false;
				// 这里如果返回true的话，touch事件将被拦截
				// 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
			}
		});
		//监听点击事件，点击其他位置，popupwindow小窗口消失
		// 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
		// 我觉得这里是API的一个bug
		popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_bg));

		// 设置好参数之后再show

		popupWindow.showAsDropDown(findViewById(R.id.info_top));

	}


	@Override
	protected void onResumeLazy() {
		final NewBean mBean = new NewBean();
		mBean.setKindergartenID(getUser().getKindergartenID());
		mBean.setPageIndex(0);
		mBean.setPageSize(5);
		mBean.setNoticeType(5);
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
}
