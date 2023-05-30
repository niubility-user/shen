package com.jingdong.manto.m.g1;

import android.os.Bundle;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.k.b;
import com.jingdong.union.common.config.UnionConstants;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class d extends f {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ b.C0702b b;

        a(int i2, b.C0702b c0702b) {
            this.a = i2;
            this.b = c0702b;
        }

        @Override // java.lang.Runnable
        public void run() {
            d dVar;
            String str;
            com.jingdong.manto.widget.k.b bVar = (com.jingdong.manto.widget.k.b) d.this.a(com.jingdong.manto.widget.k.b.class);
            if (bVar == null) {
                dVar = d.this;
                str = "fail:picker not exist";
            } else {
                bVar.a(this.a, this.b);
                dVar = d.this;
                str = IMantoBaseModule.SUCCESS;
            }
            dVar.a(str, (Bundle) null);
        }
    }

    @Override // com.jingdong.manto.m.g1.f
    void a(Bundle bundle, MantoCore mantoCore) {
        try {
            JSONObject jSONObject = new JSONObject(bundle.getString("params"));
            int optInt = jSONObject.optInt("column", -1);
            JSONArray optJSONArray = jSONObject.optJSONArray("array");
            if (optInt < 0 || optJSONArray == null) {
                a("fail", (Bundle) null);
                return;
            }
            try {
                int length = optJSONArray.length();
                String[] strArr = new String[length];
                for (int i2 = 0; i2 < length; i2++) {
                    strArr[i2] = optJSONArray.getString(i2);
                }
                MantoUtils.runOnUiThread(new a(optInt, new b.C0702b(strArr, jSONObject.optInt(UnionConstants.BUNDLE_CURRENT, 0))));
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            a("fail", (Bundle) null);
        }
    }
}
