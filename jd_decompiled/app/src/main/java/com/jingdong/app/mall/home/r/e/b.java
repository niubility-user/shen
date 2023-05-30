package com.jingdong.app.mall.home.r.e;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;

/* loaded from: classes4.dex */
public class b {
    public static final String IMG_EMPTY = "http://imgEmpty";
    public JDJSONObject srcJson;

    public b(JDJSONObject jDJSONObject) {
        this.srcJson = jDJSONObject;
    }

    public static int[] getColor(String str, int[] iArr) {
        return m.n(str, iArr, true);
    }

    public static JDJSONArray getJsonArr(JDJSONObject jDJSONObject, String str) {
        if (jDJSONObject == null) {
            return null;
        }
        try {
            return jDJSONObject.getJSONArray(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean getJsonBoolean(JDJSONObject jDJSONObject, String str, boolean z) {
        return jDJSONObject == null ? z : JDJSONObjectWithDefaultUtils.getJSONBooleanWithDefault(jDJSONObject, str, z);
    }

    public static int getJsonInt(JDJSONObject jDJSONObject, String str, int i2) {
        return jDJSONObject == null ? i2 : JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, str, i2);
    }

    public static long getJsonLong(JDJSONObject jDJSONObject, String str, long j2) {
        return jDJSONObject == null ? j2 : JDJSONObjectWithDefaultUtils.getJSONLongWithDefault(jDJSONObject, str, j2);
    }

    public static JDJSONObject getJsonObject(JDJSONObject jDJSONObject, String str) {
        if (jDJSONObject != null) {
            try {
            } catch (Exception unused) {
                return null;
            }
        }
        return jDJSONObject.getJSONObject(str);
    }

    public static String getJsonString(JDJSONObject jDJSONObject, String str, String str2) {
        return jDJSONObject == null ? str2 : JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, str, str2);
    }

    public static <T> T getObject(JDJSONObject jDJSONObject, String str, Class<T> cls) {
        if (jDJSONObject == null) {
            return null;
        }
        return (T) jDJSONObject.getObject(str, cls);
    }

    public static int[] getColor(String str, int i2) {
        return m.o(str, i2);
    }

    public boolean getJsonBoolean(String str, boolean z) {
        return getJsonBoolean(this.srcJson, str, z);
    }

    public int getJsonInt(String str, int i2) {
        return getJsonInt(this.srcJson, str, i2);
    }

    public long getJsonLong(String str, long j2) {
        return getJsonLong(this.srcJson, str, j2);
    }

    public JDJSONObject getJsonObject(String str) {
        return getJsonObject(this.srcJson, str);
    }

    public String getJsonString(String str, String str2) {
        return getJsonString(this.srcJson, str, str2);
    }

    public <T> T getObject(String str, Class<T> cls) {
        return (T) getObject(this.srcJson, str, cls);
    }

    public JDJSONArray getJsonArr(String str) {
        return getJsonArr(this.srcJson, str);
    }

    public boolean getJsonBoolean(String str) {
        return getJsonBoolean(str, false);
    }

    public int getJsonInt(String str) {
        return getJsonInt(str, 0);
    }

    public long getJsonLong(String str) {
        return getJsonLong(str, 0L);
    }

    public String getJsonString(String str) {
        return getJsonString(str, "");
    }
}
