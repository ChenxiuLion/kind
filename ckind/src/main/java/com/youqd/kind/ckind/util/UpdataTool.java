package com.youqd.kind.ckind.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.youqd.kind.ckind.bean.ImageBean;
import com.youqd.kind.ckind.bean.UpdataResult;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 上传文件工具类
 * Created by Chenxiu on 2016/7/30.
 */
public class UpdataTool {


    private ArrayList<ImageBean> mPaths = new ArrayList<>();

    private ArrayList<String> mResult = new ArrayList<>();

    private int mUpIndex = 0;

    private OnUpdata mOnUpdata;

    private Handler mHandler = new Handler(Looper.getMainLooper());


    public static UpdataTool getTool(ArrayList<ImageBean> paths){
            return new UpdataTool(paths);
    }

    public UpdataTool(ArrayList<ImageBean> paths){
        this.mPaths.addAll(paths);
    }



    public void statrUpdata(OnUpdata onUpdata){
        this.mOnUpdata = onUpdata;
        new Thread(){
            @Override
            public void run() {
                super.run();

                updata();
            }
        }.start();
    }


    public void updata(){
        if(mUpIndex < mPaths.size()){
            UpdataResult result = null;
            if(mPaths.get(mUpIndex).getPath().contains("mp4") || mPaths.get(mUpIndex).getPath().contains("3gp")) {
                result = new Gson().fromJson(Upload.uploadFile(mPaths.get(mUpIndex).getPath()), UpdataResult.class);
            }else{
                String pas = getMinImageFile(mPaths.get(mUpIndex).getPath());
                result = new Gson().fromJson(Upload.uploadFile(pas), UpdataResult.class);

            }

            if(result.getResultCode()==1) {
                mResult.add(result.getResultData().getFilePath());
            }else{
                mUpIndex =1000;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mOnUpdata.onErro();
                    }
                });

                return;
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mOnUpdata.onIndex(mUpIndex);
                }
            });
            mUpIndex++;
            updata();
        }else{
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mOnUpdata.onSucceed(mResult);
                }
            });
        }
    }


    public interface OnUpdata{
        void onIndex(int index);
        void onSucceed(ArrayList<String> paths);
        void onErro();
    }
    /**
     * 图片压缩
     * @param phth
     * @return 图片保存地址
     */
    public static String getMinImageFile(String phth)
    {
        //获取bitmap
        Bitmap bit = getimage(phth);


        return saveBitmap(bit);

    }
    @SuppressLint("SdCardPath")
    public static String saveBitmap(Bitmap bit) {
        // Bitmap bita =compressImage(bit);
        addImageFiles();
        String fileName = System.currentTimeMillis()+"";
        File f = new File("/sdcard/CosFund/Image", fileName+".jpg");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bit.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            return f.getAbsolutePath();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";

    }

    public static void addImageFiles()
    {
        File f = new File("/sdcard/CosFund/Image");
        if(!f.isDirectory())
        {
            f.mkdirs();
        }
    }

    private static Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 640f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }
    private static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>200) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }
}
