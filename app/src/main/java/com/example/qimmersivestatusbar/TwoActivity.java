package com.example.qimmersivestatusbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.qimmersivestatusbar.base.BaseActivity;

public class TwoActivity extends BaseActivity {
    private boolean isDarkText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        getTitleBarBuilder().setBackgroundColor(Color.RED);
        findViewById(R.id.text_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDarkText = !isDarkText;
                setImmersiveStatusBar(Color.RED, isDarkText);
            }
        });
        findViewById(R.id.text_btn).performClick();
    }
}
