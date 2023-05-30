package jpbury;

import android.content.Context;
import android.os.Build;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes11.dex */
public class i extends j {

    /* renamed from: e */
    public static final String f20132e = "https://cltm.jd.com/event/newWrite";

    public i() {
        this(f20132e);
    }

    public i(String str) {
        super(str);
        a("os", "Android");
        a(HybridSDK.OS_VERSION, Build.VERSION.RELEASE);
        a("deviceName", BaseInfo.getDeviceModel());
        a("rootState", e0.b());
        a("proxyState", e0.a());
        Context b = a0.b();
        if (b == null) {
            return;
        }
        a(f0.b(b));
        b(f0.c(b));
        a("appBuild", f0.a(b));
    }
}
