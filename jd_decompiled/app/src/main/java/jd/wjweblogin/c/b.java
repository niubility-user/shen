package jd.wjweblogin.c;

import android.text.TextUtils;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import jd.wjweblogin.d.g;

/* loaded from: classes11.dex */
public class b {
    private static final String b = "WJWebLogin.HttpRequestUtils";

    /* renamed from: c  reason: collision with root package name */
    private static b f20013c;
    private int a = 5;

    public void a(int i2) {
        this.a = i2;
    }

    public HttpSetting b() {
        String str;
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setPost(true);
        httpSetting.setUseFastJsonParser(true);
        String b2 = c.b();
        g.b(b, "getSetting host=" + b2);
        httpSetting.setHost(b2);
        httpSetting.setEffect(1);
        httpSetting.setNotifyUser(false);
        httpSetting.setCallTimeout(c() * 1000);
        String str2 = "";
        if (jd.wjweblogin.common.c.a.d() == null || jd.wjweblogin.common.c.a.d().getNetInitParams() == null) {
            str = "";
        } else {
            str2 = jd.wjweblogin.common.c.a.d().getNetInitParams().getNetAppId();
            str = jd.wjweblogin.common.c.a.d().getNetInitParams().getNetAppSecretKey();
        }
        if (!TextUtils.isEmpty(str2)) {
            httpSetting.setAppId(str2);
        }
        if (!TextUtils.isEmpty(str)) {
            httpSetting.setSecretKey(str);
        }
        return httpSetting;
    }

    public int c() {
        return this.a;
    }

    public static b a() {
        g.b(b, "getInstance");
        if (f20013c == null) {
            f20013c = new b();
        }
        return f20013c;
    }

    public void a(String str, String str2, int i2, int i3, HttpGroup.OnCommonListener onCommonListener) {
        String str3;
        g.b(b, "request");
        if (jd.wjweblogin.common.c.a.d() != null) {
            jd.wjweblogin.common.c.a.d().initJDHttpTookit();
        }
        if (TextUtils.isEmpty(str)) {
            str3 = "";
        } else {
            str3 = jd.wjweblogin.b.b.b(str);
            g.b(b, "request ptPin=" + str + "  enctryPtPin=" + str3);
        }
        HttpSetting b2 = b();
        b2.setFunctionId("ptLogin");
        b2.putJsonParam("appid", Integer.valueOf(i2));
        b2.putJsonParam("sceneid", Integer.valueOf(i3));
        if (!TextUtils.isEmpty(str3)) {
            b2.putJsonParam("ptlogin_key", str2);
            b2.putJsonParam("ptlogin_pin", str3);
        }
        b2.setListener(onCommonListener);
        HttpGroupUtils.getHttpGroupaAsynPool().add(b2);
    }
}
