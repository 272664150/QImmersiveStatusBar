package com.example.qimmersivestatusbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.qimmersivestatusbar.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getTitleBarBuilder()
                .setBackgroundColor(Color.BLUE)
                .setLeft1Visibility(View.GONE);
        setImmersiveStatusBar(Color.WHITE);

        findViewById(R.id.one_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OneActivity.class));
            }
        });
        findViewById(R.id.two_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TwoActivity.class));
            }
        });
        findViewById(R.id.three_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThreeActivity.class));
            }
        });
    }
}
