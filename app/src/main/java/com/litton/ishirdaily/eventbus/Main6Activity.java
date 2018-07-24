package com.litton.ishirdaily.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.litton.ishirdaily.R;

import org.greenrobot.eventbus.EventBus;

public class Main6Activity extends BaseEventBusActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
    }

    public void start_event(View view) {
        EventBus.getDefault().post(new ActivityFinishEvent(""));
        startActivity(new Intent(this, LoginActivity.class));
    }
}
