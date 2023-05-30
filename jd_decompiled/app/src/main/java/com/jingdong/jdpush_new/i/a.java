package com.jingdong.jdpush_new.i;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.common.messagecenter.MIPushMsg;
import com.jingdong.jdpush_new.connect.e;
import com.jingdong.jdpush_new.connect.f;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdpush_new.j.n;
import com.jingdong.jdpush_new.j.o;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class a implements com.jingdong.jdpush_new.j.d<com.jingdong.jdpush_new.g.b> {
    private Context a;
    private f b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.jdpush_new.i.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public class RunnableC0498a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f12845g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ JSONObject f12846h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ com.jingdong.jdpush_new.g.c.c f12847i;

        RunnableC0498a(a aVar, Context context, JSONObject jSONObject, com.jingdong.jdpush_new.g.c.c cVar) {
            this.f12845g = context;
            this.f12846h = jSONObject;
            this.f12847i = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            n.a(this.f12845g, 2, this.f12846h.toString(), this.f12847i.a(), this.f12847i.h());
        }
    }

    public a(Context context, f fVar) {
        this.a = context;
        this.b = fVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(android.content.Context r5, com.jingdong.jdpush_new.g.c.c r6, short r7) {
        /*
            r4 = this;
            com.jingdong.jdpush_new.f.e r0 = com.jingdong.jdpush_new.f.e.i(r5)
            boolean r0 = r0.d(r6)
            if (r0 != 0) goto L6b
            r0 = 0
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> L37
            r1.<init>()     // Catch: org.json.JSONException -> L37
            java.lang.String r0 = "msgSeq"
            java.lang.String r2 = r6.f()     // Catch: org.json.JSONException -> L35
            r1.put(r0, r2)     // Catch: org.json.JSONException -> L35
            java.lang.String r0 = "msgBody"
            java.lang.String r2 = r6.e()     // Catch: org.json.JSONException -> L35
            r1.put(r0, r2)     // Catch: org.json.JSONException -> L35
            java.lang.String r0 = "echo"
            java.lang.String r2 = r6.d()     // Catch: org.json.JSONException -> L35
            r1.put(r0, r2)     // Catch: org.json.JSONException -> L35
            java.lang.String r0 = "sign"
            java.lang.String r2 = r6.i()     // Catch: org.json.JSONException -> L35
            r1.put(r0, r2)     // Catch: org.json.JSONException -> L35
            goto L3e
        L35:
            r0 = move-exception
            goto L3b
        L37:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
        L3b:
            com.jingdong.jdpush_new.j.g.g(r0)
        L3e:
            r0 = 2114(0x842, float:2.962E-42)
            if (r7 == r0) goto L67
            r0 = 2121(0x849, float:2.972E-42)
            if (r7 == r0) goto L47
            goto L83
        L47:
            r7 = 6
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = r6.a()
            java.lang.String r2 = r6.h()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L5f
            java.lang.String r6 = r6.h()
            goto L63
        L5f:
            java.lang.String r6 = r5.getPackageName()
        L63:
            com.jingdong.jdpush_new.j.n.a(r5, r7, r0, r1, r6)
            goto L83
        L67:
            r4.c(r5, r6, r1)
            goto L83
        L6b:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "msg repeated : "
            r5.append(r7)
            java.lang.String r6 = com.jingdong.jdpush_new.g.c.c.v(r6)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.jingdong.jdpush_new.j.g.c(r5)
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdpush_new.i.a.b(android.content.Context, com.jingdong.jdpush_new.g.c.c, short):void");
    }

    private void c(Context context, com.jingdong.jdpush_new.g.c.c cVar, JSONObject jSONObject) {
        if (!TextUtils.isEmpty(cVar.h())) {
            if (com.jingdong.jdpush_new.j.c.l(context).equals(cVar.h())) {
                n.a(context, 2, jSONObject.toString(), cVar.a(), cVar.h());
                g.h("send [ACTION_TYPE_RECEIVE_MSG] event to broadcast");
                return;
            }
            try {
                if (Build.VERSION.SDK_INT < 28) {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName(cVar.h(), "com.jingdong.jdpush_new.BridgeActivity"));
                    intent.setFlags(335544320);
                    context.startActivity(intent);
                    o.b().c(new RunnableC0498a(this, context, jSONObject, cVar), 1L, TimeUnit.SECONDS);
                    return;
                }
                throw new ActivityNotFoundException();
            } catch (ActivityNotFoundException unused) {
                return;
            }
        }
        g.c("error: no package name");
    }

    @Override // com.jingdong.jdpush_new.j.d
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public void accept(com.jingdong.jdpush_new.g.b bVar) {
        f fVar;
        com.jingdong.jdpush_new.g.b b;
        JSONObject jSONObject;
        com.jingdong.jdpush_new.g.c.c cVar;
        String msgBody = bVar.getMsgBody();
        short command = bVar.getCommand();
        if (TextUtils.isEmpty(msgBody)) {
            return;
        }
        com.jingdong.jdpush_new.g.c.c cVar2 = null;
        try {
            jSONObject = new JSONObject(msgBody);
            cVar = new com.jingdong.jdpush_new.g.c.c();
        } catch (JSONException unused) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            cVar.l(String.valueOf(jSONObject.optInt(MIPushMsg.APP_ID)));
            cVar.q(jSONObject.optString(MIPushMsg.MSG_SEQ));
            cVar.p(jSONObject.optString(MIPushMsg.MSG_BODY));
            cVar.r(String.valueOf(jSONObject.optInt(MIPushMsg.APP_ID)) + jSONObject.optString(MIPushMsg.MSG_SEQ));
            cVar.o(jSONObject.optString(MIPushMsg.ECHO));
            cVar.n(String.valueOf(new Date().getTime()));
            cVar.u("0");
            cVar.s(jSONObject.optString("PKG_NAME"));
            cVar.t(jSONObject.optString("SIGN"));
            cVar.m(jSONObject.optString("callbackParam"));
            b(this.a, cVar, command);
            fVar = this.b;
            b = e.b(this.a, cVar);
        } catch (JSONException unused2) {
            cVar2 = cVar;
            fVar = this.b;
            b = e.b(this.a, cVar2);
            fVar.g(b);
        } catch (Throwable th2) {
            th = th2;
            cVar2 = cVar;
            this.b.g(e.b(this.a, cVar2));
            throw th;
        }
        fVar.g(b);
    }
}
