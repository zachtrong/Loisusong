package net.loisusong.loisusong;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

import io.realm.Realm;

public class LoisusongApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Hawk.init(this).build();
    }
}
