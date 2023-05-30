package com.jingdong.manto.m.o1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.pkg.b.g;
import com.jingdong.manto.q.j;
import com.jingdong.manto.q.q;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.d;
import java.io.InputStream;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class c extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        try {
            int i3 = jSONObject.getInt("index");
            String optString = jSONObject.optString("text", "");
            String optString2 = jSONObject.optString("iconPath", "");
            String optString3 = jSONObject.optString("selectedIconPath", "");
            j firstPage = hVar.h().f13014f.getFirstPage();
            if (!(firstPage instanceof q)) {
                hVar.a(i2, putErrMsg("fail:page not ready", null, str));
                return;
            }
            InputStream d = g.d(hVar.h(), optString2);
            Bitmap decodeStream = BitmapFactory.decodeStream(d);
            if (d != null) {
                MantoUtils.qualityClose(d);
            }
            InputStream d2 = g.d(hVar.h(), optString3);
            Bitmap decodeStream2 = BitmapFactory.decodeStream(d2);
            if (d2 != null) {
                MantoUtils.qualityClose(d);
            }
            com.jingdong.manto.widget.d dVar = ((q) firstPage).f14092i;
            if (i3 < dVar.f14347f.size()) {
                d.g gVar = dVar.f14347f.get(i3);
                gVar.f14359i = optString;
                if (decodeStream == null) {
                    decodeStream = gVar.b;
                }
                gVar.b = decodeStream;
                if (decodeStream2 == null) {
                    decodeStream2 = gVar.a;
                }
                gVar.a = decodeStream2;
                dVar.b();
            }
            hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
        } catch (Exception unused) {
            hVar.a(i2, putErrMsg("fail", null, str));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setTabBarItem";
    }
}
