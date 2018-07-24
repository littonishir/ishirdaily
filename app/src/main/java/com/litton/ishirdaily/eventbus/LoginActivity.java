package com.litton.ishirdaily.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.litton.ishirdaily.R;

public class LoginActivity extends BaseEventBusActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void login(View view) {
        startActivity(new Intent(this, Main5Activity.class));
        finish();
    }
}
