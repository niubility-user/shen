package com.jingdong.manto.o;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.R;
import com.jingdong.manto.i.a;
import com.jingdong.manto.o.m;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.IImageLoader;
import com.jingdong.manto.sdk.api.IShortcutManager;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.MantoTrack;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class h extends m {

    /* loaded from: classes16.dex */
    public class a implements IImageLoader.ImageLoaderCallback {
        final /* synthetic */ PkgDetailEntity a;
        final /* synthetic */ com.jingdong.manto.q.n b;

        /* renamed from: c */
        final /* synthetic */ Activity f13905c;

        /* renamed from: com.jingdong.manto.o.h$a$a */
        /* loaded from: classes16.dex */
        class RunnableC0646a implements Runnable {
            final /* synthetic */ Bitmap a;

            RunnableC0646a(Bitmap bitmap) {
                a.this = r1;
                this.a = bitmap;
            }

            @Override // java.lang.Runnable
            public void run() {
                IShortcutManager iShortcutManager = (IShortcutManager) com.jingdong.a.n(IShortcutManager.class);
                if (iShortcutManager == null) {
                    return;
                }
                IShortcutManager.a aVar = new IShortcutManager.a();
                a aVar2 = a.this;
                PkgDetailEntity pkgDetailEntity = aVar2.a;
                aVar.f14183c = pkgDetailEntity.appId;
                aVar.a = pkgDetailEntity.name;
                aVar.d = pkgDetailEntity.type;
                aVar.b = this.a;
                aVar.f14184e = aVar2.b.h().r.f13083f;
                aVar.f14185f = a.this.b.h().r.f13090m;
                iShortcutManager.sendToDesktop(a.this.f13905c, aVar);
            }
        }

        a(h hVar, PkgDetailEntity pkgDetailEntity, com.jingdong.manto.q.n nVar, Activity activity) {
            this.a = pkgDetailEntity;
            this.b = nVar;
            this.f13905c = activity;
        }

        @Override // com.jingdong.manto.sdk.api.IImageLoader.ImageLoaderCallback
        public void onFail() {
        }

        @Override // com.jingdong.manto.sdk.api.IImageLoader.ImageLoaderCallback
        public void onSuccess(Bitmap bitmap) {
            MantoThreadUtils.runOnUIThread(new RunnableC0646a(bitmap));
        }
    }

    public h() {
        super(8);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Activity activity, com.jingdong.manto.q.n nVar, String str, n nVar2) {
        PkgDetailEntity pkgDetailEntity;
        IImageLoader iImageLoader = (IImageLoader) com.jingdong.a.n(IImageLoader.class);
        if (iImageLoader == null || (pkgDetailEntity = nVar.h().f13016h) == null || MantoStringUtils.isEmpty(pkgDetailEntity.logo)) {
            return;
        }
        iImageLoader.loadImage(activity, pkgDetailEntity.logo, new a(this, pkgDetailEntity, nVar, activity));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("vapp_type", pkgDetailEntity.type);
        } catch (JSONException e2) {
            MantoLog.e(DYConstants.DY_TRACK, e2);
        }
        MantoTrack.sendCommonDataWithExt(activity, "\u53d1\u9001\u5230\u684c\u9762", "applets_capsule_send", pkgDetailEntity.appId, "\u80f6\u56ca\u83dc\u5355\u5f39\u7a97", "", jSONObject.toString(), "applets_capsule", null);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Context context, com.jingdong.manto.q.n nVar, com.jingdong.manto.widget.j.c cVar, String str, m.a aVar) {
        com.jingdong.manto.f h2;
        a.e eVar;
        n nVar2 = nVar.p().get(this.a);
        if (nVar2 == null || (h2 = nVar.h()) == null || h2.x()) {
            return;
        }
        com.jingdong.manto.i.a aVar2 = h2.t;
        if (aVar2 != null && (eVar = aVar2.f13050h) != null) {
            nVar2.b = eVar.f13057c;
        }
        cVar.a(nVar2.f13908c, R.string.manto_page_menu_send_to_desktop, R.drawable.manto_menu_desktop).a(true);
    }
}
