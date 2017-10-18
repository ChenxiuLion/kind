package com.youqd.kind.ckind.activity;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jialin.chat.Message;
import com.jialin.chat.MessageAdapter;
import com.jialin.chat.MessageInputToolBox;
import com.jialin.chat.OnOperationListener;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.base.BaseEditActivity;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.ContactBean;
import com.youqd.kind.ckind.bean.JobResult;
import com.youqd.kind.ckind.bean.MessgerList;
import com.youqd.kind.ckind.bean.PostMessger;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UserManage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ChatActivity extends BaseEditActivity {

    private MessageInputToolBox box;
    private ListView listView;
    private MessageAdapter adapter;

    private ContactBean mBean;

    @Override
    protected int initLayout() {
        return R.layout.activity_chat;
    }

    /**
     * init MessageInputToolBox
     */
    @SuppressLint("ShowToast")
    private void initMessageInputToolBox(){
        box = (MessageInputToolBox) findViewById(R.id.messageInputToolBox);
        box.setOnOperationListener(new OnOperationListener() {

            @Override
            public void send(final String content) {
                final PostMessger messger = new PostMessger();
                messger.setContent(content);
                messger.setGardenerAccount(getUser().getUserAccount());
                messger.setGardenerID(getUser().getID());
                messger.setGardenerPhoto(getUser().getPhoto());
                messger.setGardenerName(getUser().getUserName());

                messger.setClassID(getUser().getClassID());
                messger.setKindergartenID(getUser().getKindergartenID());

                messger.setParentPhoto(mBean.getImage());
                messger.setParentName(mBean.getName());
                messger.setParentAccount(mBean.getTel()+"");
                messger.setParentID(mBean.getId());

                HttpTool.getInstance().addMessger(messger,
                        new KingCallback<JobResult>(JobResult.class) {
                            @Override
                            public void onSucceed(JobResult data) {
                                Message message = new Message(
                                        0,
                                        1,
                                        messger.getGardenerName(),
                                        messger.getGardenerPhoto(),
                                        messger.getGardenerPhoto(),
                                        messger.getGardenerPhoto(),
                                        content,
                                        true,
                                        true,
                                        new Date());
                                adapter.getData().add(message);
                                listView.setSelection(listView.getBottom());
                            }

                            @Override
                            public void onErro() {
                                Message message = new Message(
                                        0,
                                        1,
                                        messger.getGardenerName(),
                                        messger.getGardenerPhoto(),
                                        messger.getGardenerPhoto(),
                                        messger.getGardenerPhoto(),
                                        content,
                                        true,
                                        false,
                                        new Date());
                                adapter.getData().add(message);
                                listView.setSelection(listView.getBottom());
                                showShortToast("发送失败");
                            }
                        });

                //Just demo
            }

            @Override
            public void selectedFace(String content) {
            }


            @Override
            public void selectedFuncation(int index) {

            }

        });
    }
    List<Message> messages;


    private void initListView(){
        listView = (ListView) findViewById(R.id.messageListview);
        mBean = (ContactBean) getIntent().getSerializableExtra("data");
        messages = new ArrayList<Message>();
        ((TextView)findViewById(R.id.top_title)).setText(mBean.getName());
        //create Data
        adapter = new MessageAdapter(this, messages);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HttpTool.getInstance().doMessgerListg(getUser().getID() + "", mBean.getId()+"", getUser().getKindergartenID() + "", getUser().getClassID() + ""
                , new KingCallback<MessgerList>(MessgerList.class) {
                    @Override
                    public void onSucceed(MessgerList data) {
                        if(data!=null){
                            if(data.getResultData().size()>0){
                                findViewById(R.id.no_data).setVisibility(View.GONE);
                                for(MessgerList.ResultDataBean bean:data.getResultData()){
                                    Message message1 = null;
                                    if(bean.getSenderType()==4){
                                        try {
                                            message1 = new Message(
                                                    Message.MSG_TYPE_TEXT,
                                                    Message.MSG_STATE_SUCCESS,
                                                    bean.getGardenerName(),
                                                    bean.getGardenerPhoto(),
                                                    bean.getGardenerPhoto(),
                                                    bean.getGardenerPhoto(),
                                                    bean.getContent(),
                                                    true,
                                                    true,
                                                    format.parse(bean.getCreateTime()));
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }

                                    }else{
                                        try {
                                            message1 = new Message(
                                                    Message.MSG_TYPE_TEXT,
                                                    Message.MSG_STATE_SUCCESS,
                                                    bean.getParentName(),
                                                    bean.getParentPhoto(),
                                                    bean.getParentPhoto(),
                                                    bean.getParentPhoto(),
                                                    bean.getContent(),
                                                    false,
                                                    true,
                                                    format.parse(bean.getCreateTime()));
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    messages.add(message1);
                                    adapter.notifyDataSetChanged();

                                }
                            }
                            listView.setSelection(listView.getBottom());
                        }
                    }

                    @Override
                    public void onErro() {

                    }
                });
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                box.hide();
                return false;
            }
        });

    }


    private void createReplayMsg(Message message){

        final Message reMessage = new Message(message.getType(), 1, "李老师", "avatar", "Jerry", "avatar",
                message.getType() == 0 ? "Re:" + message.getContent() : message.getContent(),
                false, true, new Date());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * (new Random().nextInt(3) +1));
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            adapter.getData().add(reMessage);
                            listView.setSelection(listView.getBottom());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void initViews() {
        findViewById(R.id.rightId).setVisibility(View.GONE);

        initMessageInputToolBox();
        initListView();
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }
}
