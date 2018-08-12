package com.example.qimmersivestatusbar.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.graphics.ColorUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 沉浸式状态栏工具类
 */
public class StatusBarUtils {
    private static final int ID_FAKE_STATUS_BAR_FOR_KK = 1234567890;

    /**
     * 设置状态栏颜色
     *
     * @param activity    需要设置的activity
     * @param statusColor 状态栏颜色值
     */
    public static void setColorBar(Activity activity, int statusColor) {
        if (activity == null || isFullScreen(activity)) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M &&
                    ColorUtils.setAlphaComponent(statusColor, 255) == Color.WHITE) {
                statusColor = Color.GRAY;
            }
            // 如果状态栏已经是该颜色，则不重复设置
            if (window.getStatusBarColor() != statusColor) {
                window.setStatusBarColor(statusColor);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            decorView.addView(createStatusBarView(activity, statusColor));
            setRootView(activity, true);
        }
    }

    /**
     * 设置状态栏透明
     *
     * @param activity 需要设置的activity
     */
    public static void setTransparentBar(Activity activity) {
        if (activity == null || isFullScreen(activity)) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            for (int i = 0; i < decorView.getChildCount(); i++) {
                View childView = decorView.getChildAt(i);
                if (childView.getId() == ID_FAKE_STATUS_BAR_FOR_KK) {
                    childView.setBackgroundColor(Color.TRANSPARENT);
                }
            }
            setRootView(activity, false);
        }
    }

    /**
     * 设置状态栏字体、图标颜色
     *
     * @param activity 需要设置的activity
     * @param darkText 是否把状态栏字体及图标颜色设置为深色
     */
    public static void setStatusBarMode(Activity activity, boolean darkText) {
        if (activity == null) {
            return;
        }

        if (RomUtils.isMIUI()
                && RomUtils.getMIUIVersionCode() >= 6
                && RomUtils.getMIUIVersionCode() < 9) {
            setMIUIStatusBarMode(activity.getWindow(), darkText);
        } else if (RomUtils.isFlyme()
                && RomUtils.getFlymeVersionCode() >= 4) {
            setFlymeStatusBarMode(activity.getWindow(), darkText);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatusBarModeFromM(activity.getWindow(), darkText);
        }
    }

    /**
     * 当前界面是否为全屏模式
     *
     * @param activity 需要设置的activity
     * @return boolean 全屏返回true
     */
    private static boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    /**
     * 创建一个和状态栏宽、高相同的矩形条
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     * @return View    状态栏矩形条
     */
    private static View createStatusBarView(Activity activity, int color) {
        View statusBarView = new View(activity);
        statusBarView.setId(ID_FAKE_STATUS_BAR_FOR_KK);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(color);
        return statusBarView;
    }

    /**
     * 设置根布局参数，使适应透明状态栏
     *
     * @param activity 需要设置的activity
     * @param isExtend 是否延伸到状态栏之下
     */
    private static void setRootView(Activity activity, boolean isExtend) {
        ViewGroup parent = activity.findViewById(android.R.id.content);
        for (int i = 0; i < parent.getChildCount(); i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(isExtend);
                ((ViewGroup) childView).setClipToPadding(isExtend);
            }
        }
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     *
     * @param window   需要设置的窗口
     * @param darkText 是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    private static boolean setMIUIStatusBarMode(Window window, boolean darkText) {
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (darkText) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag); //状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag); //清除黑色字体
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //开发版 7.7.13 及以后版本采用了系统API，旧方法无效但不会报错，所以两个方式都要加上
                    if (darkText) {
                        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    } else {
                        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    }
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     *
     * @param window   需要设置的窗口
     * @param darkText 是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    private static boolean setFlymeStatusBarMode(Window window, boolean darkText) {
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (darkText) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 设置原生Android 6.0及以上系统状态栏
     *
     * @param window   需要设置的窗口
     * @param darkText 是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    private static boolean setStatusBarModeFromM(Window window, boolean darkText) {
        if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(darkText ? View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR : View.SYSTEM_UI_FLAG_VISIBLE);
            return true;
        }
        return false;
    }

    /**
     * 获取状态栏高度
     *
     * @param context 上下文
     * @return int 高度
     */
    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}