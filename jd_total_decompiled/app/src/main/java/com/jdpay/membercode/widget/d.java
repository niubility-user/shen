package com.jdpay.membercode.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.image.loader.target.RequestTarget;
import com.jdpay.lib.helper.ContextHelper;
import com.jdpay.lib.util.JDPayLog;
import com.jdpay.lib.util.Utils;
import com.jdpay.membercode.JDPayMemberCode;
import com.jdpay.membercode.R;
import com.jdpay.membercode.bean.CodeInfoBean;
import com.jdpay.membercode.bean.FastPayPageInfoBean;
import com.jdpay.membercode.bean.OpenFastPayPageInfoBean;
import com.jdpay.membercode.f.d;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes18.dex */
public class d extends com.jdpay.membercode.widget.b implements d.b, View.OnClickListener {

    /* renamed from: i */
    private TextView f7570i;

    /* renamed from: j */
    private TextView f7571j;

    /* renamed from: k */
    private Button f7572k;

    /* renamed from: l */
    private ImageView f7573l;

    /* renamed from: m */
    private ImageView f7574m;

    /* renamed from: n */
    private com.jdpay.membercode.d.a f7575n;
    private com.jdpay.membercode.d.c o;
    private b p;
    private final com.jdpay.membercode.f.a q;
    private final com.jdpay.membercode.f.e r;
    private final com.jdpay.membercode.f.d s;
    private final ExecutorService t;
    private volatile boolean u;
    private volatile boolean v;
    private final int w;
    private final int x;

    /* loaded from: classes18.dex */
    public class a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ CodeInfoBean f7576g;

