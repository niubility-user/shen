package com.jingdong.manto.jsapi.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.d1.i;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.c;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class c extends d {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements Runnable {
        final /* synthetic */ n a;
        final /* synthetic */ e0 b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13123c;
        final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ JSONObject f13124e;

        a(n nVar, e0 e0Var, int i2, String str, JSONObject jSONObject) {
            this.a = nVar;
            this.b = e0Var;
            this.f13123c = i2;
            this.d = str;
            this.f13124e = jSONObject;
        }

        /* JADX WARN: Removed duplicated region for block: B:105:0x0230  */
        /* JADX WARN: Removed duplicated region for block: B:113:0x0155 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:118:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:69:0x0140 A[Catch: JSONException -> 0x0247, TryCatch #1 {JSONException -> 0x0247, blocks: (B:6:0x0019, B:8:0x0024, B:11:0x003c, B:13:0x0047, B:15:0x0052, B:17:0x005d, B:19:0x0068, B:21:0x0073, B:26:0x0088, B:28:0x0090, B:30:0x0098, B:33:0x00aa, B:36:0x00be, B:38:0x00c7, B:40:0x00cf, B:43:0x00e6, B:45:0x00ee, B:48:0x00fa, B:50:0x0107, B:52:0x010b, B:57:0x0118, B:64:0x0128, B:66:0x0134, B:68:0x013c, B:69:0x0140, B:72:0x0149, B:74:0x014d, B:85:0x01af, B:90:0x01ca, B:91:0x01cd, B:93:0x01d5, B:97:0x01e1, B:98:0x01f7, B:100:0x0211, B:102:0x021f, B:108:0x0237, B:49:0x0101, B:41:0x00d8, B:32:0x00a2, B:87:0x01b7), top: B:115:0x0019, inners: #2 }] */
        /* JADX WARN: Removed duplicated region for block: B:74:0x014d A[Catch: JSONException -> 0x0247, TRY_LEAVE, TryCatch #1 {JSONException -> 0x0247, blocks: (B:6:0x0019, B:8:0x0024, B:11:0x003c, B:13:0x0047, B:15:0x0052, B:17:0x005d, B:19:0x0068, B:21:0x0073, B:26:0x0088, B:28:0x0090, B:30:0x0098, B:33:0x00aa, B:36:0x00be, B:38:0x00c7, B:40:0x00cf, B:43:0x00e6, B:45:0x00ee, B:48:0x00fa, B:50:0x0107, B:52:0x010b, B:57:0x0118, B:64:0x0128, B:66:0x0134, B:68:0x013c, B:69:0x0140, B:72:0x0149, B:74:0x014d, B:85:0x01af, B:90:0x01ca, B:91:0x01cd, B:93:0x01d5, B:97:0x01e1, B:98:0x01f7, B:100:0x0211, B:102:0x021f, B:108:0x0237, B:49:0x0101, B:41:0x00d8, B:32:0x00a2, B:87:0x01b7), top: B:115:0x0019, inners: #2 }] */
        /* JADX WARN: Removed duplicated region for block: B:85:0x01af A[Catch: JSONException -> 0x0247, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x0247, blocks: (B:6:0x0019, B:8:0x0024, B:11:0x003c, B:13:0x0047, B:15:0x0052, B:17:0x005d, B:19:0x0068, B:21:0x0073, B:26:0x0088, B:28:0x0090, B:30:0x0098, B:33:0x00aa, B:36:0x00be, B:38:0x00c7, B:40:0x00cf, B:43:0x00e6, B:45:0x00ee, B:48:0x00fa, B:50:0x0107, B:52:0x010b, B:57:0x0118, B:64:0x0128, B:66:0x0134, B:68:0x013c, B:69:0x0140, B:72:0x0149, B:74:0x014d, B:85:0x01af, B:90:0x01ca, B:91:0x01cd, B:93:0x01d5, B:97:0x01e1, B:98:0x01f7, B:100:0x0211, B:102:0x021f, B:108:0x0237, B:49:0x0101, B:41:0x00d8, B:32:0x00a2, B:87:0x01b7), top: B:115:0x0019, inners: #2 }] */
        /* JADX WARN: Removed duplicated region for block: B:93:0x01d5 A[Catch: JSONException -> 0x0247, TryCatch #1 {JSONException -> 0x0247, blocks: (B:6:0x0019, B:8:0x0024, B:11:0x003c, B:13:0x0047, B:15:0x0052, B:17:0x005d, B:19:0x0068, B:21:0x0073, B:26:0x0088, B:28:0x0090, B:30:0x0098, B:33:0x00aa, B:36:0x00be, B:38:0x00c7, B:40:0x00cf, B:43:0x00e6, B:45:0x00ee, B:48:0x00fa, B:50:0x0107, B:52:0x010b, B:57:0x0118, B:64:0x0128, B:66:0x0134, B:68:0x013c, B:69:0x0140, B:72:0x0149, B:74:0x014d, B:85:0x01af, B:90:0x01ca, B:91:0x01cd, B:93:0x01d5, B:97:0x01e1, B:98:0x01f7, B:100:0x0211, B:102:0x021f, B:108:0x0237, B:49:0x0101, B:41:0x00d8, B:32:0x00a2, B:87:0x01b7), top: B:115:0x0019, inners: #2 }] */
        /* JADX WARN: Removed duplicated region for block: B:95:0x01dd  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            Bundle bundle;
            AbstractMantoViewManager abstractMantoViewManager;
            int a;
            View c2;
            boolean z;
            boolean z2;
            boolean c3;
            c.a a2;
            String str;
            if (this.a == null) {
                this.b.a(this.f13123c, c.this.putErrMsg("fail:page is null", null, this.d));
                return;
            }
            try {
                Bundle bundle2 = new Bundle();
                i iVar = c.this.a;
                if (iVar != null) {
                    abstractMantoViewManager = (AbstractMantoViewManager) iVar.c();
                    bundle = abstractMantoViewManager.handleUpdateData(c.this.getCore(this.b), this.f13124e);
                    if (bundle != null) {
                        bundle.putString("appId", this.a.a());
                        bundle.putString("appid", this.a.a());
                        bundle.putString("type", this.a.b());
                        bundle.putString("appUniqueId", this.a.c());
                        bundle.putInt("hashCode", this.a.hashCode());
                        bundle.putInt("runtimeHashCode", this.b.h().hashCode());
                    }
                } else {
                    bundle = bundle2;
                    abstractMantoViewManager = null;
                }
                if (abstractMantoViewManager == null || bundle == null) {
                    a = c.this.a(this.f13124e);
                } else {
                    a = bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY, 0);
                    if (a == 0) {
                        this.b.a(this.f13123c, c.this.putErrMsg("fail:viewID NULL", null, this.d));
                        return;
                    }
                }
                String replace = this.d.replace("update", "");
                if (TextUtils.equals("drawCanvas", this.d)) {
                    replace = "Canvas";
                }
                boolean z3 = true;
                if (!this.a.i(replace) || this.a.a(replace, a) == null) {
                    c2 = this.a.n().c(a);
                    z = false;
                } else {
                    c2 = this.a.a(replace, a);
                    z = true;
                }
                if (c2 == null) {
                    this.b.a(this.f13123c, c.this.putErrMsg("fail:got 'null' when get view by the given viewId", null, this.d));
                    return;
                }
                boolean z4 = abstractMantoViewManager != null ? bundle.getBoolean("abg", false) : c.this.b();
                boolean b = c2 instanceof CoverViewContainer ? ((CoverViewContainer) c2).b() : false;
                if (!z4 && !this.f13124e.optBoolean("disableScroll", b)) {
                    z2 = false;
                    if (z2 && !z && (a2 = this.a.n().a(a, false)) != null) {
                        if (a2.b("isTouching")) {
                            str = a2.a("disableScroll", false) ? "disableScroll" : "disableScroll-nextState";
                        }
                        a2.b(str, true);
                    }
                    if (c2 instanceof CoverViewContainer) {
                        ((CoverViewContainer) c2).setDisableScroll(z2);
                    }
                    if (z) {
                        try {
                            boolean a3 = this.a.n().a(a, d.e(this.f13124e), d.b(this.f13124e), d.d(this.f13124e), d.c(this.f13124e));
                            com.jingdong.manto.q.b b2 = this.a.n().b(a);
                            int i2 = b2 != null ? b2.b : 0;
                            if (com.jingdong.a.a) {
                                MantoLog.i("BaseUpdateViewJsApi", String.format("update view(parentId : %s, viewId : %d), ret : %b", Integer.valueOf(i2), Integer.valueOf(a), Boolean.valueOf(a3)));
                            }
                            z3 = a3;
                        } catch (Exception unused) {
                        }
                    } else if (!TextUtils.equals("drawCanvas", this.d)) {
                        try {
                            this.a.a(replace, a, d.e(this.f13124e), d.b(this.f13124e));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    c3 = c.this.c();
                    if (abstractMantoViewManager != null) {
                        c3 = bundle.getBoolean("abi", false);
                    }
                    if (z3) {
                        z3 = c3 ? abstractMantoViewManager != null ? abstractMantoViewManager.onViewUpdate(c.this.getCore(this.b), c2, bundle, new MantoCallback(this.b, this.f13123c)) : c.this.a(this.a, a, c2, this.f13124e, new MantoCallback(this.b, this.f13123c), this.d) : abstractMantoViewManager != null ? abstractMantoViewManager.onViewUpdate(c.this.getCore(this.b), c2, bundle) : c.this.a(this.a, a, c2, this.f13124e, this.d);
                    }
                    if (c3) {
                        this.b.a(this.f13123c, c.this.putErrMsg(z3 ? IMantoBaseModule.SUCCESS : "fail", null, this.d));
                        return;
                    }
                    return;
                }
                z2 = true;
                if (z2) {
                    if (a2.b("isTouching")) {
                    }
                    a2.b(str, true);
                }
                if (c2 instanceof CoverViewContainer) {
                }
                if (z) {
                }
                c3 = c.this.c();
                if (abstractMantoViewManager != null) {
                }
                if (z3) {
                }
                if (c3) {
                }
            } catch (JSONException unused2) {
                this.b.a(this.f13123c, c.this.putErrMsg("fail:view id do not exist", null, this.d));
            }
        }
    }

    public c() {
    }

    public c(i iVar) {
        super(iVar);
    }

    private void a(e0 e0Var, int i2, n nVar, JSONObject jSONObject, String str) {
        com.jingdong.manto.sdk.thread.a.a(new a(nVar, e0Var, i2, str, jSONObject));
    }

    public boolean a(n nVar, int i2, View view, JSONObject jSONObject, MantoCallback mantoCallback, String str) {
        return true;
    }

    public boolean a(n nVar, int i2, View view, JSONObject jSONObject, String str) {
        return true;
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        super.exec(hVar, jSONObject, i2, str);
        n pageView = c0.getPageView(hVar);
        if (pageView == null) {
            hVar.a(i2, putErrMsg("fail:page is null", null, str));
        } else {
            a(hVar, i2, pageView, jSONObject, str);
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(n nVar, JSONObject jSONObject, int i2, String str) {
        super.exec(nVar, jSONObject, i2, str);
        a(nVar, i2, nVar, jSONObject, str);
    }
}
