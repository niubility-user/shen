package com.xiaomi.push.service;

import android.app.Notification;
import android.content.Context;
import com.xiaomi.push.f4;
import com.xiaomi.push.y7;
import java.util.Map;

/* loaded from: classes11.dex */
public abstract class t {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract f4 a(Context context, int i2, String str, Map<String, String> map);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void b(y7 y7Var, Map<String, String> map, int i2, Notification notification);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void c(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean d(Context context, int i2, String str, Map<String, String> map);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean e(Map<String, String> map, int i2, Notification notification);
}
