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
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.GetNews;
import com.youqd.kind.ckind.bean.GetUserName;
import com.youqd.kind.ckind.bean.NewsModel;
import com.youqd.kind.ckind.bean.NewsUser;
import com.youqd.kind.ckind.bean.UserLookList;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.model.KingVideos;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UserManage;
import com.youqd.kind.ckind.widget.LabelView;

import java.util.ArrayList;
import java.util.HashMap;

import tm.model.ImageBDInfo;
import tm.model.ImageInfo;

public class NewDetailActivity extends KingActivity {

    public static String TITLE = "TITLE";
    public static String CONTENT = "Content";
    public static String BEAN = "bean";
    public NewsModel.ResultDataBean mBean;

    private LinearLayout mImageLin ;

    private int mWight ;

    private GridView mGridView;

    private UserLookList mData;

    private ScrollView mScroll;

    private LabelView mLabelView;

    private ImageView mNewImage;

    private  String id;
    @Override
    protected int initLayout() {
        return R.layout.activity_new_detail;
    }
    @Override
    protected void initViews() {
        findViewById(R.id.rightId).setVisibility(View.GONE);
        mGridView = (GridView) findViewById(R.id.user_grid);
        mWight = (int) (getWindowManager().getDefaultDisplay().getWidth()*0.7);

        mLabelView = (LabelView) findViewById(R.id.lb_v);
        mBean = (NewsModel.ResultDataBean) getIntent().getSerializableExtra(BEAN);
        mScroll = (ScrollView) findViewById(R.id.scrollView1) ;
        mNewImage = (ImageView) findViewById(R.id.new_image);
        id = getIntent().getStringExtra("id");
        if(mBean != null){
            id = mBean.getID()+"";
        }
        String json = mACache.getAsString("NewDetailActivity_"+id);

        if(TextUtils.isEmpty(json)) {
            if (mBean == null) {
                HttpTool.getInstance().getNoticeInfo(id, new KingCallback<GetNews>(GetNews.class) {
                    @Override
                    public void onSucceed(GetNews data) {

                        mBean = data.getResultData();
                        mACache.put("NewDetailActivity_"+id,new Gson().toJson(mBean));
                        getData();
                    }

                    @Override
                    public void onErro() {

                    }
                });
            } else {
                mACache.put("NewDetailActivity_"+id,new Gson().toJson(mBean));
                getData();
            }
        }else{
            mBean = new Gson().fromJson(json,NewsModel.ResultDataBean.class);
            getData();
        }
    }




