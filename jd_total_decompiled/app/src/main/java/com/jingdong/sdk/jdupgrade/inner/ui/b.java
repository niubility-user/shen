package com.jingdong.sdk.jdupgrade.inner.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
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
    /* JADX WARN: Removed duplicated region for block: B:159:0x00b5 A[Catch: all -> 0x00bf, TryCatch #8 {all -> 0x00bf, blocks: (B:157:0x009f, B:159:0x00b5, B:160:0x00b8), top: B:192:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x00d0 A[Catch: all -> 0x00da, TryCatch #2 {all -> 0x00da, blocks: (B:161:0x00bf, B:163:0x00d0, B:164:0x00d3), top: B:180:0x00bf }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void b() {
        int i2;
        int i3;
        CheckBox checkBox;
        int i4;
        try {
            ImageView imageView = (ImageView) this.a.findViewById(R.id.upgrade_header);
            int i5 = this.f15135i;
            if (i5 != 0) {
                imageView.setImageResource(i5);
            } else {
                imageView.setBackgroundResource(R.drawable.bg_white_top_radius_16);
            }
        } catch (Throwable unused) {
        }
        try {
            View findViewById = this.a.findViewById(R.id.upgrade_content_ll);
            int i6 = this.f15136j;
            if (i6 == 0) {
                i6 = R.drawable.bg_white_bottom_radius_8;
            }
            findViewById.setBackgroundResource(i6);
        } catch (Throwable unused2) {
        }
        try {
            if (this.f15139m != 0) {
                this.a.findViewById(R.id.upgrade_dialog_bg).setBackgroundResource(this.f15139m);
            }
        } catch (Throwable unused3) {
        }
        try {
            a((TextView) this.a.findViewById(R.id.upgrade_title), this.b, this.p);
        } catch (Throwable unused4) {
        }
        try {
            a((TextView) this.a.findViewById(R.id.upgrade_subtitle), this.f15130c, this.q);
        } catch (Throwable unused5) {
        }
        try {
            TextView textView = (TextView) this.a.findViewById(R.id.upgrade_content);
            textView.setMovementMethod(ScrollingMovementMethod.getInstance());
            a(textView, this.d, this.q);
        } catch (Throwable unused6) {
        }
        try {
            this.f15133g = (CheckBox) this.a.findViewById(R.id.upgrade_reject);
        } catch (Throwable unused7) {
        }
        try {
            if (!isForceUpgrade() && !hideRejectCheckbox()) {
                checkBox = this.f15133g;
                i4 = 0;
                checkBox.setVisibility(i4);
                a aVar = new a();
                Button button = (Button) this.a.findViewById(R.id.upgrade_confirm);
                button.setOnClickListener(new ViewOnClickListenerC0728b());
                i3 = this.f15137k;
                if (i3 != 0) {
                    button.setBackgroundResource(i3);
                }
                a(button, this.f15131e, this.f15140n);
                Button button2 = (Button) this.a.findViewById(R.id.upgrade_cancel);
                button2.setOnClickListener(aVar);
                i2 = this.f15138l;
                if (i2 != 0) {
                    button2.setBackgroundResource(i2);
                }
                a(button2, this.f15132f, this.o);
                return;
            }
            Button button22 = (Button) this.a.findViewById(R.id.upgrade_cancel);
            button22.setOnClickListener(aVar);
            i2 = this.f15138l;
            if (i2 != 0) {
            }
            a(button22, this.f15132f, this.o);
            return;
        } catch (Throwable unused8) {
            return;
        }
        checkBox = this.f15133g;
        i4 = 8;
        checkBox.setVisibility(i4);
        a aVar2 = new a();
        Button button3 = (Button) this.a.findViewById(R.id.upgrade_confirm);
        button3.setOnClickListener(new ViewOnClickListenerC0728b());
        i3 = this.f15137k;
        if (i3 != 0) {
        }
        a(button3, this.f15131e, this.f15140n);
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
