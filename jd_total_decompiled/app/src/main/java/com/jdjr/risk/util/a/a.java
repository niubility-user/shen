package com.jdjr.risk.util.a;

import android.content.Context;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class a {
    private static volatile a b;
    private ReadWriteLock a = new ReentrantReadWriteLock();

    private a(Context context) {
    }

    public static a a(Context context) {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a(context);
                }
            }
        }
        return b;
    }

    public void a(Context context, String str, JSONObject jSONObject) {
        try {
            this.a.writeLock().lock();
            jSONObject.put("expireTime", System.currentTimeMillis() + jSONObject.optLong("expireTime"));
            com.jdjr.risk.util.b.d.a(context, jSONObject.toString(), "common");
            this.a.writeLock().unlock();
        } catch (Exception unused) {
        }
    }

    public byte[] a(Context context, String str) {
        byte[] bArr = null;
        try {
            this.a.readLock().lock();
            bArr = com.jdjr.risk.util.b.d.a(context, "common");
            this.a.readLock().unlock();
        } catch (Exception unused) {
        }
        return bArr;
    }
}
