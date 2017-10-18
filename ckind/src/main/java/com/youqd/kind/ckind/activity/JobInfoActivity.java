package com.youqd.kind.ckind.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cxandroid.imageutil.imageload.SelectImageActivity;
import com.example.bajian.sheetdialogue.SheetDialog;
import com.google.gson.Gson;
import com.liulishuo.magicprogresswidget.MagicProgressBar;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.ImageBean;
import com.youqd.kind.ckind.bean.JobBean;
import com.youqd.kind.ckind.bean.JobResult;
import com.youqd.kind.ckind.bean.KindBean;
import com.youqd.kind.ckind.bean.PostJob;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.model.KingVideos;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.MyGridView;
import com.youqd.kind.ckind.util.UpdataNewTool;
import com.youqd.kind.ckind.util.UpdataTool;
import com.youqd.kind.ckind.video.NewRecordVideoActivity;
import com.youqd.kind.ckind.video.PlayVideoActiviy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import chenxiu.zz.com.camer.CameraAty;
import cn.dreamtobe.percentsmoothhandler.ISmoothTarget;
import cn.pedant.SweetAlert.SweetAlertDialog;
import krelve.view.Kanner;

/**
 * Created by Chenxiu on 2016/7/26.
 */
public class JobInfoActivity extends KingActivity {
    private String[] strs = new String[]{"小视频","拍照", "从手机相册选择"};

    private JobBean mJobBean;

    private ArrayList<ImageBean> mPaths = new ArrayList<>();

    private ArrayList<String> mImages = new ArrayList<>();

    private ArrayList<String> mVideos = new ArrayList<>();

    private MyGridView mGridView;


    @Override
    protected int initLayout() {
        return R.layout.activity_job_info;
    }

    @Override
    protected void initViews() {
        mJobBean = (JobBean) getIntent().getSerializableExtra("data");
        findViewById(R.id.rightId).setVisibility(View.GONE);


        ((TextView) findViewById(R.id.top_title)).setText("作业详情");
        ((TextView) findViewById(R.id.job_info_name)).setText(mJobBean.getName());
        ((TextView) findViewById(R.id.job_info_content)).setText(mJobBean.getContent());
        ((TextView) findViewById(R.id.job_info_date)).setText(mJobBean.getDate());

        findViewById(R.id.login_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPaths.size()>0){
                   final SweetAlertDialog pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("提交中");
                    pDialog.setCancelable(false);
                    pDialog.show();
                    UpdataNewTool.getTool(mPaths).statrUpdata(new UpdataNewTool.OnUpdata() {
                        @Override
                        public void onIndex(int index) {
                        }

                        @Override
                        public void onSucceed(ArrayList<String> paths) {
                            Logger.e("全部上传成功");
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            PostJob job = new PostJob();
                            job.setHomeWorkID(mJobBean.getId());
                            job.setHomeWorkTitle(mJobBean.getName());
                            job.setOperationID(getUser().getID());
                            job.setBabyID(getBaby().getID());
                            job.setProfile( ((EditText) findViewById(R.id.job_info_edit)).getText().toString().trim());
                            job.setBabyName(getBaby().getUserName());
                            job.setOperationTime(simpleDateFormat.format(new Date()));
                            job.setOperationUser(getBaby().getUserName()+"的"+getUser().getRelation());
                            ArrayList<String> images = new ArrayList<String>();
                            ArrayList<KingVideos> videos = new ArrayList<KingVideos>();
                            for(String path : paths){
                                if(path.contains("mp4")){
                                    videos.add(new KingVideos(path.split(";")[0],path.split(";")[1]));
                                }else{
                                    images.add(path);
                                }
                            }
                            job.setPhoto(new Gson().toJson(images));
                            job.setVideo(new Gson().toJson(videos));
                            job.setStatus(true);
                            HttpTool.getInstance().doPostJob(job, new KingCallback<JobResult>(JobResult.class) {
                                @Override
                                public void onSucceed(JobResult data) {
                                    pDialog.dismiss();
                                    if(data.getResultCode()==1) {
                                        new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE)
                                                .setTitleText("上传成功")
                                                .setContentText("学生作业提交成功!")
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                        showShortToast("成功");
                                                        sweetAlertDialog.dismiss();
                                                        finish();
                                                    }
                                                })
                                                .show();
                                    }else{
                                        new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("上传失败")
                                                .setContentText("请再次重新提交!")
                                                .show();
                                    }
                                }

                                @Override
                                public void onErro() {

                                }
                            });

                        }

