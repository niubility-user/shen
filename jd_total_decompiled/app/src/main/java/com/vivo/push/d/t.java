package com.vivo.push.d;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.database.table.PushMessageTable;
import com.vivo.push.d.r;
import java.util.HashMap;

/* loaded from: classes11.dex */
final class t implements r.a {
    final /* synthetic */ s a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public t(s sVar) {
        this.a = sVar;
    }

    @Override // com.vivo.push.d.r.a
    public final void a() {
        Context context;
        Context context2;
        long l2 = com.vivo.push.e.a().l();
        if (l2 < 1400 && l2 != 1340) {
            com.vivo.push.util.p.b("OnNotificationArrivedTask", "\u5f15\u64ce\u7248\u672c\u592a\u4f4e\uff0c\u4e0d\u652f\u6301\u6b63\u5411\u5c55\u793a\u529f\u80fd\uff0cpushEngineSDKVersion\uff1a".concat(String.valueOf(l2)));
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("srt", "1");
        hashMap.put(PushMessageTable.TB_CLOUMN_MESSAGE_ID, String.valueOf(this.a.b.f()));
        context = ((com.vivo.push.l) this.a.f18267c).a;
        context2 = ((com.vivo.push.l) this.a.f18267c).a;
        String b = com.vivo.push.util.z.b(context, context2.getPackageName());
        if (!TextUtils.isEmpty(b)) {
            hashMap.put("app_id", b);
        }
        hashMap.put("type", "1");
        hashMap.put("dtp", "1");
        com.vivo.push.util.e.a(6L, hashMap);
    }

    @Override // com.vivo.push.d.r.a
    public final void b() {
        Context context;
        Context context2;
        HashMap hashMap = new HashMap();
        hashMap.put("messageID", String.valueOf(this.a.b.f()));
        context = ((com.vivo.push.l) this.a.f18267c).a;
        context2 = ((com.vivo.push.l) this.a.f18267c).a;
        String b = com.vivo.push.util.z.b(context, context2.getPackageName());
        if (!TextUtils.isEmpty(b)) {
            hashMap.put("remoteAppId", b);
        }
        com.vivo.push.util.e.a(2122L, hashMap);
    }
}
