package com.jd.lib.flexcube.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.LineHeightSpan;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import com.jd.lib.flexcube.help.MsgActionImp;
import com.jd.lib.flexcube.help.MsgActionInterface;
import com.jd.lib.flexcube.help.MsgInterface;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.entity.material.PaddingInfo;
import com.jd.lib.flexcube.iwidget.ui.IKnowWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.widgets.b.b;
import com.jd.lib.flexcube.widgets.b.e;
import com.jd.lib.flexcube.widgets.b.f;
import com.jd.lib.flexcube.widgets.c.a;
import com.jd.lib.flexcube.widgets.entity.TextEntity;
import com.jd.lib.flexcube.widgets.entity.common.FontInfo;
import com.jd.lib.flexcube.widgets.entity.common.FrameInfo;
import com.jd.lib.flexcube.widgets.entity.text.TextConfig;
import com.jd.lib.flexcube.widgets.entity.text.TextDataPath;
import com.jingdong.common.utils.LangUtils;
import java.io.Serializable;
import java.util.Map;

/* loaded from: classes15.dex */
public class FlexTextView extends AppCompatTextView implements IWidget<TextEntity>, IKnowWidget<TextEntity>, MsgInterface {
    private String A;
    private int B;
    private int C;

    /* renamed from: g */
    private TextEntity f4484g;

    /* renamed from: h */
    private float f4485h;

    /* renamed from: i */
    private String f4486i;

    /* renamed from: j */
    private int f4487j;

    /* renamed from: k */
    private int f4488k;

    /* renamed from: l */
    private int f4489l;

    /* renamed from: m */
    private int f4490m;

    /* renamed from: n */
    private int f4491n;
    private String o;
    private e p;
    protected Paint q;
    private int r;
    private String s;
    private String t;
    private int u;
    private boolean v;
    private TextConfig w;
    private int x;
    private boolean y;
    private String z;

    /* loaded from: classes15.dex */
    public class a implements LineHeightSpan {

        /* renamed from: g */
        private int f4492g;

        public a(FlexTextView flexTextView, int i2) {
            this.f4492g = i2;
        }

        @Override // android.text.style.LineHeightSpan
        public void chooseHeight(CharSequence charSequence, int i2, int i3, int i4, int i5, Paint.FontMetricsInt fontMetricsInt) {
            int i6 = fontMetricsInt.descent;
            int i7 = i6 - fontMetricsInt.ascent;
            if (i7 <= 0) {
                return;
            }
            int round = Math.round(i6 * ((this.f4492g * 1.0f) / i7));
            fontMetricsInt.descent = round;
            fontMetricsInt.ascent = round - this.f4492g;
        }
    }

    public FlexTextView(Context context) {
        super(context);
        this.f4491n = 1;
        this.p = new e(this);
    }

    private int a(@NonNull Map map, String str, int i2) {
        if (!TextUtils.isEmpty(str) && this.w != null) {
            String d = b.d(map, str);
            if (!TextUtils.isEmpty(d)) {
                return com.jd.lib.flexcube.iwidget.b.a.a(d, -16777216);
            }
        }
        return i2;
    }

    private void h(@NonNull Map map) {
        if (!TextUtils.isEmpty(this.s)) {
            String d = b.d(map, this.s);
            if (!TextUtils.isEmpty(d)) {
                this.r = com.jd.lib.flexcube.iwidget.b.a.a(d, 0);
            }
        }
        setBackgroundColor(this.r);
    }

    private void j(Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        Serializable serializable = iWidgetCommunication.getStateBundle().getSerializable(this.f4484g.dataPath.clickEvent);
        ClickEvent clickEvent = serializable instanceof ClickEvent ? (ClickEvent) serializable : null;
        if (clickEvent == null) {
            clickEvent = b.a(map, this.f4484g.dataPath.clickEvent);
        }
        if (clickEvent != null) {
            a.b bVar = new a.b(getContext(), clickEvent);
            bVar.a(iWidgetCommunication.getBabelScope());
            setOnClickListener(bVar.b());
            iWidgetCommunication.getStateBundle().putSerializable(this.f4484g.dataPath.clickEvent, clickEvent);
            return;
        }
        setClickable(false);
    }

