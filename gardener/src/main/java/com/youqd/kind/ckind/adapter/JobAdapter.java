package com.youqd.kind.ckind.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.activity.JobOkInfoActivity;
import com.youqd.kind.ckind.activity.PhotosActivity;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.AllBoby;
import com.youqd.kind.ckind.bean.CheckList;
import com.youqd.kind.ckind.bean.JobBean;
import com.youqd.kind.ckind.bean.JobList;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.model.KingVideos;
import com.youqd.kind.ckind.util.MyGridView;
import com.youqd.kind.ckind.util.UserManage;

import java.util.ArrayList;
import java.util.List;

import krelve.view.Kanner;

/**
 * Created by Administrator on 2016/3/22.
 */
public class JobAdapter extends BaseAdapter {

    private List<JobBean> datas;
    private LayoutInflater li = null;
    private Context context;
    private boolean isInfo = false;

    public JobAdapter(Context context, List<JobBean> data) {
        this.context = context;
        this.datas = data;
        li = LayoutInflater.from(context);
    }

    public JobAdapter(Context context, List<JobBean> datas, boolean isInfo) {
        this.context = context;
        this.datas = datas;
        this.isInfo = isInfo;
        li = LayoutInflater.from(context);


    }


    public void setData(List<JobBean> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        convertView = li.inflate(R.layout.fragment_job_item, null);
        viewHolder = new ViewHolder();
        viewHolder.name = (TextView) convertView.findViewById(R.id.job_name);
        viewHolder.content = (TextView) convertView.findViewById(R.id.job_content);
        viewHolder.jobTime = (TextView) convertView.findViewById(R.id.job_time);
        viewHolder.count1 = (TextView) convertView.findViewById(R.id.qiandao_yi);
        viewHolder.count2 = (TextView) convertView.findViewById(R.id.qiandao_wei);
        viewHolder.mLayout = (LinearLayout) convertView.findViewById(R.id.sl_student);
        viewHolder.gridView1 = (GridView) convertView.findViewById(R.id.recodeList) ;
        viewHolder.gridView2 = (GridView) convertView.findViewById(R.id.recodeList2) ;
        viewHolder.mJobClassName = (TextView) convertView.findViewById(R.id.job_class);
        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.top_layout);
        viewHolder.finish = (ImageView) convertView.findViewById(R.id.job_finish);
        viewHolder.jobImage = (ImageView) convertView.findViewById(R.id.icon_class_job);
        convertView.setTag(viewHolder);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnJobLiseten!=null){
                    mOnJobLiseten.onJob(position);
                }
            }
        });



        if(isInfo){
            viewHolder.jobImage.setVisibility(View.GONE);
            final ArrayList<KingVideos> mPaths = new ArrayList<>();
            if(!TextUtils.isEmpty(datas.get(position).getImage())) {
                final String[] image = datas.get(position).getImage().split(";");
                for (String str : image) {
                    mPaths.add(new KingVideos("",str));
                }
            }
            MyGridView mKanner = (MyGridView) convertView.findViewById(R.id.kanner);
            mKanner.setVisibility(View.VISIBLE);
            mKanner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, PhotosActivity.class);
                    intent.putParcelableArrayListExtra("images", mPaths);
                    intent.putExtra("postion", position);
                    context.startActivity(intent);
                }
            });
            mKanner.setAdapter(new GridImageAdapter(mPaths));
            viewHolder.mLayout.setVisibility(View.VISIBLE);
            final AllBoby mAllbobywei= UserManage.getInstance().getAllBoby();
            final AllBoby mAllbobyyi= new AllBoby();

            if(datas.get(position).getUsers()!=null){

                for(JobList.ResultDataBean bean : datas.get(position).getUsers()){
                    for(AllBoby.ResultDataBean boby : mAllbobywei.getResultData()){
                        if(boby.getID() == bean.getBabyID()){
                            boby.setImages(bean.getPhoto());
                            boby.setVideo(bean.getVideo());
                            boby.setJobBean(datas.get(position));
                            boby.setBean(bean);
                            mAllbobyyi.getResultData().add(boby);
                            mAllbobywei.getResultData().remove(boby);
                            break;
                        }
                    }
                }
            }

            viewHolder.count1.setText(mAllbobyyi.getResultData().size()+"");
            viewHolder.count2.setText(mAllbobywei.getResultData().size()+"");
            viewHolder.gridView2.setAdapter(new JobUserAdapter(mAllbobywei));
            viewHolder.gridView1.setAdapter(new JobUserAdapter(mAllbobyyi));
        }else{
            viewHolder.content.setLines(1);
        }

        viewHolder.name.setText(datas.get(position).getName());
        viewHolder.content.setText(datas.get(position).getContent());


        if(TextUtils.isEmpty(datas.get(position).getImage().split(";")[0])){
            viewHolder.jobImage.setVisibility(View.GONE);
        }else{
            ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + datas.get(position).getImage().split(";")[0]
                    , viewHolder.jobImage, KingActivity.getOptions(R.drawable.icon_defutl_loding_icon)
            );
        }
        if (datas.get(position).isFinish()) {
            viewHolder.finish.setImageResource(R.drawable.icon_suc_finish);
            viewHolder.jobTime.setTextColor(context.getResources().getColor(R.color.main_green_bg));
            viewHolder.jobTime.setText("完成时间：" + datas.get(position).getDate());
        } else {
            viewHolder.finish.setImageResource(R.drawable.icon_no_finish);
            viewHolder.jobTime.setTextColor(context.getResources().getColor(R.color.main_h_bg));
            viewHolder.jobTime.setText("发布时间：" + datas.get(position).getDate());
        }

        return convertView;
    }
    public void updata(AllBoby as,JobList.ResultDataBean bean){

    }

    private OnJobLiseten mOnJobLiseten;


    public void setOnJobLiseten(OnJobLiseten mOnJobLiseten) {
        this.mOnJobLiseten = mOnJobLiseten;
    }

    public interface OnJobLiseten{
        void onJob(int position);
    }
    public class JobUserAdapter extends BaseAdapter{

        public AllBoby data;

        public JobUserAdapter(AllBoby data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.getResultData().size();
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
            convertView = li.inflate(R.layout.job_item,null);

            ImageView userImage = (ImageView) convertView.findViewById(R.id.userimage);

            TextView userTv = (TextView) convertView.findViewById(R.id.username);
            userTv.setText( data.getResultData().get(position).getUserName());
            ImageLoader.getInstance().displayImage(Constant.IMAGE_URL+
                    data.getResultData().get(position).getPhoto(),userImage,
                    KingActivity.getOptions(R.drawable.icon_girl));
            userImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, JobOkInfoActivity.class);
                    intent.putExtra("data",data.getResultData().get(position).getBean());
                    intent.putExtra("jbdata",data.getResultData().get(position).getJobBean());
                    if(data.getResultData().get(position).getBean()!=null) {
                        context.startActivity(intent);
                    }
                }
            });
            return convertView;
        }
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
                convertView =li.inflate(R.layout.dynamic_image_item_one, null);
            } else {
                convertView =li.inflate(R.layout.job_image_item, null);
            }
            final ImageView image = (ImageView) convertView.findViewById(R.id.dynamic_image);
            final ImageView play = (ImageView) convertView.findViewById(R.id.dynamic_image_item_play);
            TextView mImageCount = (TextView) convertView.findViewById(R.id.image_count);

            ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + mPaths.get(position).getThumbnail(),
                    image,  KingActivity.getOptions());

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
    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        public TextView name;
        public TextView content;
        public ImageView finish;
        public TextView jobTime;

        public ImageView jobImage;


        public TextView count1,count2;
        public GridView gridView1,gridView2;

        public LinearLayout mLayout;
        public TextView mJobClassName;
    }
}
