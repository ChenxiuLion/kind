package com.youqd.kind.ckind.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cosfund.library.dialog.HintDialog;
import com.cosfund.library.util.LogUtils;
import com.cosfund.library.util.NetWorkUtils;
import com.cosfund.library.widget.BadgeView;
import com.cxandroid.imageutil.imageload.SelectImageActivity;
import com.dou361.update.UpdateHelper;
import com.dou361.update.type.UpdateType;
import com.example.bajian.sheetdialogue.SheetDialog;
import com.google.gson.Gson;
import com.nineoldandroids.view.ViewHelper;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.BaseActivity;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.GetUser;
import com.youqd.kind.ckind.bean.GetUserName;
import com.youqd.kind.ckind.bean.ImageBean;
import com.youqd.kind.ckind.bean.KindBean;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.bean.MessgerList;
import com.youqd.kind.ckind.bean.PingResult;
import com.youqd.kind.ckind.bean.UpdateBean;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.fragment.DynamicFragment;
import com.youqd.kind.ckind.fragment.MainFragment;
import com.youqd.kind.ckind.fragment.NesMessgerFragment;
import com.youqd.kind.ckind.fragment.SeeFragment;
import com.youqd.kind.ckind.fragment.SeeMainFragment;
import com.youqd.kind.ckind.fragment.StreamViewerFragment;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.MyPopupWindow;
import com.youqd.kind.ckind.util.UpdataTool;
import com.youqd.kind.ckind.util.UserManage;
import com.youqd.kind.ckind.video.NewRecordVideoActivity;
import com.youqd.kind.ckind.widget.DragLayout;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import chenxiu.zz.com.camer.CameraAty;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends KingActivity {

    private int mIndex = 0;
    private DragLayout dl;
    private Class[] mClass = {MainFragment.class, SeeMainFragment.class, DynamicFragment.class, NesMessgerFragment.class};

    private LoginBean mUser;
    public static MainActivity mActivity;

    private BadgeView mMessgerBadge;

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
                case 2:
                    ((ImageView)findViewById(R.id.main_btm_atv)).setImageResource(R.drawable.icon_menu_grow_sel);
                    ((TextView)findViewById(R.id.main_btm_intro3)).setTextColor(getResources().getColor(R.color.main_green_bg));
                    break;
                case 1:
                    ((TextView)findViewById(R.id.main_btm_intro4)).setTextColor(getResources().getColor(R.color.main_green_bg));
                    break;
            }
            initFragment(postion);
            mIndex = postion;
        }
    }

    public void getMessgerData(){
        if(getBaby()!=null) {
            HttpTool.getInstance().getWDMessger(getBaby().getKindergartenID() + "", getUser().getID() + "", 0,
                    new KingCallback<MessgerList>(MessgerList.class) {
                        @Override
                        public void onSucceed(MessgerList data) {
                            if (data != null) {
                                if (data.getResultData().size() > 0) {
                                    mMessgerBadge = new BadgeView(mContext);
                                    mMessgerBadge.setBadgeCount(data.getResultData().size());
                                    mMessgerBadge.setBadgeMargin(0, 0, 10, 0);
                                    mMessgerBadge.setTargetView(findViewById(R.id.main_btn_item_2));
                                } else {
                                    if (mMessgerBadge != null) {
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


    private PowerManager.WakeLock mWakeLock;

    @Override
    protected int initLayout() {
        return R.layout.activity_m;
    }

    @Override
    protected void initViews() {
        mActivity = this;
        mUser = getUser();


        HttpTool.getInstance().doUpdata(new KingCallback<UpdateBean>(UpdateBean.class) {
            @Override
            public void onSucceed(UpdateBean data) {
                UpdateHelper.getInstance()
                        .setRequestResultData(new Gson().toJson(data))
                        .checkNoUrl(MainActivity.this);
            }

            @Override
            public void onErro() {

            }
        });
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
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
         mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
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

        LogUtils.e("id="+ JPushInterface.getRegistrationID(this));

        findViewById(R.id.main_btm_intro).setFocusable(true);
        findViewById(R.id.left_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SheetDialog(mContext)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItems(new String[]{"拍照","从手机相册选择"}
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

        ((TextView) findViewById(R.id.baby_left_name)).setText(getUser().getUserName());

        findViewById(R.id.baby_left_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(mContext,UpdataUserActivity.class);
              //  startActivityForResult(intent,170);
            }
        });
    }

    @Override
    protected void initEvents() {

    }
    // 退出程序监听
    private long mExitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                showShortToast("再按一次退出\"家园宝宝\"");
                mExitTime = System.currentTimeMillis();
                return true;
            }
        }
        // backCount = 0;
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    public void acqueire(){
        mWakeLock.acquire();
    }
    public void onrelease(){
        mWakeLock.release();
    }
    @Override
    protected void onResume() {
        getMessgerData();
     //
        super.onResume();
    }

    public void initBoby() {
        HttpTool.getInstance().getUserName(getUser().getID() + "",getBaby().getID()+"", new KingCallback<GetUserName>(GetUserName.class) {
            @Override
            public void onSucceed(GetUserName data) {
                LoginBean loginBean = getUser();
                loginBean.setRelation(data.getResultData().getRelation());
                mUser = loginBean;
                ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + getUser().getPhoto(),
                        ((ImageView) findViewById(R.id.left_image)), getOptions());
                setUser(loginBean);
                UserManage.getInstance().addPatriarch(getUser().getID() + "", getUser().getRelation());

            }

            @Override
            public void onErro() {

            }
        });

    }    HintDialog dialog;


    public void seeEnd(){
        dialog = new HintDialog(mContext);
        dialog.setMessage("看宝宝账户已到期，给您造成不变敬请谅解，如需继续使用，请联系宝宝所在幼儿园进行续费");
        dialog.setPositiveButton("联系校园", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = UserManage.getInstance().getKind().getResultData().getTelephone();
                if (TextUtils.isEmpty(phone)) {

                } else {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setTitle("");
        dialog.show();
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
                startActivity(DynamicActivity.class);
                break;
            case R.id.main_btm_intro:
            case R.id.main_btn_item_1:
                goPostion(0);
                break;
            case R.id.main_btm_atv:
            case R.id.main_btn_item_3:
                goPostion(2);
                break;
            case R.id.bb_cj:
                startActivity(RecordActivity.class);
                break;
            case R.id.main_btm_my:
            case R.id.main_btn_item_4:
                dl.open();
                break;
            case R.id.main_btm_see:
                //startActivity(SeeActivity.class);
                if(NetWorkUtils.isConnected(this)) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        long endTime = format.parse(getUser().getVideoEndTime().replace("T", " ")).getTime();
                        if (endTime < System.currentTimeMillis()) {
                        } else {
                            mShare.edit().putString("1" + format.format(new Date()).split(" ")[0], "1").commit();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    String isDay = mShare.getString("1" + format.format(new Date()).split(" ")[0], "");
                    if (TextUtils.isEmpty(isDay)) {
                        //当天第一次点
                        showShortToast("每天有免费60秒的观看时间，您现在处于免费观看时间。");
                        goPostion(1);
                    } else {
                        try {
                            long endTime = format.parse(getUser().getVideoEndTime().replace("T", " ")).getTime();
                            if (endTime < System.currentTimeMillis()) {
                                seeEnd();
                            } else {
                                goPostion(1);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    showShortToast("网络未连接，");
                }
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
                if(mMessgerBadge!=null){
                  //  mMessgerBadge.setVisibility(View.GONE);
                }
                break;
            case R.id.main_left_bbInfo:
                startActivity(BBInfoActivity.class);
                break;
            case R.id.main_left_account:


                startActivity(AccountActivity.class);
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