    private void k(int i2) {
        this.p.m(this.w.frameInfo, i2, this.f4485h);
        setTextColor(i2);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IKnowWidget
    /* renamed from: b */
    public TextEntity getWidgetEntity() {
        return this.f4484g;
    }

    public void c(@NonNull Map map) {
        this.B = a(map, this.z, this.B);
        this.C = a(map, this.A, this.C);
        this.t = e() ? this.A : this.z;
        this.x = e() ? this.C : this.B;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
        setText("");
        this.o = null;
        setBackgroundColor(0);
        setTextSize(0, 0.0f);
        this.p.q(0);
        setClickable(false);
    }

    public void d() {
        if (!TextUtils.isEmpty(this.w.darkColor) && this.w.darkColor.startsWith("$")) {
            this.A = this.w.darkColor;
        } else {
            this.C = com.jd.lib.flexcube.iwidget.b.a.a(this.w.darkColor, -16777216);
        }
        if (!TextUtils.isEmpty(this.w.color) && this.w.color.startsWith("$")) {
            this.z = this.w.color;
        } else {
            this.B = com.jd.lib.flexcube.iwidget.b.a.a(this.w.color, -16777216);
        }
        this.t = e() ? this.A : this.z;
        this.x = e() ? this.C : this.B;
        if (TextUtils.isEmpty(this.t)) {
            setTextColor(this.x);
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.p.b(canvas);
        super.draw(canvas);
    }

    public boolean e() {
        return this.y && !TextUtils.isEmpty(this.w.darkColor);
    }

    public boolean f() {
        return !TextUtils.isEmpty(this.w.darkColor);
    }

    public void g(boolean z) {
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        if ("11".equals(this.f4486i)) {
            return this.f4488k;
        }
        return -2;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        if ("11".equals(this.f4486i)) {
            return this.f4487j;
        }
        if ("1".equals(this.f4486i)) {
            return -2;
        }
        return this.f4487j;
    }

    public void i(CharSequence charSequence) {
        SpannableStringBuilder spannableStringBuilder;
        if (charSequence == null) {
            return;
        }
        int textSize = (int) getTextSize();
        if (charSequence instanceof SpannableStringBuilder) {
            spannableStringBuilder = (SpannableStringBuilder) charSequence;
            double d = textSize;
            Double.isNaN(d);
            spannableStringBuilder.setSpan(new a(this, (int) (d * 1.6d)), 0, charSequence.length(), 33);
        } else {
            spannableStringBuilder = new SpannableStringBuilder(charSequence);
            double d2 = textSize;
            Double.isNaN(d2);
            spannableStringBuilder.setSpan(new a(this, (int) (d2 * 1.6d)), 0, charSequence.length(), 33);
        }
        setText(spannableStringBuilder);
    }

    public void l(boolean z) {
        if (this.y == z) {
            return;
        }
        this.y = z;
        this.t = e() ? this.A : this.z;
        int i2 = e() ? this.C : this.B;
        this.x = i2;
        k(i2);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    /* renamed from: m */
    public void updateStyle(@NonNull TextEntity textEntity, float f2) {
        TextConfig textConfig;
        this.f4484g = textEntity;
        if (textEntity != null && (textConfig = textEntity.config) != null) {
            this.f4485h = f2;
            this.w = textConfig;
            this.f4486i = textConfig.autoFitType;
            this.f4487j = com.jd.lib.flexcube.iwidget.b.b.d((int) textConfig.w, f2);
            this.f4488k = com.jd.lib.flexcube.iwidget.b.b.d((int) this.w.f4225h, f2);
            this.f4491n = com.jd.lib.flexcube.iwidget.b.b.f(this.w.maxRowNum, 1);
            if ("1".equals(this.f4486i)) {
                setMaxLines(1);
            } else {
                int i2 = this.f4491n;
                if (i2 > 0) {
                    setMaxLines(i2);
                }
            }
            d();
            if (!TextUtils.isEmpty(this.w.bgColor) && this.w.bgColor.startsWith("$")) {
                this.s = this.w.bgColor;
            } else {
                int a2 = com.jd.lib.flexcube.iwidget.b.a.a(this.w.bgColor, 0);
                this.r = a2;
                setBackgroundColor(a2);
            }
            FontInfo fontInfo = this.w.fontInfo;
            if (fontInfo != null) {
                this.f4489l = com.jd.lib.flexcube.iwidget.b.b.d((int) fontInfo.size, this.f4485h);
            }
            FontInfo fontInfo2 = this.w.fontInfo;
            if (fontInfo2 != null) {
                this.v = "1".equals(fontInfo2.italic);
            }
            f.f(this, this.w.fontInfo, f2, true);
            f.e(this, this.w.fontInfo);
            PaddingInfo paddingInfo = this.w.paddingInfo;
            if (paddingInfo != null) {
                this.f4490m = com.jd.lib.flexcube.iwidget.b.b.d(paddingInfo.getLeftAndRightValue(), this.f4485h);
            }
            f.d(this, this.w.paddingInfo, f2);
            if (!"1".equals(this.f4486i)) {
                f.c(this, this.w.fontInfo);
            } else {
                setGravity(19);
            }
            try {
                if ("1".equals(this.w.ellipsizeClose)) {
                    setEllipsize(null);
                } else {
                    setEllipsize(TextUtils.TruncateAt.END);
                }
            } catch (Exception unused) {
            }
            this.p.i(this.w.cfInfo, f2);
            FrameInfo frameInfo = this.w.frameInfo;
            if (frameInfo != null) {
                int f3 = com.jd.lib.flexcube.iwidget.b.b.f(frameInfo.size, 0);
                int d = com.jd.lib.flexcube.iwidget.b.b.d(f3, f2);
                this.u = d;
                if (d == 0 && f3 > 0) {
                    this.u = 1;
                }
            }
            if (TextUtils.isEmpty(this.t)) {
                this.p.m(this.w.frameInfo, this.x, f2);
            }
            TextDataPath textDataPath = textEntity.dataPath;
            if (textDataPath != null) {
                String str = textDataPath.text;
                if (c.d(str)) {
                    this.o = str;
                    return;
                } else {
                    this.o = null;
                    return;
                }
            }
            return;
        }
        clear();
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        this.p.h(canvas);
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        TextConfig textConfig = this.w;
        if (textConfig == null || textConfig.cfInfo == null || getHeight() <= 0) {
            return;
        }
        if (this.w.cfInfo.setHeightHalf(getHeight() >> 1) || this.w.cfInfo.radiusChange) {
            this.p.i(this.w.cfInfo, 1.0f);
        }
    }

    @Override // com.jd.lib.flexcube.help.MsgInterface
    public void pushAction(Class<? extends MsgActionInterface> cls, Object obj) {
        if (!(obj instanceof MsgActionImp) || obj == null) {
            return;
        }
        try {
            if ("darkModeChange".equals((String) ((MsgActionImp) obj).hashMap.get("type"))) {
                l(((Boolean) ((MsgActionImp) obj).hashMap.get("darkMode")).booleanValue());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateContent(@NonNull Map map, IWidgetCommunication iWidgetCommunication) {
        TextDataPath textDataPath;
        String d = b.d(map, this.o);
        if (c.d(d)) {
            setVisibility(0);
            if (this.v) {
                d = d + LangUtils.SINGLE_SPACE;
            }
            if ("1".equals(this.f4486i)) {
                if (this.q == null) {
                    this.q = new Paint();
                }
                int a2 = f.a(this.q, d, this.f4489l);
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                int i2 = this.f4487j;
                if (i2 > 0 && i2 < a2 + this.f4490m) {
                    layoutParams.width = i2;
                } else {
                    layoutParams.width = -2;
                }
                setLayoutParams(layoutParams);
            } else {
                ViewGroup.LayoutParams layoutParams2 = getLayoutParams();
                layoutParams2.width = this.f4487j;
                setLayoutParams(layoutParams2);
            }
            h(map);
            this.y = iWidgetCommunication.getBabelScope() == null ? false : iWidgetCommunication.getBabelScope().isDark();
            c(map);
            if (f()) {
                k(this.x);
            } else if (!TextUtils.isEmpty(this.t)) {
                k(this.x);
            }
            this.p.q(this.u);
            i(d);
            TextEntity textEntity = this.f4484g;
            if (textEntity != null && (textDataPath = textEntity.dataPath) != null && !TextUtils.isEmpty(textDataPath.clickEvent)) {
                j(map, iWidgetCommunication);
                return;
            } else {
                setClickable(false);
                return;
            }
        }
        setVisibility(8);
        setBackgroundColor(0);
        this.p.q(0);
        setText("");
        setClickable(false);
    }
}
