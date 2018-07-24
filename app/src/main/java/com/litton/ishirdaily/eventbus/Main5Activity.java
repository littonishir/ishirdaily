package com.litton.ishirdaily.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.litton.ishirdaily.R;

public class Main5Activity extends BaseEventBusActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }

    public void next(View view) {
        startActivity(new Intent(this, Main6Activity.class));
    }
}
