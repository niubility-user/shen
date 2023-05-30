package com.jingdong.manto.m.g1;

import android.os.Bundle;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.k.a;
import com.jingdong.union.common.config.UnionConstants;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class e extends f {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements Runnable {
        final /* synthetic */ MantoCore a;
        final /* synthetic */ JSONObject b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String[] f13357c;
        final /* synthetic */ int d;

        /* renamed from: com.jingdong.manto.m.g1.e$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0563a implements a.InterfaceC0701a<String> {
            final /* synthetic */ com.jingdong.manto.widget.k.e a;

            C0563a(com.jingdong.manto.widget.k.e eVar) {
                this.a = eVar;
            }

            @Override // com.jingdong.manto.widget.k.a.InterfaceC0701a
            public void a(String str) {
                Bundle bundle = new Bundle();
                bundle.putString("value", this.a.b());
                bundle.putInt("index", this.a.e());
                e.this.a(IMantoBaseModule.SUCCESS, bundle);
            }

            @Override // com.jingdong.manto.widget.k.a.InterfaceC0701a
            public void onCancel() {
                e.this.a("cancel", (Bundle) null);
            }
        }

        a(MantoCore mantoCore, JSONObject jSONObject, String[] strArr, int i2) {
            this.a = mantoCore;
            this.b = jSONObject;
            this.f13357c = strArr;
            this.d = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.widget.k.f a = e.this.a(this.a);
            if (a == null) {
                return;
            }
            a.setHeaderText(this.b.optString("headerText"));
            com.jingdong.manto.widget.k.e eVar = (com.jingdong.manto.widget.k.e) e.this.a(com.jingdong.manto.widget.k.e.class);
            if (eVar == null) {
                eVar = new com.jingdong.manto.widget.k.e(a.getContext());
            }
            eVar.a(this.f13357c);
            eVar.a(this.d);
            eVar.a(new C0563a(eVar));
            a.a(eVar);
        }
    }

    @Override // com.jingdong.manto.m.g1.f
    void a(Bundle bundle, MantoCore mantoCore) {
        try {
            JSONObject jSONObject = new JSONObject(bundle.getString("params"));
            JSONArray optJSONArray = jSONObject.optJSONArray("array");
            int optInt = jSONObject.optInt(UnionConstants.BUNDLE_CURRENT, 0);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                a("fail", (Bundle) null);
                return;
            }
            String[] strArr = new String[optJSONArray.length()];
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                try {
                    strArr[i2] = optJSONArray.getString(i2);
                } catch (Throwable th) {
                    MantoLog.e("OptionPickerInvoker", String.format("opt data.array, exp = %s", MantoStringUtils.throwable2String(th)));
                    a("fail", (Bundle) null);
                    return;
                }
            }
            MantoUtils.runOnUiThread(new a(mantoCore, jSONObject, strArr, optInt));
        } catch (Exception unused) {
            a("fail", (Bundle) null);
        }
    }
}
