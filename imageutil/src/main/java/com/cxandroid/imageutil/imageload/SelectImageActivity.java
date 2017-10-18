package com.cxandroid.imageutil.imageload;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cxandroid.imageutil.R;
import com.cxandroid.imageutil.imageload.bean.FolderBean;
import com.cxandroid.imageutil.imageloader.util.FileUtil;
import com.cxandroid.imageutil.imageloader.util.ImageDown;
import com.cxandroid.imageutil.title.KFTitleView;
import com.cxandroid.imageutil.title.OnTitleOncilkEvent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SelectImageActivity extends Activity {

    private KFTitleView mTitleView;
    private GridView mGridView;
    private List<String> mImgs;
    private ImageAdapter mImageAdapter;


    private RelativeLayout mButtomly;
    private TextView mDirName;
    private TextView mDirCount;

    private Intent intent;
    private File mCurrenDir;
    private int mMaxCount;
    public int maxCount;
    private ProgressDialog mProgressDialog;

    private LinearLayout mImagesLin, mSmtLin;
    private List<FolderBean> mFolderBeans = new ArrayList<FolderBean>();

    private static final int DATA_LOADED = 0x110;

    private ListImageDirPopupWindow mDirPopupWindow;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == DATA_LOADED) {
                //mProgressDialog.dismiss();
                //绑定数据到vie中
                data2View();

                initDirPopuWindow();
            }
        }

        ;
    };

    private int isPhoto = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageload_activity_main);
        mTitleView = (KFTitleView) findViewById(R.id.title);
        intent = getIntent();
        isPhoto = intent.getIntExtra("isPhoto",0);
        findViewById(R.id.yulan_tv).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ImageAdapter.mImages.size() > 0) {
                    Intent intent = new Intent(SelectImageActivity.this, PhotosLookActivity.class);
                    startActivity(intent);
                }
            }
        });
        if (!TextUtils.isEmpty(intent.getStringExtra("title"))) {
            setTitle(intent.getStringExtra("title"));
        } else {
            setTitle("选择图片");
        }


        mTitleView.setRight("选择相册");
        mTitleView.setTitleOncilk(new OnTitleOncilkEvent() {

            @Override
            public void onRight() {
                // TODO Auto-generated method stub
                mDirPopupWindow.showAsDropDown(mButtomly, 0, 0);
                lightoff();
            }

            @Override
            public void onLeft() {
                // TODO Auto-generated method stub
                onBackPressed();
            }
        });
        maxCount = intent.getIntExtra("maxCount", 5);
        initView();
        initDatas();
        initEvent();
        if(isPhoto == 1){
            carem();
        }
    }

    protected void initDirPopuWindow() {


        mDirPopupWindow.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {

                listhOn();
            }
        });

    }

    /**
     * 内容区域变亮
     */
    protected void listhOn() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1.0f;
        getWindow().setAttributes(lp);

    }

    /***
     * 内容区域变暗
     */
    protected void lightoff() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().setAttributes(lp);

    }

    protected void data2View() {
        String path = "";
        if (mCurrenDir == null) {
            //Toast.makeText(this, "未扫描到任何图片", Toast.LENGTH_SHORT).show();
            //return;
        } else {
            path = mCurrenDir.getAbsolutePath();
            mImgs = Arrays.asList(mCurrenDir.list());
            mDirName.setText(mCurrenDir.getName());
        }
        mImageAdapter = new ImageAdapter(this, mImgs, path, mDirCount);
        mImageAdapter.maxCount = maxCount;

        mImageAdapter.mImages = new Gson().fromJson(intent.getStringExtra("images"), new TypeToken<ArrayList<String>>() {
        }.getType());
        mGridView.setAdapter(mImageAdapter);
        reseImagesLin();
        mDirCount.setText("预览(" + mImageAdapter.mImages.size() + ")");


    }

    private void initEvent() {

/*		mButtomly.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				mDirPopupWindow.showAsDropDown(mButtomly, 0 , 0);
				lightoff();
			}
		});	*/
        mDirPopupWindow.setOnDirSelectedListener(new ListImageDirPopupWindow.OnDirSelectedListener() {

            @Override
            public void onSelected(FolderBean folderBean) {
                mCurrenDir = new File(folderBean.getDir());

                mImgs = Arrays.asList(mCurrenDir.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String filename) {

                        // TODO Auto-generated method stub
                        if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") ||
                                filename.endsWith(".png") || filename.endsWith(".gif"))
                            return true;
                        return false;


                    }
                }));

                mImageAdapter = new ImageAdapter(SelectImageActivity.this, mImgs,
                        mCurrenDir.getAbsolutePath(), mDirCount);
                mImageAdapter.maxCount = maxCount;
                mGridView.setAdapter(mImageAdapter);
                reseImagesLin();
                mDirCount.setText("预览(" + mImageAdapter.mImages.size() + ")");

	/*			mDirName.setText(folderBean.getName());*/

                mDirPopupWindow.dismiss();

            }
        });
    }

    public void reseImagesLin() {
        mImagesLin.removeAllViews();
        for (String path : mImageAdapter.mImages) {
            addImage(path);
        }
    }

    /**
     * 利用countprovider扫描手机中的所有图片
     */
    private void initDatas() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "当前存储卡不可用", Toast.LENGTH_SHORT).show();
            return;
        }
        //	mProgressDialog = ProgressDialog.show(this, null, "正在加载");

        new Thread() {
            public void run() {
                Uri mImgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver cr = SelectImageActivity.this.getContentResolver();

                Cursor cursor = cr.query(mImgUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "= ? or " + MediaStore.Images.Media.MIME_TYPE + "= ? or  " + MediaStore.Images.Media.MIME_TYPE + "= ?  ",
                        new String[]{"image/jpeg", "image/png", "image/gif"}, MediaStore.Images.Media.DATE_MODIFIED);
                Set<String> mDirPaths = new HashSet<String>();
                while (cursor.moveToNext()) {
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null)
                        continue;
                    String dirPath = parentFile.getAbsolutePath();


                    FolderBean folderBean = null;

                    if (mDirPaths.contains(dirPath)) {

                        continue;
                    } else {
                        mDirPaths.add(dirPath);
                        folderBean = new FolderBean();
                        folderBean.setDir(dirPath);
                        folderBean.setFirstImagPath(path);
                    }

                    if (parentFile.list() == null)
                        continue;

                    int picSize = parentFile.list(new FilenameFilter() {

                        @Override
                        public boolean accept(File dir, String filename) {
                            // TODO Auto-generated method stub
                            if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") ||
                                    filename.endsWith(".png") || filename.endsWith(".gif"))
                                return true;
                            return false;


                        }
                    }).length;

                    folderBean.setCount(picSize);

                    mFolderBeans.add(folderBean);

                    if (picSize > mMaxCount) {
                        mMaxCount = picSize;
                        mCurrenDir = parentFile;
                    }
                }
                cursor.close();
                //通知handler扫描图片完成
                mHandler.sendEmptyMessage(DATA_LOADED);

            }

            ;
        }.start();

    }

    public void initView() {
        mGridView = (GridView) findViewById(R.id.id_picture_select_gridview);
        mButtomly = (RelativeLayout) findViewById(R.id.id_bottom_ly);
        mDirName = (TextView) findViewById(R.id.id_dir_name);
        mDirCount = (TextView) findViewById(R.id.yulan_tv);
        mImagesLin = (LinearLayout) findViewById(R.id.images_dis_lin);
        mSmtLin = (LinearLayout) findViewById(R.id.smt_lin_imags);
        mDirPopupWindow = new ListImageDirPopupWindow(this, mFolderBeans);
        mSmtLin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                intent.putExtra("imageJson", new Gson().toJson(mImageAdapter.mImages));
                intent.putStringArrayListExtra("images", mImageAdapter.mImages);
                setResult(196, intent);
                onBackPressed();
                mImageAdapter.mImages.clear();
            }
        });
        mGridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
            }
        });
    }

    File fos = null;

    public void carem() {
        if (FileUtil.hasSdcard()) {
            try {
                fos = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "_" + System.currentTimeMillis() + "celove.jpg");
                Uri u = Uri.fromFile(fos);
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                i.putExtra(MediaStore.EXTRA_OUTPUT, u);
                startActivityForResult(i, 159);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(SelectImageActivity.this, "拍照失败，请从系统相机拍照", Toast.LENGTH_LONG).show();
                finish();
            }

        } else {
            Toast.makeText(SelectImageActivity.this, "没有找到sd卡", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode == 159) {
            if (resultCode == Activity.RESULT_OK) {
                mImageAdapter.mImages.add(fos.getAbsolutePath());
                intent.putExtra("imageJson", new Gson().toJson(mImageAdapter.mImages));
                intent.putStringArrayListExtra("images", mImageAdapter.mImages);
                setResult(196, intent);
                onBackPressed();
                mImageAdapter.mImages.clear();
            }else{
                finish();
            }
        }
    }

    public void addImage(String Path) {
        View convertView = LayoutInflater.from(SelectImageActivity.this).inflate(R.layout.opima_img_view, null);
        ImageView image = (ImageView) convertView.findViewById(R.id.img_hsv_img);
        ImageDown.getInstance(3, ImageDown.Type.LIFO).loadImage(Path, image);
        image.setTag(Path);
        mImagesLin.addView(convertView);
    }
}
