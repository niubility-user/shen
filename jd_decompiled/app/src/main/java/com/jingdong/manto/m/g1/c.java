package com.jingdong.manto.m.g1;

import android.os.Bundle;
import android.widget.FrameLayout;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.widget.k.a;
import com.jingdong.manto.widget.k.b;
import com.jingdong.union.common.config.UnionConstants;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class c extends f {

    /* renamed from: f  reason: collision with root package name */
    private AtomicReference<b.C0702b[]> f13353f = new AtomicReference<>();

    /* renamed from: g  reason: collision with root package name */
    int f13354g;

    /* renamed from: h  reason: collision with root package name */
    String f13355h;

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ MantoCore a;

        a(MantoCore mantoCore) {
            this.a = mantoCore;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.b(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements b.c {
        final /* synthetic */ MantoCore a;

        b(MantoCore mantoCore) {
            this.a = mantoCore;
        }

        @Override // com.jingdong.manto.widget.k.b.c
        public void a(int i2, int i3) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("errMsg", IMantoBaseModule.SUCCESS);
                jSONObject.put("column", i2);
                jSONObject.put(UnionConstants.BUNDLE_CURRENT, i3);
            } catch (Exception unused) {
            }
            c cVar = c.this;
            cVar.a(this.a, "onMultiPickerViewChange", jSONObject, new int[]{cVar.f13354g});
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.m.g1.c$c  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public class C0562c implements a.InterfaceC0701a<int[]> {
        C0562c() {
        }

        @Override // com.jingdong.manto.widget.k.a.InterfaceC0701a
        public void a(int[] iArr) {
            c cVar;
            Bundle bundle;
            String str;
            if (iArr == null || iArr.length <= 0) {
                cVar = c.this;
                bundle = null;
                str = "fail:empty result";
            } else {
                bundle = new Bundle();
                bundle.putIntArray(UnionConstants.BUNDLE_CURRENT, iArr);
                cVar = c.this;
                str = IMantoBaseModule.SUCCESS;
            }
            cVar.a(str, bundle);
        }

        @Override // com.jingdong.manto.widget.k.a.InterfaceC0701a
        public void onCancel() {
            c.this.a("cancel", (Bundle) null);
        }
    }

    @Override // com.jingdong.manto.m.g1.f
    void a(Bundle bundle, MantoCore mantoCore) {
        try {
            JSONObject jSONObject = new JSONObject(bundle.getString("params"));
            JSONArray optJSONArray = jSONObject.optJSONArray("array");
            JSONArray optJSONArray2 = jSONObject.optJSONArray(UnionConstants.BUNDLE_CURRENT);
            this.f13355h = jSONObject.optString("headerText");
            this.f13354g = bundle.getInt(IMantoBaseModule.COMPONENT_HASHCODE);
            String str = "fail:invalid data";
            if (optJSONArray != null && optJSONArray2 != null && optJSONArray.length() == optJSONArray2.length()) {
                if (optJSONArray.length() == 0) {
                    str = "fail:empty range";
                } else {
                    try {
                        b.C0702b[] c0702bArr = new b.C0702b[optJSONArray.length()];
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            JSONArray optJSONArray3 = optJSONArray.optJSONArray(i2);
                            int length = optJSONArray3.length();
                            String[] strArr = new String[length];
                            int i3 = optJSONArray2.getInt(i2);
                            for (int i4 = 0; i4 < length; i4++) {
                                strArr[i4] = optJSONArray3.getString(i4);
                            }
                            c0702bArr[i2] = new b.C0702b(strArr, i3);
                        }
                        this.f13353f.set(c0702bArr);
                        MantoThreadUtils.post(new a(mantoCore));
                        return;
                    } catch (Exception e2) {
                        MantoLog.d("MultiPickerInvoker", "parse params:" + MantoStringUtils.throwable2String(e2));
                    }
                }
            }
            a(str, (Bundle) null);
        } catch (Exception unused) {
            a("fail", (Bundle) null);
        }
    }

    public void b(MantoCore mantoCore) {
        com.jingdong.manto.widget.k.f a2 = a(mantoCore);
        if (a2 == null) {
            return;
        }
        a2.setHeaderText(this.f13355h);
        com.jingdong.manto.widget.k.b bVar = (com.jingdong.manto.widget.k.b) a(com.jingdong.manto.widget.k.b.class);
        if (bVar == null) {
            bVar = new com.jingdong.manto.widget.k.b(a2.getContext());
        }
        bVar.a(this.f13353f.get());
        bVar.a(new b(mantoCore));
        bVar.a(new C0562c());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        a2.a(bVar, layoutParams);
    }
}
