package com.huawei.hms.hatool;

import com.jingdong.jdsdk.constant.CartConstant;
import java.util.Calendar;
import java.util.UUID;

/* loaded from: classes12.dex */
public class p0 {
    private long a = 1800000;
    private volatile boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    private a f1400c = null;

    /* loaded from: classes12.dex */
    private class a {
        String a = UUID.randomUUID().toString().replace("-", "");
        boolean b;

        /* renamed from: c  reason: collision with root package name */
        private long f1401c;

        a(long j2) {
            this.a += CartConstant.KEY_YB_INFO_LINK + j2;
            this.f1401c = j2;
            this.b = true;
            p0.this.b = false;
        }

        private boolean a(long j2, long j3) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j2);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTimeInMillis(j3);
            return (calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6)) ? false : true;
        }

        private void b(long j2) {
            v.c("hmsSdk", "getNewSession() session is flush!");
            String uuid = UUID.randomUUID().toString();
            this.a = uuid;
            this.a = uuid.replace("-", "");
            this.a += CartConstant.KEY_YB_INFO_LINK + j2;
            this.f1401c = j2;
            this.b = true;
        }

        private boolean b(long j2, long j3) {
            return j3 - j2 >= p0.this.a;
        }

        void a(long j2) {
            if (p0.this.b) {
                p0.this.b = false;
                b(j2);
            } else if (b(this.f1401c, j2) || a(this.f1401c, j2)) {
                b(j2);
            } else {
                this.f1401c = j2;
                this.b = false;
            }
        }
    }

    public String a() {
        a aVar = this.f1400c;
        if (aVar == null) {
            v.f("hmsSdk", "getSessionName(): session not prepared. onEvent() must be called first.");
            return "";
        }
        return aVar.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2) {
        a aVar = this.f1400c;
        if (aVar != null) {
            aVar.a(j2);
            return;
        }
        v.c("hmsSdk", "Session is first flush");
        this.f1400c = new a(j2);
    }

    public boolean b() {
        a aVar = this.f1400c;
        if (aVar == null) {
            v.f("hmsSdk", "isFirstEvent(): session not prepared. onEvent() must be called first.");
            return false;
        }
        return aVar.b;
    }
}
