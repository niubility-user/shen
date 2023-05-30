package com.jingdong.jdpush_new.i;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.common.messagecenter.MIPushMsg;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.jdpush_new.connect.f;
import com.jingdong.jdpush_new.f.e;
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
    */
    private void b(Context context, com.jingdong.jdpush_new.g.c.c cVar, short s) {
        JSONObject jSONObject;
        JSONException e2;
        if (!e.i(context).d(cVar)) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e3) {
                jSONObject = null;
                e2 = e3;
            }
            try {
                jSONObject.put(NotificationMessageSummary.MSG_SEQ, cVar.f());
                jSONObject.put(NotificationMessageSummary.MSG_BODY, cVar.e());
                jSONObject.put(NotificationMessageSummary.ECHO, cVar.d());
                jSONObject.put("sign", cVar.i());
            } catch (JSONException e4) {
                e2 = e4;
                g.g(e2);
                if (s != 2114) {
                }
            }
            if (s != 2114) {
                c(context, cVar, jSONObject);
                return;
            } else if (s != 2121) {
                return;
            } else {
                n.a(context, 6, jSONObject.toString(), cVar.a(), !TextUtils.isEmpty(cVar.h()) ? cVar.h() : context.getPackageName());
                return;
            }
        }
        g.c("msg repeated : " + com.jingdong.jdpush_new.g.c.c.v(cVar));
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
            b = com.jingdong.jdpush_new.connect.e.b(this.a, cVar);
        } catch (JSONException unused2) {
            cVar2 = cVar;
            fVar = this.b;
            b = com.jingdong.jdpush_new.connect.e.b(this.a, cVar2);
            fVar.g(b);
        } catch (Throwable th2) {
            th = th2;
            cVar2 = cVar;
            this.b.g(com.jingdong.jdpush_new.connect.e.b(this.a, cVar2));
            throw th;
        }
        fVar.g(b);
    }
}
