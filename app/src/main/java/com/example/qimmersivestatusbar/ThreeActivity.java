package com.example.qimmersivestatusbar;

import android.os.Bundle;

import com.example.qimmersivestatusbar.base.BaseActivity;
import com.example.qimmersivestatusbar.util.StatusBarUtils;

public class ThreeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        StatusBarUtils.setTransparentBar(this);
    }
}
