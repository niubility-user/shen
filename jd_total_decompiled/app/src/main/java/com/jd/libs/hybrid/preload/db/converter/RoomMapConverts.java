package com.jd.libs.hybrid.preload.db.converter;

import androidx.annotation.Keep;
import androidx.room.TypeConverter;
import com.jd.framework.json.JDJSON;
import java.util.Map;

@Keep
/* loaded from: classes16.dex */
public class RoomMapConverts {
    @TypeConverter
    public Map<String, String> toMap(String str) {
        return (Map) JDJSON.parseObject(str, Map.class);
    }

    @TypeConverter
    public String toString(Map<String, String> map) {
        return JDJSON.toJSONString(map);
    }
}
