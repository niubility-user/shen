package com.jd.manto.sdkimpl.live.v2;

import android.app.Activity;
import android.graphics.Rect;
import android.view.ViewGroup;
import com.jd.manto.sdkimpl.live.v2.b;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes17.dex */
public class a {
    private com.jd.manto.sdkimpl.live.v2.c a;
    private com.jd.manto.sdkimpl.live.v2.b b;

    /* renamed from: c  reason: collision with root package name */
    private Activity f6808c;
    private MantoLivePlayerV2 d;

    /* renamed from: e  reason: collision with root package name */
    private c f6809e;

    /* renamed from: f  reason: collision with root package name */
    private b f6810f;

    /* renamed from: g  reason: collision with root package name */
    private int f6811g = 6;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.manto.sdkimpl.live.v2.a$a  reason: collision with other inner class name */
    /* loaded from: classes17.dex */
    public class C0204a implements b.InterfaceC0205b {
        final /* synthetic */ int a;
        final /* synthetic */ ViewGroup b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ ViewGroup.LayoutParams f6812c;
        final /* synthetic */ Rect d;

        C0204a(int i2, ViewGroup viewGroup, ViewGroup.LayoutParams layoutParams, Rect rect) {
            this.a = i2;
            this.b = viewGroup;
            this.f6812c = layoutParams;
            this.d = rect;
        }

        @Override // com.jd.manto.sdkimpl.live.v2.b.InterfaceC0205b
        public void onDismiss(boolean z) {
            if (a.this.d == null || a.this.f6808c == null) {
                return;
            }
            if (a.this.f6810f != null) {
                a.this.f6810f.onChange(false, 1);
            }
            a.this.f6808c.setRequestedOrientation(1);
            int childCount = this.a > this.b.getChildCount() ? this.b.getChildCount() : this.a;
            if (childCount < 0) {
                childCount = 0;
            }
            this.b.addView(a.this.d, childCount, this.f6812c);
            MantoLog.d("MantoDefaultFullVideoChanger", "rect:" + this.d + " indexOfChild:" + this.a + " index:" + childCount);
            a.this.b = null;
            if (a.this.f6809e != null) {
                a.this.f6809e.onChange(false, 1);
            }
        }
    }

    /* loaded from: classes17.dex */
    public interface b {
        void onChange(boolean z, int i2);
    }

    /* loaded from: classes17.dex */
    public interface c {
        void onChange(boolean z, int i2);
    }

    public a(Activity activity, MantoLivePlayerV2 mantoLivePlayerV2) {
        this.f6808c = activity;
        this.d = mantoLivePlayerV2;
    }

    public void f() {
        if (this.f6808c == null) {
            return;
        }
        b bVar = this.f6810f;
        if (bVar != null) {
            bVar.onChange(false, 1);
        }
        this.f6808c.setRequestedOrientation(this.f6811g);
        ViewGroup viewGroup = (ViewGroup) this.d.getParent();
        ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
        Rect rect = new Rect(this.d.getLeft(), this.d.getTop(), this.d.getRight(), this.d.getBottom());
        int indexOfChild = viewGroup.indexOfChild(this.d);
        viewGroup.removeView(this.d);
        com.jd.manto.sdkimpl.live.v2.b bVar2 = new com.jd.manto.sdkimpl.live.v2.b();
        this.b = bVar2;
        bVar2.e(this.f6808c, this.d);
        this.b.d(new C0204a(indexOfChild, viewGroup, layoutParams, rect));
        c cVar = this.f6809e;
        if (cVar != null) {
            cVar.onChange(true, this.f6811g);
        }
    }

    public void g() {
        com.jd.manto.sdkimpl.live.v2.b bVar = this.b;
        if (bVar == null) {
            return;
        }
        bVar.b(this.f6808c, false);
    }

    public void h() {
        com.jd.manto.sdkimpl.live.v2.c cVar = this.a;
        if (cVar != null) {
            cVar.disable();
        }
    }

    public void i(int i2) {
        if (i2 == 6) {
            this.f6811g = 6;
        } else if (i2 == 1) {
            this.f6811g = 1;
        }
    }

    public void j(c cVar) {
        this.f6809e = cVar;
    }
}
