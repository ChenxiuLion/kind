package com.youqd.kind.ckind.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cxandroid.imageutil.imageload.SelectImageActivity;
import com.example.bajian.sheetdialogue.SheetDialog;
import com.google.gson.Gson;
import com.kind.chx.gardener.R;
import com.kyleduo.switchbutton.SwitchButton;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.AddDynamic;
import com.youqd.kind.ckind.bean.AddNotif;
import com.youqd.kind.ckind.bean.ImageBean;
import com.youqd.kind.ckind.bean.JobResult;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.MyGridView;
import com.youqd.kind.ckind.util.UpdataNewTool;
import com.youqd.kind.ckind.util.UserManage;
import com.youqd.kind.ckind.video.PlayVideoActiviy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import chenxiu.zz.com.camer.CameraAty;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Chenxiu on 2016/9/20.
 */
public class AddNotifiActivity extends KingActivity {
    private String[] strs = new String[]{"拍照", "从手机相册选择"};
    private ArrayList<ImageBean> mPaths = new ArrayList<>();

    private ArrayList<String> mImages = new ArrayList<>();

    private ArrayList<String> mVideos = new ArrayList<>();

    private MyGridView mGridView;

    private EditText mEditText,mTitleEdit;

    private SwitchButton mAddClassSwitch;

    private int mType = 1;

    @Override
    protected int initLayout() {
        return R.layout.activity_add_notifi;
    }

    @Override
    protected void initViews() {
        mType = getIntent().getIntExtra("type",1);
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("发布班级通知");
        mGridView = (MyGridView) findViewById(R.id.select_grid);
        mAddClassSwitch = (SwitchButton) findViewById(R.id.add_class_switch);
        if(mType == 2){
            ((TextView)findViewById(R.id.type_tv)).setText("同步到家园动态");
        }
        mEditText = (EditText) findViewById(R.id.job_info_edit);
        mTitleEdit = (EditText) findViewById(R.id.job_title_edit);
        (findViewById(R.id.select_lin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SheetDialog(mContext)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItems(strs
                                , SheetDialog.SheetItemColor.Blue, new SheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        if(which==1){
                                            Intent intent = new Intent(mContext, SelectImageActivity.class);
                                            intent.putExtra("images", new Gson().toJson(mImages));
                                            intent.putExtra("maxCount",60);
                                            intent.putExtra("isPhoto", 1);
                                            startActivityForResult(intent, 189);
                                        }else  if(which==2){
                                            Intent intent = new Intent(mContext, SelectImageActivity.class);
                                            intent.putExtra("images", new Gson().toJson(mImages));
                                            intent.putExtra("maxCount", 60);
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
                                            if(which==1){
                                                Intent intent = new Intent(mContext, SelectImageActivity.class);
                                                intent.putExtra("images", new Gson().toJson(mImages));
                                                intent.putExtra("maxCount", Integer.MAX_VALUE);
                                                intent.putExtra("isPhoto", 1);
                                                startActivityForResult(intent, 189);
                                            }else  if(which==2){
                                                Intent intent = new Intent(mContext, SelectImageActivity.class);
                                                intent.putExtra("images", new Gson().toJson(mImages));
                                                intent.putExtra("maxCount", 60);
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

        findViewById(R.id.login_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mEditText.getText().toString().trim())){
                    showShortToast("标题未填写");
                    return;
                }
                if(TextUtils.isEmpty(mEditText.getText().toString().trim())){
                    showShortToast("内容未填写");
                    return;
                }
                    final SweetAlertDialog pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("提交中");
                    pDialog.setCancelable(true);
                    pDialog.show();
                    UpdataNewTool.getTool(mPaths).statrUpdata(new UpdataNewTool.OnUpdata() {
                        @Override
                        public void onIndex(int index) {
                        }

                        @Override
                        public void onSucceed(ArrayList<String> paths) {
                            AddNotif add = new AddNotif();
                            String strPhoto="",strVideo="";
                            for(String path : paths){
                                if(path.contains("mp4") ||path.contains("3gp")){
                                    String[] temps = path.split(";");
                                    strVideo += temps[0]+";";
                                }else{
                                    strPhoto += path+";";
                                }
                            }

                            add.setImageURL(strPhoto);
                            add.setMediaURL(strVideo);
                            add.setReadClassID(getUser().getClassID());
                            add.setKindergartenID(getUser().getKindergartenID());
                            add.setCreateUserType(4);
                            add.setNoticeType(6);
                            add.setReadGradeID(getUser().getGradeID());

                            add.setSubNoticeType(2);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                            add.setCreateTime(dateFormat.format(new Date()));
                            add.setCreateUserID(getUser().getID());
                            add.setReadGradeID(getUser().getGradeID());
                            add.setAgentID(getUser().getAgentID());
                            add.setReadClassName(UserManage.getInstance().getUser().getClassName());
                            add.setCreateUserName(getUser().getUserName());
                            add.setContent(mEditText.getText().toString().trim());
                            add.setTitle(mTitleEdit.getText().toString().trim());
                            HttpTool.getInstance().doAddNotif(add, new KingCallback<JobResult>(JobResult.class) {
                                @Override
                                public void onSucceed(JobResult data) {
                                    pDialog.dismiss();
                                    if(data.getResultCode()==1) {
                                        new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE)
                                                .setTitleText("成功")
                                                .setContentText("发布成功!")
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                        showShortToast("成功");
                                                        sweetAlertDialog.dismiss();
                                                        setResult(88,getIntent());
                                                        finish();
                                                    }
                                                })
                                                .show();
                                    }else{
                                        new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("发布失败")
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
        });
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

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


    public class ImageAdapter extends BaseAdapter {

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
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.image_grid,null);

            final ImageView imageView = (ImageView) convertView.findViewById(R.id.grid_image);
            ImageView play = (ImageView) convertView.findViewById(R.id.play_image);
            if(position !=mPaths.size()) {
                if (mPaths.get(position).getType() == 0) {
                    ImageLoader.getInstance().displayImage("file://" + mPaths.get(position).getPath(), imageView, getOptions());

                } else {
                    new Thread(){
                        @Override
                        public void run() {
                            final Bitmap bitmap = getVideoThumbnail(mPaths.get(position).getPath());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    imageView.setImageBitmap(bitmap);
                                }
                            });
                            super.run();
                        }
                    }.start();

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
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                retriever.release();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}
