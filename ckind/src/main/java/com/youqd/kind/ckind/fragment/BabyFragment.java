package com.youqd.kind.ckind.fragment;

import android.os.Bundle;

import com.shizhefei.fragment.LazyFragment;
import com.youqd.kind.ckind.R;

public class BabyFragment extends LazyFragment {
	private int tabIndex;
	public static final String INTENT_INT_INDEX = "intent_int_index";

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.activity_baby_detail);
		tabIndex = getArguments().getInt(INTENT_INT_INDEX);
	}

	@Override
	public void onDestroyViewLazy() {
		super.onDestroyViewLazy();
	}
}
