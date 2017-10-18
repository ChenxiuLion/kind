package com.cosfund.library.refresh.pulltozoomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.cosfund.library.R;

/**
 * 作者 by Gavin on 2015/12/29 0029.
 * 描述：
 * 下拉刷新并且缩放头部的ScrollView
 * <p>
 * XML
 * <com.cosfund.library.refresh.pulltozoomview.PullToZoomScrollView
 * android:id="@+id/scroll_view"
 * android:layout_width="match_parent"
 * android:layout_height="match_parent"/>
 * </p>
 * <p>
 * Activity
 * PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx) findViewById(R.id.scroll_view);
 * 添加头部View,缩放的View,底部View
 * <p/>
 * View headView = LayoutInflater.from(this).inflate(R.layout.profile_head_view, null, false);
 * View zoomView = LayoutInflater.from(this).inflate(R.layout.profile_zoom_view, null, false);
 * View contentView = LayoutInflater.from(this).inflate(R.layout.profile_content_view, null, false);
 * scrollView.setHeaderView(headView);
 * scrollView.setZoomView(zoomView);
 * scrollView.setScrollContentView(contentView);
 * <p/>
 * LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(ScreenWidth, (int) (9.0F * (ScreenWidth / 16.0F)));
 * scrollView.setHeaderLayoutParams(localObject);
 * </p>
 */
public class PullToZoomScrollView extends PullToZoomBase<ScrollView> {

    private static final Interpolator sInterpolator = new Interpolator() {
        public float getInterpolation(float paramAnonymousFloat) {
            float f = paramAnonymousFloat - 1.0F;
            return 1.0F + f * (f * (f * (f * f)));
        }
    };
    private boolean isCustomHeaderHeight = false;
    private FrameLayout mHeaderContainer;
    private LinearLayout mRootContainer;
    private View mContentView;
    private int mHeaderHeight;
    private ScalingRunnable mScalingRunnable;

    public PullToZoomScrollView(Context context) {
        this(context, null);
    }

