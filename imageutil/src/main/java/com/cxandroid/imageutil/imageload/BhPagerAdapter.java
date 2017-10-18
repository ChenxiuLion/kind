package com.cxandroid.imageutil.imageload;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

public class BhPagerAdapter extends FragmentPagerAdapter {
	public ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
	public FragmentManager mFragmentManager;
	
	  private String mTabTitle[] = null;
	
	public String[] getmTabTitle() {
		return mTabTitle;
	}


	public void setmTabTitle(String[] mTabTitle) {
		this.mTabTitle = mTabTitle;
	}


	public BhPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
		super(fm);
		// TODO Auto-generated constructor stub
		mFragmentManager = fm;
		mFragments = fragments;
	}


	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return mFragments.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mFragments.size();
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		Fragment f =  (Fragment) super.instantiateItem(container, position);
		
		
		return f ;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return mTabTitle[position];
	}

}
