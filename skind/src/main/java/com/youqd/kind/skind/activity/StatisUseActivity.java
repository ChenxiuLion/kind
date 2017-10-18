package com.youqd.kind.skind.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.youqd.kind.skind.R;

public class StatisUseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statis_use);
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("家长用量报告");
    }
}