        a(CodeInfoBean codeInfoBean) {
            d.this = r1;
            this.f7576g = codeInfoBean;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!d.this.f()) {
                JDPayLog.i("ScanViewHolder not attached CodeView");
                return;
            }
            if (!TextUtils.isEmpty(this.f7576g.tip)) {
                d.this.f7570i.setText(this.f7576g.tip);
            }
            if (!this.f7576g.isFastPayAvailable) {
                d.this.f7571j.setTextColor(d.this.a(R.color.jdpay_mb_scan_activate_tip));
                d.this.f7571j.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                d.this.f7572k.setTag(this.f7576g.openFastPayPageInfo);
                d.this.f7572k.setVisibility(0);
                OpenFastPayPageInfoBean openFastPayPageInfoBean = this.f7576g.openFastPayPageInfo;
                if (openFastPayPageInfoBean != null) {
                    if (TextUtils.isEmpty(openFastPayPageInfoBean.openText)) {
                        d.this.f7571j.setText(R.string.jdpay_mb_scan_activate_tip);
                        return;
                    } else {
                        d.this.f7571j.setText(openFastPayPageInfoBean.openText);
                        return;
                    }
                }
                return;
            }
            d.this.f7571j.setTextColor(d.this.a(R.color.jdpay_mb_common_tip));
            d.this.f7572k.setTag(null);
            d.this.f7572k.setVisibility(8);
            FastPayPageInfoBean fastPayPageInfoBean = this.f7576g.fastPayPageInfo;
            if (fastPayPageInfoBean != null) {
                if (TextUtils.isEmpty(fastPayPageInfoBean.text)) {
                    d.this.f7571j.setText(R.string.jdpay_mb_scan_bottom_tip);
                } else {
                    d.this.f7571j.setText(fastPayPageInfoBean.text);
                }
                if (!TextUtils.isEmpty(fastPayPageInfoBean.logoUrl) && d.this.p != null) {
                    d.this.p.setUri(fastPayPageInfoBean.logoUrl);
                    JDPayMemberCode.getImageLoader().uri(fastPayPageInfoBean.logoUrl).defaultCache(d.this.f7571j.getContext().getApplicationContext()).to(d.this.p).load();
                    return;
                }
            } else {
                d.this.f7571j.setText(R.string.jdpay_mb_scan_bottom_tip);
            }
            d.this.f7571j.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_jdpay_mb_scan_jd, 0, 0, 0);
        }
    }

    /* loaded from: classes18.dex */
    private class b extends RequestTarget<TextView> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(@NonNull TextView textView) {
            super(textView);
            d.this = r1;
        }

        @Override // com.jdpay.image.loader.target.RequestTarget
        public void apply(@Nullable Object obj) {
            try {
                BitmapDrawable bitmapDrawable = obj instanceof Bitmap ? new BitmapDrawable(Bitmap.createScaledBitmap((Bitmap) obj, d.this.w, d.this.x, false)) : obj instanceof BitmapDrawable ? (BitmapDrawable) obj : null;
                TextView textView = get();
                if (bitmapDrawable == null || textView == null) {
                    return;
                }
                d.this.f7571j.setCompoundDrawablesWithIntrinsicBounds(bitmapDrawable, (Drawable) null, (Drawable) null, (Drawable) null);
            } catch (Throwable th) {
                JDPayLog.e(th);
            }
        }

        @Override // com.jdpay.image.loader.target.RequestTarget
        public void applyPlaceholder() {
        }
    }

    public d(@NonNull CodeView codeView) {
        super(codeView);
        com.jdpay.membercode.f.a aVar = new com.jdpay.membercode.f.a();
        this.q = aVar;
        com.jdpay.membercode.f.e eVar = new com.jdpay.membercode.f.e();
        this.r = eVar;
        this.s = new com.jdpay.membercode.f.d(aVar, eVar, this);
        this.t = Executors.newSingleThreadExecutor();
        this.v = true;
        Resources resources = codeView.getResources();
        this.w = resources.getDimensionPixelSize(R.dimen.jdpay_mb_scan_bottom_tip_icon_width);
        this.x = resources.getDimensionPixelSize(R.dimen.jdpay_mb_scan_bottom_tip_icon_height);
    }

    private void h(@Nullable com.jdpay.membercode.d.b bVar, @Nullable com.jdpay.membercode.f.c cVar) {
        if (bVar == null || !bVar.isShowing() || cVar == null) {
            return;
        }
        bVar.a(cVar);
    }

    private void m(int i2) {
        int i3 = (i2 * R2.attr.customPixelDimension) / 1000;
        int i4 = (i3 * 64) / 250;
        ViewGroup.LayoutParams layoutParams = this.f7573l.getLayoutParams();
        if (layoutParams.width != i3 || layoutParams.height != i4) {
            this.q.f(i3, i4);
            layoutParams.width = i3;
            layoutParams.height = i4;
            this.f7573l.requestLayout();
        }
        int i5 = (i2 * R2.attr.barrierAllowsGoneWidgets) / 1000;
        ViewGroup.LayoutParams layoutParams2 = this.f7574m.getLayoutParams();
        if (layoutParams2.width == i5 || layoutParams2.height == i5) {
            return;
        }
        this.r.f(i5);
        layoutParams2.height = i5;
        layoutParams2.width = i5;
        this.f7574m.requestLayout();
    }

    private void t() {
        if (!this.s.h() && this.s.f()) {
            this.t.execute(this.s);
        }
    }

    @Override // com.jdpay.membercode.f.d.b
    public void a() {
        JDPayLog.i("Code:" + this.s.e() + "/" + this.q.d());
        this.v = true;
        if (f()) {
            this.f7573l.setImageBitmap(this.q.e());
            this.f7574m.setImageBitmap(this.r.e());
            h(this.f7575n, this.q);
            h(this.o, this.r);
        }
    }

    @Override // com.jdpay.membercode.f.d.b
    public void a(Throwable th) {
        JDPayLog.i(Utils.getThrowableMessage(th));
        if (this.v) {
            return;
        }
        CharSequence exceptionMessage = CodeView.getExceptionMessage(th);
        if (TextUtils.isEmpty(exceptionMessage)) {
            exceptionMessage = this.f7566g.getResources().getString(R.string.jdpay_mb_err_create_code);
        }
        this.f7566g.showToast(exceptionMessage);
    }

    @Override // com.jdpay.membercode.widget.b
    protected View b(@NonNull Context context) {
        View inflate = View.inflate(context, R.layout.jdpay_mb_scan_code, null);
        this.f7570i = (TextView) inflate.findViewById(R.id.jdpay_mb_scan_top_tip);
        this.f7571j = (TextView) inflate.findViewById(R.id.jdpay_mb_scan_bottom_tip);
        Button button = (Button) inflate.findViewById(R.id.jdpay_mb_scan_activate_button);
        this.f7572k = button;
        button.setOnClickListener(this);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.jdpay_mb_scan_barcode);
        this.f7573l = imageView;
        imageView.setOnClickListener(this);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.jdpay_mb_scan_qrcode);
        this.f7574m = imageView2;
        imageView2.setOnClickListener(this);
        this.p = new b(this.f7571j);
        return inflate;
    }

    @Override // com.jdpay.membercode.f.d.b
    public void b() {
        JDPayLog.i("");
        if (this.s.g()) {
            this.t.execute(this.s);
        }
    }

    @Override // com.jdpay.membercode.widget.b
    public void c() {
        Activity activity = ContextHelper.getActivity(this.f7566g.getContext());
        if (activity != null) {
            this.f7566g.forbiddenScreenCapture(activity.getWindow());
        }
        if (this.r.h() == null) {
            this.r.g(BitmapFactory.decodeResource(this.f7566g.getResources(), R.mipmap.qrcode_logo));
        }
        super.c();
    }

    @Override // com.jdpay.membercode.widget.b
    public void d() {
        if (this.u) {
            return;
        }
        this.s.a();
        this.u = true;
    }

    @Override // com.jdpay.membercode.widget.b
    public void e() {
        super.e();
        Activity activity = ContextHelper.getActivity(this.f7566g.getContext());
        if (activity != null) {
            this.f7566g.resumeScreenCapture(activity.getWindow());
        }
    }

    public void i(@NonNull String str) {
        JDPayLog.i("Target:" + str + " Bar/Qr:" + this.q.d() + "/" + this.r.d());
        if (!f() || TextUtils.isEmpty(str)) {
            return;
        }
        this.s.b(str);
        t();
    }

    public void j(boolean z) {
        this.v = z;
    }

    public void k(boolean z, @NonNull CodeInfoBean codeInfoBean) {
        if (this.v) {
            this.v = z;
        }
        this.f7566g.post(new a(codeInfoBean));
        this.f7566g.updateCode(codeInfoBean.code);
        i(codeInfoBean.code);
    }

    public void n(@NonNull String str) {
        this.f7566g.updateCode(str);
        i(str);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        StringBuilder sb;
        com.jdpay.membercode.f.c cVar;
        if (view == this.f7573l) {
            com.jdpay.membercode.d.c cVar2 = this.o;
            if (cVar2 != null && cVar2.isShowing()) {
                return;
            }
            if (this.f7575n == null) {
                this.f7575n = new com.jdpay.membercode.d.a(this.f7566g.getContext());
            }
            this.f7575n.show();
            this.f7575n.a(this.q);
            sb = new StringBuilder();
            sb.append("Bar:");
            sb.append(this.q.d());
            sb.append(" Bitmap:");
            cVar = this.q;
        } else if (view != this.f7574m) {
            if (view == this.f7572k) {
                this.f7566g.activateFastPay((OpenFastPayPageInfoBean) view.getTag());
                return;
            }
            return;
        } else {
            com.jdpay.membercode.d.a aVar = this.f7575n;
            if (aVar != null && aVar.isShowing()) {
                return;
            }
            if (this.o == null) {
                this.o = new com.jdpay.membercode.d.c(this.f7566g.getContext());
            }
            this.o.show();
            this.o.a(this.r);
            sb = new StringBuilder();
            sb.append("Qr:");
            sb.append(this.r.d());
            sb.append(" Bitmap:");
            cVar = this.r;
        }
        sb.append(cVar.e());
        JDPayLog.i(sb.toString());
    }

    @Override // com.jdpay.membercode.widget.b, android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        super.onGlobalLayout();
        ViewGroup.LayoutParams layoutParams = this.f7567h.getLayoutParams();
        if (layoutParams != null) {
            if (layoutParams.width <= 0) {
                layoutParams.width = this.f7567h.getWidth();
                layoutParams.height = this.f7567h.getHeight();
                if (layoutParams.width <= 0) {
                    layoutParams.width = this.f7566g.getWidth();
                }
                if (layoutParams.height <= 0) {
                    layoutParams.height = this.f7566g.getHeight();
                }
                this.f7567h.requestLayout();
            }
            m(layoutParams.width);
        }
        if (this.s.g()) {
            t();
        }
    }

    public void s() {
        JDPayLog.i("destroy");
        this.s.d();
        this.q.c();
        this.r.c();
    }
}
