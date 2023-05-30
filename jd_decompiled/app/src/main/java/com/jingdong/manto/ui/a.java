package com.jingdong.manto.ui;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import com.jingdong.manto.MantoCore;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class a implements ComponentCallbacks2 {
    private final Reference<MantoCore> a;

    public a(MantoCore mantoCore) {
        this.a = new SoftReference(mantoCore);
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i2) {
        MantoCore mantoCore = this.a.get();
        if (mantoCore == null || mantoCore.isFinishing()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("level", i2);
        } catch (JSONException unused) {
        }
        if (i2 == 5 || i2 == 10 || i2 == 15) {
            mantoCore.dispatchEevent("onMemoryWarningReceive", jSONObject, 0);
        }
    }
}
