package com.youqd.kind.skind.evaluation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.youqd.kind.skind.R;
import com.youqd.kind.skind.activity.GrowAddActivity;
import com.youqd.kind.skind.activity.NewAddActivity;
import com.youqd.kind.skind.base.BaseActivity;
import com.youqd.kind.skind.evaluation.callback.JsonCallback;
import com.youqd.kind.skind.evaluation.bean.Evaluation;
import com.youqd.kind.skind.evaluation.bean.EvaluationItem;
import com.youqd.kind.skind.evaluation.utils.Urls;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.HttpParams;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import okhttp3.Request;
import okhttp3.Response;

public class EvaluationActivity extends BaseActivity{

    @Bind(R.id.ptr) PtrClassicFrameLayout ptr;
    @Bind(R.id.listView) ListView listView;

    private ImageView rightImg;
    private EvaluationAdapter mAdapter;
    private ArrayList<EvaluationItem> data;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);
        ButterKnife.bind(this);
        ((TextView)findViewById(R.id.top_title)).setText("成生动态");
        View emptyView = View.inflate(this, R.layout.item_empty, null);
        addContentView(emptyView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        listView.setEmptyView(emptyView);

        mAdapter = new EvaluationAdapter(this, new ArrayList<EvaluationItem>());
        listView.setAdapter(mAdapter);

        initData(false);

        ptr.setLastUpdateTimeRelateObject(this);
        ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                initData(true);
            }
        });

        rightImg = (ImageView)findViewById(R.id.rightId);
        rightImg.setImageResource(R.drawable.add_gray_selector);
        rightImg.setOnClickListener(this);
    }


    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rightId:
                startActivity(GrowAddActivity.class);
                break;
            default:
                break;
        }
    }

    private void initData(final boolean isMore) {
        HttpParams params = new HttpParams();
        params.put("goodsId", "98573");
        params.put("pageNo", String.valueOf(page));
        OkHttpUtils.post(Urls.Evaluation)//
                .tag(this)//
                .params(params)//
                .execute(new JsonCallback<Evaluation>(Evaluation.class) {
                    @Override
                    public void onResponse(boolean isFromCache, Evaluation evaluation, Request request, @Nullable Response response) {
                        if (isMore) {
                            data.addAll(0, evaluation.getEvaluataions());
                        } else {
                            data = evaluation.getEvaluataions();
                        }
                        mAdapter.setData(data);
                        page++;
                        ptr.refreshComplete();
                    }
                });
    }
}