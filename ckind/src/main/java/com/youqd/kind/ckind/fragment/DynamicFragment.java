package com.youqd.kind.ckind.fragment;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.canyinghao.caneffect.CanRippleLayout;
import com.canyinghao.caneffect.CanShadowDrawable;
import com.cosfund.library.dialog.HintDialog;
import com.cosfund.library.refresh.PullToRefreshBase;
import com.cosfund.library.refresh.PullToRefreshListView;
import com.cosfund.library.refresh.PullToRefreshScrollView;
import com.cosfund.library.util.NetWorkUtils;
import com.cxandroid.imageutil.imageload.SelectImageActivity;
import com.example.bajian.sheetdialogue.SheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.activity.AddDynamicActivity;
import com.youqd.kind.ckind.activity.AddPingActivity;
import com.youqd.kind.ckind.activity.PhotosActivity;
import com.youqd.kind.ckind.activity.ViewPlayActivity;
import com.youqd.kind.ckind.bean.GetPingList;
import com.youqd.kind.ckind.bean.JobResult;
import com.youqd.kind.ckind.bean.NewBean;
import com.youqd.kind.ckind.bean.NewDynamicList;
import com.youqd.kind.ckind.bean.PingList;
import com.youqd.kind.ckind.bean.PingResult;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.model.ClassInfo;
import com.youqd.kind.ckind.model.GetDynamicList;
import com.youqd.kind.ckind.model.KingVideos;
import com.youqd.kind.ckind.model.Ping;
import com.youqd.kind.ckind.util.ACache;
import com.youqd.kind.ckind.util.CircleImageView;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.PopupList;
import com.youqd.kind.ckind.util.UserManage;
import com.youqd.kind.ckind.video.NewRecordVideoActivity;
import com.youqd.kind.ckind.widget.MyListView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Chenxiu on 2016/8/13.
 */
public class DynamicFragment extends KingFragment {

    private PullToRefreshListView mSwiper;

    private List<NewDynamicList.ResultDataBean> mData = new ArrayList<>();

    private HashMap<Integer, GetDynamicList.ResultDataBean> mDynaicForPings = new HashMap<>();

    private int mPager = 0;

    private DynamicAdapter mAdapter;

    private int mType = 1;

    @Override
    public int initLayout() {
        return R.layout.fragment_daymic;
    }

    public void setType(int type){
        mType = type;
    }


