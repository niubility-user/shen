package com.meizu.cloud.pushsdk.handler.f.i;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.e.j.g;
import com.meizu.cloud.pushsdk.notification.c;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import com.meizu.cloud.pushsdk.util.d;
import java.io.File;

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
    */
    public void h(g gVar, c cVar) {
        File file;
        String str;
        String str2;
        com.meizu.cloud.pushsdk.e.b.c<String> a;
        String str3;
        DebugLogger.flush();
        String i2 = gVar.a().e().i();
        String b = gVar.a().e().b();
        String b2 = com.meizu.cloud.pushsdk.d.c.b(t());
        String documentsPath = MzSystemUtils.getDocumentsPath(t());
        String str4 = documentsPath + "/pushSdktmp/" + i2 + CartConstant.KEY_YB_INFO_LINK + b + ".zip";
        String str5 = null;
        try {
            new b(str4).b(documentsPath, gVar.b());
            file = new File(str4);
        } catch (Exception e2) {
            String message = e2.getMessage();
            DebugLogger.e("AbstractMessageHandler", "zip error message " + message);
            file = null;
            str5 = message;
        }
        if (file != null && file.length() / 1024 > gVar.c()) {
            str2 = "the upload file exceeds the max size";
        } else if (!gVar.d() || com.meizu.cloud.pushsdk.util.a.b(t())) {
            str = str5;
            a = com.meizu.cloud.pushsdk.platform.c.b.b(t()).a(gVar.a().e().i(), b, b2, str, file);
            if (a == null && a.f()) {
                if (file != null) {
                    file.delete();
                }
                DebugLogger.e("AbstractMessageHandler", "upload success " + a.e());
                return;
            }
            if (a == null) {
                str3 = "upload error code " + a.c() + a.e();
            } else {
                str3 = "upload error";
            }
            DebugLogger.i("AbstractMessageHandler", str3);
        } else {
            str2 = "current network not allowed upload log file";
        }
        str = str2;
        a = com.meizu.cloud.pushsdk.platform.c.b.b(t()).a(gVar.a().e().i(), b, b2, str, file);
        if (a == null) {
        }
        if (a == null) {
        }
        DebugLogger.i("AbstractMessageHandler", str3);
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
