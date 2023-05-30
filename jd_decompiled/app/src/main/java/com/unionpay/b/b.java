package com.unionpay.b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.nfc.sdk.service.HwOpenPayTask;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.sdk.platform.business.personal.R2;
import com.unionpay.UPQuerySEPayInfoCallback;
import com.unionpay.UPSEInfoResp;
import com.unionpay.tsmservice.UPTsmAddon;
import com.unionpay.tsmservice.data.Constant;
import com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams;
import com.unionpay.utils.UPUtils;

/* loaded from: classes11.dex */
public final class b {
    private Context a;
    private UPQuerySEPayInfoCallback b;

    /* renamed from: c  reason: collision with root package name */
    private UPTsmAddon f18146c;

    /* renamed from: f  reason: collision with root package name */
    private boolean f18148f;

    /* renamed from: h  reason: collision with root package name */
    private QueryVendorPayStatusRequestParams f18150h;

    /* renamed from: i  reason: collision with root package name */
    private final Handler.Callback f18151i;

    /* renamed from: j  reason: collision with root package name */
    private final Handler f18152j;

    /* renamed from: k  reason: collision with root package name */
    private final UPTsmAddon.UPTsmConnectionListener f18153k;
    private String d = "";

    /* renamed from: e  reason: collision with root package name */
    private String f18147e = "";

    /* renamed from: g  reason: collision with root package name */
    private boolean f18149g = false;

