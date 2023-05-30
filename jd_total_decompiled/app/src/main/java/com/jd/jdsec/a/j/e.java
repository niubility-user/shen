package com.jd.jdsec.a.j;

import com.jd.jdsec.c.g;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class e {
    /* JADX WARN: Removed duplicated region for block: B:177:0x0152 A[Catch: all -> 0x019d, LOOP:0: B:176:0x0150->B:177:0x0152, LOOP_END, TRY_ENTER, TryCatch #5 {all -> 0x019d, blocks: (B:174:0x0143, B:177:0x0152, B:178:0x016a), top: B:203:0x0143 }] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x01c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static float[] a(float[] fArr, String str, String str2, JSONObject jSONObject) throws Exception {
        String str3;
        Method method;
        float[] fArr2;
        String str4;
        String str5;
        float[] fArr3;
        float[] fArr4 = new float[0];
        String[] strArr = new String[3];
        String str6 = str + "device_model.pb.enc";
        com.jd.jdsec.a.l.b.e("\u5f00\u59cb\u63a8\u7406 - \u6a21\u578b\u5730\u5740", str);
        com.jd.jdsec.a.l.b.e("input_vec \u5165\u53c2\u957f\u5ea6", String.valueOf(fArr.length));
        Class<?> loadClass = g.a.getClassLoader().loadClass("com.jd.lib.device_model_compute.DMModelPlugin");
        Object newInstance = loadClass.getConstructor(new Class[0]).newInstance(new Object[0]);
        Method method2 = loadClass.getMethod("loadEncModel", String.class, String.class);
        Method method3 = loadClass.getMethod("isStatusOK", new Class[0]);
        Method method4 = loadClass.getMethod("runWithFloatArray", float[].class);
        Method method5 = loadClass.getMethod("close", new Class[0]);
        Field field = loadClass.getField("errorCodeStr");
        Field field2 = loadClass.getField("logMsg");
        Field field3 = loadClass.getField("errorStackTrace");
        try {
            method2.invoke(newInstance, str6, str2);
            if (((Boolean) method3.invoke(newInstance, new Object[0])).booleanValue()) {
                jSONObject.put("input_vec", Arrays.toString(fArr));
                jSONObject.put("model_isinferstart", "1");
                str3 = str6;
                try {
                    try {
                        jSONObject.put("model_inferstartts", System.currentTimeMillis());
                        fArr3 = (float[]) method4.invoke(newInstance, fArr);
                        try {
                            jSONObject.put("model_isinferend", "1");
                            jSONObject.put("model_inferendts", System.currentTimeMillis());
                            jSONObject.put("output_vec", Arrays.toString(fArr3));
                        } catch (Exception unused) {
                            fArr2 = fArr3;
                            method = method5;
                            try {
                                field3.setAccessible(true);
                                String str7 = "";
                                while (r11 < r4) {
                                }
                                strArr[2] = str7;
                                jSONObject.put("model_log", Arrays.toString(strArr));
                                str5 = str + "status";
                                if (b.l(str3)) {
                                }
                                if (b.l(str5)) {
                                }
                                method.invoke(newInstance, new Object[0]);
                                return fArr2;
                            } catch (Throwable th) {
                                th = th;
                                jSONObject.put("model_log", Arrays.toString(strArr));
                                str4 = str + "status";
                                if (b.l(str3)) {
                                }
                                if (b.l(str4)) {
                                }
                                method.invoke(newInstance, new Object[0]);
                                throw th;
                            }
                        }
                    } catch (Exception unused2) {
                        method = method5;
                        fArr2 = fArr4;
                        field3.setAccessible(true);
                        String str72 = "";
                        for (StackTraceElement stackTraceElement : (StackTraceElement[]) field3.get(newInstance)) {
                            str72 = str72 + stackTraceElement.toString();
                        }
                        strArr[2] = str72;
                        jSONObject.put("model_log", Arrays.toString(strArr));
                        str5 = str + "status";
                        if (b.l(str3)) {
                            d.d(str3);
                        }
                        if (b.l(str5)) {
                            d.d(str5);
                        }
                        method.invoke(newInstance, new Object[0]);
                        return fArr2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    method = method5;
                    jSONObject.put("model_log", Arrays.toString(strArr));
                    str4 = str + "status";
                    if (b.l(str3)) {
                        d.d(str3);
                    }
                    if (b.l(str4)) {
                        d.d(str4);
                    }
                    method.invoke(newInstance, new Object[0]);
                    throw th;
                }
            } else {
                str3 = str6;
                fArr3 = fArr4;
            }
        } catch (Exception unused3) {
            str3 = str6;
        } catch (Throwable th3) {
            th = th3;
            str3 = str6;
        }
        try {
            strArr[0] = (String) field2.get(newInstance);
            strArr[1] = (String) field.get(newInstance);
            jSONObject.put("model_log", Arrays.toString(strArr));
            String str8 = str + "status";
            if (b.l(str3)) {
                d.d(str3);
            }
            if (b.l(str8)) {
                d.d(str8);
            }
            method5.invoke(newInstance, new Object[0]);
            return fArr3;
        } catch (Exception unused4) {
            method = method5;
            fArr2 = fArr3;
            field3.setAccessible(true);
            String str722 = "";
            while (r11 < r4) {
            }
            strArr[2] = str722;
            jSONObject.put("model_log", Arrays.toString(strArr));
            str5 = str + "status";
            if (b.l(str3)) {
            }
            if (b.l(str5)) {
            }
            method.invoke(newInstance, new Object[0]);
            return fArr2;
        }
    }
}
