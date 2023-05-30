package com.meizu.cloud.pushsdk.handler.f.i;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.e.j.g;
import com.meizu.cloud.pushsdk.util.d;

/* loaded from: classes14.dex */
public class a extends com.meizu.cloud.pushsdk.handler.f.a<g> {
    public a(Context context, com.meizu.cloud.pushsdk.handler.a aVar) {
        super(context, aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: K  reason: merged with bridge method [inline-methods] */
    public void y(g gVar) {
        d.y(t(), t().getPackageName(), gVar.a().e().b(), gVar.a().e().i(), gVar.a().e().g(), gVar.a().e().k());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0108  */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: L  reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h(com.meizu.cloud.pushsdk.handler.e.j.g r11, com.meizu.cloud.pushsdk.notification.c r12) {
        /*
            r10 = this;
            java.lang.String r12 = "AbstractMessageHandler"
            com.meizu.cloud.pushinternal.DebugLogger.flush()
            com.meizu.cloud.pushsdk.handler.e.j.b r0 = r11.a()
            com.meizu.cloud.pushsdk.handler.e.j.f r0 = r0.e()
            java.lang.String r0 = r0.i()
            com.meizu.cloud.pushsdk.handler.e.j.b r1 = r11.a()
            com.meizu.cloud.pushsdk.handler.e.j.f r1 = r1.e()
            java.lang.String r4 = r1.b()
            android.content.Context r1 = r10.t()
            java.lang.String r5 = com.meizu.cloud.pushsdk.d.c.b(r1)
            android.content.Context r1 = r10.t()
            java.lang.String r1 = com.meizu.cloud.pushsdk.util.MzSystemUtils.getDocumentsPath(r1)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            java.lang.String r3 = "/pushSdktmp/"
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = "_"
            r2.append(r0)
            r2.append(r4)
            java.lang.String r0 = ".zip"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.meizu.cloud.pushsdk.handler.f.i.b r2 = new com.meizu.cloud.pushsdk.handler.f.i.b
            r2.<init>(r0)
            r3 = 0
            java.util.List r6 = r11.b()     // Catch: java.lang.Exception -> L61
            r2.b(r1, r6)     // Catch: java.lang.Exception -> L61
            java.io.File r1 = new java.io.File     // Catch: java.lang.Exception -> L61
            r1.<init>(r0)     // Catch: java.lang.Exception -> L61
            goto L7c
        L61:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "zip error message "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            com.meizu.cloud.pushinternal.DebugLogger.e(r12, r1)
            r1 = r3
            r3 = r0
        L7c:
            if (r1 == 0) goto L92
            long r6 = r1.length()
            r8 = 1024(0x400, double:5.06E-321)
            long r6 = r6 / r8
            int r0 = r11.c()
            long r8 = (long) r0
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 <= 0) goto L92
            java.lang.String r0 = "the upload file exceeds the max size"
        L90:
            r6 = r0
            goto La6
        L92:
            boolean r0 = r11.d()
            if (r0 == 0) goto La5
            android.content.Context r0 = r10.t()
            boolean r0 = com.meizu.cloud.pushsdk.util.a.b(r0)
            if (r0 != 0) goto La5
            java.lang.String r0 = "current network not allowed upload log file"
            goto L90
        La5:
            r6 = r3
        La6:
            android.content.Context r0 = r10.t()
            com.meizu.cloud.pushsdk.platform.c.b r2 = com.meizu.cloud.pushsdk.platform.c.b.b(r0)
            com.meizu.cloud.pushsdk.handler.e.j.b r11 = r11.a()
            com.meizu.cloud.pushsdk.handler.e.j.f r11 = r11.e()
            java.lang.String r3 = r11.i()
            r7 = r1
            com.meizu.cloud.pushsdk.e.b.c r11 = r2.a(r3, r4, r5, r6, r7)
            if (r11 == 0) goto Le7
            boolean r0 = r11.f()
            if (r0 == 0) goto Le7
            if (r1 == 0) goto Lcc
            r1.delete()
        Lcc:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "upload success "
            r0.append(r1)
            java.lang.Object r11 = r11.e()
            java.lang.String r11 = (java.lang.String) r11
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            com.meizu.cloud.pushinternal.DebugLogger.e(r12, r11)
            goto L10d
        Le7:
            if (r11 == 0) goto L108
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "upload error code "
            r0.append(r1)
            com.meizu.cloud.pushsdk.e.c.a r1 = r11.c()
            r0.append(r1)
            java.lang.Object r11 = r11.e()
            java.lang.String r11 = (java.lang.String) r11
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            goto L10a
        L108:
            java.lang.String r11 = "upload error"
        L10a:
            com.meizu.cloud.pushinternal.DebugLogger.i(r12, r11)
        L10d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.handler.f.i.a.h(com.meizu.cloud.pushsdk.handler.e.j.g, com.meizu.cloud.pushsdk.notification.c):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: M  reason: merged with bridge method [inline-methods] */
    public g D(Intent intent) {
        String stringExtra = intent.getStringExtra(PushConstants.MZ_PUSH_CONTROL_MESSAGE);
        String stringExtra2 = intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_SEQ_ID);
        return new g(intent.getStringExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE), stringExtra, intent.getStringExtra(PushConstants.MZ_PUSH_MESSAGE_STATISTICS_IMEI_KEY), stringExtra2);
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public int a() {
        return 65536;
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public boolean b(Intent intent) {
        int i2;
        DebugLogger.i("AbstractMessageHandler", "start LogUploadMessageHandler match");
        String stringExtra = intent.getStringExtra(PushConstants.MZ_PUSH_CONTROL_MESSAGE);
        if (!TextUtils.isEmpty(stringExtra)) {
            com.meizu.cloud.pushsdk.handler.e.j.b b = com.meizu.cloud.pushsdk.handler.e.j.b.b(stringExtra);
            if (b.a() != null) {
                i2 = b.a().a();
                return PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && "2".equals(String.valueOf(i2));
            }
        }
        i2 = 0;
        if (PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction())) {
            return false;
        }
    }
}
