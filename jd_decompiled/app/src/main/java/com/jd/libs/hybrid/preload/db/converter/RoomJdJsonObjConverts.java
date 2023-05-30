package com.jd.libs.hybrid.preload.db.converter;

import androidx.annotation.Keep;
import androidx.room.TypeConverter;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;

@Keep
/* loaded from: classes16.dex */
public class RoomJdJsonObjConverts {
    @TypeConverter
    public JDJSONObject toJson(String str) {
        return JDJSON.parseObject(str);
    }

    @TypeConverter
    public String toString(JDJSONObject jDJSONObject) {
        return jDJSONObject == null ? "{}" : jDJSONObject.toJSONString();
    }
}
