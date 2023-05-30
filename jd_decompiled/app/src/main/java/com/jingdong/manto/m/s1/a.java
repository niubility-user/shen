package com.jingdong.manto.m.s1;

import android.app.Activity;
import android.content.DialogInterface;
import android.text.TextUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.R;
import com.jingdong.manto.f;
import com.jingdong.manto.h;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.pkg.PkgManager;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends d0 {

    /* renamed from: com.jingdong.manto.m.s1.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0613a implements PkgManager.k {
        final /* synthetic */ PkgDetailEntity a;
        final /* synthetic */ h b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13585c;
        final /* synthetic */ String d;

        /* renamed from: com.jingdong.manto.m.s1.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class RunnableC0614a implements Runnable {
            final /* synthetic */ PkgDetailEntity a;

            /* renamed from: com.jingdong.manto.m.s1.a$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes15.dex */
            class DialogInterfaceOnClickListenerC0615a implements DialogInterface.OnClickListener {
                DialogInterfaceOnClickListenerC0615a() {
                }

                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                    if (C0613a.this.b.h() == null || !C0613a.this.b.h().y) {
                        return;
                    }
                    RunnableC0614a runnableC0614a = RunnableC0614a.this;
                    runnableC0614a.a.favorite = C0613a.this.b.h().f13016h.favorite;
                    f h2 = C0613a.this.b.h();
                    RunnableC0614a runnableC0614a2 = RunnableC0614a.this;
                    h2.f13016h = runnableC0614a2.a;
                    C0613a.this.b.h().K();
                }
            }

            /* renamed from: com.jingdong.manto.m.s1.a$a$a$b */
            /* loaded from: classes15.dex */
            class b implements DialogInterface.OnClickListener {
                b() {
                }

                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                    C0613a c0613a = C0613a.this;
                    c0613a.b.a(c0613a.f13585c, a.this.putErrMsg("fail user canceled updateApp", null, c0613a.d));
                }
            }

            RunnableC0614a(PkgDetailEntity pkgDetailEntity) {
                this.a = pkgDetailEntity;
            }

            @Override // java.lang.Runnable
            public void run() {
                Activity p = C0613a.this.b.p();
                C0613a c0613a = C0613a.this;
                MantoCore core = a.this.getCore(c0613a.b);
                DialogInterfaceOnClickListenerC0615a dialogInterfaceOnClickListenerC0615a = new DialogInterfaceOnClickListenerC0615a();
                b bVar = new b();
                Activity activity = core.getActivity();
                int i2 = R.string.manto_update_msg;
                C0613a.this.b.h().a(com.jingdong.manto.widget.dialog.a.a(activity, p.getString(i2), p.getString(i2), p.getString(R.string.manto_confirm), p.getString(R.string.manto_cancel), dialogInterfaceOnClickListenerC0615a, bVar, null, null, null));
            }
        }

        C0613a(PkgDetailEntity pkgDetailEntity, h hVar, int i2, String str) {
            this.a = pkgDetailEntity;
            this.b = hVar;
            this.f13585c = i2;
            this.d = str;
        }

        @Override // com.jingdong.manto.pkg.PkgManager.k
        public void a(PkgDetailEntity pkgDetailEntity) {
            MantoLog.e("JsApiUpdateApp", "onSuccess: " + pkgDetailEntity);
            if (TextUtils.equals(pkgDetailEntity.build, this.a.build)) {
                this.b.a(this.f13585c, a.this.putErrMsg("fail the current version is the latest version", null, this.d));
            } else {
                com.jingdong.manto.sdk.thread.a.a(new RunnableC0614a(pkgDetailEntity));
            }
        }

        @Override // com.jingdong.manto.pkg.PkgManager.k
        public void onError(Throwable th, JSONObject jSONObject) {
            this.b.a(this.f13585c, a.this.putErrMsg("fail sync error", null, this.d));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        PkgDetailEntity pkgDetailEntity = hVar.h().f13016h;
        MantoLog.e("JsApiUpdateApp", "exec: " + pkgDetailEntity);
        if (pkgDetailEntity == null || TextUtils.isEmpty(pkgDetailEntity.appId)) {
            hVar.a(i2, putErrMsg("fail sync error", null, str));
        } else {
            PkgManager.requestPkgDetail(pkgDetailEntity.appId, pkgDetailEntity.type, new C0613a(pkgDetailEntity, hVar, i2, str));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "updateApp";
    }
}
