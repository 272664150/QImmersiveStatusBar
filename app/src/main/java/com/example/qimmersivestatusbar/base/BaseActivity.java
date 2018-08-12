package com.example.qimmersivestatusbar.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qimmersivestatusbar.R;
import com.example.qimmersivestatusbar.util.StatusBarUtils;
import com.example.qimmersivestatusbar.view.QTitleBar;

public class BaseActivity extends AppCompatActivity {

    private QTitleBar.Builder mTitleBarBuilder;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);
        LayoutInflater.from(this).inflate(layoutResID, (ViewGroup) findViewById(R.id.root_content_fl), true);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    /**
     * 获取titleBar构造器，设置相关属性，默认显示back按钮
     */
    protected QTitleBar.Builder getTitleBarBuilder() {
        if (mTitleBarBuilder == null) {
            ViewGroup rootView = getWindow().getDecorView().findViewById(android.R.id.content);
            mTitleBarBuilder = new QTitleBar.Builder(this);
            mTitleBarBuilder.setLeft1Icon(R.drawable.ic_arrow_back_black_24dp)
                    .setLeft1ClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    })
                    .setLeft1Visibility(View.VISIBLE);
            rootView.addView(mTitleBarBuilder.build());
        }
        return mTitleBarBuilder;
    }

    /**
     * 设置状态栏沉浸, 当且仅当defColor最终为Color.WHITE时，状态栏文字、图标变为黑色。
     * 若其它的defColor也需要状态栏文字、图标变为黑色，请使用另一个重载方法强行设置。
     * 假设此处使用自定义的TitleBar做为app标题栏，状态栏与其颜色保持一致。
     *
     * @param defColor 状态栏默认颜色
     */
    protected void setImmersiveStatusBar(@ColorInt int defColor) {
        try {
            if (getTitleBarBuilder() != null) {
                Drawable background = getTitleBarBuilder().build().getBackground();
                ColorDrawable colorDrawable = (ColorDrawable) background;
                defColor = colorDrawable.getColor();
            }
            setImmersiveStatusBar(defColor, ColorUtils.setAlphaComponent(defColor, 255) == Color.WHITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置状态栏沉浸
     *
     * @param barColor 状态栏的颜色
     * @param darkMode 状态栏的文字、图标是否显示黑色
     */
    protected void setImmersiveStatusBar(@ColorInt int barColor, boolean darkMode) {
        try {
            StatusBarUtils.setColorBar(this, barColor);
            StatusBarUtils.setStatusBarMode(this, darkMode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
