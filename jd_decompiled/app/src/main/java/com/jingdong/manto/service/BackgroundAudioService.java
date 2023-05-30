package com.jingdong.manto.service;

import android.app.IntentService;

/* loaded from: classes16.dex */
public class BackgroundAudioService extends IntentService {
    public BackgroundAudioService() {
        super("Audio.BackgroundService");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0042, code lost:
        if (r2.equals("Next") == false) goto L14;
     */
    @Override // android.app.IntentService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onHandleIntent(@androidx.annotation.Nullable android.content.Intent r7) {
        /*
            r6 = this;
            if (r7 != 0) goto L3
            return
        L3:
            java.lang.String r7 = r7.getAction()
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            if (r0 == 0) goto Le
            return
        Le:
            java.lang.String r0 = "_"
            java.lang.String[] r7 = r7.split(r0)
            if (r7 == 0) goto L6d
            int r0 = r7.length
            r1 = 2
            if (r0 >= r1) goto L1b
            goto L6d
        L1b:
            r0 = 0
            r2 = r7[r0]
            r3 = 1
            r7 = r7[r3]
            r2.hashCode()
            r2.hashCode()
            r4 = -1
            int r5 = r2.hashCode()
            switch(r5) {
                case -1209131241: goto L50;
                case -134623265: goto L45;
                case 2424595: goto L3c;
                case 65203672: goto L31;
                default: goto L2f;
            }
        L2f:
            r1 = -1
            goto L5a
        L31:
            java.lang.String r0 = "Close"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L3a
            goto L2f
        L3a:
            r1 = 3
            goto L5a
        L3c:
            java.lang.String r0 = "Next"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L5a
            goto L2f
        L45:
            java.lang.String r0 = "PlayOrPause"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L4e
            goto L2f
        L4e:
            r1 = 1
            goto L5a
        L50:
            java.lang.String r1 = "Previous"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L59
            goto L2f
        L59:
            r1 = 0
        L5a:
            switch(r1) {
                case 0: goto L6a;
                case 1: goto L66;
                case 2: goto L62;
                case 3: goto L5e;
                default: goto L5d;
            }
        L5d:
            goto L6d
        L5e:
            com.jingdong.manto.m.p0.d.a.b(r7)
            goto L6d
        L62:
            com.jingdong.manto.m.p0.d.a.c(r7)
            goto L6d
        L66:
            com.jingdong.manto.m.p0.d.a.d(r7)
            goto L6d
        L6a:
            com.jingdong.manto.m.p0.d.a.e(r7)
        L6d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.service.BackgroundAudioService.onHandleIntent(android.content.Intent):void");
    }
}
