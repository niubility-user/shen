package com.jingdong.sdk.jdupgrade.inner.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.FrameLayout;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.sdk.jdupgrade.RemindType;
import com.jingdong.sdk.jdupgrade.UpgradeEventListener;
import com.jingdong.sdk.jdupgrade.UpgradeType;
import com.jingdong.sdk.jdupgrade.a.h.f;
import com.jingdong.sdk.jdupgrade.a.j.g;
import com.jingdong.sdk.jdupgrade.a.j.h;
import com.jingdong.sdk.jdupgrade.a.j.l;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class c {

    /* renamed from: g  reason: collision with root package name */
    private static boolean f15143g;

    /* renamed from: h  reason: collision with root package name */
    private static e f15144h;

    /* renamed from: i  reason: collision with root package name */
    public static volatile boolean f15145i;

    /* renamed from: j  reason: collision with root package name */
    public static c f15146j = new c();
    private com.jingdong.sdk.jdupgrade.a.b a = null;
    private Bundle b;

    /* renamed from: c  reason: collision with root package name */
    private UpgradeEventListener f15147c;
    private FrameLayout d;

    /* renamed from: e  reason: collision with root package name */
    private Activity f15148e;

    /* renamed from: f  reason: collision with root package name */
    private FrameLayout f15149f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ UpgradeEventListener f15150g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Intent f15151h;

        a(UpgradeEventListener upgradeEventListener, Intent intent) {
            this.f15150g = upgradeEventListener;
            this.f15151h = intent;
        }

        /* JADX WARN: Removed duplicated region for block: B:56:0x0137 A[Catch: all -> 0x01d1, TryCatch #0 {all -> 0x01d1, blocks: (B:50:0x00de, B:52:0x012e, B:54:0x0132, B:56:0x0137, B:57:0x013b, B:59:0x013f, B:60:0x0143, B:62:0x0147, B:63:0x014b, B:65:0x014f, B:66:0x0153, B:68:0x0159, B:70:0x015f, B:73:0x0182, B:71:0x0169, B:72:0x0173), top: B:84:0x00de }] */
        /* JADX WARN: Removed duplicated region for block: B:59:0x013f A[Catch: all -> 0x01d1, TryCatch #0 {all -> 0x01d1, blocks: (B:50:0x00de, B:52:0x012e, B:54:0x0132, B:56:0x0137, B:57:0x013b, B:59:0x013f, B:60:0x0143, B:62:0x0147, B:63:0x014b, B:65:0x014f, B:66:0x0153, B:68:0x0159, B:70:0x015f, B:73:0x0182, B:71:0x0169, B:72:0x0173), top: B:84:0x00de }] */
        /* JADX WARN: Removed duplicated region for block: B:62:0x0147 A[Catch: all -> 0x01d1, TryCatch #0 {all -> 0x01d1, blocks: (B:50:0x00de, B:52:0x012e, B:54:0x0132, B:56:0x0137, B:57:0x013b, B:59:0x013f, B:60:0x0143, B:62:0x0147, B:63:0x014b, B:65:0x014f, B:66:0x0153, B:68:0x0159, B:70:0x015f, B:73:0x0182, B:71:0x0169, B:72:0x0173), top: B:84:0x00de }] */
        /* JADX WARN: Removed duplicated region for block: B:65:0x014f A[Catch: all -> 0x01d1, TryCatch #0 {all -> 0x01d1, blocks: (B:50:0x00de, B:52:0x012e, B:54:0x0132, B:56:0x0137, B:57:0x013b, B:59:0x013f, B:60:0x0143, B:62:0x0147, B:63:0x014b, B:65:0x014f, B:66:0x0153, B:68:0x0159, B:70:0x015f, B:73:0x0182, B:71:0x0169, B:72:0x0173), top: B:84:0x00de }] */
        /* JADX WARN: Removed duplicated region for block: B:68:0x0159 A[Catch: all -> 0x01d1, TryCatch #0 {all -> 0x01d1, blocks: (B:50:0x00de, B:52:0x012e, B:54:0x0132, B:56:0x0137, B:57:0x013b, B:59:0x013f, B:60:0x0143, B:62:0x0147, B:63:0x014b, B:65:0x014f, B:66:0x0153, B:68:0x0159, B:70:0x015f, B:73:0x0182, B:71:0x0169, B:72:0x0173), top: B:84:0x00de }] */
        /* JADX WARN: Removed duplicated region for block: B:72:0x0173 A[Catch: all -> 0x01d1, TryCatch #0 {all -> 0x01d1, blocks: (B:50:0x00de, B:52:0x012e, B:54:0x0132, B:56:0x0137, B:57:0x013b, B:59:0x013f, B:60:0x0143, B:62:0x0147, B:63:0x014b, B:65:0x014f, B:66:0x0153, B:68:0x0159, B:70:0x015f, B:73:0x0182, B:71:0x0169, B:72:0x0173), top: B:84:0x00de }] */
        /* JADX WARN: Removed duplicated region for block: B:88:0x01bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:98:? A[RETURN, SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            c cVar;
            com.jingdong.sdk.jdupgrade.a.b bVar;
            int[] q;
            Drawable p;
            UpgradeEventListener upgradeEventListener;
            c.this.f15148e = g.a();
            if (c.this.f15148e == null) {
                UpgradeEventListener upgradeEventListener2 = this.f15150g;
                if (upgradeEventListener2 != null) {
                    try {
                        upgradeEventListener2.onMessage("12", "no activity is shown");
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            } else if (c.f15145i && c.this.f15149f != null) {
                h.b("", "a view is showing");
                UpgradeEventListener upgradeEventListener3 = this.f15150g;
                if (upgradeEventListener3 != null) {
                    try {
                        upgradeEventListener3.onMessage("13", "a view is showing");
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
            } else {
                c.this.b = null;
                c.this.a = null;
                c.f15145i = true;
                c.this.f15147c = this.f15150g;
                c cVar2 = c.this;
                cVar2.d = (FrameLayout) cVar2.f15148e.getWindow().getDecorView();
                try {
                    Intent intent = this.f15151h;
                    if (intent != null) {
                        c.this.b = intent.getExtras();
                    }
                } catch (Throwable unused) {
                }
                int i2 = C0729c.a[c.this.g().ordinal()];
                if (i2 == 1 || i2 == 2) {
                    if (c.f15143g && com.jingdong.sdk.jdupgrade.a.c.T()) {
                        c.this.a = com.jingdong.sdk.jdupgrade.a.c.l();
                    }
                    if (c.this.a == null) {
                        cVar = c.this;
                        bVar = new com.jingdong.sdk.jdupgrade.inner.ui.b();
                        cVar.a = bVar;
                    }
                    try {
                        c.this.a.onAttach();
                        View onCreateView = c.this.a.onCreateView(c.this.f15148e);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                        c.this.f15149f = new FrameLayout(c.this.f15148e);
                        FrameLayout frameLayout = new FrameLayout(c.this.f15148e);
                        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
                        layoutParams2.gravity = 17;
                        layoutParams2.leftMargin = 60;
                        layoutParams2.rightMargin = 60;
                        q = com.jingdong.sdk.jdupgrade.a.c.q();
                        if (q != null && q.length == 4) {
                            if (q[0] > 0) {
                                layoutParams2.leftMargin = q[0];
                            }
                            if (q[1] > 0) {
                                layoutParams2.rightMargin = q[1];
                            }
                            if (q[2] > 0) {
                                layoutParams2.topMargin = q[2];
                            }
                            if (q[3] > 0) {
                                layoutParams2.bottomMargin = q[3];
                            }
                        }
                        p = com.jingdong.sdk.jdupgrade.a.c.p();
                        if (p != null) {
                            c.this.f15149f.setBackgroundColor(Color.parseColor("#88000000"));
                        } else if (Build.VERSION.SDK_INT >= 16) {
                            c.this.f15149f.setBackground(p);
                        } else {
                            c.this.f15149f.setBackgroundDrawable(p);
                        }
                        frameLayout.addView(onCreateView, layoutParams2);
                        c.this.f15149f.addView(frameLayout, layoutParams);
                        c.this.f15149f.setFocusable(true);
                        c.this.f15149f.setClickable(true);
                        c.this.d.addView(c.this.f15149f);
                        c.this.a.onResume();
                        upgradeEventListener = this.f15150g;
                        if (upgradeEventListener == null) {
                            try {
                                upgradeEventListener.onShowRemindDialog(c.this.g(), c.this.i());
                                return;
                            } catch (Throwable th3) {
                                th3.printStackTrace();
                                return;
                            }
                        }
                        return;
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                        return;
                    }
                }
                if (i2 == 3) {
                    if (c.f15143g && com.jingdong.sdk.jdupgrade.a.c.S()) {
                        c.this.a = com.jingdong.sdk.jdupgrade.a.c.k();
                    }
                    if (c.this.a == null) {
                        cVar = c.this;
                        bVar = new com.jingdong.sdk.jdupgrade.inner.ui.a();
                        cVar.a = bVar;
                    }
                }
                c.this.a.onAttach();
                View onCreateView2 = c.this.a.onCreateView(c.this.f15148e);
                FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -1);
                c.this.f15149f = new FrameLayout(c.this.f15148e);
                FrameLayout frameLayout2 = new FrameLayout(c.this.f15148e);
                FrameLayout.LayoutParams layoutParams22 = new FrameLayout.LayoutParams(-1, -2);
                layoutParams22.gravity = 17;
                layoutParams22.leftMargin = 60;
                layoutParams22.rightMargin = 60;
                q = com.jingdong.sdk.jdupgrade.a.c.q();
                if (q != null) {
                    if (q[0] > 0) {
                    }
                    if (q[1] > 0) {
                    }
                    if (q[2] > 0) {
                    }
                    if (q[3] > 0) {
                    }
                }
                p = com.jingdong.sdk.jdupgrade.a.c.p();
                if (p != null) {
                }
                frameLayout2.addView(onCreateView2, layoutParams22);
                c.this.f15149f.addView(frameLayout2, layoutParams3);
                c.this.f15149f.setFocusable(true);
                c.this.f15149f.setClickable(true);
                c.this.d.addView(c.this.f15149f);
                c.this.a.onResume();
                upgradeEventListener = this.f15150g;
                if (upgradeEventListener == null) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f15153g;

        b(boolean z) {
            this.f15153g = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (c.this.d == null || c.this.f15149f == null) {
                return;
            }
            c.f15145i = false;
            c.this.d.removeView(c.this.f15149f);
            if (c.this.a != null) {
                c.this.a.onDetach();
            }
            if (c.this.f15147c != null) {
                try {
                    h.a("upgrade", "removeRemindView fromUser:" + this.f15153g);
                    if (this.f15153g) {
                        c.this.f15147c.onCloseRemindDialog(c.this.g(), c.this.i());
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            c.this.a = null;
            c.this.b = null;
            c.this.f15149f = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.sdk.jdupgrade.inner.ui.c$c  reason: collision with other inner class name */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C0729c {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[com.jingdong.sdk.jdupgrade.a.h.e.values().length];
            b = iArr;
            try {
                iArr[com.jingdong.sdk.jdupgrade.a.h.e.UPGRADE_GRAYSCALE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[com.jingdong.sdk.jdupgrade.a.h.e.UPGRADE_FORCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[com.jingdong.sdk.jdupgrade.a.h.e.UPGRADE_ORDINARY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[RemindType.values().length];
            a = iArr2;
            try {
                iArr2[RemindType.INSTALL_REMIND.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[RemindType.UPGRADE_REMIND.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[RemindType.DOWNLOADING_REMIND.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private static Intent a(com.jingdong.sdk.jdupgrade.a.h.a aVar, String str, e eVar, boolean z, RemindType remindType, String str2, boolean z2, com.jingdong.sdk.jdupgrade.a.h.e eVar2, boolean z3) {
        f15143g = z;
        f15144h = eVar;
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("remindInfo", aVar.d);
        bundle.putString(NavigatorHolder.NaviEntity.TYPE_CUSTOM, str2);
        bundle.putString("packageCopyWriting", str);
        bundle.putParcelable("remindType", remindType);
        bundle.putParcelable(XView2Constants.STATE, eVar2);
        bundle.putBoolean("forceUpgrade", z2);
        bundle.putBoolean("hideRejectCheckbox", z3);
        intent.putExtras(bundle);
        return intent;
    }

    public static void a(com.jingdong.sdk.jdupgrade.a.h.a aVar, String str, e eVar, boolean z, RemindType remindType, String str2, boolean z2, com.jingdong.sdk.jdupgrade.a.h.e eVar2, UpgradeEventListener upgradeEventListener, boolean z3) {
        f15146j.a(a(aVar, str, eVar, z, remindType, str2, z2, eVar2, z3), upgradeEventListener);
    }

    public static void a(f fVar, UpgradeEventListener upgradeEventListener) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("upgradeInfo", fVar);
        bundle.putString(NavigatorHolder.NaviEntity.TYPE_CUSTOM, fVar.f15073g);
        bundle.putString("packageCopyWriting", fVar.f15072f);
        bundle.putParcelable(XView2Constants.STATE, fVar.a);
        bundle.putParcelable("remindType", RemindType.DOWNLOADING_REMIND);
        intent.putExtras(bundle);
        f15146j.a(intent, upgradeEventListener);
    }

    public void a(Activity activity) {
        Activity activity2;
        if (activity == null || this.f15149f == null || (activity2 = this.f15148e) == null || activity.equals(activity2)) {
            return;
        }
        this.d.removeView(this.f15149f);
        com.jingdong.sdk.jdupgrade.a.b bVar = this.a;
        if (bVar != null) {
            bVar.onDetach();
        }
        this.f15148e = activity;
        this.d = (FrameLayout) activity.getWindow().getDecorView();
        this.f15149f.setFocusable(true);
        this.f15149f.setClickable(true);
        this.d.addView(this.f15149f);
        this.a.onResume();
    }

    public void a(Intent intent, UpgradeEventListener upgradeEventListener) {
        l.a().a(new a(upgradeEventListener, intent));
    }

    public final void a(boolean z) {
        if (k()) {
            com.jingdong.sdk.jdupgrade.a.j.b.a();
            return;
        }
        e eVar = f15144h;
        if (eVar != null) {
            eVar.a(z);
        }
        b(true);
    }

    public final void b() {
        e eVar = f15144h;
        if (eVar != null) {
            eVar.a();
        }
    }

    public void b(boolean z) {
        l.a().a(new b(z));
    }

    public final String c() {
        Bundle bundle = this.b;
        return bundle == null ? "" : bundle.getString(NavigatorHolder.NaviEntity.TYPE_CUSTOM, "");
    }

    public UpgradeEventListener d() {
        return this.f15147c;
    }

    public final JSONObject e() {
        Bundle bundle = this.b;
        if (bundle == null) {
            return null;
        }
        try {
            return new JSONObject(bundle.getString("packageCopyWriting", "{}"));
        } catch (Throwable unused) {
            return null;
        }
    }

    public final JSONObject f() {
        Bundle bundle = this.b;
        if (bundle == null) {
            return null;
        }
        try {
            return new JSONObject(bundle.getString("remindInfo", "{}"));
        } catch (Throwable unused) {
            return null;
        }
    }

    public final RemindType g() {
        try {
            RemindType remindType = (RemindType) this.b.getParcelable("remindType");
            return remindType == null ? RemindType.UPGRADE_REMIND : remindType;
        } catch (Throwable unused) {
            return RemindType.UPGRADE_REMIND;
        }
    }

    public final f h() {
        Bundle bundle = this.b;
        if (bundle == null) {
            return null;
        }
        Parcelable parcelable = bundle.getParcelable("upgradeInfo");
        if (parcelable instanceof f) {
            return (f) parcelable;
        }
        return null;
    }

    public final UpgradeType i() {
        com.jingdong.sdk.jdupgrade.a.h.e eVar;
        Bundle bundle = this.b;
        if (bundle != null && (eVar = (com.jingdong.sdk.jdupgrade.a.h.e) bundle.getParcelable(XView2Constants.STATE)) != null) {
            int i2 = C0729c.b[eVar.ordinal()];
            if (i2 == 1) {
                return UpgradeType.UPGRADE_GRAYSCALE;
            }
            if (i2 == 2) {
                return UpgradeType.UPGRADE_FORCE;
            }
            if (i2 == 3) {
                return UpgradeType.UPGRADE_ORDINARY;
            }
        }
        return null;
    }

    public final boolean j() {
        Bundle bundle = this.b;
        return bundle != null && bundle.getBoolean("hideRejectCheckbox", false);
    }

    public final boolean k() {
        Bundle bundle = this.b;
        return bundle != null && bundle.getBoolean("forceUpgrade", false);
    }
}
