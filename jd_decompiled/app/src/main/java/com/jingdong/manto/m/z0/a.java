package com.jingdong.manto.m.z0;

import com.jingdong.manto.m.d0;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends d0 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.m.z0.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public class RunnableC0638a implements Runnable {
        final /* synthetic */ e0 a;
        final /* synthetic */ int b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13832c;
        final /* synthetic */ String d;

        RunnableC0638a(e0 e0Var, int i2, int i3, String str) {
            this.a = e0Var;
            this.b = i2;
            this.f13832c = i3;
            this.d = str;
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0037, code lost:
            if (com.jingdong.manto.widget.input.o.a(com.jingdong.manto.m.c0.getPageView((com.jingdong.manto.h) r0), java.lang.Integer.valueOf(r6.f13832c)) != false) goto L11;
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x0039, code lost:
            r3 = com.jingdong.manto.jsapi.openmodule.IMantoBaseModule.SUCCESS;
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x003c, code lost:
            r3 = "fail:input not exists";
         */
        /* JADX WARN: Code restructure failed: missing block: B:7:0x001f, code lost:
            if (com.jingdong.manto.widget.input.o.a((com.jingdong.manto.q.n) r0, java.lang.Integer.valueOf(r6.f13832c)) != false) goto L11;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                r6 = this;
                com.jingdong.manto.m.e0 r0 = r6.a
                boolean r0 = r0.f()
                if (r0 == 0) goto L48
                com.jingdong.manto.m.e0 r0 = r6.a
                boolean r1 = r0 instanceof com.jingdong.manto.q.n
                if (r1 == 0) goto L22
                int r1 = r6.b
                com.jingdong.manto.m.z0.a r2 = com.jingdong.manto.m.z0.a.this
                r3 = r0
                com.jingdong.manto.q.n r3 = (com.jingdong.manto.q.n) r3
                int r4 = r6.f13832c
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                boolean r3 = com.jingdong.manto.widget.input.o.a(r3, r4)
                if (r3 == 0) goto L3c
                goto L39
            L22:
                int r1 = r6.b
                com.jingdong.manto.m.z0.a r2 = com.jingdong.manto.m.z0.a.this
                r3 = r0
                com.jingdong.manto.h r3 = (com.jingdong.manto.h) r3
                com.jingdong.manto.q.n r3 = com.jingdong.manto.m.c0.getPageView(r3)
                int r4 = r6.f13832c
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                boolean r3 = com.jingdong.manto.widget.input.o.a(r3, r4)
                if (r3 == 0) goto L3c
            L39:
                java.lang.String r3 = "ok"
                goto L3e
            L3c:
                java.lang.String r3 = "fail:input not exists"
            L3e:
                java.lang.String r4 = r6.d
                r5 = 0
                java.lang.String r2 = r2.putErrMsg(r3, r5, r4)
                r0.a(r1, r2)
            L48:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.m.z0.a.RunnableC0638a.run():void");
        }
    }

    private void a(e0 e0Var, JSONObject jSONObject, int i2, String str) {
        MantoUtils.runOnUiThread(new RunnableC0638a(e0Var, i2, jSONObject.optInt("inputId"), str));
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        a(hVar, jSONObject, i2, str);
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(n nVar, JSONObject jSONObject, int i2, String str) {
        a(nVar, jSONObject, i2, str);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "hideKeyboard";
    }
}
