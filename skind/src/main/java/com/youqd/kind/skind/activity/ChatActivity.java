package com.youqd.kind.skind.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jialin.chat.Message;
import com.jialin.chat.MessageAdapter;
import com.jialin.chat.MessageInputToolBox;
import com.jialin.chat.OnOperationListener;
import com.jialin.chat.Option;
import com.youqd.kind.skind.R;
import com.youqd.kind.skind.base.BaseActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ChatActivity extends BaseActivity {
    public static String TITLE = "TITLE";
    private MessageInputToolBox box;
    private ListView listView;
    private MessageAdapter adapter;
    private String parentName;

    @SuppressLint("UseSparseArrays")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        findViewById(R.id.rightId).setVisibility(View.GONE);
        parentName = ((String)getIntent().getStringExtra(TITLE));
        ((TextView)findViewById(R.id.top_title)).setText(parentName);
        initMessageInputToolBox();
        initListView();

    }

    /**
     * init MessageInputToolBox
     */
    @SuppressLint("ShowToast")
    private void initMessageInputToolBox(){
        box = (MessageInputToolBox) findViewById(R.id.messageInputToolBox);
        box.setOnOperationListener(new OnOperationListener() {

            @Override
            public void send(String content) {
                Message message = new Message(0, 1, "Tom", "avatar", "Jerry", "avatar", content, true, true, new Date());
                adapter.getData().add(message);
                listView.setSelection(listView.getBottom());

                //Just demo
                createReplayMsg(message);
            }

            @Override
            public void selectedFace(String content) {
                Message message = new Message(Message.MSG_TYPE_FACE, Message.MSG_STATE_SUCCESS, "Tomcat", "avatar", "Jerry", "avatar", content, true, true, new Date());
                adapter.getData().add(message);
                listView.setSelection(listView.getBottom());

                //Just demo
                createReplayMsg(message);
            }


            @Override
            public void selectedFuncation(int index) {

            }

        });

        ArrayList<String> faceNameList = new ArrayList<String>();
        for(int x = 1; x <= 10; x++){
            faceNameList.add("big"+x);
        }

        ArrayList<String> faceNameList1 = new ArrayList<String>();
        for(int x = 1; x <= 7; x++){
            faceNameList1.add("cig"+x);
        }

        ArrayList<String> faceNameList2 = new ArrayList<String>();
        for(int x = 1; x <= 24; x++){
            faceNameList2.add("dig"+x);
        }

        Map<Integer, ArrayList<String>> faceData = new HashMap<Integer, ArrayList<String>>();
        faceData.put(R.drawable.em_cate_magic, faceNameList2);
        faceData.put(R.drawable.em_cate_rib, faceNameList1);
        faceData.put(R.drawable.em_cate_duck, faceNameList);
        box.setFaceData(faceData);


        List<Option> functionData = new ArrayList<Option>();
        for(int x = 0; x < 5; x++){
            Option takePhotoOption = new Option(this, "Take", R.drawable.take_photo);
            Option galleryOption = new Option(this, "Gallery", R.drawable.gallery);
            functionData.add(galleryOption);
            functionData.add(takePhotoOption);
        }
        box.setFunctionData(functionData);
    }



    private void initListView(){
        listView = (ListView) findViewById(R.id.messageListview);

        //create Data
        Message message = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_SUCCESS, parentName, "avatar", "Jerry", "avatar", "你好,我是豆豆的老师", false, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8));
        Message message1 = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_SUCCESS, "", "avatar", "Jerry", "avatar", "你好，我是豆豆的妈妈", true, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24)* 8));

        List<Message> messages = new ArrayList<Message>();
        messages.add(message);
        messages.add(message1);

        adapter = new MessageAdapter(this, messages);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                box.hide();
                return false;
            }
        });

    }


    private void createReplayMsg(Message message){

        final Message reMessage = new Message(message.getType(), 1, "", "avatar", "Jerry", "avatar",
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

    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }
}
