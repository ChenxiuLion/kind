package com.youqd.kind.skind.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.muzhi.camerasdk.model.CameraSdkParameterInfo;
import com.youqd.kind.skind.R;
import com.youqd.kind.skind.adapter.ImageGridAdapter;
import com.youqd.kind.skind.base.BaseActivity;
import com.youqd.kind.skind.bean.ImageInfo;
import com.youqd.kind.skind.evaluation.EvaluationActivity;

import java.util.ArrayList;

public class GrowAddActivity extends BaseActivity {

    public static String TITLE = "TITLE";
    private GridView noScrollgridview;
    private ArrayList<ImageInfo> pic_list;
    private ImageGridAdapter mImageGridAdapter;
    private CameraSdkParameterInfo mCameraSdkParameterInfo=new CameraSdkParameterInfo();
    private ImageView rightImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        ((TextView)findViewById(R.id.top_title)).setText("添加成长动态");
        findViewById(R.id.rightId).setVisibility(View.VISIBLE);

        rightImg = (ImageView)findViewById(R.id.rightId);
        rightImg.setImageResource(R.drawable.add_gray_selector);
        rightImg.setOnClickListener(this);

        noScrollgridview= (GridView) findViewById(R.id.noScrollgridview);
        mImageGridAdapter = new ImageGridAdapter(this,mCameraSdkParameterInfo.getMax_image());
        noScrollgridview.setAdapter(mImageGridAdapter);

        Bundle b = getIntent().getExtras();
        getBundle(b);
        initEvent();
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rightId:
                startActivity(EvaluationActivity.class);
                break;
            default:
                break;
        }
    }

    private void getBundle(Bundle bundle){
        pic_list=new ArrayList<ImageInfo>();
        if(bundle!=null){
            mCameraSdkParameterInfo=(CameraSdkParameterInfo)bundle.getSerializable(CameraSdkParameterInfo.EXTRA_PARAMETER);
            ArrayList<String> list= mCameraSdkParameterInfo.getImage_list();
            if(list!=null){
                for(int i=0;i<list.size();i++){
                    ImageInfo img = new ImageInfo();
                    img.setSource_image(list.get(i));
                    pic_list.add(img);
                }

            }
        }

        if(pic_list.size()<mCameraSdkParameterInfo.getMax_image()){
            ImageInfo item=new ImageInfo();
            item.setAddButton(true);
            pic_list.add(item);
        }
        mImageGridAdapter.setList(pic_list);

    }

    private void initEvent(){
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
                //ImageInfo info=(ImageInfo)ImageGridAdapter.getItem(position);
                ImageInfo info=(ImageInfo)arg0.getAdapter().getItem(position);
                if(info.isAddButton()){

                    ArrayList<String> list=new ArrayList<String>();
                    for(ImageInfo pic:pic_list){
                        if(!pic.isAddButton()){
                            list.add(pic.getSource_image());
                        }
                    }
                    openCameraSDKPhotoPick(GrowAddActivity.this, list);
                }
                else{
                    openCameraSDKImagePreview(GrowAddActivity.this,pic_list.get(position).getSource_image(),position);
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CameraSdkParameterInfo.TAKE_PICTURE_FROM_GALLERY:
                if(data!=null){
                    getBundle(data.getExtras());
                }
                break;
            case CameraSdkParameterInfo.TAKE_PICTURE_PREVIEW:
                if(data!=null){
                    int position = data.getIntExtra("position", -1);
                    if(position>=0){
                        mImageGridAdapter.deleteItem(position);
                        mCameraSdkParameterInfo.getImage_list().remove(position);
                    }
                }
                break;
        }

    }


    //图片预览
    public void openCameraSDKImagePreview(Activity activity,String path,int position) {
		/*Intent intent = new Intent();
		intent.setClassName(activity.getApplication(), "com.muzhi.camerasdk.PreviewActivity");
		ArrayList<String> list=new ArrayList<String>();
		list.add(path);
		mCameraSdkParameterInfo.setImage_list(list);
		mCameraSdkParameterInfo.setPosition(position);*/

        mCameraSdkParameterInfo.setPosition(position);
        Intent intent = new Intent();
        intent.setClassName(activity.getApplication(), "com.muzhi.camerasdk.PreviewActivity");
        Bundle b=new Bundle();
        b.putSerializable(CameraSdkParameterInfo.EXTRA_PARAMETER, mCameraSdkParameterInfo);
        intent.putExtras(b);
        startActivityForResult(intent, CameraSdkParameterInfo.TAKE_PICTURE_PREVIEW);
    }

    //本地相册选择
    public void openCameraSDKPhotoPick(Activity activity,ArrayList<String> list) {
        Intent intent = new Intent();
        intent.setClassName(activity.getApplication(), "com.muzhi.camerasdk.PhotoPickActivity");
        Bundle b=new Bundle();

        b.putSerializable(CameraSdkParameterInfo.EXTRA_PARAMETER, mCameraSdkParameterInfo);
        intent.putExtras(b);
        startActivityForResult(intent, CameraSdkParameterInfo.TAKE_PICTURE_FROM_GALLERY);

    }
}
