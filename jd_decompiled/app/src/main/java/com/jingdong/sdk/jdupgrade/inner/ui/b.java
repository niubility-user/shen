package com.jingdong.sdk.jdupgrade.inner.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.jingdong.sdk.jdupgrade.R;
import com.jingdong.sdk.jdupgrade.RemindType;
import com.jingdong.sdk.jdupgrade.RemindView;

/* loaded from: classes7.dex */
public class b extends RemindView {
    private View a = null;
    private String b;

    /* renamed from: c */
    private String f15130c;
    private String d;

    /* renamed from: e */
    private String f15131e;

    /* renamed from: f */
    private String f15132f;

    /* renamed from: g */
    private CheckBox f15133g;

    /* renamed from: h */
    private RemindType f15134h;

    /* renamed from: i */
    private int f15135i;

    /* renamed from: j */
    private int f15136j;

    /* renamed from: k */
    private int f15137k;

    /* renamed from: l */
    private int f15138l;

    /* renamed from: m */
    private int f15139m;

    /* renamed from: n */
    private d f15140n;
    private d o;
    private d p;
    private d q;

    /* loaded from: classes7.dex */
    public class a implements View.OnClickListener {
        a() {
            b.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            b bVar = b.this;
            bVar.cancel(bVar.f15133g != null && b.this.f15133g.isChecked());
        }
    }

    /* renamed from: com.jingdong.sdk.jdupgrade.inner.ui.b$b */
    /* loaded from: classes7.dex */
    public class ViewOnClickListenerC0728b implements View.OnClickListener {
        ViewOnClickListenerC0728b() {
            b.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            b.this.confirm();
        }
    }

