package net.loisusong.loisusong.service.utils.realm;

import io.realm.RealmConfiguration;

/**
 * Created by android on 1/29/18.
 */

public class RealmConfigs {
    public static RealmConfiguration getConfig(String name) {
        return new RealmConfiguration.Builder()
                .name(name)
                .build();
    }
}
