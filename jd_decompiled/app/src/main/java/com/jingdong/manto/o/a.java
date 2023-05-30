package com.jingdong.manto.o;

import android.app.Activity;
import android.content.Context;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.R;
import com.jingdong.manto.i.a;
import com.jingdong.manto.o.m;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.ui.MantoAboutActivity;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class a extends m {
    public a() {
        super(7);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Activity activity, com.jingdong.manto.q.n nVar, String str, n nVar2) {
        com.jingdong.manto.i.a aVar;
        a.e eVar;
        PkgDetailEntity pkgDetailEntity = nVar.h().f13016h;
        com.jingdong.manto.f h2 = nVar.h();
        MantoAboutActivity.a(activity, str, pkgDetailEntity == null ? "1" : pkgDetailEntity.type, (h2 == null || (aVar = h2.t) == null || (eVar = aVar.f13050h) == null) ? true : eVar.b);
        if (pkgDetailEntity == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("vapp_type", pkgDetailEntity.type);
        } catch (JSONException e2) {
            MantoLog.e(DYConstants.DY_TRACK, e2);
        }
        MantoTrack.sendCommonDataWithExt(com.jingdong.manto.c.a(), "\u5173\u4e8e", "applets_capsule_about", pkgDetailEntity.appId, "\u80f6\u56ca\u83dc\u5355\u5f39\u7a97", "", jSONObject.toString(), "applets_capsule", null);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Context context, com.jingdong.manto.q.n nVar, com.jingdong.manto.widget.j.c cVar, String str, m.a aVar) {
        n nVar2 = nVar.p().get(this.a);
        if (nVar2 == null) {
            return;
        }
        cVar.a(nVar2.f13908c, R.string.manto_page_menu_about, R.drawable.manto_menu_about).a(true);
    }
}
