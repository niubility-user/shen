package com.hihonor.push.sdk;

import android.content.Intent;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class m0 implements Callable<c> {

    /* renamed from: g */
    public final Intent f1103g;

    public m0(Intent intent) {
        this.f1103g = intent;
    }

    /* JADX WARN: Removed duplicated region for block: B:135:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:148:? A[RETURN, SYNTHETIC] */
    @Override // java.util.concurrent.Callable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public c call() throws Exception {
        byte[] bArr;
        String str;
        Intent intent = this.f1103g;
        if (intent == null) {
            return null;
        }
        long j2 = 0;
        try {
            j2 = intent.getLongExtra("msg_id", 0L);
        } catch (Exception e2) {
            l.b("PassByMsgIntentParser", "parserMsgId", e2);
        }
        try {
            bArr = this.f1103g.getByteArrayExtra("msg_content");
        } catch (Exception e3) {
            l.b("PassByMsgIntentParser", "parseMsgContent", e3);
            bArr = null;
        }
        if (bArr != null && bArr.length != 0) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            InflaterInputStream inflaterInputStream = new InflaterInputStream(byteArrayInputStream, new Inflater());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byte[] bArr2 = new byte[256];
                while (true) {
                    int read = inflaterInputStream.read(bArr2);
                    if (read <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
                str = byteArrayOutputStream.toString("UTF-8");
            } catch (IOException e4) {
                l.b("DeflateUtil", "unZipString", e4);
            } finally {
                i.i(byteArrayInputStream);
                i.i(inflaterInputStream);
                i.i(byteArrayOutputStream);
            }
            if (str == null) {
                String optString = new JSONObject(str).optString("data");
                if (TextUtils.isEmpty(optString)) {
                    return null;
                }
                c cVar = new c();
                cVar.f(j2);
                cVar.e(optString);
                return cVar;
            }
            return null;
        }
        str = null;
        if (str == null) {
        }
    }
}
