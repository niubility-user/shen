package com.jd.dynamic.a;

import android.text.TextUtils;
import com.jd.dynamic.engine.jni.TypeConvertor;
import com.jd.dynamic.lib.utils.m;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class h {
    public static long a(long j2, Object obj) {
        String obj2;
        if (obj == null || m.T(j2)) {
            return TypeConvertor.makeUndefined(j2);
        }
        if (obj instanceof Number) {
            return TypeConvertor.makeNumber(j2, ((Number) obj).doubleValue());
        }
        if (obj instanceof Boolean) {
            return TypeConvertor.makeBoolean(j2, ((Boolean) obj).booleanValue());
        }
        if (obj instanceof JSONObject) {
            return TypeConvertor.makeFromJsonString(j2, obj.toString());
        }
        if (obj.getClass().isArray()) {
            String str = null;
            if (obj instanceof int[]) {
                str = Arrays.toString((int[]) obj);
            } else if (obj instanceof byte[]) {
                str = Arrays.toString((byte[]) obj);
            } else if (obj instanceof char[]) {
                str = Arrays.toString((char[]) obj);
            } else if (obj instanceof long[]) {
                str = Arrays.toString((long[]) obj);
            } else if (obj instanceof float[]) {
                str = Arrays.toString((float[]) obj);
            } else if (obj instanceof short[]) {
                str = Arrays.toString((short[]) obj);
            } else if (obj instanceof double[]) {
                str = Arrays.toString((double[]) obj);
            } else if (obj instanceof boolean[]) {
                str = Arrays.toString((boolean[]) obj);
            } else if (obj instanceof Object[]) {
                str = Arrays.toString((Object[]) obj);
            }
            if (!TextUtils.isEmpty(str)) {
                obj2 = c.b + str;
                return TypeConvertor.makeString(j2, obj2);
            }
        }
        obj2 = obj.toString();
        return TypeConvertor.makeString(j2, obj2);
    }

    public static Object b(long j2, long j3) {
        if (m.T(j2)) {
            return null;
        }
        i iVar = new i(j2, j3);
        iVar.o();
        Object c2 = c(iVar);
        iVar.p();
        return c2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.lang.String] */
    public static Object c(i iVar) {
        if (iVar == 0 || iVar.i()) {
            return null;
        }
        if (iVar.e()) {
            return Double.valueOf(iVar.k());
        }
        if (iVar.f()) {
            return Boolean.valueOf(iVar.l());
        }
        if (!iVar.g()) {
            if (iVar.h()) {
                return f.r(iVar.a, iVar.f1715c);
            }
            if (iVar.j()) {
                iVar = iVar.m();
                try {
                    return new JSONObject((String) iVar);
                } catch (Exception unused) {
                }
            }
            return iVar;
        }
        String n2 = iVar.n();
        if (!TextUtils.isEmpty(n2)) {
            if (n2.startsWith(c.f1709c)) {
                n2 = n2.replace(c.f1709c, "");
                try {
                    return new JSONObject(n2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else if (n2.startsWith(c.b)) {
                n2 = n2.replace(c.b, "");
                if (!TextUtils.isEmpty(n2)) {
                    try {
                        JSONArray jSONArray = new JSONArray(n2);
                        Object[] objArr = new Object[jSONArray.length()];
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            objArr[i2] = jSONArray.get(i2);
                        }
                        return objArr;
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
        return n2;
    }

    public static long[] d(long j2, Object... objArr) {
        if (objArr == null || m.T(j2)) {
            return new long[0];
        }
        long[] jArr = new long[objArr.length];
        for (int i2 = 0; i2 < objArr.length; i2++) {
            jArr[i2] = a(j2, objArr[i2]);
        }
        return jArr;
    }

    public static Object[] e(long j2, long... jArr) {
        if (jArr == null || jArr.length <= 0) {
            return new Object[0];
        }
        Object[] objArr = new Object[jArr.length];
        for (int i2 = 0; i2 < jArr.length; i2++) {
            objArr[i2] = b(j2, jArr[i2]);
        }
        return objArr;
    }
}