    /* loaded from: classes7.dex */
    static /* synthetic */ class c {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[RemindType.values().length];
            a = iArr;
            try {
                iArr[RemindType.UPGRADE_REMIND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[RemindType.INSTALL_REMIND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void a() {
        this.b = getRemindTitle();
        this.f15130c = getRemindSubTitle();
        this.f15131e = getConfirmButtonText();
        this.f15132f = getCancelButtonText();
        this.d = getRemindContent();
        int J = com.jingdong.sdk.jdupgrade.a.c.J();
        if (J != 0) {
            this.f15135i = J;
        }
        int H = com.jingdong.sdk.jdupgrade.a.c.H();
        if (H != 0) {
            this.f15136j = H;
        }
        int K = com.jingdong.sdk.jdupgrade.a.c.K();
        if (K != 0) {
            this.f15139m = K;
        }
        d L = com.jingdong.sdk.jdupgrade.a.c.L();
        if (L != null) {
            this.p = L;
        }
        d I = com.jingdong.sdk.jdupgrade.a.c.I();
        if (I != null) {
            this.q = I;
        }
        d G = com.jingdong.sdk.jdupgrade.a.c.G();
        if (G != null) {
            this.f15140n = G;
        }
        d E = com.jingdong.sdk.jdupgrade.a.c.E();
        if (G != null) {
            this.o = E;
        }
    }

    private void a(TextView textView, String str, d dVar) {
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        textView.setText(str);
        if (dVar != null) {
            int i2 = dVar.a;
            if (i2 > 0) {
                textView.setTextSize(2, i2);
            }
            textView.setTextColor(dVar.b);
            if (dVar.f15155c) {
                textView.setTypeface(Typeface.defaultFromStyle(1));
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:(4:1|2|(1:4)(1:62)|5)|(4:7|8|(1:60)|10)|(3:12|13|(1:15))|(2:17|18)|(2:20|21)|(2:22|23)|(2:25|26)|(6:(12:31|32|33|34|35|(1:37)|38|39|40|(1:42)|43|44)|39|40|(0)|43|44)|52|53|32|33|34|35|(0)|38|(1:(0))) */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00b5 A[Catch: all -> 0x00bf, TryCatch #8 {all -> 0x00bf, blocks: (B:92:0x009f, B:94:0x00b5, B:95:0x00b8), top: B:127:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x00d0 A[Catch: all -> 0x00da, TryCatch #2 {all -> 0x00da, blocks: (B:96:0x00bf, B:98:0x00d0, B:99:0x00d3), top: B:115:0x00bf }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b() {
        /*
            r4 = this;
            android.view.View r0 = r4.a     // Catch: java.lang.Throwable -> L17
            int r1 = com.jingdong.sdk.jdupgrade.R.id.upgrade_header     // Catch: java.lang.Throwable -> L17
            android.view.View r0 = r0.findViewById(r1)     // Catch: java.lang.Throwable -> L17
            android.widget.ImageView r0 = (android.widget.ImageView) r0     // Catch: java.lang.Throwable -> L17
            int r1 = r4.f15135i     // Catch: java.lang.Throwable -> L17
            if (r1 == 0) goto L12
            r0.setImageResource(r1)     // Catch: java.lang.Throwable -> L17
            goto L17
        L12:
            int r1 = com.jingdong.sdk.jdupgrade.R.drawable.bg_white_top_radius_16     // Catch: java.lang.Throwable -> L17
            r0.setBackgroundResource(r1)     // Catch: java.lang.Throwable -> L17
        L17:
            android.view.View r0 = r4.a     // Catch: java.lang.Throwable -> L2a
            int r1 = com.jingdong.sdk.jdupgrade.R.id.upgrade_content_ll     // Catch: java.lang.Throwable -> L2a
            android.view.View r0 = r0.findViewById(r1)     // Catch: java.lang.Throwable -> L2a
            int r1 = r4.f15136j     // Catch: java.lang.Throwable -> L2a
            if (r1 == 0) goto L27
        L23:
            r0.setBackgroundResource(r1)     // Catch: java.lang.Throwable -> L2a
            goto L2a
        L27:
            int r1 = com.jingdong.sdk.jdupgrade.R.drawable.bg_white_bottom_radius_8     // Catch: java.lang.Throwable -> L2a
            goto L23
        L2a:
            int r0 = r4.f15139m     // Catch: java.lang.Throwable -> L3b
            if (r0 == 0) goto L3b
            android.view.View r0 = r4.a     // Catch: java.lang.Throwable -> L3b
            int r1 = com.jingdong.sdk.jdupgrade.R.id.upgrade_dialog_bg     // Catch: java.lang.Throwable -> L3b
            android.view.View r0 = r0.findViewById(r1)     // Catch: java.lang.Throwable -> L3b
            int r1 = r4.f15139m     // Catch: java.lang.Throwable -> L3b
            r0.setBackgroundResource(r1)     // Catch: java.lang.Throwable -> L3b
        L3b:
            android.view.View r0 = r4.a     // Catch: java.lang.Throwable -> L4c
            int r1 = com.jingdong.sdk.jdupgrade.R.id.upgrade_title     // Catch: java.lang.Throwable -> L4c
            android.view.View r0 = r0.findViewById(r1)     // Catch: java.lang.Throwable -> L4c
            android.widget.TextView r0 = (android.widget.TextView) r0     // Catch: java.lang.Throwable -> L4c
            java.lang.String r1 = r4.b     // Catch: java.lang.Throwable -> L4c
            com.jingdong.sdk.jdupgrade.inner.ui.d r2 = r4.p     // Catch: java.lang.Throwable -> L4c
            r4.a(r0, r1, r2)     // Catch: java.lang.Throwable -> L4c
        L4c:
            android.view.View r0 = r4.a     // Catch: java.lang.Throwable -> L5d
            int r1 = com.jingdong.sdk.jdupgrade.R.id.upgrade_subtitle     // Catch: java.lang.Throwable -> L5d
            android.view.View r0 = r0.findViewById(r1)     // Catch: java.lang.Throwable -> L5d
            android.widget.TextView r0 = (android.widget.TextView) r0     // Catch: java.lang.Throwable -> L5d
            java.lang.String r1 = r4.f15130c     // Catch: java.lang.Throwable -> L5d
            com.jingdong.sdk.jdupgrade.inner.ui.d r2 = r4.q     // Catch: java.lang.Throwable -> L5d
            r4.a(r0, r1, r2)     // Catch: java.lang.Throwable -> L5d
        L5d:
            android.view.View r0 = r4.a     // Catch: java.lang.Throwable -> L75
            int r1 = com.jingdong.sdk.jdupgrade.R.id.upgrade_content     // Catch: java.lang.Throwable -> L75
            android.view.View r0 = r0.findViewById(r1)     // Catch: java.lang.Throwable -> L75
            android.widget.TextView r0 = (android.widget.TextView) r0     // Catch: java.lang.Throwable -> L75
            android.text.method.MovementMethod r1 = android.text.method.ScrollingMovementMethod.getInstance()     // Catch: java.lang.Throwable -> L75
            r0.setMovementMethod(r1)     // Catch: java.lang.Throwable -> L75
            java.lang.String r1 = r4.d     // Catch: java.lang.Throwable -> L75
            com.jingdong.sdk.jdupgrade.inner.ui.d r2 = r4.q     // Catch: java.lang.Throwable -> L75
            r4.a(r0, r1, r2)     // Catch: java.lang.Throwable -> L75
        L75:
            android.view.View r0 = r4.a     // Catch: java.lang.Throwable -> L9a
            int r1 = com.jingdong.sdk.jdupgrade.R.id.upgrade_reject     // Catch: java.lang.Throwable -> L9a
            android.view.View r0 = r0.findViewById(r1)     // Catch: java.lang.Throwable -> L9a
            android.widget.CheckBox r0 = (android.widget.CheckBox) r0     // Catch: java.lang.Throwable -> L9a
            r4.f15133g = r0     // Catch: java.lang.Throwable -> L9a
            boolean r0 = r4.isForceUpgrade()     // Catch: java.lang.Throwable -> L9a
            if (r0 != 0) goto L95
            boolean r0 = r4.hideRejectCheckbox()     // Catch: java.lang.Throwable -> L9a
            if (r0 == 0) goto L8e
            goto L95
        L8e:
            android.widget.CheckBox r0 = r4.f15133g     // Catch: java.lang.Throwable -> L9a
            r1 = 0
        L91:
            r0.setVisibility(r1)     // Catch: java.lang.Throwable -> L9a
            goto L9a
        L95:
            android.widget.CheckBox r0 = r4.f15133g     // Catch: java.lang.Throwable -> L9a
            r1 = 8
            goto L91
        L9a:
            com.jingdong.sdk.jdupgrade.inner.ui.b$a r0 = new com.jingdong.sdk.jdupgrade.inner.ui.b$a
            r0.<init>()
            android.view.View r1 = r4.a     // Catch: java.lang.Throwable -> Lbf
            int r2 = com.jingdong.sdk.jdupgrade.R.id.upgrade_confirm     // Catch: java.lang.Throwable -> Lbf
            android.view.View r1 = r1.findViewById(r2)     // Catch: java.lang.Throwable -> Lbf
            android.widget.Button r1 = (android.widget.Button) r1     // Catch: java.lang.Throwable -> Lbf
            com.jingdong.sdk.jdupgrade.inner.ui.b$b r2 = new com.jingdong.sdk.jdupgrade.inner.ui.b$b     // Catch: java.lang.Throwable -> Lbf
            r2.<init>()     // Catch: java.lang.Throwable -> Lbf
            r1.setOnClickListener(r2)     // Catch: java.lang.Throwable -> Lbf
            int r2 = r4.f15137k     // Catch: java.lang.Throwable -> Lbf
            if (r2 == 0) goto Lb8
            r1.setBackgroundResource(r2)     // Catch: java.lang.Throwable -> Lbf
        Lb8:
            java.lang.String r2 = r4.f15131e     // Catch: java.lang.Throwable -> Lbf
            com.jingdong.sdk.jdupgrade.inner.ui.d r3 = r4.f15140n     // Catch: java.lang.Throwable -> Lbf
            r4.a(r1, r2, r3)     // Catch: java.lang.Throwable -> Lbf
        Lbf:
            android.view.View r1 = r4.a     // Catch: java.lang.Throwable -> Lda
            int r2 = com.jingdong.sdk.jdupgrade.R.id.upgrade_cancel     // Catch: java.lang.Throwable -> Lda
            android.view.View r1 = r1.findViewById(r2)     // Catch: java.lang.Throwable -> Lda
            android.widget.Button r1 = (android.widget.Button) r1     // Catch: java.lang.Throwable -> Lda
            r1.setOnClickListener(r0)     // Catch: java.lang.Throwable -> Lda
            int r0 = r4.f15138l     // Catch: java.lang.Throwable -> Lda
            if (r0 == 0) goto Ld3
            r1.setBackgroundResource(r0)     // Catch: java.lang.Throwable -> Lda
        Ld3:
            java.lang.String r0 = r4.f15132f     // Catch: java.lang.Throwable -> Lda
            com.jingdong.sdk.jdupgrade.inner.ui.d r2 = r4.o     // Catch: java.lang.Throwable -> Lda
            r4.a(r1, r0, r2)     // Catch: java.lang.Throwable -> Lda
        Lda:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.jdupgrade.inner.ui.b.b():void");
    }

    @Override // com.jingdong.sdk.jdupgrade.RemindView, com.jingdong.sdk.jdupgrade.a.b
    public View onCreateView(Context context) {
        LayoutInflater from;
        int i2;
        RemindType remindType = getRemindType();
        this.f15134h = remindType;
        int i3 = c.a[remindType.ordinal()];
        if (i3 != 1) {
            if (i3 == 2) {
                this.f15137k = com.jingdong.sdk.jdupgrade.a.c.y();
                this.f15138l = com.jingdong.sdk.jdupgrade.a.c.x();
                from = LayoutInflater.from(context);
                i2 = R.layout.upgrade_install_dialog_layout;
            }
            return this.a;
        }
        this.f15137k = com.jingdong.sdk.jdupgrade.a.c.F();
        this.f15138l = com.jingdong.sdk.jdupgrade.a.c.D();
        from = LayoutInflater.from(context);
        i2 = R.layout.upgrade_remind_dialog_layout;
        this.a = from.inflate(i2, (ViewGroup) null);
        return this.a;
    }

    @Override // com.jingdong.sdk.jdupgrade.RemindView, com.jingdong.sdk.jdupgrade.a.b
    public void onResume() {
        super.onResume();
        a();
        b();
    }
}
