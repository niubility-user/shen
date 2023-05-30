package com.tencent.mapsdk.internal;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;

/* loaded from: classes9.dex */
public class ha {

    /* loaded from: classes9.dex */
    public static class a {
        private SharedPreferences.Editor a;

        public a(SharedPreferences.Editor editor) {
            this.a = editor;
        }

        public void a(String str, float f2) {
            this.a.putFloat(str, f2);
            this.a.commit();
        }

        public void a(String str, int i2) {
            this.a.putInt(str, i2);
            this.a.commit();
        }

        public void a(String str, long j2) {
            this.a.putLong(str, j2);
            this.a.commit();
        }

        public void a(String str, String str2) {
            this.a.putString(str, str2);
            this.a.commit();
        }

        public void a(String str, Set<String> set) {
            this.a.putStringSet(str, set);
            this.a.commit();
        }

        public void a(String str, boolean z) {
            this.a.putBoolean(str, z);
            this.a.commit();
        }
    }

    public static SharedPreferences a(Context context, String str) {
        if (context != null) {
            return context.getSharedPreferences("TMS_" + str, 0);
        }
        return null;
    }

    public static a a(SharedPreferences sharedPreferences) {
        return new a(sharedPreferences.edit());
    }
}
