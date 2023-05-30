package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Build;
import com.tencent.smtt.export.external.interfaces.IX5CoreServiceWorkerController;
import com.tencent.smtt.export.external.interfaces.ServiceWorkerClient;
import com.tencent.smtt.export.external.interfaces.ServiceWorkerWebSettings;

/* loaded from: classes9.dex */
public abstract class ServiceWorkerController {
    public static ServiceWorkerController getInstance(Context context) {
        u a = u.a();
        a.a(context);
        if (!a.b()) {
            if (Build.VERSION.SDK_INT >= 24) {
                return new j();
            }
            return null;
        }
        final IX5CoreServiceWorkerController q = u.a().c().q();
        if (q != null) {
            return new ServiceWorkerController() { // from class: com.tencent.smtt.sdk.ServiceWorkerController.1
                @Override // com.tencent.smtt.sdk.ServiceWorkerController
                public ServiceWorkerWebSettings getServiceWorkerWebSettings() {
                    return IX5CoreServiceWorkerController.this.getServiceWorkerWebSettings();
                }

                @Override // com.tencent.smtt.sdk.ServiceWorkerController
                public void setServiceWorkerClient(ServiceWorkerClient serviceWorkerClient) {
                    IX5CoreServiceWorkerController.this.setServiceWorkerClient(serviceWorkerClient);
                }
            };
        }
        return null;
    }

    public abstract ServiceWorkerWebSettings getServiceWorkerWebSettings();

    public abstract void setServiceWorkerClient(ServiceWorkerClient serviceWorkerClient);
}
