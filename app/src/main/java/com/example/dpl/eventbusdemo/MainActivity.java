package com.example.dpl.eventbusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {
    private Button btn_send;
    private TextView tv_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_content= (TextView) findViewById(R.id.tv_content);
        btn_send= (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//发送数据点击事件
             MyEvent myEvent=new MyEvent();
                myEvent.setType("0");
                myEvent.setContent("0的内容");
                EventBus.getDefault().post(myEvent);
            }
        });
        EventBus.getDefault().register(this);//注册
    }
//    private void postData(){//发送事件
//        String string="我是消息";
//        EventBus.getDefault().post(string);
//    }
   @Subscribe
    public void onEvent(MyEvent Event){//接收数据消息
      if(Event.getType().equals("0")){
          tv_content.setText(Event.getContent());
      }
    }
//    public void onEventMainThread(MyEvent Event){//接收数据消息
//
//    }
//    public void onEventPostThread(MyEvent Event){//接收数据消息
//
//    }
//    public void onEventBackground(MyEvent Event){//接收数据消息
//
//    }
//    public void onEventAsync(MyEvent Event){}//接收数据消息
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
    }
}
