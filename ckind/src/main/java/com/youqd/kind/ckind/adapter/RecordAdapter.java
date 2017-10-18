package com.youqd.kind.ckind.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.NewsModel;
import com.youqd.kind.ckind.bean.RecordBean;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.util.ACache;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by youqd on 2016/3/24.
 */
public class RecordAdapter extends BaseAdapter {

    private List<NewsModel.ResultDataBean> datas;
    private LayoutInflater li = null;
    private Context mContext;
    private int mType = 5;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public RecordAdapter(Context context, List<NewsModel.ResultDataBean> data) {
        this.datas = data;
        mContext = context;
        li = LayoutInflater.from(context);
    }
    public RecordAdapter(Context context, List<NewsModel.ResultDataBean> data,int type) {
        this.datas = data;
        mContext = context;
        mType = type;
        li = LayoutInflater.from(context);
    }
    public void setData(List<NewsModel.ResultDataBean> datas) {
        this.datas = datas;
    }

    public List<NewsModel.ResultDataBean> getData() {
        return this.datas;
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
        convertView = li.inflate(R.layout.fragment_record_item, null);
        viewHolder = new ViewHolder();
        viewHolder.title = (TextView) convertView.findViewById(R.id.record_title);
        viewHolder.content = (TextView) convertView.findViewById(R.id.record_content);
        viewHolder.source = (TextView) convertView.findViewById(R.id.record_src);
        viewHolder.dataTime = (TextView) convertView.findViewById(R.id.record_time);
        ImageView newImage = (ImageView) convertView.findViewById(R.id.icon_record_new);
        final ImageView fmImage = (ImageView) convertView.findViewById(R.id.fengm_image);
        viewHolder.title.setText(datas.get(position).getTitle());
        viewHolder.content.setText(datas.get(position).getSummary());
        if(datas.get(position).getNoticeType()==6) {
          //  viewHolder.source.setText(datas.get(position).getReadClassName());
        }
        viewHolder.dataTime.setText(datas.get(position).getLastUpdateTime().replace("T", " "));
        if (datas.get(position).getFengmian() != null) {
            if (!datas.get(position).getFengmian().contains("mp4")) {
                ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + datas.get(position).getFengmian(), fmImage, KingActivity.getOptions());
            } else {
                new Thread() {
                    @Override
                    public void run() {

                        final Bitmap bitmap = createVideoThumbnail(datas.get(position).getFengmian(), 100, 100);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                fmImage.setImageBitmap(bitmap);
                            }
                        });
                    }
                }.start();
            }
        } else {
            fmImage.setVisibility(View.GONE);
        }
        if (position < 3) {
            Date d = datas.get(position).getLastUpdateTimeDate();
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(d);

            if(calendar.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)){
                newImage.setVisibility(View.VISIBLE);
            }
        }
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        public TextView title;
        public TextView content;
        public TextView source;

        public TextView dataTime;
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private Bitmap createVideoThumbnail(String url, int width, int height) {

        ACache mACache = ACache.get(mContext);
        Bitmap bitmap = mACache.getAsBitmap(url);

        if (bitmap == null) {
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
        } else {
            return bitmap;
        }

    }
}
