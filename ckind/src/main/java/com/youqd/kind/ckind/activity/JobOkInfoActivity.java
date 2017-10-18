package com.youqd.kind.ckind.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.GetJobInfo;
import com.youqd.kind.ckind.bean.ImageBean;
import com.youqd.kind.ckind.bean.JobBean;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.model.KingVideos;
import com.youqd.kind.ckind.util.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;

import krelve.view.Kanner;

import static com.youqd.kind.ckind.activity.JobInfoActivity.*;

/**
 * Created by Chenxiu on 2016/8/9.
 */
public class JobOkInfoActivity extends KingActivity {
    private JobBean mJobBean;

    GetJobInfo.ResultDataBean mJobInfo;

    private MyGridView mGridView;

    private ArrayList<ImageBean> mPaths = new ArrayList<>();
    @Override
    protected int initLayout() {
        return R.layout.activity_job_info;
    }

    @Override
    protected void initViews() {
        mJobBean = (JobBean) getIntent().getSerializableExtra("data");
        findViewById(R.id.rightId).setVisibility(View.GONE);
        mGridView = (MyGridView) findViewById(R.id.select_grid);
        ((TextView) findViewById(R.id.top_title)).setText("作业详情");
        ((EditText) findViewById(R.id.job_info_edit)).setFocusable(false);
        ((EditText) findViewById(R.id.job_info_edit)).setText(mJobBean.getOkText());
        ((EditText) findViewById(R.id.job_info_edit)).setEnabled(false);
        ((TextView) findViewById(R.id.job_info_name)).setText(mJobBean.getName());
        ((TextView) findViewById(R.id.job_info_date)).setText(mJobBean.getDate());
        if(!TextUtils.isEmpty(mJobBean.getOkLaoshi())) {
            ((TextView) findViewById(R.id.laoshi_tv)).setText(mJobBean.getOkLaoshi());
            ((TextView) findViewById(R.id.laoshi_tv)).setVisibility(View.VISIBLE);
            findViewById(R.id.laoshi_tvp).setVisibility(View.VISIBLE);
            findViewById(R.id.laoshi_tvv).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.laoshi_tvname)).setText(mJobBean.getOkLaoshiName());
            ((TextView) findViewById(R.id.laoshi_tvname)).setVisibility(View.VISIBLE);

            ((TextView) findViewById(R.id.laoshi_tvdate)).setText(mJobBean.getOkLaoshiDate());
            ((TextView) findViewById(R.id.laoshi_tvdate)).setVisibility(View.VISIBLE);
        }
        ((TextView) findViewById(R.id.job_info_content)).setText(mJobBean.getContent());
        findViewById(R.id.login_submit).setVisibility(View.GONE);
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

        (findViewById(R.id.select_lin)).setVisibility(View.GONE);
        final ArrayList<KingVideos> data = new ArrayList<>();
        for(String str : mJobBean.getOkImagePath()){
            data.add(new KingVideos("",str));
        }
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!mPaths.get(position).getPath().contains("mp4")) {
                    Intent intent = new Intent(mContext, PhotosActivity.class);
                    intent.putParcelableArrayListExtra("images", data);
                    intent.putExtra("postion", position);
                    mContext.startActivity(intent);
                }else{
                    Intent intent = new Intent(mContext, ViewPlayActivity.class);
                    intent.putExtra("path", mPaths.get(position).getPath());
                    startActivity(intent);
                }
            }
        });
        for(String path : mJobBean.getOkImagePath()){
            ImageBean image = new ImageBean();
            image.setPath(path);
            image.setType(0);
            mPaths.add(image);
        }
        for(KingVideos path : mJobBean.getOkVideoPath()){
            ImageBean image = new ImageBean();
            image.setPath(path.getVideoPath());
            image.setType(1);
            image.setSuo(path.getThumbnail());
            mPaths.add(image);
        }
        mGridView.setAdapter(new ImageAdapter());
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

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
    public void finish() {
        setResult(11,getIntent());
        super.finish();
    }

    @Override
    public void onBackPressed() {
        setResult(11,getIntent());
        super.onBackPressed();
    }

    public class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mPaths.size();
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
                    ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + mPaths.get(position).getPath(), imageView, getOptions());

                } else {
                    ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + mPaths.get(position).getSuo(), imageView, getOptions());
                    play.setVisibility(View.VISIBLE);
                }
            }
            return convertView;
        }
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private Bitmap createVideoThumbnail(String url, int width, int height) {
        Bitmap bitmap = null;
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
        return bitmap;
    }

}
