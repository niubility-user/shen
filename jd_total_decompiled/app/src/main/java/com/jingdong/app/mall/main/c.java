package com.jingdong.app.mall.main;

import android.app.Activity;
import com.jingdong.common.ui.DialogController;

/* loaded from: classes4.dex */
abstract class c {
    protected Activity a;
    protected DialogController b;

    /* renamed from: c  reason: collision with root package name */
    private c f11244c;

    public c(Activity activity, c cVar) {
        this.a = activity;
        this.f11244c = cVar;
    }

    public void a() {
        DialogController dialogController = this.b;
        if (dialogController != null) {
            dialogController.dismiss();
        }
        c cVar = this.f11244c;
        if (cVar != null) {
            cVar.a();
        }
    }

    public abstract void b();

    /* JADX INFO: Access modifiers changed from: protected */
    public void c() {
        c cVar = this.f11244c;
        if (cVar != null) {
            cVar.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d() {
        c cVar = this.f11244c;
        if (cVar != null) {
            cVar.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e() {
        c cVar = this.f11244c;
        if (cVar != null) {
            cVar.e();
        }
    }
}
