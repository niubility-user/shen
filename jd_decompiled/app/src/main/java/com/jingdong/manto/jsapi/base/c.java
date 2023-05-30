package com.jingdong.manto.jsapi.base;

import android.view.View;
import com.jingdong.manto.h;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.d1.i;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.q.n;
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
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 601
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.base.c.a.run():void");
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
