package com.youqd.kind.ckind.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cosfund.library.dialog.HintDialog;
import com.cosfund.library.util.NetWorkUtils;
import com.cosfund.library.widget.BadgeView;
import com.cxandroid.imageutil.imageload.SelectImageActivity;
import com.example.bajian.sheetdialogue.SheetDialog;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.AllBoby;
import com.youqd.kind.ckind.bean.GetUserName;
import com.youqd.kind.ckind.bean.ImageBean;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.bean.MessgerList;
import com.youqd.kind.ckind.bean.PingResult;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.fragment.DynamicFragment;
import com.youqd.kind.ckind.fragment.MainFragment;
import com.youqd.kind.ckind.fragment.NesMessgerFragment;
import com.youqd.kind.ckind.fragment.SeeMainFragment;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UpdataTool;
import com.youqd.kind.ckind.util.UserManage;
import com.youqd.kind.ckind.widget.DragLayout;

import org.json.JSONObject;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends KingActivity {

    private int mIndex = 0;
    private DragLayout dl;
    private Class[] mClass = {MainFragment.class, SeeMainFragment.class, DynamicFragment.class, NesMessgerFragment.class};

    private LoginBean mUser;
    public static MainActivity mActivity;

    public void goPostion(int postion) {
        if (postion != mIndex) {
            initTab();
            switch (postion){
                case 0:
                    ((ImageView)findViewById(R.id.main_btm_intro)).setImageResource(R.drawable.icon_menu_home_sel);
                    ((TextView)findViewById(R.id.main_btm_intro1)).setTextColor(getResources().getColor(R.color.main_green_bg));
                    break;
                case 3:
                    ((ImageView)findViewById(R.id.main_btm_msg)).setImageResource(R.drawable.icon_menu_msg_sel);
                    ((TextView)findViewById(R.id.main_btm_intro2)).setTextColor(getResources().getColor(R.color.main_green_bg));
                    break;
                case 1:
                    ((ImageView)findViewById(R.id.main_btm_atv)).setImageResource(R.drawable.icon_menu_grow_sel);
                    ((TextView)findViewById(R.id.main_btm_intro3)).setTextColor(getResources().getColor(R.color.main_green_bg));
                    break;
                case 2:
                    ((TextView)findViewById(R.id.main_btm_intro4)).setTextColor(getResources().getColor(R.color.main_green_bg));
                    break;
            }
            initFragment(postion);
            mIndex = postion;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        String infoType ="";
        String id ="";
        JSONObject json = null;
        try {
            json = new JSONObject(intent.getStringExtra(JPushInterface.EXTRA_EXTRA));
            infoType =  json.getString("InfoType");
            id = json.getString("ID");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Bundle bundle = new Bundle();
        if("1".equals(infoType)){
            bundle.putString("id",id);
            startActivity(NewDetailActivity.class,bundle);
        }
        if("2".equals(infoType)){  //成长动态
            goPostion(2);
        }
        if("3".equals(infoType)){ // 家庭作业
            startActivity(JobActivity.class);
        }
        if("4".equals(infoType)){ // 宝宝考勤
            startActivity(WorkActivity.class);
        }
        if("5".equals(infoType)){
            bundle.putString("id",id);
            startActivity(NewDetailActivity.class,bundle);
        }
        if("6".equals(infoType)){
            bundle.putString("id",id);
            startActivity(NewDetailActivity.class,bundle);
        }
        if("7".equals(infoType)){
            bundle.putString("id",id);
            startActivity(NewDetailActivity.class,bundle);
        }
        if("8".equals(infoType)){
            startActivity(MessgerListActivity.class);
        }
    }

    public void initTab(){
        ((ImageView)findViewById(R.id.main_btm_intro)).setImageResource(R.drawable.main_tab_home_selector);
        ((ImageView)findViewById(R.id.main_btm_msg)).setImageResource(R.drawable.main_tab_msg_selector);
        ((ImageView)findViewById(R.id.main_btm_atv)).setImageResource(R.drawable.main_tab_grow_selector);
        ((TextView)findViewById(R.id.main_btm_intro1)).setTextColor(Color.parseColor("#757575"));
        ((TextView)findViewById(R.id.main_btm_intro2)).setTextColor(Color.parseColor("#757575"));
        ((TextView)findViewById(R.id.main_btm_intro3)).setTextColor(Color.parseColor("#757575"));
        ((TextView)findViewById(R.id.main_btm_intro4)).setTextColor(Color.parseColor("#757575"));
    }
    public void initFragment(int postion) {
        FragmentManager manager = getSupportFragmentManager();
        try {
            Fragment fragment = (Fragment) Class.forName(mClass[postion].getName()).newInstance();
            manager.beginTransaction().replace(R.id.frame_content, fragment).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private BadgeView mMessgerBadge;
    public void getMessgerData(){
        HttpTool.getInstance().getWDMessger(getUser().getKindergartenID()+"",getUser().getID()+"",0,
                new KingCallback<MessgerList>(MessgerList.class) {
                    @Override
                    public void onSucceed(MessgerList data) {
                        if(data!=null){
                            if(data.getResultData().size()>0){
                                mMessgerBadge = new BadgeView(mContext);
                                mMessgerBadge.setBadgeCount(data.getResultData().size());
                                mMessgerBadge.setBadgeMargin(20,0,10,0);
                                mMessgerBadge.setTargetView(findViewById(R.id.main_btn_item_2));
                            }else{
                                if(mMessgerBadge!=null){
                                    mMessgerBadge.setVisibility(View.GONE);
                                }
                            }
                        }
                    }

                    @Override
                    public void onErro() {

                    }
                });
    }


    @Override
    protected int initLayout() {
        return R.layout.activity_m;
    }

    @Override
    protected void initViews() {
        mActivity = this;
        mUser = getUser();
        dl = (DragLayout) findViewById(R.id.dl);
        dl.setDragListener(new DragLayout.DragListener() {
            //界面打开的时候
            @Override
            public void onOpen() {
            }

            //界面关闭的时候
            @Override
            public void onClose() {
            }

            //界面滑动的时候
            @Override
            public void onDrag(float percent) {
            }
        });
        initFragment(0);
        mUser.setIMEI(JPushInterface.getRegistrationID(this));
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

        findViewById(R.id.main_btm_intro).setFocusable(true);
        findViewById(R.id.left_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ((TextView) findViewById(R.id.baby_left_name)).setText(getUser().getUserName());

        findViewById(R.id.baby_left_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initEvents() {

    }


    public void initBoby(String path) {

        HttpTool.getInstance().getUserName(getUser().getID() + "", new KingCallback<GetUserName>(GetUserName.class) {
            @Override
            public void onSucceed(GetUserName data) {
                LoginBean loginBean = getUser();
                mUser = loginBean;
                ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + getUser().getPhoto(),
                        ((ImageView) findViewById(R.id.left_image)), getOptions());
                setUser(loginBean);

                HttpTool.getInstance().getBobyAll(mUser.getClassID() + "", new KingCallback<AllBoby>(AllBoby.class) {
                    @Override
                    public void onSucceed(AllBoby data) {
                        if(data!=null){
                            UserManage.getInstance().setAllBoby(data);
                        }
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

    }

    @Override
    protected void onResume() {
        getMessgerData();
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.main_course:
                startActivity(CourseActivity.class);
                break;
            case R.id.main_work:
                startActivity(WorkActivity.class);
                break;
            case R.id.main_schoolnew:
                bundle.putString("title", "校园新闻");
                startActivity(SchoolNewActivity.class, bundle);
                break;
            case R.id.main_classnew:
                bundle.putInt("type", 6);
                bundle.putString("title", "班级通知");
                startActivity(SchoolNewActivity.class, bundle);
                break;
            case R.id.main_food:
                startActivity(FoodActivity.class);
                break;
            case R.id.left_add_family:
                startActivity(AddFamilyActivity.class);
                break;
            case R.id.main_job:
                startActivity(JobActivity.class);
                break;
            case R.id.main_chat:
                startActivity(MessgerListActivity.class);
                break;
            case R.id.main_baby:
                startActivity(RecordActivity.class);
                break;
            case R.id.main_btm_intro:
            case R.id.main_btn_item_1:
                goPostion(0);
                break;
            case R.id.main_btm_atv:
            case R.id.main_btn_item_3:
                //goPostion(2);
                bundle.putInt("type", 5);
                bundle.putString("title", "园务通知");
                startActivity(SchoolNewActivity.class, bundle);

                break;
            case R.id.bb_cj:
                startActivity(DynamicActivity.class);

                break;
            case R.id.main_btm_my:
            case R.id.main_btn_item_4:
                dl.open();
                break;
            case R.id.main_btm_see:
                //startActivity(SeeActivity.class);
                goPostion(2);
                break;
            case R.id.main_left_setting:
                startActivity(MyActivity.class);
                break;
            case R.id.main_left_intro:
                startActivity(IntroActivity.class);
                break;
            case R.id.main_btm_msg:
            case R.id.main_btn_item_2:
                goPostion(3);
                break;
            case R.id.main_left_bbInfo:
                startActivity(BBInfoActivity.class);
                break;
            case R.id.main_left_account:


                startActivity(Contact2Activity.class);
                break;
            case R.id.main_left_contact:
                startActivity(ContactActivity.class);
                break;
            default:
                break;
        }
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

                ((TextView) findViewById(R.id.baby_left_name)).setText(getUser().getUserName());

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
                    ImageLoader.getInstance().displayImage("file://" + cropImagePath, ((ImageView) findViewById(R.id.left_image)));
                }

            }
        }
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
