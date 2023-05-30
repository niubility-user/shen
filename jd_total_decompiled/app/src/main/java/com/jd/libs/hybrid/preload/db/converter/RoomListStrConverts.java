package com.jd.libs.hybrid.preload.db.converter;

import androidx.annotation.Keep;
import androidx.room.TypeConverter;
import com.jd.framework.json.JDJSON;
import java.util.Collections;
import java.util.List;

@Keep
/* loaded from: classes16.dex */
public class RoomListStrConverts {
    @TypeConverter
    public List<String> toArray(String str) {
        try {
            return JDJSON.parseArray(str, String.class);
        } catch (Exception unused) {
            return Collections.emptyList();
        }
    }

    @TypeConverter
    public String toString(List<String> list) {
        return JDJSON.toJSONString(list);
    }
}
