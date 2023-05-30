package com.jd.libs.hybrid.offlineload.db.converter;

import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.room.TypeConverter;
import com.jd.framework.json.JDJSON;
import java.util.Date;
import java.util.Map;

@Keep
/* loaded from: classes16.dex */
public class RoomDateConverts {
    @TypeConverter
    public Map<String, String> fromString(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (Map) JDJSON.parseObject(str, Map.class);
    }

    @TypeConverter
    public String fromStringMap(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        return JDJSON.toJSON(map).toString();
    }

    @TypeConverter
    public Date toDate(Long l2) {
        if (l2 == null) {
            return null;
        }
        return new Date(l2.longValue());
    }

    @TypeConverter
    public Long toTimeStamp(Date date) {
        if (date == null) {
            return null;
        }
        return Long.valueOf(date.getTime());
    }
}
