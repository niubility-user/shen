package com.jingdong.pdj.libcore.point;

import android.text.TextUtils;
import android.util.Log;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class HourlyFloorMaiDianJson extends JSONObject {
    public HourlyFloorMaiDianJson() {
    }

    public static HourlyFloorMaiDianJson build(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return new HourlyFloorMaiDianJson(str);
            }
        } catch (Exception e2) {
            OKLog.e("HourlyFloorMaiDianJson", Log.getStackTraceString(e2));
        }
        return new HourlyFloorMaiDianJson();
    }

    public static JDJSONArray buildElementInJsonArray(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null) {
            return new JDJSONArray();
        }
        for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
            try {
                Object obj = jDJSONArray.get(i2);
                if (obj instanceof String) {
                    Object obj2 = null;
                    try {
                        obj2 = JDJSON.parse((String) obj);
                    } catch (Exception unused) {
                    }
                    if (obj2 instanceof JDJSONObject) {
                        jDJSONArray.set(i2, buildElementInJsonObject((JDJSONObject) obj2));
                    }
                }
            } catch (Exception e2) {
                OKLog.e("HourlyFloorMaiDianJson", Log.getStackTraceString(e2));
            }
        }
        return jDJSONArray;
    }

    public static JDJSONObject buildElementInJsonObject(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return new JDJSONObject();
        }
        try {
            Set<Map.Entry<String, Object>> entrySet = jDJSONObject.entrySet();
            if (entrySet != null) {
                for (Map.Entry<String, Object> entry : entrySet) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        Object obj = null;
                        try {
                            obj = JDJSON.parse((String) value);
                        } catch (Exception unused) {
                        }
                        if ((obj instanceof JDJSONObject) || (obj instanceof JDJSONArray)) {
                            jDJSONObject.put(key, obj);
                        }
                    }
                }
            }
            return jDJSONObject;
        } catch (Exception e2) {
            OKLog.e("HourlyFloorMaiDianJson", Log.getStackTraceString(e2));
            return jDJSONObject;
        }
    }

    public static String buildElementToJson(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                Object obj = null;
                try {
                    obj = JDJSON.parse(str);
                } catch (Exception unused) {
                }
                if (obj instanceof JDJSONObject) {
                    return buildElementInJsonObject((JDJSONObject) obj).toJSONString();
                }
                if (obj instanceof JDJSONArray) {
                    return buildElementInJsonArray((JDJSONArray) obj).toJSONString();
                }
            }
        } catch (Exception e2) {
            OKLog.e("HourlyFloorMaiDianJson", Log.getStackTraceString(e2));
        }
        return str;
    }

    public JSONObject addInfo(String str, Object obj) {
        return put(str, obj);
    }

    @Override // org.json.JSONObject
    public JSONObject put(String str, Object obj) {
        try {
            return super.put(str, obj);
        } catch (Exception e2) {
            OKLog.e("HourlyFloorMaiDianJson", Log.getStackTraceString(e2));
            return this;
        }
    }

    private HourlyFloorMaiDianJson(String str) throws JSONException {
        super(str);
    }

    @Override // org.json.JSONObject
    public JSONObject put(String str, int i2) {
        try {
            return super.put(str, i2);
        } catch (Exception e2) {
            OKLog.e("HourlyFloorMaiDianJson", Log.getStackTraceString(e2));
            return this;
        }
    }
}
