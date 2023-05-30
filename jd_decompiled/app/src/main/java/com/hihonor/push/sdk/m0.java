package com.hihonor.push.sdk;

import android.content.Intent;
import java.util.concurrent.Callable;

/* loaded from: classes12.dex */
public class m0 implements Callable<c> {

    /* renamed from: g */
    public final Intent f1103g;

    public m0(Intent intent) {
        this.f1103g = intent;
    }

    /* JADX WARN: Removed duplicated region for block: B:85:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:98:? A[RETURN, SYNTHETIC] */
    @Override // java.util.concurrent.Callable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.hihonor.push.sdk.c call() throws java.lang.Exception {
        /*
            r9 = this;
            java.lang.String r0 = "PassByMsgIntentParser"
            android.content.Intent r1 = r9.f1103g
            r2 = 0
            if (r1 == 0) goto L9d
            r3 = 0
            java.lang.String r5 = "msg_id"
            long r3 = r1.getLongExtra(r5, r3)     // Catch: java.lang.Exception -> L10
            goto L16
        L10:
            r1 = move-exception
            java.lang.String r5 = "parserMsgId"
            com.hihonor.push.sdk.l.b(r0, r5, r1)
        L16:
            android.content.Intent r1 = r9.f1103g
            java.lang.String r5 = "msg_content"
            byte[] r0 = r1.getByteArrayExtra(r5)     // Catch: java.lang.Exception -> L1f
            goto L26
        L1f:
            r1 = move-exception
            java.lang.String r5 = "parseMsgContent"
            com.hihonor.push.sdk.l.b(r0, r5, r1)
            r0 = r2
        L26:
            if (r0 == 0) goto L7e
            int r1 = r0.length
            if (r1 != 0) goto L2c
            goto L7e
        L2c:
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream
            r1.<init>(r0)
            java.util.zip.InflaterInputStream r0 = new java.util.zip.InflaterInputStream
            java.util.zip.Inflater r5 = new java.util.zip.Inflater
            r5.<init>()
            r0.<init>(r1, r5)
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream
            r5.<init>()
            r6 = 256(0x100, float:3.59E-43)
            byte[] r6 = new byte[r6]     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
        L44:
            int r7 = r0.read(r6)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            if (r7 <= 0) goto L4f
            r8 = 0
            r5.write(r6, r8, r7)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            goto L44
        L4f:
            java.lang.String r6 = "UTF-8"
            java.lang.String r6 = r5.toString(r6)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            com.hihonor.push.sdk.i.i(r1)
            com.hihonor.push.sdk.i.i(r0)
            com.hihonor.push.sdk.i.i(r5)
            goto L7f
        L5f:
            r2 = move-exception
            goto L74
        L61:
            r6 = move-exception
            java.lang.String r7 = "DeflateUtil"
            java.lang.String r8 = "unZipString"
            com.hihonor.push.sdk.l.b(r7, r8, r6)     // Catch: java.lang.Throwable -> L5f
            com.hihonor.push.sdk.i.i(r1)
            com.hihonor.push.sdk.i.i(r0)
            com.hihonor.push.sdk.i.i(r5)
            goto L7e
        L74:
            com.hihonor.push.sdk.i.i(r1)
            com.hihonor.push.sdk.i.i(r0)
            com.hihonor.push.sdk.i.i(r5)
            throw r2
        L7e:
            r6 = r2
        L7f:
            if (r6 == 0) goto L9d
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>(r6)
            java.lang.String r1 = "data"
            java.lang.String r0 = r0.optString(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L9d
            com.hihonor.push.sdk.c r2 = new com.hihonor.push.sdk.c
            r2.<init>()
            r2.f(r3)
            r2.e(r0)
        L9d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hihonor.push.sdk.m0.call():java.lang.Object");
    }
}
