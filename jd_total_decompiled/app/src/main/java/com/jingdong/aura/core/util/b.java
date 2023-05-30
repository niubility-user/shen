package com.jingdong.aura.core.util;

import android.content.SharedPreferences;
import com.jingdong.aura.a.c.l;

/* loaded from: classes4.dex */
public class b {
    private static volatile SharedPreferences a;

    private b() {
    }

    public static SharedPreferences a() {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = l.a.getSharedPreferences("aura_config", 0);
                }
            }
        }
        return a;
    }
}