    @Override
    public void initData() {

        Logger.e("type = "+mType);
        mSwiper = (PullToRefreshListView) findViewById(R.id.swipe_layout);
        mAdapter = new DynamicAdapter();
        mSwiper.setMode(PullToRefreshBase.Mode.BOTH);
        if (findViewById(R.id.base_back) != null) {

            findViewById(R.id.base_back_image).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AddDynamicActivity.class);
                    intent.putExtra("type",mType);
                    startActivityForResult(intent, 145);
                }
            });
        }

        if(mType==2){
            ((TextView)findViewById(R.id.top_title)).setText("家园动态");
            ((TextView)findViewById(R.id.nodata)).setText("未发布家园动态");
            findViewById(R.id.base_sss).setVisibility(View.VISIBLE);
            findViewById(R.id.base_sss).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
            findViewById(R.id.base_back).setVisibility(View.GONE);
        }else{
            ((TextView)findViewById(R.id.top_title)).setText("成长动态");
            ((TextView)findViewById(R.id.nodata)).setText("未发布成长动态");
        }
        mSwiper.setAdapter(mAdapter);
        mSwiper.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                mPager = 0;
                getHttpData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                mPager++;
                getHttpData();
            }
        });

        String str = mACache.getAsString("tynamicfragment_"+mType + getUser().getUserAccount());
        String pg = mACache.getAsString("tynamicfragment_mpage"+mType);

        if (!TextUtils.isEmpty(str)) {
            findViewById(R.id.nodata).setVisibility(View.GONE);
            if (!TextUtils.isEmpty(pg)) {
                mPager = Integer.parseInt(pg);
            }
            List<NewDynamicList.ResultDataBean> dataBeen = new Gson().fromJson(str,
                    new TypeToken<List<NewDynamicList.ResultDataBean>>() {
                    }.getType());
            mData.addAll(dataBeen);
            mAdapter.notifyDataSetChanged();
            mSwiper.onRefreshComplete();
        }
        getHttpData();
    }

    public void getHttpData() {


        HttpTool.getInstance().growthDynamics(getBaby().getKindergartenID() + "",mType,getBaby().getClassID()+"", mPager,
                new KingCallback<NewDynamicList>(NewDynamicList.class) {
                    @Override
                    public void onSucceed(NewDynamicList data) {
                        if (data.getResultData().size() > 0) {
                            if (mPager == 0) {
                                mData.clear();
                            }

                            mData.addAll(data.getResultData());
                            mACache.put("tynamicfragment_"+mType + getUser().getUserAccount(), new Gson().toJson(mData), 7 * ACache.TIME_DAY);
                            mACache.put("tynamicfragment_mpage"+mType, mPager + "");
                            mAdapter.notifyDataSetChanged();
                            mSwiper.onRefreshComplete();
                        }else{
                            findViewById(R.id.nodata).setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onErro() {
                        mSwiper.onRefreshComplete();
                    }
                });
    }

    float dp2Px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
    HintDialog dialog;

    public class DynamicAdapter extends BaseAdapter {

        List<NewDynamicList.ResultDataBean> mData;

        public Context mContext;

        public DynamicAdapter() {
            mContext = getActivity();
            DynamicAdapter.this.mData = DynamicFragment.this.mData;
        }

        public DynamicAdapter(Context context, List<NewDynamicList.ResultDataBean> data) {
            this.mContext = context;
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_dynamic_view, null);
            final ViewHodler viewHodler = new ViewHodler();
            viewHodler.mUserNameTv = (TextView) convertView.findViewById(R.id.dynamic_item_name);
            viewHodler.mContentTv = (TextView) convertView.findViewById(R.id.dynamic_item_content);
            viewHodler.mYearTv = (TextView) convertView.findViewById(R.id.dynamic_date_year);
            viewHodler.mGridView = (GridView) convertView.findViewById(R.id.dynamic_item_images);
            viewHodler.mUserImage = (ImageView) convertView.findViewById(R.id.dynamic_userimage);
            viewHodler.mBabyInfo = (TextView) convertView.findViewById(R.id.dynamic_baby_tv);
            viewHodler.mDateDay = (TextView) convertView.findViewById(R.id.dynamic_date_day);
            viewHodler.mDateM = (TextView) convertView.findViewById(R.id.dynamic_date_month);
            viewHodler.mDateTime = (TextView) convertView.findViewById(R.id.dynamic_date_time);
            viewHodler.mShowAdd = (LinearLayout) convertView.findViewById(R.id.show_add);
            viewHodler.mPingList = (MyListView) convertView.findViewById(R.id.dynamic_item_pings);
            viewHodler.mAllPing = (TextView) convertView.findViewById(R.id.all_ping);
            viewHodler.mZanLin = (LinearLayout) convertView.findViewById(R.id.zan_lin);
            viewHodler.mZanSelectLin = (LinearLayout) convertView.findViewById(R.id.show_zan);
            viewHodler.mZanImage = (ImageView) convertView.findViewById(R.id.zan_icon);
            viewHodler.mZanTv = (TextView) convertView.findViewById(R.id.zan_tv);
            viewHodler.mUserNameTv.setText(mData.get(position).getCreateUserName());
            viewHodler.mContentTv.setText(mData.get(position).getDescription());
            TextView date_tv = (TextView) convertView.findViewById(R.id.dynamic_time_tv);
            TextView zan_tv = (TextView) convertView.findViewById(R.id.zan_count_tv);
            TextView add_tv = (TextView) convertView.findViewById(R.id.add_count_tv);
            LinearLayout delete = (LinearLayout) convertView.findViewById(R.id.dynamic_delete);
            final TextView class_tv = (TextView) convertView.findViewById(R.id.dynamic_class_tv);
            if(mData.get(position).getCreateUserID() == getUser().getID()){
                delete.setVisibility(View.VISIBLE);
            }

            ImageLoader.getInstance().displayImage(Constant.IMAGE_URL+mData.get(position).getCreatePhoto(),viewHodler.mUserImage,getOptions());
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new SheetDialog(mContext)
                            .setCanceledOnTouchOutside(false)
                            .addSheetItems(new String[]{"删除"}
                                    , SheetDialog.SheetItemColor.Blue, new SheetDialog.OnSheetItemClickListener() {
                                        @Override
                                        public void onClick(int which) {
                                            if(which == 1){
                                                HttpTool.getInstance().doDeleteDynamic(mData.get(position).getID()+"",
                                                new KingCallback<PingResult>(PingResult.class) {
                                                    @Override
                                                    public void onSucceed(PingResult data) {
                                                        if(data.getResultData()){
                                                            mData.remove(position);
                                                            mACache.put("tynamicfragment_" + mType+ getUser().getUserAccount(), new Gson().toJson(mData), 7 * ACache.TIME_DAY);
                                                            mACache.put("tynamicfragment_mpage"+ mType, mPager + "");
                                                            mAdapter.notifyDataSetChanged();
                                                        }
                                                    }

                                                    @Override
                                                    public void onErro() {

                                                    }
                                                });


                                            }
                                        }
                                    }).show();
                }
            });

            if (mData.get(position).getClassID() != 0) {
                String classname = mACache.getAsString("class1_" + mData.get(position).getClassID());
                if (TextUtils.isEmpty(classname)) {
                    HttpTool.getInstance().getClassName(mData.get(position).getClassID() + "",
                            new KingCallback<ClassInfo>(ClassInfo.class) {
                                @Override
                                public void onSucceed(ClassInfo data) {
                                    class_tv.setText("来自" + data.getResultData().getName());
                                    class_tv.setVisibility(View.VISIBLE);
                                    mACache.put("class1_" + mData.get(position).getClassID(), new Gson().toJson(data));
                                }

                                @Override
                                public void onErro() {

                                }
                            });
                } else {
                    ClassInfo info = new Gson().fromJson(classname, ClassInfo.class);
                    class_tv.setText("来自" + info.getResultData().getName());
                    class_tv.setVisibility(View.VISIBLE);
                }
            }

            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simple2 = new SimpleDateFormat("M个月\nd天\n");
            String date = getBaby().getEntryDate().split(" ")[0];
            String newData = getCurrentDate();
            try {
                long cTiem = simple.parse(newData).getTime() - simple.parse(date).getTime();
                String str = (compareDate(date, null, 2) > 0 ? compareDate(date, null, 2) + "岁\n" : "") + simple2.format(cTiem);
                viewHodler.mBabyInfo.setText(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            SimpleDateFormat simples = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date_tv.setText(getTime(simples.parse(mData.get(position).getCreateTime()).getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String str = mData.get(position).getCreateTime().split(" ")[0];
            viewHodler.mDateDay.setText(str.split("-")[2]);
            viewHodler.mDateM.setText(str.split("-")[1] + "月");

            viewHodler.mYearTv.setText(str.split("-")[0]);
            viewHodler.mDateTime.setText(mData.get(position).getCreateTime().split(" ")[1]);
            viewHodler.mShowAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AddPingActivity.class);
                    Ping ping = new Ping();
                    ping.setGrowthDynamicsID(mData.get(position).getID());
                    intent.putExtra("Data", ping);
                    intent.putExtra("position", position);
                    startActivityForResult(intent, 199);
                }
            });

            if(!TextUtils.isEmpty(mData.get(position).getPhoto())) {
                final ArrayList<KingVideos> mPaths = new ArrayList<>();
                ArrayList<KingVideos> mVideos =  new ArrayList<>();

                final ArrayList<String> mImages = new Gson().fromJson(mData.get(position).getPhoto(),
                        new TypeToken<ArrayList<String>>() {
                        }.getType());

                for(String path : mImages){
                    mPaths.add(new KingVideos("",path));
                }
                if(!TextUtils.isEmpty(mData.get(position).getVideo())) {
                    try {
                        ArrayList<String> data = new Gson().fromJson(mData.get(position).getVideo(),
                                new TypeToken<ArrayList<String>>() {
                                }.getType());

                        for(String a : data){
                            mVideos.add(new KingVideos(a,"null"));
                        }
                    }catch (Exception e){
                        mVideos = new Gson().fromJson(mData.get(position).getVideo(),
                                new TypeToken<ArrayList<KingVideos>>() {
                                }.getType());

                    }

                }
                mPaths.addAll(mVideos);
                if (mPaths.size() > 1) {
                    viewHodler.mGridView.setNumColumns(3);
                } else {
                    viewHodler.mGridView.setNumColumns(1);
                }
                viewHodler.mGridView.setAdapter(new GridImageAdapter(mPaths));

                viewHodler.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                        if (!mPaths.get(position).isVideo()) {
                            Intent intent = new Intent(getActivity(), PhotosActivity.class);
                            intent.putParcelableArrayListExtra("images",mPaths);
                            intent.putExtra("postion", position);
                            startActivity(intent);
                        }else{
                            if (NetWorkUtils.isWifi(getActivity())) {
                                Intent intent = new Intent(getActivity(), ViewPlayActivity.class);
                                intent.putExtra("path", mPaths.get(position).getVideoPath());
                                intent.putExtra("image", mPaths.get(position).getThumbnail());
                                startActivity(intent);
                            } else {

                                dialog = new HintDialog(getActivity());
                                dialog.setTitle("你正在使用2G/3G/4G网络");
                                dialog.setMessage("家园宝宝提醒您：您正在使用非WiFi网络，播放将产生流量费用，请您确定是否继续播放");
                                dialog.setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getActivity(), ViewPlayActivity.class);
                                        intent.putExtra("path", mPaths.get(position).getVideoPath());
                                        intent.putExtra("image", mPaths.get(position).getThumbnail());
                                        startActivity(intent);
                                        dialog.dismiss();
                                    }
                                });
                                dialog.setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                                dialog.show();
                            }

                        }

                    }
                });
            }
            //赞
            if (mData.get(position).getZans().size() > 0) {
                zan_tv.setText(mData.get(position).getZans().size() + "");
                zan_tv.setVisibility(View.VISIBLE);
                viewHodler.mZanLin.setVisibility(View.VISIBLE);
                if (mData.get(position).getZans().size() == 1) {
                    viewHodler.mZanTv.setText(mData.get(position).getZans().get(0).getCommentUserName() + "觉得好赞");
                } else {
                    viewHodler.mZanTv.setText(mData.get(position).getZans().get(0).getCommentUserName() + "、" +
                            mData.get(position).getZans().get(1).getCommentUserName() + "等" +
                            mData.get(position).getZans().size() + "人觉得好赞");
                }

            }


            if (mData.get(position).isZan(getUser().getUserAccount())) {
                viewHodler.mZanImage.setImageResource(R.drawable.icon_messge_xit_d);
            } else {
                viewHodler.mZanSelectLin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Ping mPing = new Ping();
                        viewHodler.mZanSelectLin.setEnabled(false);
                        mPing.setCommentContent("");
                        mPing.setGrowthDynamicsID(mData.get(position).getID());
                        mPing.setCommentUserAccount(getUser().getUserAccount());
                        mPing.setCommentUserName(getUser().getUserName());
                        mPing.setTitle("1");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        mPing.setCreateTime(dateFormat.format(new Date()));
                        HttpTool.getInstance().AddPing(mPing, new KingCallback<JobResult>(JobResult.class) {
                            @Override
                            public void onSucceed(JobResult data) {
                                Logger.e(new Gson().toJson(mPing));
                                GetPingList.ResultDataBean dataBean = new GetPingList.ResultDataBean();
                                dataBean.setGrowthDynamicsID(mPing.getGrowthDynamicsID());
                                dataBean.setCommentUserName(mPing.getCommentUserName());
                                dataBean.setCommentUserAccount(getUser().getUserAccount());
                                dataBean.setCommentContent(mPing.getCommentContent());
                                dataBean.setTitle("1");
                                mData.get(position).getCommentList().add(dataBean);
                                mAdapter.notifyDataSetChanged();
                                mACache.put("tynamicfragment_" + mType+ getUser().getUserAccount(), new Gson().toJson(mData), 7 * ACache.TIME_DAY);
                                mACache.put("tynamicfragment_mpage"+ mType, mPager + "");
                            }

                            @Override
                            public void onErro() {
                                viewHodler.mZanSelectLin.setEnabled(true);
                            }
                        });
                    }
                });
            }

            viewHodler.mPingList.setAdapter(new PingAdapter(mData.get(position).getPings()));
            viewHodler.mPingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int ss, long id) {
                    Logger.json(new Gson().toJson(mData.get(position).getPings().get(ss)));
                }
            });
            viewHodler.mPingList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int p, long id) {
                    if(!mData.get(position).getPings().get(p).
                            getCommentUserAccount().equals(getUser().getUserAccount())){
                        return false;
                    }
                    List<String> s = new ArrayList<String>();
                    s.add("删除");
                    PopupList popupList = new PopupList();
                    popupList.init(getActivity(), view, s, new PopupList.OnPopupListClickListener() {
                        @Override
                        public void onPopupListClick(View contextView, int contextPosition, int position) {
                            Toast.makeText(getActivity(), contextPosition + "," + position, Toast.LENGTH_LONG).show();
                        }
                    });
                    popupList.setTextSize(popupList.sp2px(12));
   /*                 View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.popup, null);
                    final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT, true);
                    TextView deletetv= (TextView) popupView.findViewById(R.id.delete_item_dynamic);
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                    int popupWidth = popupView.getMeasuredWidth();
                    int popupHeight = popupView.getMeasuredHeight();
                    int[] location = new int[2];
                    deletetv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            HttpTool.getInstance().doDeletePing(mData.get(position).getPings().get(p).getID()+"",
                                    new KingCallback<PingResult>(PingResult.class) {
                                        @Override
                                        public void onSucceed(PingResult data) {


                                            mData.get(position).deletePing(mData.get(position).getPings().get(p).getID());
                                            mACache.put("tynamicfragment_"+ mType + getUser().getUserAccount(), new Gson().toJson(mData), 7 * ACache.TIME_DAY);
                                            mACache.put("tynamicfragment_mpage"+ mType, mPager + "");
                                            mAdapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onErro() {

                                        }
                                    });
                        }
                    });
                    view.getLocationOnScreen(location);
                    popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, (location[0] + view.getWidth() / 2) - popupWidth / 2,
                            location[1] - popupHeight);*/
                    return false;
                }
            });
            if (mData.get(position).getPings().size() > 0) {
                add_tv.setText(mData.get(position).getPings().size() + "");
                add_tv.setVisibility(View.VISIBLE);
            }
            if (mData.get(position).getPings().size() > 5) {

                viewHodler.mAllPing.setVisibility(View.VISIBLE);
                viewHodler.mAllPing.setTag(viewHodler.mPingList);
                viewHodler.mAllPing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ListView listView = (ListView) v.getTag();
                        PingAdapter adapter = (PingAdapter) listView.getAdapter();
                        adapter.setConut(mData.get(position).getPings().size());
                        v.setVisibility(View.GONE);
                    }
                });
            }
            return convertView;
        }
    }

    private class ViewHodler {

        public TextView mUserNameTv;

        public TextView mContentTv;
        public TextView mYearTv;

        public GridView mGridView;

        public ImageView mUserImage;

        public TextView mDateDay, mDateM, mDateTime;

        public TextView mBabyInfo;

        public TextView mTimeOut;

        public LinearLayout mShowAdd;

        public MyListView mPingList;

        public TextView mAllPing;

        public ImageView mZanImage;

        public LinearLayout mZanLin;

        public LinearLayout mZanSelectLin;

        public TextView mZanTv;
    }


    /**
     * @param date1 需要比较的时间 不能为空(null),需要正确的日期格式
     * @param date2 被比较的时间  为空(null)则为当前时间
     * @param stype 返回值类型   0为多少天，1为多少个月，2为多少年
     * @return
     */
    public static int compareDate(String date1, String date2, int stype) {
        int n = 0;

        String[] u = {"天", "月", "年"};
        String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

        date2 = date2 == null ? getCurrentDate() : date2;

        DateFormat df = new SimpleDateFormat(formatStyle);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch (Exception e3) {
            System.out.println("wrong occured");
        }
        //List list = new ArrayList();
        while (!c1.after(c2)) {                     // 循环对比，直到相等，n 就是所要的结果
            //list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组中 打印出来
            n++;
            if (stype == 1) {
                c1.add(Calendar.MONTH, 1);          // 比较月份，月份+1
            } else {
                c1.add(Calendar.DATE, 1);           // 比较天数，日期+1
            }
        }

        n = n - 1;

        if (stype == 2) {
            n = (int) n / 365;
        }

        System.out.println(date1 + " -- " + date2 + " 相差多少" + u[stype] + ":" + n);
        return n;
    }

    /**
     * 得到当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simple.format(date);
    }

    private class GridImageAdapter extends BaseAdapter {

        private ArrayList<KingVideos> mPaths;

        public GridImageAdapter(ArrayList<KingVideos> paths) {
            this.mPaths = paths;
        }

        @Override
        public int getCount() {
            return mPaths.size()>9?9:mPaths.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (mPaths.size() == 1) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.dynamic_image_item_one, null);
            } else {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.dynamic_image_item, null);
            }
            final ImageView image = (ImageView) convertView.findViewById(R.id.dynamic_image);
            final ImageView play = (ImageView) convertView.findViewById(R.id.dynamic_image_item_play);
            TextView mImageCount = (TextView) convertView.findViewById(R.id.image_count);

            ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + mPaths.get(position).getThumbnail(),
                    image, getOptions());

            if(mPaths.get(position).isVideo()) {
                play.setVisibility(View.VISIBLE);
            }
            if(position==8){
                mImageCount.setVisibility(View.VISIBLE);
                mImageCount.setText(mPaths.size()+"");
            }
            return convertView;
        }
    }

    private class PingAdapter extends BaseAdapter {

        private List<GetPingList.ResultDataBean> mPaths;

        public int mCount = 5;

        public void setConut(int i) {
            mCount = i;
            notifyDataSetChanged();
        }

        public PingAdapter(List<GetPingList.ResultDataBean> paths) {
            this.mPaths = paths;
        }

        @Override
        public int getCount() {
            return mPaths.size() < 5 ? mPaths.size() : mCount;
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
            convertView = LayoutInflater.from(getActivity()).inflate(R.layout.pings_item_view, null);
            TextView name = (TextView) convertView.findViewById(R.id.user_name);
            TextView cont = (TextView) convertView.findViewById(R.id.content_tv);
            name.setText(mPaths.get(position).getCommentUserName() + "：");
            cont.setText(mPaths.get(position).getCommentContent());
            return convertView;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            Ping ping = (Ping) data.getSerializableExtra("Data");
            int p = data.getIntExtra("position", 0);
            Logger.e(new Gson().toJson(ping));
            GetPingList.ResultDataBean dataBean = new GetPingList.ResultDataBean();
            dataBean.setGrowthDynamicsID(ping.getGrowthDynamicsID());
            dataBean.setCommentUserName(ping.getCommentUserName());
            dataBean.setCommentContent(ping.getCommentContent());
            dataBean.setCommentUserAccount(ping.getCommentUserAccount());
            dataBean.setID(ping.getID());
            mData.get(p).getCommentList().add(dataBean);
            mAdapter.notifyDataSetChanged();
            mACache.put("tynamicfragment_"+ mType + getUser().getUserAccount(), new Gson().toJson(mData), 7 * ACache.TIME_DAY);
            mACache.put("tynamicfragment_mpage"+ mType, mPager + "");
            //   mSwiper.setRefreshing();
        }
        if (requestCode == 145 && resultCode == 88) {
            getHttpData();
        }
    }

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private Bitmap createVideoThumbnail(String url, int width, int height) {


        Bitmap bitmap = mACache.getAsBitmap(url);

        if(bitmap == null) {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            int kind = MediaStore.Video.Thumbnails.MINI_KIND;
            try {
                if (Build.VERSION.SDK_INT >= 14) {
                    retriever.setDataSource(url, new HashMap<String, String>());
                } else {
                    retriever.setDataSource(url);
                }
                bitmap = retriever.getFrameAtTime();
            } catch (IllegalArgumentException ex) {
                // Assume this is a corrupt video file
            } catch (RuntimeException ex) {
                // Assume this is a corrupt video file.
            } finally {
                try {
                    retriever.release();
                } catch (RuntimeException ex) {
                    // Ignore failures while cleaning up.
                }
            }
            if (kind == MediaStore.Images.Thumbnails.MICRO_KIND && bitmap != null) {
                bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                        ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
            }
            mACache.put(url, bitmap);
            return bitmap;
        }else{
            return bitmap;
        }

    }


    public static String getTime(long time) {
        long date = System.currentTimeMillis() - time;
        long hour = 1000 * 60 * 60;
        long ifDay = hour * 24;
        long ifmut = hour * 24 * 30;
        long ifyear = hour * 24 * 30 * 12;

        long day = hour * 24;
        long mut = hour * 24 * 30;
        long year = hour * 24 * 30 * 12;
        if (date < (1000 * 60)) {
            return "刚刚";
        } else if (date < (1000 * 60 * 60)) {
            int a = (int) (date / (1000 * 60));
            return a + "分钟前";
        } else if (date < ifDay) {
            int a = (int) (date / (1000 * 60 * 60));
            return a + "小时前";
        } else if (date < ifmut) {
            int a = (int) (date / day);
            if (a == 0) a++;
            return a + "天前";
        } else if (date < ifyear) {
            int a = (int) (date / mut);
            if (a == 0) a++;
            return a + "月前";
        } else {
            int a = (int) (date / year);
            if (a == 0) a++;
            return a + "年前";
        }

    }
}
