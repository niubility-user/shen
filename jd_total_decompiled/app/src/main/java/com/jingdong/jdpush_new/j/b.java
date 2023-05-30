package com.jingdong.jdpush_new.j;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class b {
    private static final String b = "b";

    /* renamed from: c  reason: collision with root package name */
    private static b f12853c;
    private volatile HashMap<Integer, String> a = new HashMap<>();

    public static b a() {
        if (f12853c == null) {
            synchronized (b.class) {
                if (f12853c == null) {
                    f12853c = new b();
                }
            }
        }
        return f12853c;
    }

    public void b(Context context, String str) {
        com.jingdong.jdpush_new.g.a b2 = com.jingdong.jdpush_new.g.a.b(str);
        if (b2 != null) {
            try {
                JSONObject jSONObject = new JSONObject(b2.a());
                String optString = jSONObject.optString(NotificationMessageSummary.BUSINESS_TYPE);
                String optString2 = jSONObject.optString("content");
                if (TextUtils.isEmpty(optString)) {
                    return;
                }
                int intValue = Integer.valueOf(optString).intValue();
                if (TextUtils.isEmpty(this.a.get(Integer.valueOf(intValue)))) {
                    return;
                }
                Class<?> cls = Class.forName(this.a.get(Integer.valueOf(intValue)));
                Object newInstance = cls.newInstance();
                Method declaredMethod = cls.getDeclaredMethod("onMessageEvent" + intValue, Context.class, String.class);
                declaredMethod.setAccessible(true);
                g.d(b, declaredMethod.toString());
                declaredMethod.invoke(newInstance, context, optString2);
            } catch (Exception e2) {
                g.g(e2);
            }
        }
    }

    public void c(int i2, Class<?> cls) {
        this.a.put(Integer.valueOf(i2), cls.getName());
    }
}
