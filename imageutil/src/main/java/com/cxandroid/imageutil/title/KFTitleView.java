package com.cxandroid.imageutil.title;


import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxandroid.imageutil.R;


public class KFTitleView extends LinearLayout {

	private Context mContext;
	
	private View mRootView;
	
	public TextView mLeftText;  //��߲���
	
	public TextView mRightText;  //�ұ߲���
	
	public TextView mTitleText;//����
	
	private String mInitColor = "";
	
	private OnTitleOncilkEvent mTitleOncilkEvent;
/*	@SuppressLint("NewApi") public KFTitleView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		initView();
	}
*/


	public KFTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initView();
	}

	public KFTitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView();
	}

	public KFTitleView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}

	
	//
	private void initView() {
		// TODO Auto-generated method stub
		mContext = getContext();
		mRootView = LayoutInflater.from(getContext()).inflate(R.layout.title_view, this);
		
		mLeftText = (TextView) mRootView.findViewById(R.id.left_tv);
		mTitleText = (TextView) mRootView.findViewById(R.id.title_tv);
		mRightText = (TextView) mRootView.findViewById(R.id.right_tv);
		Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "break.ttf");
		mLeftText.setTypeface(typeface);
		mLeftText.setText(getResources().getString(R.string.xy_break));
		mLeftText.setTextSize(30f);
	}
	
	/**
	 * ���ñ���
	 * @param title
	 */
	public void setTitlte(CharSequence title)
	{
		if(TextUtils.isEmpty(title))
		{
			mTitleText.setText(getResources().getString(R.string.app_name));
		}
		else
		{
			mTitleText.setText(title);
		}
	}
	
	/**
	 * �������
	 * @param left
	 */
	public void setLeft(CharSequence left)
	{
		if(TextUtils.isEmpty(left))
		{
			mLeftText.setText("     ");
		}
		else
		{
			mLeftText.setText(left);
			mLeftText.setVisibility(View.VISIBLE);
		}
		
	}
	
	public void showLeft()
	{
		mLeftText.setVisibility(View.GONE);
	}
	/**
	 * ������߼�ͼ��
	 * @param left
	 */
	public void setLeft(CharSequence left,int res)
	{
		if(res != 0)
		{
		mLeftText.setCompoundDrawablesWithIntrinsicBounds(res, 0, 0, 0);
		}
		else
		{
			mLeftText.setVisibility(View.GONE);	
		}
		setLeft(left);
	}
	/**
	 * �����ұ�
	 */
	public void setRight(CharSequence right)
	{
		if(TextUtils.isEmpty(right))
		{
			mRightText.setText("     ");
			mRightText.setVisibility(View.VISIBLE);
		}
		else
		{
			mRightText.setText(right);
			mRightText.setVisibility(View.VISIBLE);
		}
	
	}
	
	/**
	 * �����ұ߼�ͼ��
	 */
	public void setRight(CharSequence right,int res)
	{
		if(res != 0)
		{
			mRightText.setCompoundDrawablesWithIntrinsicBounds(res, 0, 0, 0);
		}
		setRight(right);
	}
	/**
	 * ����bar�����ҵ���¼�
	 * @param OncilkEvent
	 */
	public void setTitleOncilk(OnTitleOncilkEvent OncilkEvent) {
		this.mTitleOncilkEvent = OncilkEvent;
		
		mLeftText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mTitleOncilkEvent.onLeft();
			}
		});
		mRightText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mTitleOncilkEvent.onRight();
			}
		});
	}
	
	
}
