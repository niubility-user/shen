package com.jd.manto.c;

import android.app.Activity;
import android.os.Bundle;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoPermission;
import com.jingdong.moutaibuy.lib.g.a;

/* loaded from: classes17.dex */
public class b implements com.jingdong.moutaibuy.lib.g.a {
    static final Bundle a = PermissionHelper.generateBundle("moutai", "moutai", "impl", true);

    /* loaded from: classes17.dex */
    class a implements IPermission.PermissionCallBack {
        final /* synthetic */ a.InterfaceC0707a a;

        a(b bVar, a.InterfaceC0707a interfaceC0707a) {
            this.a = interfaceC0707a;
        }

        @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
        public void onDenied() {
            a.InterfaceC0707a interfaceC0707a = this.a;
            if (interfaceC0707a != null) {
                interfaceC0707a.onDenied();
            }
        }

        @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
        public void onGranted() {
            a.InterfaceC0707a interfaceC0707a = this.a;
            if (interfaceC0707a != null) {
                interfaceC0707a.onGranted();
            }
        }
    }

    @Override // com.jingdong.moutaibuy.lib.g.a
    public void a(Activity activity, String str, a.InterfaceC0707a interfaceC0707a) {
        MantoPermission.requestPermission(activity, str, new a(this, interfaceC0707a));
    }

    @Override // com.jingdong.moutaibuy.lib.g.a
    public boolean hasPermission(Activity activity, String str) {
        return PermissionHelper.hasPermission(a, str);
    }

    @Override // com.jingdong.moutaibuy.lib.g.a
    public void onRequestPermissionsResult(Activity activity, int i2, String[] strArr, int[] iArr) {
        PermissionHelper.onRequestPermissionsResult(activity, i2, strArr, iArr);
    }
}
