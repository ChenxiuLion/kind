package com.youqd.kind.ckind.adapter;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.activity.PhotosActivity;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.constant.Constant;

import java.util.HashMap;

public class GalleryAdapter extends
        RecyclerView.Adapter<GalleryAdapter.ViewHolder>
{

    private LayoutInflater mInflater;
    private String[] mDatas;

    private Context mContext;

    public GalleryAdapter(Context context, String[] datats)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(View arg0)
        {
            super(arg0);
        }

        ImageView mImg;
        ImageView mTxt;
    }

    @Override
    public int getItemCount()
    {
        return mDatas.length;
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = mInflater.inflate(R.layout.desc_image,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.mImg = (ImageView) view
                .findViewById(R.id.id_index_gallery_item_image);
        viewHolder.mTxt = (ImageView) view
                .findViewById(R.id.id_index_gallery_item_play);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i)
    {
        if(!mDatas[i].contains("mp4")) {
            viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PhotosActivity.class);
                    intent.putExtra("images",mDatas);
                    intent.putExtra("postion",i);
                    mContext.startActivity(intent);
                }
            });
            ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + mDatas[i], viewHolder.mImg, KingActivity.getOptions());
        }else{
            viewHolder.mTxt.setVisibility(View.VISIBLE);

            new Thread(){
                @Override
                public void run() {
                    super.run();
                    final Bitmap b = createVideoThumbnail(Constant.IMAGE_URL + mDatas[i],64, 48);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            viewHolder.mImg.setImageBitmap(b);
                        }
                    });
                }
            }.start();

        }

    }


    private Handler mHandler = new Handler(Looper.getMainLooper());

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