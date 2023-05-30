package com.jingdong.app.mall.bundle.marketing_sdk.contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactListener;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class c {
    private static boolean a;

    /* loaded from: classes.dex */
    static class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Activity f8221g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Intent f8222h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ IContactListener f8223i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ JSONObject f8224j;

        a(Activity activity, Intent intent, IContactListener iContactListener, JSONObject jSONObject) {
            this.f8221g = activity;
            this.f8222h = intent;
            this.f8223i = iContactListener;
            this.f8224j = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                boolean unused = c.a = true;
                JSONArray b = com.jingdong.app.mall.bundle.marketing_sdk.b.b((Context) new WeakReference(this.f8221g).get(), this.f8222h);
                if (b != null && b.length() > 0) {
                    if (this.f8221g.isFinishing()) {
                        boolean unused2 = c.a = false;
                        return;
                    }
                    boolean unused3 = c.a = false;
                    if (b != null) {
                        this.f8224j.put("contacts", b);
                    }
                    this.f8223i.updateResult(this.f8224j);
                    return;
                }
                boolean unused4 = c.a = false;
                this.f8223i.updateResult(this.f8224j);
            } catch (Exception e2) {
                boolean unused5 = c.a = false;
                this.f8223i.updateResult(this.f8224j);
                e2.printStackTrace();
            }
        }
    }

    public static void a(Activity activity, Intent intent, IContactListener iContactListener) {
        JSONObject jSONObject = new JSONObject();
        if (iContactListener == null) {
            return;
        }
        try {
            if (!activity.isFinishing() && !a) {
                new Thread(new a(activity, intent, iContactListener, jSONObject)).start();
            }
        } catch (Exception unused) {
            a = false;
        }
    }
}
