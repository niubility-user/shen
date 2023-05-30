package com.jingdong.app.mall.home.shakeandshow;

import android.content.Context;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.HomeShakeCtrl;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority;
import com.jingdong.app.mall.home.shakeandshow.g;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.ui.JDCheckDialog;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.sdk.log.Log;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: l  reason: collision with root package name */
    private static final AtomicInteger f10826l = new AtomicInteger(0);
    protected com.jingdong.app.mall.home.shakeandshow.g a;
    private Context b;

    /* renamed from: e  reason: collision with root package name */
    private JDDialog f10828e;

    /* renamed from: h  reason: collision with root package name */
    private long f10831h;

    /* renamed from: i  reason: collision with root package name */
    private long f10832i;

    /* renamed from: j  reason: collision with root package name */
    private BaseFloatPriority f10833j;

    /* renamed from: c  reason: collision with root package name */
    private com.jingdong.app.mall.home.shakeandshow.e f10827c = null;
    private ShakeAdNewView d = null;

    /* renamed from: f  reason: collision with root package name */
    protected AtomicBoolean f10829f = new AtomicBoolean(false);

    /* renamed from: g  reason: collision with root package name */
    protected AtomicBoolean f10830g = new AtomicBoolean(false);

    /* renamed from: k  reason: collision with root package name */
    public g.b f10834k = new a();

    /* loaded from: classes4.dex */
    class a implements g.b {

        /* renamed from: com.jingdong.app.mall.home.shakeandshow.c$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class C0323a extends com.jingdong.app.mall.home.o.a.b {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ float f10835g;

            C0323a(float f2) {
                this.f10835g = f2;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                if (c.this.k(this.f10835g)) {
                    return;
                }
                if (Log.D) {
                    Log.i("ShakeActionCtrl", "createAndShowADView false");
                }
                c.this.f10829f.set(false);
            }
        }

        a() {
        }

        @Override // com.jingdong.app.mall.home.shakeandshow.g.b
        public void a(float f2) {
            if (Log.D) {
                Log.i("ShakeActionCtrl", "shakeListener1:" + c.this.f10829f.get());
            }
            if (c.this.f10829f.compareAndSet(false, true)) {
                if (Log.D) {
                    Log.i("ShakeActionCtrl", "shakeListener2:" + c.this.f10829f.get());
                }
                com.jingdong.app.mall.home.o.a.f.u0(new C0323a(f2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends BaseFloatPriority {
        b(String str, int i2) {
            super(str, i2);
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        public boolean e() {
            return true;
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        protected void g(int i2) {
            if (c.this.d != null) {
                c.this.d.f();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        public void h() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.home.shakeandshow.c$c  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public class C0324c implements com.jingdong.app.mall.home.shakeandshow.a {
        C0324c() {
        }

        @Override // com.jingdong.app.mall.home.shakeandshow.a
        public void a() {
            c.this.f10830g.set(true);
            c.this.f10831h = SystemClock.elapsedRealtime();
        }

        @Override // com.jingdong.app.mall.home.shakeandshow.a
        public void b() {
            boolean z = false;
            if ((c.this.f10827c != null && c.this.f10827c.f10851h) && c.f10826l.incrementAndGet() > 1) {
                z = true;
            }
            if (z) {
                c.this.q();
            }
        }

        @Override // com.jingdong.app.mall.home.shakeandshow.a
        public void onClose() {
            c.this.j();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d extends com.jingdong.app.mall.home.o.a.b {
        d() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            if (c.this.d == null) {
                return;
            }
            c.this.d.f();
            if (c.this.f10830g.get()) {
                return;
            }
            c.this.f10829f.set(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e implements DialogInterface.OnDismissListener {
        e() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (c.this.f10833j != null) {
                c.this.f10833j.b(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class f implements View.OnClickListener {
        f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            c.this.o();
            c.this.f10828e.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class g implements View.OnClickListener {
        g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            HomeShakeCtrl.updateShakeSwitch(false);
            if (c.this.f10827c != null && c.this.f10827c.f10853j != null) {
                com.jingdong.app.mall.home.r.c.a.s("Home_ShakerCloseComfirm", c.this.f10827c.f10853j.getSrv(), c.this.f10827c.f10853j.getSrvJson());
            }
            c.this.f10828e.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class h implements DialogInterface.OnKeyListener {
        h() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
            if (i2 == 4 && c.this.f10828e.isShowing()) {
                Log.d("ShakeActionCtrl", i2 + "");
                c.this.o();
                c.this.f10828e.dismiss();
                return false;
            }
            return false;
        }
    }

    public c(RelativeLayout relativeLayout) {
        this.a = null;
        Context context = relativeLayout.getContext();
        this.b = context;
        if (com.jingdong.app.mall.home.shakeandshow.g.j(context)) {
            this.a = new com.jingdong.app.mall.home.shakeandshow.g(this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.d != null) {
            com.jingdong.app.mall.home.o.a.f.u0(new d());
        }
        BaseFloatPriority baseFloatPriority = this.f10833j;
        if (baseFloatPriority != null) {
            baseFloatPriority.b(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k(float f2) {
        if (this.a == null) {
            return false;
        }
        b bVar = new b("\u6447\u4e00\u6447", 16);
        this.f10833j = bVar;
        if (bVar.a()) {
            if (this.d == null) {
                this.d = new ShakeAdNewView(this.b, new C0324c());
            }
            com.jingdong.app.mall.home.v.c.a.a(this.d);
            if (this.d.d(this.f10827c) && this.a.k(this.f10834k)) {
                this.f10833j.l();
                com.jingdong.app.mall.home.o.a.d.g();
                if (SystemClock.elapsedRealtime() - this.f10832i < 500) {
                    this.a.l();
                }
                this.d.h();
                return true;
            }
            this.d.f();
            return true;
        }
        return false;
    }

    private void n(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.jingdong.app.mall.home.floor.ctrl.e.z(str, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        JumpEntity jumpEntity;
        JDDialog jDDialog = this.f10828e;
        if (jDDialog != null && jDDialog.isShowing()) {
            return;
        }
        Context context = this.b;
        JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(context, context.getString(R.string.home_shake_dialog_title), context.getString(R.string.home_shake_dialog_subtitle), context.getString(R.string.home_shake_dialog_cancel), context.getString(R.string.home_shake_dialog_confirm));
        this.f10828e = createJdDialogWithStyle6;
        createJdDialogWithStyle6.setOnDismissListener(new e());
        this.f10828e.setOnLeftButtonClickListener(new f());
        this.f10828e.setOnRightButtonClickListener(new g());
        this.f10828e.setOnKeyListener(new h());
        com.jingdong.app.mall.home.shakeandshow.e eVar = this.f10827c;
        if (eVar != null && (jumpEntity = eVar.f10853j) != null) {
            com.jingdong.app.mall.home.r.c.a.y("Home_ShakerClosePopup", jumpEntity.srv, jumpEntity.getSrvJson());
        }
        r();
        this.f10828e.show();
    }

    public void l() {
        this.f10830g.set(false);
        this.f10829f.set(false);
    }

    public void m() {
        JDDialog jDDialog = this.f10828e;
        if (jDDialog != null) {
            jDDialog.dismiss();
        }
    }

    public void o() {
        if (!HomeShakeCtrl.getCurrentShakeSwitch()) {
            r();
        } else if (this.a == null) {
        } else {
            if (Log.D) {
                Log.i("ShakeActionCtrl", "registShakeListener");
            }
            com.jingdong.app.mall.home.shakeandshow.e eVar = this.f10827c;
            if (eVar != null) {
                this.a.p(eVar.f10852i);
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.f10832i = elapsedRealtime;
            if (elapsedRealtime - this.f10831h < 1800) {
                this.a.l();
            }
            this.a.n(this.f10834k);
        }
    }

    public void p(com.jingdong.app.mall.home.shakeandshow.e eVar) {
        this.f10827c = eVar;
        if (eVar != null) {
            String str = eVar.f10848e;
            n(eVar.a());
        }
    }

    public void r() {
        if (this.a == null) {
            return;
        }
        if (Log.D) {
            Log.i("ShakeActionCtrl", "unregistShakeListener");
        }
        this.a.q(this.f10834k);
        try {
            j();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
