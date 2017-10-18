package com.youqd.kind.skind.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.youqd.kind.skind.R;
import com.youqd.kind.skind.base.BaseActivity;
import com.youqd.kind.skind.evaluation.EvaluationActivity;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

public class MainActivity extends BaseActivity {
    private static final String STATE_CONTENT_TEXT = "net.simonvt.menudrawer.samples.LeftDrawerSample.contentText";

    private String mContentText;
    private TextView mContentTextView;
    private static final String STATE_ACTIVE_POSITION =
            "net.simonvt.menudrawer.samples.LeftDrawerSample.activePosition";

    protected MenuDrawer mMenuDrawer;

    @Override
    protected void onCreate(Bundle inState) {
        super.onCreate(inState);

        if (inState != null) {
            mContentText = inState.getString(STATE_CONTENT_TEXT);
        }
        mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND, getDrawerPosition(), getDragMode());
        mMenuDrawer.setMenuView(LayoutInflater.from(this).inflate(R.layout.leftmenu,null));

        mMenuDrawer.setContentView(R.layout.activity_main);
        mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);
        mMenuDrawer.setSlideDrawable(R.drawable.ic_drawer);
        mMenuDrawer.setDrawerIndicatorEnabled(true);

        mMenuDrawer.setOnInterceptMoveEventListener(new MenuDrawer.OnInterceptMoveEventListener() {
            @Override
            public boolean isViewDraggable(View v, int dx, int x, int y) {
                return v instanceof SeekBar;
            }
        });
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mMenuDrawer.toggleMenu();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        final int drawerState = mMenuDrawer.getDrawerState();
        if (drawerState == MenuDrawer.STATE_OPEN || drawerState == MenuDrawer.STATE_OPENING) {
            mMenuDrawer.closeMenu();
            return;
        }

        super.onBackPressed();
    }

    protected int getDragMode() {
        return MenuDrawer.MENU_DRAG_CONTENT;
    }

    protected Position getDrawerPosition() {
        return Position.START;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.main_schoolnew:
                startActivity(SchoolNewActivity.class);
                break;
            case R.id.main_classnew:
                startActivity(SchoolNoticeActivity.class);
                break;
            case R.id.main_work:
                startActivity(SalaryActivity.class);
                break;
            case R.id.main_food:
                startActivity(FoodActivity.class);
                break;
            case R.id.main_course:
                startActivity(CourseActivity.class);
                break;
            case R.id.main_job:
                startActivity(JobActivity.class);
                break;
            case R.id.main_chat:
                startActivity(ChatListActivity.class);
                break;
            case R.id.main_baby:
                startActivity(SeeActivity.class);
                break;
           case R.id.main_btm_active:
                startActivity(EvaluationActivity.class);
                break;
            case R.id.main_btm_intro:
                startActivity(IntroActivity.class);
                break;
            case R.id.main_btm_msg:
                startActivity(MsgActivity.class);
                break;
            case R.id.main_btm_my:
                mMenuDrawer.toggleMenu();
                break;
            case R.id.main_btm_atv:
                startActivity(PlanActivity.class);
                break;
            case R.id.main_left_setting:
                startActivity(SettingActivity.class);
                break;
            case R.id.main_left_intro:
                startActivity(IntroActivity.class);
                break;
            case R.id.main_left_info:
                startActivity(MyInfoActivity.class);
                break;
            case R.id.main_left_contact:
                startActivity(ContactActivity.class);
                break;
            case R.id.main_left_use:
                startActivity(StatisClassActivity.class);
                break;
            case R.id.main_left_class:
                startActivity(StatisUseActivity.class);
                break;
            default:
                break;
        }
    }
}