    public b(Context context, UPQuerySEPayInfoCallback uPQuerySEPayInfoCallback) {
        this.f18148f = false;
        c cVar = new c(this);
        this.f18151i = cVar;
        this.f18152j = new Handler(cVar);
        this.f18153k = new e(this);
        this.a = context;
        this.b = uPQuerySEPayInfoCallback;
        this.f18148f = true;
        if (1 != 0) {
            System.loadLibrary("entryexpro");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(b bVar, int i2, String str) {
        if (i2 != 4000) {
            return;
        }
        bVar.a(bVar.d, bVar.f18147e, UPSEInfoResp.ERROR_NOT_SUPPORT, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(b bVar, Bundle bundle) {
        Context context;
        bVar.d = bundle.getString("vendorPayName");
        bVar.f18147e = bundle.getString("vendorPayAliasType");
        int i2 = bundle.getInt("vendorPayStatus");
        String string = bundle.getString("errorDesc");
        int i3 = bundle.getInt("cardNumber", 0);
        if (!TextUtils.isEmpty(bVar.f18147e) && (context = bVar.a) != null) {
            UPUtils.a(context, bVar.f18147e, "se_type");
        }
        if (i2 == 0) {
            if (i3 > 0) {
                bVar.a(bVar.d, bVar.f18147e, i3, bundle);
            } else {
                bVar.a(bVar.d, bVar.f18147e, UPSEInfoResp.ERROR_NOT_READY, "card number 0");
            }
        } else if (i2 == 1) {
            bVar.a(bVar.d, bVar.f18147e, UPSEInfoResp.ERROR_NOT_READY, "not ready");
        } else if (i2 == 2 || i2 == 3 || i2 == 4) {
            bVar.a(bVar.d, bVar.f18147e, UPSEInfoResp.ERROR_NOT_SUPPORT, string);
        } else {
            bVar.a(bVar.d, bVar.f18147e, UPSEInfoResp.ERROR_NOT_SUPPORT, string);
        }
    }

    private void a(String str, String str2, int i2, Bundle bundle) {
        d();
        UPQuerySEPayInfoCallback uPQuerySEPayInfoCallback = this.b;
        if (uPQuerySEPayInfoCallback != null) {
            uPQuerySEPayInfoCallback.onResult(str, str2, i2, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3, String str4) {
        d();
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
            return packageInfo.versionCode >= 18;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(b bVar, Bundle bundle) {
        if (bundle != null) {
            bVar.d = "Huawei Pay";
            bVar.f18147e = Constant.RECHARGE_MODE_DESIGNATED_AND_CACH;
            if (!"0000".equals(bundle.getString("resultCode"))) {
                bVar.a(bVar.d, bVar.f18147e, UPSEInfoResp.ERROR_NOT_READY, "not ready");
                return;
            }
            bVar.a(bVar.d, bVar.f18147e, bundle.getInt("cardNumber"), bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ UPQuerySEPayInfoCallback c(b bVar) {
        bVar.b = null;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        String str;
        String str2;
        String str3;
        String str4;
        if (!a("com.unionpay.tsmservice")) {
            if (com.unionpay.utils.b.d(this.a, "com.unionpay.tsmservice")) {
                str = this.d;
                str2 = this.f18147e;
                str3 = UPSEInfoResp.ERROR_NOT_SUPPORT;
                str4 = "Tsm service apk version is low";
            } else {
                str = this.d;
                str2 = this.f18147e;
                str3 = UPSEInfoResp.ERROR_TSM_UNINSTALLED;
                str4 = "Tsm service apk is not installed";
            }
            a(str, str2, str3, str4);
            return;
        }
        UPTsmAddon uPTsmAddon = UPTsmAddon.getInstance(this.a);
        this.f18146c = uPTsmAddon;
        uPTsmAddon.addConnectionListener(this.f18153k);
        com.unionpay.utils.j.c("uppay-spay", "type se  bind service");
        UPTsmAddon uPTsmAddon2 = this.f18146c;
        if (uPTsmAddon2 != null && !uPTsmAddon2.isConnected()) {
            com.unionpay.utils.j.c("uppay", "bind service");
            if (this.f18146c.bind()) {
                return;
            }
            a(this.d, this.f18147e, UPSEInfoResp.ERROR_NONE, "Tsm service bind fail");
            return;
        }
        UPTsmAddon uPTsmAddon3 = this.f18146c;
        if (uPTsmAddon3 == null || !uPTsmAddon3.isConnected()) {
            return;
        }
        com.unionpay.utils.j.c("uppay", "tsm service already connected");
        b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void c(b bVar, Bundle bundle) {
        if (bundle != null) {
            bVar.d = "Huawei Pay";
            bVar.f18147e = Constant.RECHARGE_MODE_DESIGNATED_AND_CACH;
            String string = bundle.getString("errorCode");
            bVar.a(bVar.d, bVar.f18147e, "0002".equals(string) ? UPSEInfoResp.ERROR_NOT_READY : UPSEInfoResp.ERROR_NOT_SUPPORT, bundle.getString("errorDesc"));
        }
    }

    private void d() {
        UPTsmAddon uPTsmAddon = this.f18146c;
        if (uPTsmAddon != null) {
            uPTsmAddon.removeConnectionListener(this.f18153k);
            this.f18146c.unbind();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void e(b bVar) {
        HwOpenPayTask hwOpenPayTask = new HwOpenPayTask(bVar.a);
        com.unionpay.utils.j.c("uppay", "queryHwPayStatus start");
        bVar.f18152j.sendEmptyMessageDelayed(R2.color.key_step_logger_analyser_bg_color, 3000L);
        hwOpenPayTask.getUnionOnlinePayStatus(new f(bVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean g(b bVar) {
        bVar.f18149g = true;
        return true;
    }

    public final int a() {
        if (this.a == null || this.b == null) {
            return UPSEInfoResp.PARAM_ERROR;
        }
        this.f18149g = false;
        if (com.unionpay.utils.b.b()) {
            HwOpenPayTask hwOpenPayTask = new HwOpenPayTask(this.a);
            com.unionpay.utils.j.c("uppay", "supportCapacity");
            this.f18152j.sendEmptyMessageDelayed(R2.color.keyboard_color_action_text_dark, 2000L);
            hwOpenPayTask.supportCapacity("UNIONONLINEPAY", new d(this));
        } else {
            c();
        }
        return UPSEInfoResp.SUCCESS;
    }

    public final boolean b() {
        try {
            com.unionpay.utils.j.c("uppay", "getVendorPayStatus()");
            if (this.f18150h == null) {
                this.f18150h = new QueryVendorPayStatusRequestParams();
            }
            if (this.f18146c.queryVendorPayStatus(this.f18150h, new a(this.f18152j)) != 0) {
                com.unionpay.utils.j.c("uppay", "ret != 0");
                a(this.d, this.f18147e, UPSEInfoResp.ERROR_NOT_SUPPORT, "Tsm service apk version is low");
                return false;
            }
            Handler handler = this.f18152j;
            handler.sendMessageDelayed(Message.obtain(handler, 4, 4000, 0, ""), Final.FIVE_SECOND);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
