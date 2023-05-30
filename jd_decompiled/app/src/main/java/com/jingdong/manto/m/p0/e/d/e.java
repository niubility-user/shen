package com.jingdong.manto.m.p0.e.d;

import android.util.Pair;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;

/* loaded from: classes15.dex */
public class e extends d0 {

    /* loaded from: classes15.dex */
    class a extends com.jingdong.manto.m.p0.e.c {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ boolean f13531c;
        final /* synthetic */ float d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ int f13532e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f13533f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ h f13534g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f13535h;

        a(String str, boolean z, boolean z2, float f2, int i2, String str2, h hVar, int i3) {
            this.a = str;
            this.b = z;
            this.f13531c = z2;
            this.d = f2;
            this.f13532e = i2;
            this.f13533f = str2;
            this.f13534g = hVar;
            this.f13535h = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar;
            int i2;
            e eVar;
            String str;
            boolean booleanValue;
            String str2;
            try {
                Pair<Boolean, String> a = com.jingdong.manto.m.p0.e.b.a(this.a, this.b, this.f13531c, this.d, this.f13532e, this.f13533f);
                booleanValue = ((Boolean) a.first).booleanValue();
                str2 = (String) a.second;
            } catch (Throwable unused) {
                hVar = this.f13534g;
                i2 = this.f13535h;
                eVar = e.this;
                str = "fail:set audio state fail";
            }
            if (booleanValue) {
                hVar = this.f13534g;
                i2 = this.f13535h;
                eVar = e.this;
                str = IMantoBaseModule.SUCCESS;
                hVar.a(i2, eVar.putErrMsg(str));
                return;
            }
            this.f13534g.a(this.f13535h, e.this.putErrMsg("fail:" + str2));
        }
    }

    /* loaded from: classes15.dex */
    public static class b extends com.jingdong.manto.m.d {
        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onAudioStateChange";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c0  */
    @Override // com.jingdong.manto.m.d0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void exec(com.jingdong.manto.h r11, org.json.JSONObject r12, int r13, java.lang.String r14) {
        /*
            r10 = this;
            r11.a()
            boolean r14 = r10.isAppForeground(r11)
            java.lang.String r0 = "Audio.SetAudioState"
            if (r14 != 0) goto L1a
            java.lang.String r12 = "can't do setAudioState, App is paused or background"
            com.jingdong.manto.utils.MantoLog.d(r0, r12)
            java.lang.String r12 = "fail:App is paused or background"
        L12:
            java.lang.String r12 = r10.putErrMsg(r12)
            r11.a(r13, r12)
            return
        L1a:
            if (r12 != 0) goto L24
            java.lang.String r12 = "setAudioState data is null"
            com.jingdong.manto.utils.MantoLog.d(r0, r12)
            java.lang.String r12 = "fail:data is null"
            goto L12
        L24:
            java.lang.String r14 = "audioId"
            java.lang.String r2 = r12.optString(r14)
            java.lang.String r14 = "startTime"
            r0 = 0
            int r14 = r12.optInt(r14, r0)
            int r6 = r14 / 1000
            java.lang.String r14 = "src"
            java.lang.String r14 = r12.optString(r14)
            boolean r1 = android.text.TextUtils.isEmpty(r14)
            java.lang.String r3 = ""
            if (r1 == 0) goto L43
        L41:
            r7 = r3
            goto L9b
        L43:
            java.lang.String r1 = "jdfile://"
            boolean r4 = r14.startsWith(r1)
            if (r4 == 0) goto L77
            java.lang.String r1 = r14.replace(r1, r3)
            com.jingdong.manto.f r4 = r11.h()
            java.lang.String r4 = r4.f13019k
            com.jingdong.manto.t.d r14 = com.jingdong.manto.t.c.g(r4, r14)
            if (r14 == 0) goto L41
            java.lang.String r4 = r14.b
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L41
            java.lang.String r3 = r11.c()
            java.lang.String r1 = com.jingdong.manto.t.c.e(r3, r1)
            boolean r3 = com.jingdong.manto.utils.s.d(r1)
            if (r3 != 0) goto L9a
            java.lang.String r14 = r14.b
            com.jingdong.manto.utils.s.a(r14, r1)
            goto L9a
        L77:
            java.lang.String r1 = "http"
            boolean r1 = r14.startsWith(r1)
            if (r1 == 0) goto L81
            r7 = r14
            goto L9b
        L81:
            java.lang.String r1 = r11.c()
            java.lang.String r1 = com.jingdong.manto.t.c.e(r1, r14)
            boolean r3 = com.jingdong.manto.utils.s.d(r1)
            if (r3 != 0) goto L9a
            com.jingdong.manto.f r3 = r11.h()
            java.io.InputStream r14 = com.jingdong.manto.pkg.b.g.d(r3, r14)
            com.jingdong.manto.utils.s.a(r14, r1)
        L9a:
            r7 = r1
        L9b:
            java.lang.String r14 = "autoplay"
            boolean r3 = r12.optBoolean(r14, r0)
            java.lang.String r14 = "loop"
            boolean r4 = r12.optBoolean(r14, r0)
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.String r14 = "volume"
            double r0 = r12.optDouble(r14, r0)
            float r5 = (float) r0
            boolean r12 = android.text.TextUtils.isEmpty(r2)
            if (r12 == 0) goto Lc0
            java.lang.String r12 = "fail:audioId is empty"
        Lb8:
            java.lang.String r12 = r10.putErrMsg(r12)
            r11.a(r13, r12)
            goto Ld5
        Lc0:
            boolean r12 = android.text.TextUtils.isEmpty(r7)
            if (r12 == 0) goto Lc9
            java.lang.String r12 = "fail:src is empty"
            goto Lb8
        Lc9:
            com.jingdong.manto.m.p0.e.d.e$a r12 = new com.jingdong.manto.m.p0.e.d.e$a
            r0 = r12
            r1 = r10
            r8 = r11
            r9 = r13
            r0.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            r12.a()
        Ld5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.m.p0.e.d.e.exec(com.jingdong.manto.h, org.json.JSONObject, int, java.lang.String):void");
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setAudioState";
    }
}
