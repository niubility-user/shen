package com.jingdong.jdma.bean.e;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes12.dex */
public class a {
    private JSONArray a = new JSONArray();
    private int b = 3;

    /* renamed from: c  reason: collision with root package name */
    private int f12641c = 10;
    private boolean d = true;

    public void a(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                this.a = new JSONArray(str);
            } else {
                this.a = new JSONArray();
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void b(int i2) {
        if (i2 > 0) {
            this.b = i2;
        }
    }

    public void c(int i2) {
        if (this.f12641c > 0) {
            this.f12641c = i2;
        }
    }

    public int b() {
        return this.f12641c;
    }

    public boolean c() {
        return this.d;
    }

    public int a() {
        return this.b;
    }

    public boolean a(int i2) {
        try {
            int length = this.a.length();
            for (int i3 = 0; i3 < length; i3++) {
                if (this.a.getInt(i3) == i2) {
                    return true;
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public void a(boolean z) {
        this.d = z;
    }
}
