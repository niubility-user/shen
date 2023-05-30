package com.jingdong.manto.o;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.R;
import com.jingdong.manto.o.m;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.IHostMenuInterface;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import com.un.lib.popup.JDTopPopupWindowHelper;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class e extends m {
    public e() {
        super(2);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Activity activity, com.jingdong.manto.q.n nVar, String str, n nVar2) {
        com.jingdong.manto.f h2 = nVar.h();
        if (h2 == null || h2.f13016h == null) {
            return;
        }
        PkgDetailEntity pkgDetailEntity = nVar.h().f13016h;
        IHostMenuInterface iHostMenuInterface = (IHostMenuInterface) com.jingdong.a.n(IHostMenuInterface.class);
        if (iHostMenuInterface == null) {
            return;
        }
        iHostMenuInterface.jumpToHome(activity);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("vapp_type", pkgDetailEntity.type);
            jSONObject.put("venderId", pkgDetailEntity.venderId);
            jSONObject.put("url", nVar.r());
        } catch (JSONException e2) {
            MantoLog.e(DYConstants.DY_TRACK, e2);
        }
        MantoTrack.sendCommonDataWithExt(com.jingdong.manto.c.a(), JDTopPopupWindowHelper.BASE_HOME, "applets_apphomepage", pkgDetailEntity.appId, "\u80f6\u56ca\u83dc\u5355\u5f39\u7a97", "", jSONObject.toString(), "applets_capsule", null);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Context context, com.jingdong.manto.q.n nVar, com.jingdong.manto.widget.j.c cVar, String str, m.a aVar) {
        com.jingdong.manto.f h2;
        PkgDetailEntity pkgDetailEntity;
        n nVar2 = nVar.p().get(this.a);
        if (nVar2 == null || (h2 = nVar.h()) == null || (pkgDetailEntity = h2.f13016h) == null || TextUtils.isEmpty(pkgDetailEntity.venderId)) {
            return;
        }
        cVar.a(nVar2.f13908c, R.string.manto_page_menu_home, R.drawable.manto_menu_home).a(true);
    }
}
