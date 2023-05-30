package com.jd.lib.productdetail.mainimage.holder.dym;

import android.text.TextUtils;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.jingdong.sdk.platform.floor.isv.FloorCooperateManager;
import java.util.HashMap;

/* loaded from: classes.dex */
public class PdMCooperManager implements LifecycleObserver {

    /* renamed from: h  reason: collision with root package name */
    public static HashMap<String, FloorCooperateManager> f4811h = new HashMap<>();

    /* renamed from: g  reason: collision with root package name */
    public String f4812g;

    public PdMCooperManager(String str, String str2) {
        this.f4812g = str;
        f4811h.put(this.f4812g, new FloorCooperateManager(str2));
    }

    public static FloorCooperateManager a(String str) {
        if (!TextUtils.isEmpty(str) && f4811h.containsKey(str)) {
            return f4811h.get(str);
        }
        return null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        f4811h.remove(this.f4812g);
    }
}
