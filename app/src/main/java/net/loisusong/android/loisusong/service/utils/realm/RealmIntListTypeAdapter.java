package net.loisusong.android.loisusong.service.utils.realm;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import io.realm.RealmList;

/**
 * Created by android on 1/29/18.
 */

public class RealmIntListTypeAdapter extends TypeAdapter<RealmList<RealmInt>> {

    public static final TypeAdapter<RealmList<RealmInt>> INSTANCE =
            new RealmIntListTypeAdapter().nullSafe();

    private RealmIntListTypeAdapter() {

    }

    @Override
    public void write(JsonWriter out, RealmList<RealmInt> value) throws IOException {
        out.beginArray();
        for (RealmInt realmInt : value) {
            out.value(realmInt.value);
        }
        out.endArray();
    }

    @Override
    public RealmList<RealmInt> read(JsonReader in) throws IOException {
        RealmList<RealmInt> realmInts = new RealmList<>();
        in.beginArray();
        while (in.hasNext()) {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
            } else {
                RealmInt realmInt = new RealmInt();
                realmInt.value = in.nextInt();
                realmInts.add(realmInt);
            }
        }
        in.endArray();
        return realmInts;
    }
}
