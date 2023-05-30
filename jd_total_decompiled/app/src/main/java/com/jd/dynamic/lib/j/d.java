package com.jd.dynamic.lib.j;

import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.utils.t;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;

/* loaded from: classes13.dex */
public class d extends AsyncTask<Void, Void, Void> implements LifecycleEventObserver {

    /* renamed from: g */
    private DynamicTemplateEngine f2253g;

    /* renamed from: h */
    private int f2254h;

    /* renamed from: i */
    private long f2255i = System.nanoTime();

    /* renamed from: j */
    private JSONArray f2256j;

    public d(DynamicTemplateEngine dynamicTemplateEngine, int i2) {
        this.f2253g = dynamicTemplateEngine;
        this.f2254h = i2;
    }

    @Override // android.os.AsyncTask
    /* renamed from: a */
    public Void doInBackground(Void... voidArr) {
        try {
            for (Map.Entry<ViewNode, HashMap<String, String>> entry : this.f2253g.getUnbindMap().entrySet()) {
                ViewNode key = entry.getKey();
                if (key != null && this.f2254h == key.viewId) {
                    String str = entry.getValue().get("data");
                    ViewNode viewNode = new ViewNode();
                    if (this.f2256j == null) {
                        DynamicTemplateEngine dynamicTemplateEngine = this.f2253g;
                        dynamicTemplateEngine.newBindDataWithEL(viewNode, dynamicTemplateEngine.currentData, str, "data");
                        String str2 = viewNode.getELAttributes().get("data");
                        if (!TextUtils.isEmpty(str2)) {
                            this.f2256j = new JSONArray(str2);
                        }
                    }
                    JSONArray jSONArray = this.f2256j;
                    if (jSONArray != null) {
                        c cVar = new c(key, this.f2253g, jSONArray);
                        for (int i2 = 0; i2 < cVar.a(); i2++) {
                            long nanoTime = System.nanoTime();
                            cVar.b(i2);
                            t.e("PreParseTask", "layoutId = " + this.f2254h, "preParseItem", "time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime));
                        }
                    }
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // android.os.AsyncTask
    /* renamed from: b */
    public void onPostExecute(Void r6) {
        t.e("PreParseTask", "layoutId = " + this.f2254h, "onPostExecute", "time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - this.f2255i));
    }

    public void c(JSONArray jSONArray) {
        this.f2256j = jSONArray;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        t.e("PreParseTask", "onPreExecute");
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            try {
                t.e("PreParseTask", "ON_DESTROY", "cancel");
                cancel(true);
                this.f2253g.clearItemLocks();
            } catch (Exception unused) {
            }
        }
    }
}
