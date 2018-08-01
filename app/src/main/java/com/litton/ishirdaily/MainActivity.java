package com.litton.ishirdaily;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.litton.ishirdaily.eventbus.LoginActivity;
import com.litton.ishirdaily.recyclerview.Main2Activity;
import com.litton.ishirdaily.recyclerview.Main3Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(this, LoginActivity.class));
//        startActivity(new Intent(this, Main3Activity.class));
//        finish();
    }
}
