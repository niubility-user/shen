package g.d.a.g;

import android.content.Context;
import android.content.Intent;
import com.heytap.msp.push.mode.BaseMode;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;

/* loaded from: classes12.dex */
public class a extends c {
    @Override // g.d.a.g.d
    public BaseMode a(Context context, int i2, Intent intent) {
        if (4105 == i2) {
            return c(intent, i2);
        }
        return null;
    }

    protected BaseMode c(Intent intent, int i2) {
        try {
            g.d.a.e.b bVar = new g.d.a.e.b();
            bVar.f(Integer.parseInt(g.d.a.j.b.e(intent.getStringExtra("command"))));
            bVar.g(Integer.parseInt(g.d.a.j.b.e(intent.getStringExtra("code"))));
            bVar.setContent(g.d.a.j.b.e(intent.getStringExtra("content")));
            bVar.d(g.d.a.j.b.e(intent.getStringExtra(PairKey.APP_KEY)));
            bVar.e(g.d.a.j.b.e(intent.getStringExtra("appSecret")));
            bVar.setAppPackage(g.d.a.j.b.e(intent.getStringExtra("appPackage")));
            g.d.a.j.d.a("OnHandleIntent-message:" + bVar.toString());
            return bVar;
        } catch (Exception e2) {
            g.d.a.j.d.a("OnHandleIntent--" + e2.getMessage());
            return null;
        }
    }
}
