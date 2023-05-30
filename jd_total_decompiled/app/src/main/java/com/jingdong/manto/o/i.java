package com.jingdong.manto.o;

import android.app.Activity;
import android.content.Context;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.R;
import com.jingdong.manto.i.a;
import com.jingdong.manto.o.m;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.IShareItem;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class i extends m {

    /* loaded from: classes16.dex */
    private static final class b extends com.jingdong.manto.m.d {
        private b() {
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onShareAppMessage";
        }
    }

    public i() {
        super(5);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Activity activity, com.jingdong.manto.q.n nVar, String str, n nVar2) {
        PkgDetailEntity pkgDetailEntity;
        if (nVar.h() == null || (pkgDetailEntity = nVar.h().f13016h) == null) {
            return;
        }
        b bVar = new b();
        HashMap hashMap = new HashMap();
        hashMap.put("title", pkgDetailEntity.name);
        hashMap.put("desc", pkgDetailEntity.description);
        hashMap.put("path", nVar.r());
        hashMap.put("imageUrl", pkgDetailEntity.logo);
        nVar2.a.b("user_clicked_share_btn", true);
        bVar.a(nVar).a(hashMap).a();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("vapp_type", pkgDetailEntity.type);
            jSONObject.put("url", nVar.r());
        } catch (Throwable th) {
            MantoLog.e(DYConstants.DY_TRACK, th);
        }
        MantoTrack.sendCommonDataWithExt(com.jingdong.manto.c.a(), "\u63a8\u8350", "applets_capsule_share", pkgDetailEntity.appId, "\u80f6\u56ca\u83dc\u5355\u5f39\u7a97", "", jSONObject.toString(), "applets_capsule", null);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Context context, com.jingdong.manto.q.n nVar, com.jingdong.manto.widget.j.c cVar, String str, m.a aVar) {
        com.jingdong.manto.f h2;
        a.e eVar;
        n nVar2 = nVar.p().get(this.a);
        if (nVar2 == null || (h2 = nVar.h()) == null) {
            return;
        }
        com.jingdong.manto.i.a aVar2 = h2.t;
        if (aVar2 != null && (eVar = aVar2.f13050h) != null) {
            nVar2.b = eVar.b;
        }
        if (((IShareItem) com.jingdong.a.n(IShareItem.class)) != null) {
            nVar2.b = !r2.disableDefaultShare();
        }
        int i2 = nVar2.d;
        if (i2 != -1) {
            nVar2.b = i2 == 1;
        }
        cVar.a(nVar2.f13908c, R.string.manto_page_menu_share, R.drawable.manto_menu_share).a(true);
    }
}
