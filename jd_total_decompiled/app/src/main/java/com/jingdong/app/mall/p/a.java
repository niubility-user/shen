package com.jingdong.app.mall.p;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: e  reason: collision with root package name */
    private static a f11458e;
    private c b;
    private Context d;
    private final String a = a.class.getSimpleName();

    /* renamed from: c  reason: collision with root package name */
    private ArrayList<b> f11459c = new ArrayList<>();

    private a() {
    }

    public static a a() {
        if (f11458e == null) {
            synchronized (a.class) {
                if (f11458e == null) {
                    f11458e = new a();
                }
            }
        }
        return f11458e;
    }

    private void b() {
        try {
            if (Build.VERSION.SDK_INT >= 25) {
                String config = JDMobileConfig.getInstance().getConfig("JDOrderCenter", "Order-3DTouch", "configs");
                if (TextUtils.isEmpty(config)) {
                    return;
                }
                d(new JSONObjectProxy(new JSONObject(config)));
            }
        } catch (Exception unused) {
        }
    }

    public void c(Context context) {
        if (Build.VERSION.SDK_INT < 25) {
            return;
        }
        this.d = context;
        b();
    }

    @RequiresApi(api = 25)
    public void d(JSONObjectProxy jSONObjectProxy) {
        if (Log.D) {
            Log.d(this.a, "saveKvConfig-config-->> " + jSONObjectProxy);
        }
        if (jSONObjectProxy == null) {
            return;
        }
        try {
            this.b = new c(this.d);
            JSONArray optJSONArray = jSONObjectProxy.optJSONArray("pop");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                int length = optJSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    if (optJSONObject != null) {
                        this.f11459c.add(new b(optJSONObject));
                    }
                }
                this.b.a(this.f11459c);
                return;
            }
            this.b.b();
        } catch (Throwable th) {
            if (Log.E) {
                Log.e(this.a, "throwable==>" + th);
            }
        }
    }
}
