package net.loisusong.loisusong.service.utils;

import android.util.Log;

import net.loisusong.loisusong.service.constant.Debug;

public final class LogUtils {
    public static void d(String msg) {
        if (Debug.DEBUG)
            Log.d(Debug.TAG, msg);
    }

    public static void v(String msg) {
        if (Debug.DEBUG)
            Log.v(Debug.TAG, msg);
    }

    public static void e(String msg) {
        if (Debug.DEBUG)
            Log.e(Debug.TAG, msg);
    }

    public static void i(String msg) {
        if (Debug.DEBUG)
            Log.i(Debug.TAG, msg);
    }

    public static void w(String msg) {
        if (Debug.DEBUG)
            Log.w(Debug.TAG, msg);
    }

    public static void wtf(String msg) {
        if (Debug.DEBUG)
            Log.wtf(Debug.TAG, msg);
    }
}
