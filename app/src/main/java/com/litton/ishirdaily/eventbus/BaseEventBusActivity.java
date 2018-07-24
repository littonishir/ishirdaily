package com.litton.ishirdaily.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.litton.ishirdaily.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BaseEventBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_event_bus);
        // 注册EventBus
        EventBus.getDefault().register(this);
    }

    //订阅方法，接收到事件会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ActivityFinishEvent event) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销EventBus
        EventBus.getDefault().unregister(this);
    }
}
