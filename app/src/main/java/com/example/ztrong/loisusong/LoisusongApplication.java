package com.example.ztrong.loisusong;

import android.app.Application;

import io.realm.Realm;

public class LoisusongApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
