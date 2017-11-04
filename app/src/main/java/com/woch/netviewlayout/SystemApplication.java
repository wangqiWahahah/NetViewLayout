package com.woch.netviewlayout;

import android.app.Application;

/**
 * Created by wangdake on 2017/11/3.
 */

public class SystemApplication extends Application {

    private static SystemApplication systemApplication;

    public static SystemApplication getInstance(){

        return systemApplication;

    }

    @Override
    public void onCreate() {
        super.onCreate();

        systemApplication = this;

    }
}
