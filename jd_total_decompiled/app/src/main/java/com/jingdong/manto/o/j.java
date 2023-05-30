package com.jingdong.manto.o;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.ui.address.UnAddressConstants;
import com.jingdong.manto.R;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.o.m;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.IHostMenuInterface;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class j extends m {
    private String b;

    /* renamed from: c */
    private String f13906c;

    /* loaded from: classes16.dex */
    class a extends IMantoHttpListener {
        final /* synthetic */ PkgDetailEntity a;
        final /* synthetic */ IHostMenuInterface b;

        /* renamed from: c */
        final /* synthetic */ Activity f13907c;

        a(PkgDetailEntity pkgDetailEntity, IHostMenuInterface iHostMenuInterface, Activity activity) {
            j.this = r1;
            this.a = pkgDetailEntity;
            this.b = iHostMenuInterface;
            this.f13907c = activity;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            JSONObject optJSONObject;
            if (!TextUtils.equals("0", jSONObject.optString("code")) || (optJSONObject = jSONObject.optJSONObject("result")) == null) {
                return;
            }
            String optString = optJSONObject.optString(UnAddressConstants.INTENT_SHOP_ID);
            if (TextUtils.equals(this.a.venderId, optJSONObject.optString("vender_id"))) {
                j.this.b = optString;
                this.b.jumpToShop(this.f13907c, this.a.venderId, optString);
            }
        }
    }

    public j() {
        super(3);
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
        if (!TextUtils.equals(this.f13906c, pkgDetailEntity.venderId)) {
            this.f13906c = pkgDetailEntity.venderId;
            this.b = null;
        }
        if (TextUtils.isEmpty(this.b)) {
            MantoJDHttpHandler.commit(new com.jingdong.manto.network.mantorequests.d(pkgDetailEntity.appId), new a(pkgDetailEntity, iHostMenuInterface, activity));
        } else {
            iHostMenuInterface.jumpToShop(activity, pkgDetailEntity.venderId, this.b);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("vapp_type", pkgDetailEntity.type);
            jSONObject.put("venderId", pkgDetailEntity.venderId);
            jSONObject.put("url", nVar.r());
        } catch (JSONException e2) {
            MantoLog.e(DYConstants.DY_TRACK, e2);
        }
        MantoTrack.sendCommonDataWithExt(com.jingdong.manto.c.a(), "\u5e97\u94fa\u8be6\u60c5", "applets_shop_detail", pkgDetailEntity.appId, "\u80f6\u56ca\u83dc\u5355\u5f39\u7a97", "", jSONObject.toString(), "applets_capsule", null);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Context context, com.jingdong.manto.q.n nVar, com.jingdong.manto.widget.j.c cVar, String str, m.a aVar) {
        com.jingdong.manto.f h2;
        PkgDetailEntity pkgDetailEntity;
        n nVar2 = nVar.p().get(this.a);
        if (nVar2 == null || (h2 = nVar.h()) == null || (pkgDetailEntity = h2.f13016h) == null || TextUtils.isEmpty(pkgDetailEntity.venderId)) {
            return;
        }
        cVar.a(nVar2.f13908c, R.string.manto_page_menu_shop_detail, R.drawable.manto_menu_shop_detail).a(true);
    }
}
