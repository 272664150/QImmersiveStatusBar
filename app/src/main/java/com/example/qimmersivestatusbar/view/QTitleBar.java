package com.example.qimmersivestatusbar.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qimmersivestatusbar.R;

public class QTitleBar extends RelativeLayout {

    private RelativeLayout mTopBarRl;
    private TextView mLeft1Tv;
    private TextView mLeft2Tv;
    private ImageView mTitleIv;
    private TextView mTitleTv;
    private TextView mRight1Tv;
    private TextView mRight2Tv;
    private View mBottomLine;

    public QTitleBar(Context context) {
        super(context);
        initView(context);
    }

    public QTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_titlebar, this);
        mTopBarRl = findViewById(R.id.top_bar_rl);
        mLeft1Tv = findViewById(R.id.top_left1_tv);
        mLeft2Tv = findViewById(R.id.top_left2_tv);
        mTitleIv = findViewById(R.id.icon_title_iv);
        mTitleTv = findViewById(R.id.text_title_tv);
        mRight1Tv = findViewById(R.id.top_right1_tv);
        mRight2Tv = findViewById(R.id.top_right2_tv);
        mBottomLine = findViewById(R.id.view_bottom_line);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        float scale = getResources().getDisplayMetrics().density;
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec((int) (44 * scale + 0.5f), MeasureSpec.EXACTLY));
    }

    public static class Builder {
        private Context mContext;
        private QTitleBar mTitleBar;

        public Builder(Context context) {
            mContext = context;
            mTitleBar = new QTitleBar(context);
            mTitleBar.setBackgroundResource(android.R.color.white);
        }

        public Builder setAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
            mTitleBar.getTopBarRl().setAlpha(alpha);
            return this;
        }

        public Builder setBackgroundColor(@ColorInt int color) {
            mTitleBar.setBackgroundColor(color);
            return this;
        }

        public Builder setBackgroundResource(@DrawableRes int resId) {
            mTitleBar.setBackgroundResource(resId);
            return this;
        }

        public Builder setLeft1Visibility(int visibility) {
            mTitleBar.getLeft1Tv().setVisibility(visibility);
            return this;
        }

        public Builder setLeft1Icon(@Nullable Drawable left) {
            mTitleBar.getLeft1Tv().setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
            return this;
        }

        public Builder setLeft1Icon(@DrawableRes int resId) {
            mTitleBar.getLeft1Tv().setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(resId), null, null, null);
            return this;
        }

        public Builder setLeft1Text(CharSequence text) {
            mTitleBar.getLeft1Tv().setText(text);
            return this;
        }

        public Builder setLeft1Text(@StringRes int resId) {
            mTitleBar.getLeft1Tv().setText(resId);
            return this;
        }

        public Builder setLeft1TextColor(@ColorInt int resId) {
            mTitleBar.getLeft1Tv().setTextColor(resId);
            return this;
        }

        public Builder setLeft1TextSize(float size) {
            mTitleBar.getLeft1Tv().setTextSize(size);
            return this;
        }

        public Builder setLeft1ClickListener(OnClickListener onClickListener) {
            mTitleBar.getLeft1Tv().setOnClickListener(onClickListener);
            return this;
        }

        public Builder setLeft2Visibility(int visibility) {
            mTitleBar.getLeft2Tv().setVisibility(visibility);
            return this;
        }

        public Builder setLeft2Icon(@Nullable Drawable left) {
            mTitleBar.getLeft2Tv().setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
            return this;
        }

        public Builder setLeft2Icon(@DrawableRes int resId) {
            mTitleBar.getLeft2Tv().setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(resId), null, null, null);
            return this;
        }

        public Builder setLeft2Text(CharSequence text) {
            mTitleBar.getLeft2Tv().setText(text);
            return this;
        }

        public Builder setLeft2Text(@StringRes int resId) {
            mTitleBar.getLeft2Tv().setText(resId);
            return this;
        }

        public Builder setLeft2TextColor(@ColorInt int resId) {
            mTitleBar.getLeft2Tv().setTextColor(resId);
            return this;
        }

        public Builder setLeft2TextSize(float size) {
            mTitleBar.getLeft2Tv().setTextSize(size);
            return this;
        }

        public Builder setLeft2ClickListener(OnClickListener onClickListener) {
            mTitleBar.getLeft2Tv().setOnClickListener(onClickListener);
            return this;
        }

        public Builder setTitleLogoVisibility(int visibility) {
            mTitleBar.getTitleIv().setVisibility(visibility);
            return this;
        }

        public Builder setTitleLogo(@NonNull Drawable drawable) {
            mTitleBar.getTitleIv().setImageDrawable(drawable);
            return this;
        }

        public Builder setTitleLogo(@DrawableRes int resId) {
            mTitleBar.getTitleIv().setImageResource(resId);
            return this;
        }

        public Builder setTitleVisibility(int visibility) {
            mTitleBar.getTitleTv().setVisibility(visibility);
            return this;
        }

        public Builder setTitleText(CharSequence text) {
            mTitleBar.getTitleTv().setText(text);
            return this;
        }

        public Builder setTitleText(@StringRes int resId) {
            mTitleBar.getTitleTv().setText(resId);
            return this;
        }

        public Builder setTitleTextColor(@ColorInt int resId) {
            mTitleBar.getTitleTv().setTextColor(resId);
            return this;
        }

        public Builder setTitleTextSize(float size) {
            mTitleBar.getTitleTv().setTextSize(size);
            return this;
        }

        public Builder setRight1Visibility(int visibility) {
            mTitleBar.getRight1Tv().setVisibility(visibility);
            return this;
        }

        public Builder setRight1Icon(@Nullable Drawable right) {
            mTitleBar.getRight1Tv().setCompoundDrawablesWithIntrinsicBounds(null, null, right, null);
            return this;
        }

        public Builder setRight1Icon(@DrawableRes int resId) {
            mTitleBar.getRight1Tv().setCompoundDrawablesWithIntrinsicBounds(null, null, mContext.getResources().getDrawable(resId), null);
            return this;
        }

        public Builder setRight1Text(CharSequence text) {
            mTitleBar.getRight1Tv().setText(text);
            return this;
        }

        public Builder setRight1Text(@StringRes int resId) {
            mTitleBar.getRight1Tv().setText(resId);
            return this;
        }

        public Builder setRight1TextColor(@ColorInt int resId) {
            mTitleBar.getRight1Tv().setTextColor(resId);
            return this;
        }

        public Builder setRight1TextSize(float size) {
            mTitleBar.getRight1Tv().setTextSize(size);
            return this;
        }

        public Builder setRight1ClickListener(OnClickListener onClickListener) {
            mTitleBar.getRight1Tv().setOnClickListener(onClickListener);
            return this;
        }

        public Builder setRight2Visibility(int visibility) {
            mTitleBar.getRight2Tv().setVisibility(visibility);
            return this;
        }

        public Builder setRight2Icon(@Nullable Drawable right) {
            mTitleBar.getRight2Tv().setCompoundDrawablesWithIntrinsicBounds(null, null, right, null);
            return this;
        }

        public Builder setRight2Icon(@DrawableRes int resId) {
            mTitleBar.getRight2Tv().setCompoundDrawablesWithIntrinsicBounds(null, null, mContext.getResources().getDrawable(resId), null);
            return this;
        }

        public Builder setRight2Text(CharSequence text) {
            mTitleBar.getRight2Tv().setText(text);
            return this;
        }

        public Builder setRight2Text(@StringRes int resId) {
            mTitleBar.getRight2Tv().setText(resId);
            return this;
        }

        public Builder setRight2TextColor(@ColorInt int resId) {
            mTitleBar.getRight2Tv().setTextColor(resId);
            return this;
        }

        public Builder setRight2TextSize(float size) {
            mTitleBar.getRight2Tv().setTextSize(size);
            return this;
        }

        public Builder setRight2ClickListener(OnClickListener onClickListener) {
            mTitleBar.getRight2Tv().setOnClickListener(onClickListener);
            return this;
        }

        public Builder setBottomLineVisibility(int visibility) {
            mTitleBar.getBottomLine().setVisibility(visibility);
            return this;
        }

        public Builder addView(View view, int gravity) {
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (gravity == Gravity.LEFT || gravity == Gravity.START) {
                lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_START);
            }
            if (gravity == Gravity.CENTER) {
                lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            }
            if (gravity == Gravity.RIGHT || gravity == Gravity.END) {
                lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_END);
            }
            lp.addRule(RelativeLayout.CENTER_VERTICAL);
            mTitleBar.getTopBarRl().addView(view, lp);
            return this;
        }

        public Builder removeView(View view) {
            mTitleBar.getTopBarRl().removeView(view);
            return this;
        }

        public QTitleBar build() {
            return mTitleBar;
        }
    }

    private RelativeLayout getTopBarRl() {
        return mTopBarRl;
    }

    private TextView getLeft1Tv() {
        return mLeft1Tv;
    }

    private TextView getLeft2Tv() {
        return mLeft2Tv;
    }

    private ImageView getTitleIv() {
        return mTitleIv;
    }

    private TextView getTitleTv() {
        return mTitleTv;
    }

    private TextView getRight1Tv() {
        return mRight1Tv;
    }

    private TextView getRight2Tv() {
        return mRight2Tv;
    }

    private View getBottomLine() {
        return mBottomLine;
    }
}