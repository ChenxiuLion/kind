package com.youqd.kind.ckind.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.bigkoo.pickerview.TimePickerView;
import com.cxandroid.imageutil.imageload.SelectImageActivity;
import com.example.bajian.sheetdialogue.SheetDialog;
import com.kind.chx.gardener.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.ImageBean;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.bean.PingResult;
import com.youqd.kind.ckind.bean.UpdataBoby;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UpdataTool;
import com.youqd.kind.ckind.util.UserManage;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BBInfoActivity extends KingActivity {
    private ArrayList<ImageBean> mPaths = new ArrayList<>();

    private ArrayList<String> mImages = new ArrayList<>();

    private String mUserImageStr;

    private ImageView baby_info_image_view;


    private TextView user_phone;

    private TextView boby_info_name;

    private TextView school_name;


    private TextView class_name;


    private TextView zhiwei;


    private LoginBean mUser;
    @Override
    protected int initLayout() {
        return R.layout.activity_bbinfo;
    }

    @Override
    protected void initViews() {
        mUser = getUser();
        baby_info_image_view = (ImageView) findViewById(R.id.baby_info_image_view);
        user_phone = (TextView) findViewById(R.id.user_phone);
        boby_info_name = (TextView) findViewById(R.id.boby_info_name);
        school_name = (TextView) findViewById(R.id.school_name);
        class_name = (TextView) findViewById(R.id.class_name);
        zhiwei = (TextView) findViewById(R.id.zhiwei);

        user_phone.setText(getUser().getUserAccount());
        boby_info_name.setText(getUser().getUserName());
        school_name.setText(UserManage.getInstance().getKind().getResultData().getName());
        class_name.setText(getUser().getClassName());
        zhiwei.setText(getUser().getPostTitle());

        ((TextView) findViewById(R.id.desc)).setText(getUser().getIntroduce());
        findViewById(R.id.desc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,UpdataUserActivity.class);
                startActivityForResult(intent,170);
            }
        });
        findViewById(R.id.baby_info_image_lin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SheetDialog(mContext)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItems(new String[]{"拍照","相册"}
                                , SheetDialog.SheetItemColor.Blue, new SheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        if(which==1){
                                            Intent intent = new Intent(mContext, SelectImageActivity.class);
                                            intent.putExtra("images", "[]");
                                            intent.putExtra("maxCount", 1);
                                            intent.putExtra("isPhoto", 1);
                                            startActivityForResult(intent, 189);
                                        }else if(which==2){
                                            Intent intent = new Intent(mContext, SelectImageActivity.class);
                                            intent.putExtra("images", "[]");
                                            intent.putExtra("maxCount", 1);
                                            startActivityForResult(intent, 189);
                                        }
                                        System.out.println("which" + which);
                                    }
                                }).show();
            }
        });
        ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + getUser().getPhoto(),baby_info_image_view);

    }
    private static final int REQUEST_CROP_PHOTO = 102;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 189) {
                ArrayList<String> images = data.getStringArrayListExtra("images");
                for (final String path : images) {

                    Intent intent = new Intent();
                    intent.setClass(this, ClipActivity.class);
                    intent.setData(Uri.fromFile(new File(path)));
                    startActivityForResult(intent, REQUEST_CROP_PHOTO);
                }
            }else if(requestCode == 170){

                ((TextView) findViewById(R.id.desc)).setText(getUser().getIntroduce());

            }else if(requestCode == REQUEST_CROP_PHOTO){
                if(resultCode ==RESULT_OK) {
                    final Uri uri = data.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);

                    ArrayList<ImageBean> paths = new ArrayList<>();
                    ImageBean bean = new ImageBean();
                    bean.setPath(cropImagePath);
                    paths.add(bean);

                    UpdataTool.getTool(paths).statrUpdata(new UpdataTool.OnUpdata() {
                        @Override
                        public void onIndex(int index) {

                        }

                        @Override
                        public void onSucceed(ArrayList<String> paths) {
                            mUser.setPhoto(paths.get(0));
                            HttpTool.getInstance().upUser(mUser,
                                    new KingCallback<PingResult>(PingResult.class) {
                                        @Override
                                        public void onSucceed(PingResult data) {
                                            setUser(mUser);
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
                    ImageLoader.getInstance().displayImage("file://" + cropImagePath,baby_info_image_view);
                }

            }
        }
    }
    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * Try to return the absolute file path from the given Uri  兼容了file:///开头的 和 content://开头的情况
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
