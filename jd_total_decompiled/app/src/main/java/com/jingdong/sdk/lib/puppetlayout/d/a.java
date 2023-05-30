package com.jingdong.sdk.lib.puppetlayout.d;

import android.view.View;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Action;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class a {
    private HashMap<View, HashMap<String, String>> a = new HashMap<>();
    public ArrayList<Action> b;

    public void a(com.jingdong.sdk.lib.puppetlayout.h.a aVar, HashMap<String, String> hashMap) {
        if (aVar == null || aVar.h() == null || hashMap == null) {
            return;
        }
        this.a.put(aVar.h(), hashMap);
    }

    public HashMap<View, HashMap<String, String>> b() {
        return this.a;
    }
}