                        @Override
                        public void onErro() {
                            pDialog.dismiss();
                            new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("上传失败")
                                    .setContentText("请再次重新提交!")
                                    .show();
                        }
                    });
                }
            }
        });
        mGridView = (MyGridView) findViewById(R.id.select_grid);
        final ArrayList<KingVideos> mPathss = new ArrayList<>();
        if(!TextUtils.isEmpty(mJobBean.getImage())) {
            final String[] image = mJobBean.getImage().split(";");
            for (String str : image) {
                mPathss.add(new KingVideos("",str));
            }
        }

        MyGridView mKanner = (MyGridView) findViewById(R.id.kanner);
        mKanner.setVisibility(View.VISIBLE);
        mKanner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, PhotosActivity.class);
                intent.putParcelableArrayListExtra("images", mPathss);
                intent.putExtra("postion", position);
                context.startActivity(intent);
            }
        });
        mKanner.setAdapter(new GridImageAdapter(mPathss));
        (findViewById(R.id.select_lin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SheetDialog(mContext)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItems(strs
                                , SheetDialog.SheetItemColor.Blue, new SheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        if(which==2){
                                            Intent intent = new Intent(mContext, SelectImageActivity.class);
                                            intent.putExtra("images", new Gson().toJson(mImages));
                                            intent.putExtra("maxCount", 12);
                                            intent.putExtra("isPhoto", 1);
                                            startActivityForResult(intent, 189);
                                        }else if(which == 1){
                                            Intent intent = new Intent(mContext, CameraAty.class);
                                            startActivityForResult(intent, 190);
                                        }else if(which==3){
                                            Intent intent = new Intent(mContext, SelectImageActivity.class);
                                            intent.putExtra("images", new Gson().toJson(mImages));
                                            intent.putExtra("maxCount", 12);
                                            startActivityForResult(intent, 189);
                                        }
                                        System.out.println("which" + which);
                                    }
                                }).show();
            }
        });
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == mPaths.size()){
                    new SheetDialog(mContext)
                            .setCanceledOnTouchOutside(false)
                            .addSheetItems(strs
                                    , SheetDialog.SheetItemColor.Blue, new SheetDialog.OnSheetItemClickListener() {
                                        @Override
                                        public void onClick(int which) {
                                            if(which==2){
                                                Intent intent = new Intent(mContext, SelectImageActivity.class);
                                                intent.putExtra("images", new Gson().toJson(mImages));
                                                intent.putExtra("maxCount", 12);
                                                intent.putExtra("isPhoto", 1);
                                                startActivityForResult(intent, 189);
                                            }else if(which == 1){
                                                Intent intent = new Intent(mContext, CameraAty.class);
                                                startActivityForResult(intent, 190);
                                            }else if(which==3){
                                                Intent intent = new Intent(mContext, SelectImageActivity.class);
                                                intent.putExtra("images", new Gson().toJson(mImages));
                                                intent.putExtra("maxCount", 12);
                                                startActivityForResult(intent, 189);
                                            }
                                            System.out.println("which" + which);
                                        }
                                    }).show();
                }else{
                    if(mPaths.get(position).getType()==1){
                        Intent intent = new Intent(mContext, PlayVideoActiviy.class);
                        intent.putExtra(PlayVideoActiviy.KEY_FILE_PATH, mPaths.get(position).getPath());
                        startActivity(intent);
                    }
                }
            }
        });
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new SheetDialog(mContext)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItems(new String[]{"删除"}
                                , SheetDialog.SheetItemColor.Blue, new SheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Logger.e(position+"");

                                        if(mPaths.get(position).getType()==0){
                                            mImages.remove(position);
                                        }else {
                                            for (int i = 0; i < mVideos.size(); i++) {
                                                if(mVideos.get(i).equals(mPaths.get(position).getPath())){
                                                    mVideos.remove(i);
                                                }

                                            }
                                        }

                                        mPaths.remove(position);
                                        mGridView.setAdapter(new ImageAdapter());
                                    }
                                }).show();
                return false;
            }
        });
    }
    private float getIncreasedPercent(SweetAlertDialog target) {
        float increasedPercent = target.getProgressHelper().getProgress() + (1f/mPaths.size());

        return Math.min(1, increasedPercent);
    }
    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void finish() {
        setResult(11,getIntent());
        super.finish();
    }

    @Override
    public void onBackPressed() {
        setResult(11,getIntent());
        super.onBackPressed();
    }
    public class GridImageAdapter extends BaseAdapter {

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
                convertView =LayoutInflater.from(mContext).inflate(R.layout.dynamic_image_item_one, null);
            } else {
                convertView =LayoutInflater.from(mContext).inflate(R.layout.job_image_item, null);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null) {
            mPaths.clear();
            if(requestCode == 189) {
                ArrayList<String> images = data.getStringArrayListExtra("images");
                mImages.clear();
                mImages.addAll(images);
            }else if(requestCode == 190){
                Logger.e("视频地址："+data.getStringExtra(PlayVideoActiviy.KEY_FILE_PATH));
                ArrayList<String> list = data.getStringArrayListExtra(PlayVideoActiviy.KEY_FILE_PATH);
                mVideos.addAll(list);
            }

            for(String path : mImages){
                ImageBean image = new ImageBean();
                image.setPath(path);
                image.setType(0);
                mPaths.add(image);
            }
            for(String path : mVideos){
                ImageBean image = new ImageBean();
                image.setPath(path);
                image.setType(1);
                mPaths.add(image);
            }
            (findViewById(R.id.select_lin)).setVisibility(View.GONE);
            mGridView.setAdapter(new ImageAdapter());

        }
    }


    public class ImageAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mPaths.size()+1;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.image_grid,null);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.grid_image);
            ImageView play = (ImageView) convertView.findViewById(R.id.play_image);
            if(position !=mPaths.size()) {
                if (mPaths.get(position).getType() == 0) {
                    ImageLoader.getInstance().displayImage("file://" + mPaths.get(position).getPath(), imageView, getOptions());

                } else {
                    imageView.setImageBitmap(getVideoThumbnail(mPaths.get(position).getPath()));
                    play.setVisibility(View.VISIBLE);
                }
            }else{
                imageView.setImageResource(R.drawable.icon_jiahao);
            }
            return convertView;
        }
    }

    public Bitmap getVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime(TimeUnit.MILLISECONDS.toMicros(1));
        }
        catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        finally {
            try {
                retriever.release();
            }
            catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}
