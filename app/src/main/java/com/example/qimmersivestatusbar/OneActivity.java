package com.example.qimmersivestatusbar;

import android.graphics.Color;
import android.os.Bundle;

import com.example.qimmersivestatusbar.base.BaseActivity;

public class OneActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        setImmersiveStatusBar(Color.BLUE);
    }
}
