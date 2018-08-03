package com.litton.ishirdaily;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.litton.ishirdaily.bean.Games;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Class<?> gameClass3;
    private Games games1;
    private Games games2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(this, LoginActivity.class));
//        startActivity(new Intent(this, Main3Activity.class));
//        finish();

    }}
