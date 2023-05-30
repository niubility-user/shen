package com.jingdong.manto.ui.f.a;

import android.text.InputFilter;
import android.text.TextUtils;
import android.widget.EditText;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes16.dex */
public class a {
    private String a;
    public int b = 2;

    /* renamed from: c  reason: collision with root package name */
    public boolean f14289c = false;
    public WeakReference<EditText> d;

    /* renamed from: e  reason: collision with root package name */
    private int f14290e;

    /* renamed from: f  reason: collision with root package name */
    private int f14291f;

    /* renamed from: g  reason: collision with root package name */
    private ArrayList<InputFilter> f14292g;

    /* renamed from: h  reason: collision with root package name */
    public InterfaceC0687a f14293h;

    /* renamed from: com.jingdong.manto.ui.f.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public interface InterfaceC0687a {
        void a();

        void a(String str);

        void b();
    }

    public a(WeakReference<EditText> weakReference) {
        this.d = weakReference;
    }

    public static a a(EditText editText) {
        return new a(new WeakReference(editText));
    }

    public com.jingdong.manto.sdk.a a(int i2, int i3) {
        return new com.jingdong.manto.sdk.a(i2, i3);
    }

    public final a a(int i2) {
        this.f14291f = 0;
        this.f14290e = i2;
        return this;
    }

    protected final void a() {
        if (!this.f14289c) {
            if (this.d == null) {
                MantoLog.w("InputTextBoundaryCheck", "edit text view is null");
                return;
            } else if (MantoUtils.isEmpty(this.f14292g)) {
                this.d.get().setFilters(new InputFilter[]{a(this.f14290e, this.b)});
            } else {
                this.f14292g.add(a(this.f14290e, this.b));
                ArrayList<InputFilter> arrayList = this.f14292g;
                this.d.get().setFilters((InputFilter[]) arrayList.toArray(new InputFilter[arrayList.size()]));
            }
        }
        if (this.f14293h != null) {
            int b = b();
            if (b == 0) {
                this.f14293h.a(this.a);
            } else if (b == 1) {
                this.f14293h.b();
            } else if (b != 2) {
            } else {
                this.f14293h.a();
            }
        }
    }

    public final void a(InterfaceC0687a interfaceC0687a) {
        this.f14293h = interfaceC0687a;
        a();
    }

    protected final int b() {
        if (TextUtils.isEmpty(this.a)) {
            WeakReference<EditText> weakReference = this.d;
            if (weakReference == null) {
                return 1;
            }
            this.a = weakReference.get().getText().toString().trim();
        }
        int a = com.jingdong.manto.sdk.a.a(this.a, this.b);
        if ((a < 0 ? 1 : null) != null) {
            return 2;
        }
        if (a < this.f14291f) {
            return 1;
        }
        return a > this.f14290e ? 2 : 0;
    }
}
