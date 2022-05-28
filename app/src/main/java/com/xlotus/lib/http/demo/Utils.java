package com.xlotus.lib.http.demo;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

public class Utils {
    public static String getModel() {
        return Build.MODEL;
    }

    public static String getOs() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    public static String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
