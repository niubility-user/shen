package com.jingdong.manto.m.g1;

import android.os.Bundle;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.k.a;
import com.jingdong.union.common.config.UnionConstants;
import java.util.Calendar;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class g extends f {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements Runnable {
        final /* synthetic */ MantoCore a;
        final /* synthetic */ JSONObject b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13361c;
        final /* synthetic */ int d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ int f13362e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ int f13363f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f13364g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f13365h;

        /* renamed from: com.jingdong.manto.m.g1.g$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0564a implements a.InterfaceC0701a<String> {
            C0564a() {
            }

            @Override // com.jingdong.manto.widget.k.a.InterfaceC0701a
            public void a(String str) {
                Bundle bundle;
                g gVar;
                String str2;
                if (MantoStringUtils.isEmpty(str)) {
                    gVar = g.this;
                    bundle = null;
                    str2 = "fail";
                } else {
                    bundle = new Bundle();
                    bundle.putString("value", str);
                    gVar = g.this;
                    str2 = IMantoBaseModule.SUCCESS;
                }
                gVar.a(str2, bundle);
            }

            @Override // com.jingdong.manto.widget.k.a.InterfaceC0701a
            public void onCancel() {
                g.this.a("cancel", (Bundle) null);
            }
        }

        a(MantoCore mantoCore, JSONObject jSONObject, int i2, int i3, int i4, int i5, int i6, int i7) {
            this.a = mantoCore;
            this.b = jSONObject;
            this.f13361c = i2;
            this.d = i3;
            this.f13362e = i4;
            this.f13363f = i5;
            this.f13364g = i6;
            this.f13365h = i7;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.widget.k.f a = g.this.a(this.a);
            if (a == null) {
                return;
            }
            a.setHeaderText(this.b.optString("headerText"));
            com.jingdong.manto.widget.k.g gVar = (com.jingdong.manto.widget.k.g) g.this.a(com.jingdong.manto.widget.k.g.class);
            if (gVar == null) {
                gVar = new com.jingdong.manto.widget.k.g(a.getContext());
            }
            gVar.b(this.f13361c, this.d);
            gVar.a(this.f13362e, this.f13363f);
            gVar.c(this.f13364g, this.f13365h);
            gVar.a(new C0564a());
            a.a(gVar);
        }
    }

    private static int a(String str) {
        try {
            return Integer.parseInt(str, 10);
        } catch (Exception unused) {
            return -1;
        }
    }

    private static int[] b(String str) {
        if (MantoStringUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length != 2) {
            return null;
        }
        return new int[]{a(split[0]), a(split[1])};
    }

    @Override // com.jingdong.manto.m.g1.f
    void a(Bundle bundle, MantoCore mantoCore) {
        int[] iArr;
        int[] iArr2 = null;
        try {
            JSONObject jSONObject = new JSONObject(bundle.getString("params"));
            JSONObject optJSONObject = jSONObject.optJSONObject("range");
            if (optJSONObject != null) {
                iArr2 = b(optJSONObject.optString("start"));
                iArr = b(optJSONObject.optString("end"));
            } else {
                iArr = null;
            }
            int[] b = b(jSONObject.optString(UnionConstants.BUNDLE_CURRENT));
            if (iArr2 == null) {
                iArr2 = new int[]{0, 0};
            }
            if (iArr == null) {
                iArr = new int[]{23, 59};
            }
            if (b == null) {
                Calendar calendar = Calendar.getInstance();
                b = new int[]{calendar.get(10), calendar.get(12)};
            }
            MantoUtils.runOnUiThread(new a(mantoCore, jSONObject, Math.min(Math.max(0, iArr2[0]), 23), Math.min(Math.max(0, iArr2[1]), 59), Math.min(Math.max(0, iArr[0]), 23), Math.min(Math.max(0, iArr[1]), 59), Math.min(Math.max(0, b[0]), 23), Math.min(Math.max(0, b[1]), 59)));
        } catch (Exception unused) {
            a("fail", (Bundle) null);
        }
    }
}
