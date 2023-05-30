package com.vivo.push.sdk.service;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.jingdong.sdk.platform.business.personal.R2;
import com.vivo.push.util.p;
import com.vivo.push.util.z;
import java.util.List;

/* loaded from: classes11.dex */
public class LinkProxyActivity extends Activity {
    /* JADX WARN: Removed duplicated region for block: B:45:0x00cf A[Catch: Exception -> 0x00e1, TryCatch #2 {Exception -> 0x00e1, blocks: (B:25:0x0094, B:27:0x009a, B:29:0x00a6, B:31:0x00ac, B:33:0x00b4, B:36:0x00bb, B:38:0x00c3, B:40:0x00c7, B:45:0x00cf, B:46:0x00d3), top: B:57:0x0094 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d3 A[Catch: Exception -> 0x00e1, TRY_LEAVE, TryCatch #2 {Exception -> 0x00e1, blocks: (B:25:0x0094, B:27:0x009a, B:29:0x00a6, B:31:0x00ac, B:33:0x00b4, B:36:0x00bb, B:38:0x00c3, B:40:0x00c7, B:45:0x00cf, B:46:0x00d3), top: B:57:0x0094 }] */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void onCreate(Bundle bundle) {
        PackageManager packageManager;
        List<ResolveInfo> queryIntentServices;
        ResolveInfo resolveInfo;
        ServiceInfo serviceInfo;
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            p.d("LinkProxyActivity", "enter RequestPermissionsActivity onCreate, intent is null, finish");
            finish();
            return;
        }
        boolean z = true;
        try {
            Window window = getWindow();
            window.setGravity(8388659);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.x = 0;
            attributes.y = 0;
            attributes.height = 1;
            attributes.width = 1;
            window.setAttributes(attributes);
        } catch (Throwable th) {
            p.b("LinkProxyActivity", "enter onCreate error ", th);
        }
        String packageName = getPackageName();
        p.d("LinkProxyActivity", hashCode() + " enter onCreate " + packageName);
        if ("com.vivo.abe".equals(packageName)) {
            try {
                if (intent == null) {
                    p.d("LinkProxyActivity", "adapterToService intent is null");
                } else if (intent.getExtras() == null) {
                    p.d("LinkProxyActivity", "adapterToService getExtras() is null");
                } else {
                    Intent intent2 = (Intent) intent.getExtras().get("previous_intent");
                    if (intent2 == null) {
                        p.d("LinkProxyActivity", "adapterToService proxyIntent is null");
                    } else {
                        z.a(this, intent2);
                    }
                }
            } catch (Exception e2) {
                p.a("LinkProxyActivity", e2.toString(), e2);
            }
        } else {
            try {
                if (intent.getExtras() != null) {
                    Intent intent3 = (Intent) intent.getExtras().get("previous_intent");
                    if (intent3 != null && (packageManager = getPackageManager()) != null && (queryIntentServices = packageManager.queryIntentServices(intent3, R2.attr.checked_is_bold)) != null && !queryIntentServices.isEmpty() && (resolveInfo = queryIntentServices.get(0)) != null && (serviceInfo = resolveInfo.serviceInfo) != null && serviceInfo.exported) {
                        if (!z) {
                            startService(intent3);
                        } else {
                            p.b("LinkProxyActivity", "service's exported is ".concat(String.valueOf(z)));
                        }
                    }
                    z = false;
                    if (!z) {
                    }
                }
            } catch (Exception e3) {
                p.a("LinkProxyActivity", e3.toString(), e3);
            }
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        p.d("LinkProxyActivity", hashCode() + " onDestory " + getPackageName());
    }
}