    public void getData(){
       /* HttpTool.getInstance().getUserName(getUser().getID()+"", new KingCallback<GetUserName>(GetUserName.class) {
            @Override
            public void onSucceed(GetUserName data) {
                HttpTool.getInstance().doUserLook(mBean, data.getResultData().getID(),getBaby().getID(),data.getResultData().getRelation(), new KingCallback<NewsUser>(NewsUser.class) {
                    @Override
                    public void onSucceed(NewsUser data) {
                        getUsers();

                    }

                    @Override
                    public void onErro() {
                    }
                });
            }

            @Override
            public void onErro() {

            }
        });*/
        getUsers();

        ((TextView)findViewById(R.id.title_tv)).setText(mBean.getTitle());
        ((TextView)findViewById(R.id.record_content)).setText(mBean.getContent());
        if(mBean.getNoticeType() == 6){
            ((TextView)findViewById(R.id.top_title)).setText("班级通知详情");
            findViewById(R.id.yiyuedu_wenzi).setVisibility(View.VISIBLE);
            mGridView.setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.record_src)).append(TextUtils.isEmpty(mBean.getReadClassName())?"":mBean.getReadClassName());
        }else{
            ((TextView)findViewById(R.id.top_title)).setText("校园新闻详情");
            ((TextView)findViewById(R.id.record_src)).setText(UserManage.getInstance().getKind().getResultData().getName());
        }
               ((TextView)findViewById(R.id.record_time)).setText(mBean.getLastUpdateTime().replace("T"," "));
        Logger.json(new Gson().toJson(mBean));
        mImageLin = (LinearLayout) findViewById(R.id.image_list_layout);

        final ArrayList<KingVideos> mPaths = new ArrayList<>();
        if(!TextUtils.isEmpty(mBean.getImageURL())) {
            final String[] image = mBean.getImageURL().split(";");
            for (String str : image) {
                mPaths.add(new KingVideos("",str));
            }
        }
        if(!TextUtils.isEmpty(mBean.getMediaURL())) {
            final String[] video = mBean.getMediaURL().split(";");
            for(String str : video){
                mPaths.add(new KingVideos(str,""));
            }
        }
        if(mPaths.size()>0) {
            findViewById(R.id.new_images_lin).setVisibility(View.VISIBLE);
            for (int i = 0;i<mPaths.size();i++) {
                // addImage(image[i],i);
                final  int p = i;
                if(!mPaths.get(i).isVideo()) {
                    ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + mPaths.get(i).getThumbnail(), mNewImage, getOptions());
                }else{
                    new Thread(){
                        @Override
                        public void run() {
                            final Bitmap bitmap = createVideoThumbnail(Constant.IMAGE_URL+mPaths.get(0).getVideoPath()
                                    ,300,300);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mNewImage.setImageBitmap(bitmap);
                                }
                            });
                            super.run();
                        }
                    }.start();

                }
                break;
            }
            mNewImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PhotosActivity.class);
                    intent.putParcelableArrayListExtra("images", mPaths);
                    intent.putExtra("postion", 0);
                    startActivity(intent);
                }
            });
            if(mPaths.size()>1){
                findViewById(R.id.new_images).setVisibility(View.VISIBLE);
                ((TextView)findViewById(R.id.new_te)).setText("  图集\n("+mPaths.size()+")");
                if(!TextUtils.isEmpty(mBean.getMediaURL())){
                    if(TextUtils.isEmpty(mBean.getImageURL())){
                        ((TextView)findViewById(R.id.new_te)).setText("  图集\n("+mPaths.size()+")");
                    }else{
                        ((TextView)findViewById(R.id.new_te)).setText("  图集\n("+mPaths.size()+")");
                    }

                }
            }else{

                if(mPaths.get(0).isVideo()) {
                    findViewById(R.id.viewplauy).setVisibility(View.VISIBLE);
                }
            }
        }else{
            mImageLin.setVisibility(View.GONE);
        }
    }


    public void getUsers() {

        String json = mACache.getAsString("NewDetailActivity_users"+id);

        if(!TextUtils.isEmpty(json)) {
            mData = new Gson().fromJson(json,UserLookList.class);
            mGridView.setAdapter(mUserAdapter);
        }

        HttpTool.getInstance().getUserLookList(mBean.getID() + "", getUser().getClassID() + "", new KingCallback<UserLookList>(UserLookList.class) {
            @Override
            public void onSucceed(UserLookList data) {
                mData = data;
                mACache.put("NewDetailActivity_users"+id,new Gson().toJson(data));
                mGridView.setAdapter(mUserAdapter);
            }

            @Override
            public void onErro() {

            }
        });
    }

    public BaseAdapter mUserAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mData.getResultData().size();
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
            if (convertView == null) {
                convertView = LayoutInflater.from(NewDetailActivity.this).inflate(R.layout.user_look_list_item, null);

            }
            ImageView userImage = (ImageView) convertView.findViewById(R.id.user_image);
            TextView userName = (TextView) convertView.findViewById(R.id.user_name);
            ImageLoader.getInstance().displayImage(
                    Constant.IMAGE_URL + mData.getResultData().get(position).getPhoto(),
                    userImage,
                    KingActivity.getOptions());
            userName.setText(mData.getResultData().get(position).getUserName());
            // UserManage.getInstance().getPatriarchName(mData.getResultData().get(position).getID() + "", userName);
            return convertView;
        }
    };
    @Override
    protected void initEvents() {

    }
    private ArrayList<ImageInfo> data = new ArrayList<>();
    private ImageBDInfo bdInfo = new ImageBDInfo();
    @Override
    public void onClick(View v) {

    }

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

}
