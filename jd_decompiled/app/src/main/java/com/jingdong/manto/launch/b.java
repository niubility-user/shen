package com.jingdong.manto.launch;

import android.text.TextUtils;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.utils.m;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b {
    public static a a() {
        a aVar = new a();
        aVar.a = "onUpdateFailed";
        aVar.b = "";
        return aVar;
    }

    public static a a(boolean z) {
        a aVar = new a();
        aVar.a = "onCheckForUpdate";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("hasUpdate", z);
        } catch (Throwable unused) {
        }
        aVar.b = jSONObject.toString();
        return aVar;
    }

    public static boolean a(com.jingdong.manto.i.c cVar) {
        if (cVar != null) {
            return TextUtils.equals(cVar.q, "1") || TextUtils.equals(cVar.q, "2") || TextUtils.equals(cVar.q, "3");
        }
        return false;
    }

    public static boolean a(PkgDetailEntity pkgDetailEntity) {
        if (pkgDetailEntity != null) {
            return pkgDetailEntity.isSwitchOpen(5);
        }
        return false;
    }

    public static a b() {
        a aVar = new a();
        aVar.a = "onUpdateReady";
        aVar.b = "";
        return aVar;
    }

    public static boolean c() {
        return TextUtils.equals("1", m.a("appAsyncUpdateSwitch", "1"));
    }
}
