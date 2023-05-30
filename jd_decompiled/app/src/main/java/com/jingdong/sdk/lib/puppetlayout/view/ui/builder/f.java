package com.jingdong.sdk.lib.puppetlayout.view.ui.builder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.lib.puppetlayout.R;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.d;
import com.jingdong.sdk.lib.puppetlayout.ylayout.DrawableUtils;
import com.jingdong.sdk.lib.puppetlayout.ylayout.LayoutUtils;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes8.dex */
public class f extends g<LinearLayout> implements d.b {
    private LinearLayout u;

    /* renamed from: l  reason: collision with root package name */
    private d f15244l = null;

    /* renamed from: m  reason: collision with root package name */
    private TextView f15245m = null;

    /* renamed from: n  reason: collision with root package name */
    private TextView f15246n = null;
    private TextView o = null;
    private TextView p = null;
    private TextView q = null;
    private TextView r = null;
    private int s = 0;
    private boolean t = false;
    private HashMap<String, String> v = new HashMap<>();
    private boolean w = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes8.dex */
    public class a implements Runnable {
        a(f fVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    private void A(TextView textView, String str) {
        if ("bold".equals(str)) {
            textView.setTypeface(null, 1);
        } else if (FontsUtil.KEY_MULTI_REGULAR.equals(str)) {
            textView.setTypeface(null, 0);
        } else if ("JDBBold".equals(str)) {
            com.jingdong.sdk.lib.puppetlayout.e.a.a(textView, 4097);
        } else if ("JDBRegular".equals(str)) {
            com.jingdong.sdk.lib.puppetlayout.e.a.a(textView, 4099);
        } else if ("JDBLight".equals(str)) {
            com.jingdong.sdk.lib.puppetlayout.e.a.a(textView, 4098);
        }
    }

    private void w(TextView textView, TextView textView2, TextView textView3, long j2, long j3, Runnable runnable) {
        textView.setText("00");
        textView2.setText("00");
        textView3.setText("00");
        textView.setTextColor(-1);
        textView2.setTextColor(-1);
        textView3.setTextColor(-1);
        try {
            if (j2 <= 0 && j3 <= 0) {
                textView.setText("00");
                textView2.setText("00");
                textView3.setText("00");
            } else if (this.f15244l.d()) {
                this.f15244l.e(j2, j3, this);
            }
        } catch (Exception e2) {
            if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                e2.printStackTrace();
            }
        }
    }

    private void x(long j2) {
        d dVar = this.f15244l;
        if (dVar != null) {
            dVar.b();
            this.f15244l = null;
        }
        d dVar2 = new d(this.s);
        this.f15244l = dVar2;
        PuppetContext puppetContext = this.b;
        if (puppetContext != null) {
            puppetContext.putCountdown(dVar2);
        }
        long j3 = 1;
        PuppetContext puppetContext2 = this.b;
        if (puppetContext2 != null && puppetContext2.dataTimestamp > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            PuppetContext puppetContext3 = this.b;
            j3 = currentTimeMillis - puppetContext3.dataTimestamp;
            puppetContext3.dataTimestamp = -1L;
        }
        long j4 = 0 - j3;
        long j5 = (1000 * j2) - j3;
        if (j5 <= 0) {
            return;
        }
        w(this.f15245m, this.f15246n, this.o, j4, j5, new a(this));
    }

