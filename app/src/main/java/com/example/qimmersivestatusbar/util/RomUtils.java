package com.example.qimmersivestatusbar.util;

import android.os.Build;
import android.text.TextUtils;

import java.lang.reflect.Method;

/**
 * 手机ROM工具类
 */
public class RomUtils {

    /**
     * 判断是否为MIUI
     *
     * @return
     */
    public static boolean isMIUI() {
        String property = getSystemProperty("ro.miui.ui.version.name", "");
        return !TextUtils.isEmpty(property);
    }

    /**
     * 获取MUI版本
     *
     * @return
     */
    public static String getMIUIVersion() {
        return isMIUI() ? getSystemProperty("ro.miui.ui.version.name", "") : "";
    }

    /**
     * 获取MIUI版本-数字用于大小判断
     *
     * @return
     */
    public static int getMIUIVersionCode() {
        int code = -1;
        String property = getMIUIVersion();
        try {
            property = property.trim().toUpperCase().replace("V", "");
            code = Integer.parseInt(property);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 判断是否为EMUI
     *
     * @return
     */
    public static boolean isEMUI() {
        String property = getSystemProperty("ro.build.version.emui", "");
        return !TextUtils.isEmpty(property);
    }

    /**
     * 获取EMUI的版本
     *
     * @return
     */
    public static String getEMUIVersion() {
        return isEMUI() ? getSystemProperty("ro.build.version.emui", "") : "";
    }

    /**
     * 判断是否为Flyme
     *
     * @return
     */
    public static boolean isFlyme() {
        return Build.DISPLAY.toLowerCase().contains("flyme");
    }

    /**
     * 获取Flyme的版本
     *
     * @return
     */
    public static String getFlymeVersion() {
        return isFlyme() ? Build.DISPLAY : "";
    }

    /**
     * 获取Flyme版本号
     *
     * @return
     */
    public static int getFlymeVersionCode() {
        int code = 0;
        String version = getFlymeVersion();
        if (!TextUtils.isEmpty(version)) {
            if (version.toLowerCase().contains("os")) {
                code = Integer.valueOf(version.substring(9, 10));
            } else {
                code = Integer.valueOf(version.substring(6, 7));
            }
        }
        return code;
    }

    /**
     * 通过反射获取系统属性
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getSystemProperty(String key, String defaultValue) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String) get.invoke(clz, key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
