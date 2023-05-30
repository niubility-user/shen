package com.unionpay.b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.unionpay.UPQuerySEPayInfoCallback;
import com.unionpay.UPSEInfoResp;
import com.unionpay.tsmservice.mi.UPTsmAddon;
import com.unionpay.tsmservice.mi.request.QueryVendorPayStatusRequestParams;
import com.unionpay.utils.UPUtils;

/* loaded from: classes11.dex */
public final class g {
    private Context a;
    private UPQuerySEPayInfoCallback b;

    /* renamed from: c  reason: collision with root package name */
    private UPTsmAddon f18154c;
    private String d = "";

    /* renamed from: e  reason: collision with root package name */
    private String f18155e = "";

    /* renamed from: f  reason: collision with root package name */
    private boolean f18156f;

    /* renamed from: g  reason: collision with root package name */
    private QueryVendorPayStatusRequestParams f18157g;

    /* renamed from: h  reason: collision with root package name */
    private final Handler.Callback f18158h;

    /* renamed from: i  reason: collision with root package name */
    private final Handler f18159i;

    /* renamed from: j  reason: collision with root package name */
    private final UPTsmAddon.UPTsmConnectionListener f18160j;

    public g(Context context, UPQuerySEPayInfoCallback uPQuerySEPayInfoCallback) {
        this.f18156f = false;
        h hVar = new h(this);
        this.f18158h = hVar;
        this.f18159i = new Handler(hVar);
        this.f18160j = new i(this);
        this.a = context;
        this.b = uPQuerySEPayInfoCallback;
        this.f18156f = true;
        if (1 != 0) {
            System.loadLibrary("entryexpro");
            String a = UPUtils.a(this.a, "mode");
            String str = a != null ? a : "";
            try {
                Integer.decode(com.unionpay.utils.b.d(str) ? str : "02").intValue();
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(g gVar, int i2, String str) {
        if (i2 != 4000) {
            return;
        }
        gVar.a(gVar.d, gVar.f18155e, UPSEInfoResp.ERROR_NOT_SUPPORT, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(g gVar, Bundle bundle) {
        Context context;
        gVar.d = bundle.getString("vendorPayName");
        gVar.f18155e = bundle.getString("vendorPayAliasType");
        int i2 = bundle.getInt("vendorPayStatus");
        String string = bundle.getString("errorDesc");
        int i3 = bundle.getInt("cardNumber", 0);
        if (!TextUtils.isEmpty(gVar.f18155e) && (context = gVar.a) != null) {
            UPUtils.a(context, gVar.f18155e, "se_type");
        }
        if (i2 != 0) {
            if (i2 == 1) {
                gVar.a(gVar.d, gVar.f18155e, UPSEInfoResp.ERROR_NOT_READY, "not ready");
            } else if (i2 == 2 || i2 == 3 || i2 == 4) {
                gVar.a(gVar.d, gVar.f18155e, UPSEInfoResp.ERROR_NOT_SUPPORT, string);
            } else {
                gVar.a(gVar.d, gVar.f18155e, UPSEInfoResp.ERROR_NOT_SUPPORT, string);
            }
        } else if (i3 <= 0) {
            gVar.a(gVar.d, gVar.f18155e, UPSEInfoResp.ERROR_NOT_READY, "card number 0");
        } else {
            String str = gVar.d;
            String str2 = gVar.f18155e;
            gVar.c();
            UPQuerySEPayInfoCallback uPQuerySEPayInfoCallback = gVar.b;
            if (uPQuerySEPayInfoCallback != null) {
                uPQuerySEPayInfoCallback.onResult(str, str2, i3, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3, String str4) {
        c();
        UPQuerySEPayInfoCallback uPQuerySEPayInfoCallback = this.b;
        if (uPQuerySEPayInfoCallback != null) {
            uPQuerySEPayInfoCallback.onError(str, str2, str3, str4);
        }
    }

    private boolean a(String str) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = this.a.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException | Exception unused) {
        }
        if (packageInfo != null) {
            com.unionpay.utils.j.a("tsm-client", "tsm version code=" + packageInfo.versionCode);
            return packageInfo.versionCode >= 8;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ UPQuerySEPayInfoCallback b(g gVar) {
        gVar.b = null;
        return null;
    }

    private void c() {
        UPTsmAddon uPTsmAddon = this.f18154c;
        if (uPTsmAddon != null) {
            uPTsmAddon.removeConnectionListener(this.f18160j);
            this.f18154c.unbind();
        }
    }

    public final int a() {
        String str;
        String str2;
        String str3;
        String str4;
        if (this.a == null || this.b == null) {
            return UPSEInfoResp.PARAM_ERROR;
        }
        if (a("com.unionpay.tsmservice.mi")) {
            UPTsmAddon uPTsmAddon = UPTsmAddon.getInstance(this.a);
            this.f18154c = uPTsmAddon;
            uPTsmAddon.addConnectionListener(this.f18160j);
            com.unionpay.utils.j.c("uppay-spay", "type se  bind service");
            UPTsmAddon uPTsmAddon2 = this.f18154c;
            if (uPTsmAddon2 == null || uPTsmAddon2.isConnected()) {
                UPTsmAddon uPTsmAddon3 = this.f18154c;
                if (uPTsmAddon3 != null && uPTsmAddon3.isConnected()) {
                    com.unionpay.utils.j.c("uppay", "tsm service already connected");
                    b();
                }
            } else {
                com.unionpay.utils.j.c("uppay", "bind service");
                if (!this.f18154c.bind()) {
                    str = this.d;
                    str2 = this.f18155e;
                    str3 = UPSEInfoResp.ERROR_NONE;
                    str4 = "Tsm service bind fail";
                }
            }
            return UPSEInfoResp.SUCCESS;
        } else if (com.unionpay.utils.b.d(this.a, "com.unionpay.tsmservice.mi")) {
            str = this.d;
            str2 = this.f18155e;
            str3 = UPSEInfoResp.ERROR_NOT_SUPPORT;
            str4 = "Mi Tsm service apk version is low";
        } else {
            str = this.d;
            str2 = this.f18155e;
            str3 = UPSEInfoResp.ERROR_TSM_UNINSTALLED;
            str4 = "Mi Tsm service apk is not installed";
        }
        a(str, str2, str3, str4);
        return UPSEInfoResp.SUCCESS;
    }

    public final boolean b() {
        try {
            com.unionpay.utils.j.c("uppay", "getVendorPayStatus()");
            if (this.f18157g == null) {
                this.f18157g = new QueryVendorPayStatusRequestParams();
            }
            if (this.f18154c.queryVendorPayStatus(this.f18157g, new j(this.f18159i)) != 0) {
                com.unionpay.utils.j.c("uppay", "ret != 0");
                a(this.d, this.f18155e, UPSEInfoResp.ERROR_NOT_SUPPORT, "Mi Tsm service apk version is low");
                return false;
            }
            Handler handler = this.f18159i;
            handler.sendMessageDelayed(Message.obtain(handler, 4, 4000, 0, ""), Final.FIVE_SECOND);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
