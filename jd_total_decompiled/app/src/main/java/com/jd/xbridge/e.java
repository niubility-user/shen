package com.jd.xbridge;

import android.os.Handler;
import android.os.Looper;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public final class e {
    public static final e b = new e();
    private static final Handler a = new Handler(Looper.getMainLooper());

    private e() {
    }

    @NotNull
    public final JSONArray a(@NotNull Object obj) {
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < length; i2++) {
                jSONArray.put(b(Array.get(obj, i2)));
            }
            return jSONArray;
        }
        throw new JSONException("Not a primitive array: " + obj.getClass());
    }

    @Nullable
    public final Object b(@Nullable Object obj) {
        String name;
        boolean startsWith$default;
        if (obj == null) {
            return JSONObject.NULL;
        }
        if ((obj instanceof JSONArray) || (obj instanceof JSONObject) || Intrinsics.areEqual(obj, JSONObject.NULL)) {
            return obj;
        }
        if (obj instanceof Collection) {
            return new JSONArray((Collection) obj);
        }
        if (obj.getClass().isArray()) {
            return a(obj);
        }
        if (obj instanceof Map) {
            return new JSONObject((Map) obj);
        }
        if (!(obj instanceof Boolean) && !(obj instanceof Byte) && !(obj instanceof Character) && !(obj instanceof Double) && !(obj instanceof Float) && !(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Short) && !(obj instanceof String)) {
            Package r1 = obj.getClass().getPackage();
            if (r1 != null && (name = r1.getName()) != null) {
                startsWith$default = StringsKt__StringsJVMKt.startsWith$default(name, "java.", false, 2, null);
                if (startsWith$default) {
                    return obj.toString();
                }
            }
            return null;
        }
        return obj;
    }

    public final void c(@Nullable Handler handler, @NotNull Runnable runnable) {
        Thread thread;
        Looper looper;
        if (handler == null || (looper = handler.getLooper()) == null || (thread = looper.getThread()) == null) {
            Looper looper2 = a.getLooper();
            Intrinsics.checkExpressionValueIsNotNull(looper2, "this.mainHandler.looper");
            thread = looper2.getThread();
        }
        Intrinsics.checkExpressionValueIsNotNull(thread, "mainHandler?.looper?.thr\u2026mainHandler.looper.thread");
        if (Intrinsics.areEqual(Thread.currentThread(), thread)) {
            runnable.run();
        } else if (handler == null || !handler.post(runnable)) {
            a.post(runnable);
        }
    }

    @NotNull
    public final String d(@NotNull String str) {
        String replace$default;
        String replace$default2;
        String replace$default3;
        String replace$default4;
        String replace$default5;
        String replace$default6;
        String replace$default7;
        if (str.length() == 0) {
            return str;
        }
        replace$default = StringsKt__StringsJVMKt.replace$default(str, "\\", "\\\\", false, 4, (Object) null);
        replace$default2 = StringsKt__StringsJVMKt.replace$default(replace$default, "\"", "\\\"", false, 4, (Object) null);
        replace$default3 = StringsKt__StringsJVMKt.replace$default(replace$default2, "'", "\\'", false, 4, (Object) null);
        replace$default4 = StringsKt__StringsJVMKt.replace$default(replace$default3, ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "\\n", false, 4, (Object) null);
        replace$default5 = StringsKt__StringsJVMKt.replace$default(replace$default4, "\r", "\\r", false, 4, (Object) null);
        replace$default6 = StringsKt__StringsJVMKt.replace$default(replace$default5, "\u2028", "\\u2028", false, 4, (Object) null);
        replace$default7 = StringsKt__StringsJVMKt.replace$default(replace$default6, "\u2029", "\\u2029", false, 4, (Object) null);
        return replace$default7;
    }
}
