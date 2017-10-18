package com.youqd.kind.ckind.fragment;

import android.os.Bundle;

import com.shizhefei.fragment.LazyFragment;
import com.kind.chx.gardener.R;

public class TabFragment extends LazyFragment {
	private int tabIndex;
	public static final String INTENT_INT_INDEX = "intent_int_index";

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_work_item);
		tabIndex = getArguments().getInt(INTENT_INT_INDEX);
	}

	@Override
	public void onDestroyViewLazy() {
		super.onDestroyViewLazy();
	}
}