    public PullToZoomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScalingRunnable = new ScalingRunnable();
        ((InternalScrollView) mRootView).setOnScrollViewChangedListener(new OnScrollViewChangedListener() {
            @Override
            public void onInternalScrollChanged(int left, int top, int oldLeft, int oldTop) {
                if (isPullToZoomEnabled() && isParallax()) {
                    float f = mHeaderHeight - mHeaderContainer.getBottom() + mRootView.getScrollY();
                    if ((f > 0.0F) && (f < mHeaderHeight)) {
                        int i = (int) (0.65D * f);
                        mHeaderContainer.scrollTo(0, -i);
                    } else if (mHeaderContainer.getScrollY() != 0) {
                        mHeaderContainer.scrollTo(0, 0);
                    }
                }
            }
        });
    }

    @Override
    protected void pullHeaderToZoom(int newScrollValue) {
        if (mScalingRunnable != null && !mScalingRunnable.isFinished()) {
            mScalingRunnable.abortAnimation();
        }

        ViewGroup.LayoutParams localLayoutParams = mHeaderContainer.getLayoutParams();
        localLayoutParams.height = Math.abs(newScrollValue) + mHeaderHeight;
        mHeaderContainer.setLayoutParams(localLayoutParams);

        if (isCustomHeaderHeight) {
            ViewGroup.LayoutParams zoomLayoutParams = mZoomView.getLayoutParams();
            zoomLayoutParams.height = Math.abs(newScrollValue) + mHeaderHeight;
            mZoomView.setLayoutParams(zoomLayoutParams);
        }
    }

    /**
     * 是否显示headerView
     *
     * @param isHideHeader true: show false: hide
     */
    @Override
    public void setHideHeader(boolean isHideHeader) {
        if (isHideHeader != isHideHeader() && mHeaderContainer != null) {
            super.setHideHeader(isHideHeader);
            if (isHideHeader) {
                mHeaderContainer.setVisibility(GONE);
            } else {
                mHeaderContainer.setVisibility(VISIBLE);
            }
        }
    }

    @Override
    public void setHeaderView(View headerView) {
        if (headerView != null) {
            mHeaderView = headerView;
            updateHeaderView();
        }
    }

    @Override
    public void setZoomView(View zoomView) {
        if (zoomView != null) {
            mZoomView = zoomView;
            updateHeaderView();
        }
    }

    private void updateHeaderView() {
        if (mHeaderContainer != null) {
            mHeaderContainer.removeAllViews();
            if (mZoomView != null) {
                mHeaderContainer.addView(mZoomView);
            }
            if (mHeaderView != null) {
                mHeaderContainer.addView(mHeaderView);
            }
        }

    }

    public void setScrollContentView(View contentView) {
        if (contentView != null) {
            if (mContentView != null) {
                mRootContainer.removeView(mContentView);
            }
            mContentView = contentView;
            mRootContainer.addView(mContentView);
        }
    }

    @Override
    protected ScrollView createRootView(Context context, AttributeSet attrs) {
        ScrollView scrollView = new InternalScrollView(context, attrs);
        scrollView.setId(R.id.scrollview);
        return scrollView;
    }

    @Override
    protected void smoothScrollToTop() {
        mScalingRunnable.startAnimation(200L);
    }

    @Override
    protected boolean isReadyForPullStart() {
        return mRootView.getScrollY() == 0;
    }

    @Override
    public void handleStyledAttributes(TypedArray a) {
        mRootContainer = new LinearLayout(getContext());
        mRootContainer.setOrientation(LinearLayout.VERTICAL);
        mHeaderContainer = new FrameLayout(getContext());
        if (mZoomView != null) {
            mHeaderContainer.addView(mZoomView);
        }
        if (mHeaderView != null) {
            mHeaderContainer.addView(mHeaderView);
        }
        int contentViewResId = a.getResourceId(R.styleable.PullToZoomView_contentView, 0);
        if (contentViewResId > 0) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(getContext());
            mContentView = mLayoutInflater.inflate(contentViewResId, null, false);
        }
        mRootContainer.addView(mHeaderContainer);
        if (mContentView != null) {
            mRootContainer.addView(mContentView);
        }
        mRootContainer.setClipChildren(false);
        mHeaderContainer.setClipChildren(false);
        mRootView.addView(mRootContainer);
    }

    /**
     * 设置HeaderView高度
     *
     * @param width
     * @param height
     */
    public void setHeaderViewSize(int width, int height) {
        if (mHeaderContainer != null) {
            Object localObject = mHeaderContainer.getLayoutParams();
            if (localObject == null) {
                localObject = new ViewGroup.LayoutParams(width, height);
            }
            ((ViewGroup.LayoutParams) localObject).width = width;
            ((ViewGroup.LayoutParams) localObject).height = height;
            mHeaderContainer.setLayoutParams((ViewGroup.LayoutParams) localObject);
            mHeaderHeight = height;
            isCustomHeaderHeight = true;
        }
    }

    /**
     * 设置HeaderView LayoutParams
     *
     * @param layoutParams LayoutParams
     */
    public void setHeaderLayoutParams(LayoutParams layoutParams) {
        if (mHeaderContainer != null) {
            mHeaderContainer.setLayoutParams(layoutParams);
            mHeaderHeight = layoutParams.height;
            isCustomHeaderHeight = true;
        }
    }

    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
        if (mHeaderHeight == 0 && mZoomView != null) {
            mHeaderHeight = mHeaderContainer.getHeight();
        }
    }

    public interface OnScrollViewChangedListener {
        void onInternalScrollChanged(int left, int top, int oldLeft, int oldTop);
    }

    class ScalingRunnable implements Runnable {
        protected long mDuration;
        protected boolean mIsFinished = true;
        protected float mScale;
        protected long mStartTime;

        ScalingRunnable() {
        }

        public void abortAnimation() {
            mIsFinished = true;
        }

        public boolean isFinished() {
            return mIsFinished;
        }

        public void run() {
            if (mZoomView != null) {
                float f2;
                ViewGroup.LayoutParams localLayoutParams;
                if ((!mIsFinished) && (mScale > 1.0D)) {
                    float f1 = ((float) SystemClock.currentThreadTimeMillis() - (float) mStartTime) / (float) mDuration;
                    f2 = mScale - (mScale - 1.0F) * PullToZoomScrollView.sInterpolator.getInterpolation(f1);
                    localLayoutParams = mHeaderContainer.getLayoutParams();
                    if (f2 > 1.0F) {
                        localLayoutParams.height = ((int) (f2 * mHeaderHeight));
                        mHeaderContainer.setLayoutParams(localLayoutParams);
                        if (isCustomHeaderHeight) {
                            ViewGroup.LayoutParams zoomLayoutParams;
                            zoomLayoutParams = mZoomView.getLayoutParams();
                            zoomLayoutParams.height = ((int) (f2 * mHeaderHeight));
                            mZoomView.setLayoutParams(zoomLayoutParams);
                        }
                        post(this);
                        return;
                    }
                    mIsFinished = true;
                }
            }
        }

        public void startAnimation(long paramLong) {
            if (mZoomView != null) {
                mStartTime = SystemClock.currentThreadTimeMillis();
                mDuration = paramLong;
                mScale = ((float) (mHeaderContainer.getBottom()) / mHeaderHeight);
                mIsFinished = false;
                post(this);
            }
        }
    }

    protected class InternalScrollView extends ScrollView {
        private OnScrollViewChangedListener onScrollViewChangedListener;

        public InternalScrollView(Context context) {
            this(context, null);
        }

        public InternalScrollView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public void setOnScrollViewChangedListener(OnScrollViewChangedListener onScrollViewChangedListener) {
            this.onScrollViewChangedListener = onScrollViewChangedListener;
        }

        @Override
        protected void onScrollChanged(int l, int t, int oldl, int oldt) {
            super.onScrollChanged(l, t, oldl, oldt);
            if (onScrollViewChangedListener != null) {
                onScrollViewChangedListener.onInternalScrollChanged(l, t, oldl, oldt);
            }
        }
    }
}
