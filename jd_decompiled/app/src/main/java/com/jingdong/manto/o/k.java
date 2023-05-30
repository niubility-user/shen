package com.jingdong.manto.o;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.R;
import com.jingdong.manto.i.a;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.o.m;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.pkg.ipc.MantoPkgUpdate;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class k extends m {
    public k() {
        super(4);
    }

    private void a(com.jingdong.manto.f fVar, n nVar) {
        l lVar;
        HashMap hashMap;
        if (nVar.a.a("do_fav", true)) {
            fVar.p().a((Parcelable) new MantoPkgUpdate(fVar.f13016h, MantoPkgUpdate.UpdateAction.FAVO));
            lVar = new l();
            hashMap = new HashMap();
            hashMap.put("type", 0);
        } else {
            fVar.p().a((Parcelable) new MantoPkgUpdate(fVar.f13016h, MantoPkgUpdate.UpdateAction.UNFAVO));
            lVar = new l();
            hashMap = new HashMap();
            hashMap.put("type", 1);
        }
        hashMap.put("msg", IMantoBaseModule.SUCCESS);
        hashMap.put("errCode", 0);
        lVar.a(hashMap).a(fVar.f13015g).a();
        boolean a = nVar.a.a("do_fav", true);
        PkgDetailEntity pkgDetailEntity = fVar.f13016h;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("vapp_type", pkgDetailEntity.type);
            jSONObject.put("follow", String.valueOf(a ? 1 : 0));
        } catch (JSONException e2) {
            MantoLog.e(DYConstants.DY_TRACK, e2);
        }
        MantoTrack.sendCommonDataWithExt(com.jingdong.manto.c.a(), "\u5173\u6ce8", "applets_capsule_follow", pkgDetailEntity.appId, "\u80f6\u56ca\u83dc\u5355\u5f39\u7a97", "", jSONObject.toString(), "applets_capsule", null);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Activity activity, com.jingdong.manto.q.n nVar, String str, n nVar2) {
        com.jingdong.manto.f h2 = nVar.h();
        if (h2 == null) {
            return;
        }
        ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
        if (iLogin == null) {
            l lVar = new l();
            HashMap hashMap = new HashMap();
            hashMap.put("type", nVar2.a.a("do_fav", true) ? 0 : 1);
            hashMap.put("msg", "fail");
            hashMap.put("errCode", 10002);
            lVar.a(hashMap).a(h2.f13015g).a();
        } else if (h2.z || iLogin.hasLogin()) {
            a(h2, nVar2);
        } else {
            l lVar2 = new l();
            HashMap hashMap2 = new HashMap();
            hashMap2.put("type", nVar2.a.a("do_fav", true) ? 0 : 1);
            hashMap2.put("msg", "fail");
            hashMap2.put("errCode", 10001);
            lVar2.a(hashMap2).a(h2.f13015g).a();
        }
    }

    @Override // com.jingdong.manto.o.m
    public void a(Context context, com.jingdong.manto.q.n nVar, com.jingdong.manto.widget.j.c cVar, String str, m.a aVar) {
        com.jingdong.manto.f h2;
        int i2;
        int i3;
        a.e eVar;
        n nVar2 = nVar.p().get(this.a);
        if (nVar2 == null || (h2 = nVar.h()) == null) {
            return;
        }
        com.jingdong.manto.i.a aVar2 = h2.t;
        if (aVar2 != null && (eVar = aVar2.f13050h) != null) {
            nVar2.b = eVar.a;
        }
        PkgDetailEntity pkgDetailEntity = h2.f13016h;
        if (pkgDetailEntity != null && TextUtils.isEmpty(pkgDetailEntity.venderId)) {
            if (h2.f13016h.favorite) {
                nVar2.a.b("do_fav", false);
                i2 = R.string.manto_page_menu_un_favor;
                i3 = R.drawable.manto_menu_unfavo;
            } else {
                nVar2.a.b("do_fav", true);
                i2 = R.string.manto_page_menu_favor;
                i3 = R.drawable.manto_menu_favo;
            }
            cVar.a(nVar2.f13908c, i2, i3).a(true);
        }
    }
}