    private void z() {
        if (f() != null) {
            f().processCountdownAction(this.b, this.f15224j);
        }
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.view.ui.builder.d.b
    public void a(e eVar, long j2, long[] jArr, int i2) {
        if (jArr == null) {
            return;
        }
        try {
            if (jArr.length == 4) {
                String str = jArr[0] + "";
                String str2 = jArr[1] + "";
                String str3 = jArr[2] + "";
                String str4 = jArr[3] + "";
                if (jArr[0] > 0) {
                    this.r.setText(str + "\u5929");
                } else {
                    this.r.setVisibility(8);
                }
                TextView textView = this.f15245m;
                if (str2.length() <= 1) {
                    str2 = "0" + str2;
                }
                textView.setText(str2);
                TextView textView2 = this.f15246n;
                if (str3.length() <= 1) {
                    str3 = "0" + str3;
                }
                textView2.setText(str3);
                TextView textView3 = this.o;
                if (str4.length() <= 1) {
                    str4 = "0" + str4;
                }
                textView3.setText(str4);
            } else if (jArr.length == 3) {
                String str5 = jArr[0] + "";
                String str6 = jArr[1] + "";
                String str7 = jArr[2] + "";
                TextView textView4 = this.f15245m;
                if (str5.length() <= 1) {
                    str5 = "0" + str5;
                }
                textView4.setText(str5);
                TextView textView5 = this.f15246n;
                if (str6.length() <= 1) {
                    str6 = "0" + str6;
                }
                textView5.setText(str6);
                TextView textView6 = this.o;
                if (str7.length() <= 1) {
                    str7 = "0" + str7;
                }
                textView6.setText(str7);
            }
        } catch (Exception e2) {
            if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.view.ui.builder.d.b
    public boolean b(e eVar, long j2, int i2) {
        this.f15245m.setText("00");
        this.f15246n.setText("00");
        this.o.setText("00");
        if (this.a.getContext() == null || !(this.a.getContext() instanceof Activity) || ((Activity) this.a.getContext()).isFinishing()) {
            return false;
        }
        z();
        return false;
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public void t() {
        float f2;
        float f3;
        int i2;
        int i3;
        int i4;
        float f4;
        boolean z = true;
        if (this.w) {
            Iterator<Map.Entry<String, String>> it = this.v.entrySet().iterator();
            String str = null;
            String str2 = null;
            float f5 = -1.0f;
            boolean z2 = false;
            float f6 = -1.0f;
            int i5 = 0;
            int i6 = -1;
            int i7 = -1;
            float f7 = -1.0f;
            int i8 = 0;
            boolean z3 = false;
            int i9 = 0;
            boolean z4 = false;
            boolean z5 = false;
            int i10 = 0;
            int i11 = -1;
            int i12 = -1;
            while (true) {
                f2 = f7;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, String> next = it.next();
                if ("countdownWidth".equals(next.getKey())) {
                    i7 = (int) LayoutUtils.getDpValue(next.getValue(), -1.0f);
                } else if ("countdownHeight".equals(next.getKey())) {
                    i6 = (int) LayoutUtils.getDpValue(next.getValue(), -1.0f);
                } else if ("countdownBackgroundColor".equals(next.getKey())) {
                    if (!TextUtils.isEmpty(next.getValue())) {
                        try {
                            i8 = Color.parseColor(next.getValue());
                            f7 = f2;
                            z2 = true;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                } else if ("countdownRoundCornerRadius".equals(next.getKey())) {
                    f5 = LayoutUtils.getDpValue(next.getValue(), -1.0f);
                } else if ("countdownTextColor".equals(next.getKey())) {
                    if (!TextUtils.isEmpty(next.getValue())) {
                        try {
                            i5 = Color.parseColor(next.getValue());
                            f7 = f2;
                            z4 = true;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                } else if ("countdownSplitSize".equals(next.getKey())) {
                    i11 = (int) LayoutUtils.getDpValue(next.getValue(), -1.0f);
                } else if ("countdownSplitColor".equals(next.getKey())) {
                    if (!TextUtils.isEmpty(next.getValue())) {
                        try {
                            i10 = Color.parseColor(next.getValue());
                            f7 = f2;
                            z5 = true;
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                } else if ("countdownSpace".equals(next.getKey())) {
                    i12 = (int) LayoutUtils.getDpValue(next.getValue(), -1.0f);
                } else if ("countdownFont".equals(next.getKey())) {
                    str = next.getValue();
                } else if ("countdownFontSize".equals(next.getKey())) {
                    f6 = LayoutUtils.getDpValue(next.getValue(), -1.0f);
                } else if (CartConstant.KEY_COUNT_DOWN_TYPE.equals(next.getKey())) {
                    if ("1".equals(next.getValue())) {
                        this.s = 1;
                        this.r.setVisibility(0);
                    } else {
                        this.s = 0;
                        this.r.setVisibility(8);
                    }
                } else if ("dayTextColor".equals(next.getKey())) {
                    try {
                        this.r.setTextColor(Color.parseColor(next.getValue()));
                    } catch (Exception e5) {
                        e5.printStackTrace();
                    }
                } else if ("dayBackgroundColor".equals(next.getKey())) {
                    if (!TextUtils.isEmpty(next.getValue())) {
                        try {
                            i9 = Color.parseColor(next.getValue());
                            f7 = f2;
                            z3 = true;
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                    }
                } else if ("dayRoundCornerRadius".equals(next.getKey())) {
                    f7 = LayoutUtils.getDpValue(next.getValue(), -1.0f);
                } else if ("dayFont".equals(next.getKey())) {
                    if (!TextUtils.isEmpty(next.getValue())) {
                        A(this.r, str);
                    }
                } else if ("dayFontSize".equals(next.getKey())) {
                    float dpValue = LayoutUtils.getDpValue(next.getValue(), -1.0f);
                    if (dpValue != -1.0f) {
                        this.r.setTextSize(0, dpValue);
                    }
                } else if ("daySpace".equals(next.getKey())) {
                    int dpValue2 = (int) LayoutUtils.getDpValue(next.getValue(), -1.0f);
                    if (dpValue2 != -1) {
                        ((LinearLayout.LayoutParams) this.r.getLayoutParams()).rightMargin = dpValue2;
                    }
                } else if ("dayWidth".equals(next.getKey())) {
                    int dpValue3 = (int) LayoutUtils.getDpValue(next.getValue(), -1.0f);
                    if (dpValue3 != -1) {
                        ((LinearLayout.LayoutParams) this.r.getLayoutParams()).width = dpValue3;
                    }
                } else if ("dayHeight".equals(next.getKey())) {
                    int dpValue4 = (int) LayoutUtils.getDpValue(next.getValue(), -1.0f);
                    if (dpValue4 != -1) {
                        ((LinearLayout.LayoutParams) this.r.getLayoutParams()).height = dpValue4;
                    }
                } else if ("limitTime".equals(next.getKey())) {
                    str2 = next.getValue();
                }
                f7 = f2;
            }
            float[] fArr = {Math.max(0.0f, f5), Math.max(0.0f, f5), Math.max(0.0f, f5), Math.max(0.0f, f5), Math.max(0.0f, f5), Math.max(0.0f, f5), Math.max(0.0f, f5), Math.max(0.0f, f5)};
            if (z2) {
                f3 = f6;
                i2 = i5;
                i3 = i6;
                i4 = i7;
                f4 = f2;
                DrawableUtils.setCompactBg(this.f15245m, DrawableUtils.createNormalBg(i8, true, -1, -1, false, fArr, false));
                DrawableUtils.setCompactBg(this.f15246n, DrawableUtils.createNormalBg(i8, true, -1, -1, false, fArr, false));
                DrawableUtils.setCompactBg(this.o, DrawableUtils.createNormalBg(i8, true, -1, -1, false, fArr, false));
            } else {
                f3 = f6;
                i2 = i5;
                i3 = i6;
                i4 = i7;
                f4 = f2;
            }
            if (z3) {
                DrawableUtils.setCompactBg(this.r, DrawableUtils.createNormalBg(i9, true, -1, -1, false, new float[]{Math.max(0.0f, f4), Math.max(0.0f, f4), Math.max(0.0f, f4), Math.max(0.0f, f4), Math.max(0.0f, f4), Math.max(0.0f, f4), Math.max(0.0f, f4), Math.max(0.0f, f4)}, false));
            }
            int i13 = i4;
            int i14 = -1;
            if (i13 != -1) {
                ((LinearLayout.LayoutParams) this.f15245m.getLayoutParams()).width = i13;
                ((LinearLayout.LayoutParams) this.f15246n.getLayoutParams()).width = i13;
                ((LinearLayout.LayoutParams) this.o.getLayoutParams()).width = i13;
                i14 = -1;
            }
            if (i3 != i14) {
                ((LinearLayout.LayoutParams) this.f15245m.getLayoutParams()).height = i3;
                ((LinearLayout.LayoutParams) this.f15246n.getLayoutParams()).height = i3;
                ((LinearLayout.LayoutParams) this.o.getLayoutParams()).height = i3;
            }
            if (z4) {
                int i15 = i2;
                this.f15245m.setTextColor(i15);
                this.f15246n.setTextColor(i15);
                this.o.setTextColor(i15);
            }
            if (f3 != -1.0f) {
                this.f15245m.setTextSize(0, f3);
                this.f15246n.setTextSize(0, f3);
                this.o.setTextSize(0, f3);
            }
            if (z5) {
                int i16 = i10;
                this.p.setTextColor(i16);
                this.q.setTextColor(i16);
            }
            int i17 = i11;
            if (i17 != -1) {
                float f8 = i17;
                this.p.setTextSize(0, f8);
                this.q.setTextSize(0, f8);
            }
            int i18 = i12;
            if (i18 != -1) {
                ((LinearLayout.LayoutParams) this.p.getLayoutParams()).leftMargin = i18;
                ((LinearLayout.LayoutParams) this.p.getLayoutParams()).rightMargin = i18;
                ((LinearLayout.LayoutParams) this.q.getLayoutParams()).leftMargin = i18;
                ((LinearLayout.LayoutParams) this.q.getLayoutParams()).rightMargin = i18;
            }
            if (!TextUtils.isEmpty(str)) {
                A(this.f15245m, str);
                A(this.f15246n, str);
                A(this.o, str);
                A(this.p, str);
                A(this.q, str);
            }
            if (!TextUtils.isEmpty(str2)) {
                try {
                    x(Long.valueOf(str2).longValue());
                } catch (NumberFormatException e7) {
                    e7.printStackTrace();
                }
            }
            this.w = false;
            z = true;
        }
        this.t = z;
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.view.ui.builder.g
    public boolean v(String str, String str2) {
        if ("limitTime".equals(str) && !TextUtils.isEmpty(str2)) {
            try {
                if (this.t) {
                    x(Long.valueOf(str2).longValue());
                } else {
                    this.v.put(str, str2);
                    this.w = true;
                }
            } catch (NumberFormatException e2) {
                if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                    e2.printStackTrace();
                }
            }
            return true;
        } else if (("countdownWidth".equals(str) || "countdownHeight".equals(str) || "countdownBackgroundColor".equals(str) || "countdownRoundCornerRadius".equals(str) || "countdownTextColor".equals(str) || "countdownSplitSize".equals(str) || "countdownSplitColor".equals(str) || "countdownSpace".equals(str) || "countdownFont".equals(str) || "countdownFontSize".equals(str) || CartConstant.KEY_COUNT_DOWN_TYPE.equals(str) || "dayTextColor".equals(str) || "dayBackgroundColor".equals(str) || "dayRoundCornerRadius".equals(str) || "dayFont".equals(str) || "dayFontSize".equals(str) || "daySpace".equals(str) || "dayWidth".equals(str) || "dayHeight".equals(str)) && !TextUtils.isEmpty(str2)) {
            this.v.put(str, str2);
            this.w = true;
            return true;
        } else if (DYConstants.DY_GRAVITY.equals(str)) {
            this.u.setGravity(com.jingdong.sdk.lib.puppetlayout.h.c.a.b(str2).a);
            return true;
        } else {
            return false;
        }
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.view.ui.builder.g
    /* renamed from: y  reason: merged with bridge method [inline-methods] */
    public LinearLayout u(Context context) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.count_down, (ViewGroup) null, false);
        this.u = linearLayout;
        this.f15245m = (TextView) linearLayout.findViewById(R.id.coutdown_hh);
        this.f15246n = (TextView) this.u.findViewById(R.id.countdown_mm);
        this.o = (TextView) this.u.findViewById(R.id.countdown_ss);
        this.p = (TextView) this.u.findViewById(R.id.left_dot);
        this.q = (TextView) this.u.findViewById(R.id.right_dot);
        this.r = (TextView) this.u.findViewById(R.id.countdown_day);
        return this.u;
    }
}
