package com.jd.dynamic.lib.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.TypefaceSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.IImageLoader;
import com.jd.dynamic.lib.utils.DPIUtil;
import com.jd.dynamic.yoga.android.YogaLayout;
import java.util.List;

/* loaded from: classes13.dex */
public class RichTextView extends AppCompatTextView {
    private static final String C = RichTextView.class.getSimpleName();
    public static final SpannableString ELLIPSIS_STRING = new SpannableString("...");
    private SpannableStringBuilder A;
    private SpannableStringBuilder B;

    /* renamed from: g  reason: collision with root package name */
    private int f2495g;

    /* renamed from: h  reason: collision with root package name */
    private int f2496h;

    /* renamed from: i  reason: collision with root package name */
    private int f2497i;

    /* renamed from: j  reason: collision with root package name */
    private float f2498j;

    /* renamed from: k  reason: collision with root package name */
    private SpannableStringBuilder f2499k;

    /* renamed from: l  reason: collision with root package name */
    private SpannableStringBuilder f2500l;

    /* renamed from: m  reason: collision with root package name */
    private SpannableStringBuilder f2501m;

    /* renamed from: n  reason: collision with root package name */
    private SpannableString f2502n;
    private SpannableString o;
    private ImageSpan p;
    private ImageSpan q;
    private String r;
    private Typeface s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private DynamicTemplateEngine x;
    private String y;
    private String z;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class OverLinkMovementMethod extends LinkMovementMethod {
        private static OverLinkMovementMethod a;

        private OverLinkMovementMethod() {
        }

        public static MovementMethod getInstance() {
            if (a == null) {
                a = new OverLinkMovementMethod();
            }
            return a;
        }

        @Override // android.text.method.LinkMovementMethod, android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
        public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 2) {
                return true;
            }
            return super.onTouchEvent(textView, spannable, motionEvent);
        }
    }

    public RichTextView(Context context) {
        this(context, null);
    }

    public RichTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RichTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f2495g = 0;
        this.f2496h = 0;
        this.f2497i = 0;
        this.t = false;
        this.u = false;
        this.v = false;
        this.z = "";
        e();
    }

    private void A() {
        if (getSuffixText() != null && this.o != null && this.f2502n != null) {
            SpannableString spannableString = new SpannableString(getSuffixText());
            SpannableString spannableString2 = this.o;
            spannableString2.setSpan(spannableString, 0, spannableString2.length(), 33);
            int indexOf = this.f2500l.toString().indexOf(this.f2502n.toString());
            int length = this.f2502n.length() + indexOf;
            if (indexOf > 0 && length >= indexOf) {
                this.f2500l.replace(indexOf, length, (CharSequence) this.o);
            }
        }
        B();
        setMaxLines(Integer.MAX_VALUE);
        setText(this.f2500l, TextView.BufferType.SPANNABLE);
        this.A = this.f2500l;
        this.B = null;
        requestLayout();
        D();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        if (this.q == null || this.f2500l == null) {
            return;
        }
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.jd.dynamic.lib.views.RichTextView.4
            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View view) {
                List<String> i2 = com.jd.dynamic.lib.utils.s.i(RichTextView.this.z);
                for (int i3 = 0; i3 < i2.size(); i3++) {
                    RichTextView richTextView = RichTextView.this;
                    com.jd.dynamic.lib.utils.s.b(i2.get(i3), richTextView, richTextView.x, RichTextView.this);
                }
                RichTextView.this.z();
            }
        };
        int indexOf = this.f2500l.toString().indexOf(this.r);
        int length = this.r.length() + indexOf;
        if (indexOf <= 0 || length < indexOf) {
            return;
        }
        this.f2500l.setSpan(this.q, indexOf, length, 33);
        this.f2500l.setSpan(clickableSpan, indexOf, length, 33);
    }

    private void C() {
        setMaxLines(this.f2495g + 1);
        setText(this.f2501m);
        this.B = this.f2501m;
        this.A = null;
        requestLayout();
        D();
    }

    private void D() {
        if (getParent().getParent() instanceof YogaLayout) {
            ((YogaLayout) getParent().getParent()).invalidate((View) getParent());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E() {
        m(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F() {
        try {
            m(false);
        } catch (Exception unused) {
        }
    }

    private Layout a(SpannableStringBuilder spannableStringBuilder, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            StaticLayout.Builder obtain = StaticLayout.Builder.obtain(spannableStringBuilder, 0, spannableStringBuilder.length(), getPaint(), i2);
            obtain.setAlignment(Layout.Alignment.ALIGN_NORMAL);
            obtain.setLineSpacing(getLineSpacingExtra(), getLineSpacingMultiplier());
            obtain.setEllipsizedWidth(ELLIPSIS_STRING.length());
            return obtain.build();
        }
        return new StaticLayout(spannableStringBuilder, getPaint(), i2, Layout.Alignment.ALIGN_NORMAL, getLineSpacingMultiplier(), getLineSpacingExtra(), getIncludeFontPadding());
    }

    private void a(int i2, int i3) {
        if (i2 == 0) {
            i2 = Integer.MAX_VALUE;
        }
        try {
            Layout a = a(this.f2499k, i3);
            int lineEnd = a.getLineCount() > i2 ? a.getLineEnd(i2 - 1) : this.f2499k.length();
            this.f2501m = b(this.f2499k.length() <= lineEnd ? this.f2499k : this.f2499k.subSequence(0, lineEnd));
        } catch (Exception unused) {
        }
    }

    private SpannableStringBuilder b(@NonNull CharSequence charSequence) {
        return new SpannableStringBuilder(charSequence);
    }

    private void e() {
        setMovementMethod(OverLinkMovementMethod.getInstance());
        setIncludeFontPadding(false);
        if (this.t) {
            return;
        }
        post(new Runnable() { // from class: com.jd.dynamic.lib.views.q
            @Override // java.lang.Runnable
            public final void run() {
                RichTextView.this.F();
            }
        });
    }

    private void f(final SpannableStringBuilder spannableStringBuilder, final SpanView spanView, String str) {
        final String str2;
        if (DynamicSdk.getEngine().getImageLoader() != null) {
            if (TextUtils.isEmpty(spanView.subtype)) {
                str2 = "image" + System.currentTimeMillis();
            } else {
                str2 = "";
            }
            if (!TextUtils.isEmpty(spanView.onClick)) {
                this.z = spanView.onClick;
            }
            DynamicSdk.getEngine().getImageLoader().loadImage(str, new IImageLoader.ImageRequestListener<Bitmap>() { // from class: com.jd.dynamic.lib.views.RichTextView.1
                @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
                public void onCancel() {
                    int indexOf = spannableStringBuilder.toString().indexOf(str2);
                    if (indexOf > 0) {
                        spannableStringBuilder.replace(indexOf, str2.length() + indexOf, (CharSequence) "");
                    }
                }

                @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
                public void onFailure(Throwable th) {
                    int indexOf = spannableStringBuilder.toString().indexOf(str2);
                    if (indexOf > 0) {
                        spannableStringBuilder.replace(indexOf, str2.length() + indexOf, (CharSequence) "");
                    }
                }

                @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
                public void onSuccess(Bitmap bitmap) {
                    String str3;
                    if (bitmap == null || TextUtils.isEmpty(spanView.subtype) || RichTextView.this.p != null || (str3 = spanView.subtype) == null || !str3.equals("stick")) {
                        return;
                    }
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(RichTextView.this.getResources(), bitmap);
                    RichTextView.this.k(spanView, bitmapDrawable);
                    RichTextView.this.p = new ImageSpan(bitmapDrawable);
                    RichTextView.this.m(true);
                }
            });
        }
    }

    private void j(final SpanView spanView) {
        Drawable drawable;
        if (TextUtils.isEmpty(spanView.selectedSrc)) {
            return;
        }
        if (spanView.selectedSrc.startsWith("http")) {
            if (this.q != null) {
                return;
            }
            DynamicSdk.getEngine().getImageLoader().loadImage(spanView.selectedSrc, new IImageLoader.ImageRequestListener<Bitmap>() { // from class: com.jd.dynamic.lib.views.RichTextView.5
                @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
                public void onCancel() {
                }

                @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
                public void onFailure(Throwable th) {
                }

                @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
                public void onSuccess(Bitmap bitmap) {
                    if (bitmap != null) {
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(RichTextView.this.getResources(), bitmap);
                        RichTextView.this.k(spanView, bitmapDrawable);
                        RichTextView.this.q = new ImageSpan(bitmapDrawable);
                        RichTextView.this.B();
                    }
                }
            });
            return;
        }
        DynamicTemplateEngine dynamicTemplateEngine = this.x;
        String str = spanView.selectedSrc;
        Context context = getContext();
        DynamicTemplateEngine dynamicTemplateEngine2 = this.x;
        int c2 = com.jd.dynamic.lib.viewparse.d.c(dynamicTemplateEngine, str, context, dynamicTemplateEngine2 == null ? null : dynamicTemplateEngine2.mPackageName);
        if (c2 == 0 || (drawable = getResources().getDrawable(c2)) == null) {
            return;
        }
        k(spanView, drawable);
        this.q = new ImageSpan(drawable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(SpanView spanView, Drawable drawable) {
        if (spanView.getLayoutParams() == null || spanView.getLayoutParams().height <= 0 || spanView.getLayoutParams().width <= 0) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        } else {
            drawable.setBounds(0, 0, spanView.getLayoutParams().width, spanView.getLayoutParams().height);
        }
    }

    private void l(final SpanView spanView, SpannableString spannableString, int i2) {
        final int richTextColor = !Float.isNaN(spanView.textColor) ? (int) spanView.textColor : getRichTextColor();
        if (this.f2496h == 0) {
            this.f2496h = richTextColor;
        }
        float f2 = spanView.textSize;
        if (f2 == -1.0f) {
            f2 = getRichTextSize();
        }
        Typeface typeface = Typeface.DEFAULT;
        Typeface typeface2 = spanView.getTypeface() != null ? spanView.getTypeface() : getRichTextStyle();
        if (Build.VERSION.SDK_INT >= 28) {
            spannableString.setSpan(new TypefaceSpan(typeface2), 0, i2, 33);
        }
        spannableString.setSpan(new AbsoluteSizeSpan(DPIUtil.sp2px(getContext(), f2)), 0, i2, 34);
        spannableString.setSpan(new ForegroundColorSpan(richTextColor), 0, i2, 33);
        if (TextUtils.isEmpty(spanView.onClick)) {
            return;
        }
        spannableString.setSpan(new ClickableSpan() { // from class: com.jd.dynamic.lib.views.RichTextView.2
            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View view) {
                String str;
                SpanView spanView2 = spanView;
                String str2 = spanView2.onClick;
                if (TextUtils.isEmpty(spanView2.subtype) || (str = spanView.subtype) == null) {
                    str = "";
                }
                int i3 = 0;
                if (!str.equals("stick")) {
                    if (str2 != null) {
                        List<String> i4 = com.jd.dynamic.lib.utils.s.i(str2);
                        while (i3 < i4.size()) {
                            RichTextView richTextView = RichTextView.this;
                            com.jd.dynamic.lib.utils.s.b(i4.get(i3), richTextView, richTextView.x, RichTextView.this);
                            i3++;
                        }
                    }
                } else if (str2 != null) {
                    List<String> i5 = com.jd.dynamic.lib.utils.s.i(str2);
                    while (i3 < i5.size()) {
                        RichTextView richTextView2 = RichTextView.this;
                        com.jd.dynamic.lib.utils.s.b(i5.get(i3), richTextView2, richTextView2.x, RichTextView.this);
                        i3++;
                    }
                    RichTextView.this.z();
                }
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NonNull TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(richTextColor);
                textPaint.setUnderlineText(false);
            }
        }, 0, i2, 33);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(boolean z) {
        SpannableStringBuilder spannableStringBuilder;
        this.t = true;
        this.f2501m = new SpannableStringBuilder();
        int i2 = this.f2495g;
        this.f2500l = b(this.f2499k);
        int width = getWidth();
        Layout a = a(this.f2499k, width);
        if (z) {
            int lineCount = a.getLineCount();
            int i3 = ((RichTextViewContainer) getParent()).maxlines;
            if (lineCount < i3) {
                SpannableStringBuilder spannableStringBuilder2 = this.f2500l;
                this.A = spannableStringBuilder2;
                this.B = null;
                setText(spannableStringBuilder2, TextView.BufferType.SPANNABLE);
                return;
            }
            x();
            if (i3 != 0 && i3 != Integer.MAX_VALUE) {
                a(i3, width);
            }
            p();
            r();
            if (getExpendable()) {
                if (isRichExpand()) {
                    super.setMaxLines(this.f2495g + 1);
                    spannableStringBuilder = this.f2501m;
                    this.B = spannableStringBuilder;
                    this.A = null;
                } else {
                    super.setMaxLines(this.f2495g);
                    spannableStringBuilder = this.f2500l;
                    this.A = spannableStringBuilder;
                    this.B = null;
                }
            }
            requestLayout();
            D();
        }
        boolean z2 = a.getLineCount() > i2 && i2 != 0;
        if (z2) {
            x();
            a(i2, width);
            p();
            r();
        }
        setRichExpand(z2);
        if (z2) {
            if (getExpendable() && isRichExpand()) {
                super.setMaxLines(this.f2495g + 1);
            } else {
                super.setMaxLines(this.f2495g);
            }
            spannableStringBuilder = this.f2501m;
            this.B = spannableStringBuilder;
            this.A = null;
        }
        spannableStringBuilder = this.f2500l;
        this.A = spannableStringBuilder;
        this.B = null;
        setText(spannableStringBuilder, TextView.BufferType.SPANNABLE);
        requestLayout();
        D();
    }

    private void p() {
        if (!TextUtils.isEmpty(this.f2501m)) {
            int length = this.f2501m.length();
            SpannableString spannableString = ELLIPSIS_STRING;
            if (length > spannableString.length()) {
                SpannableStringBuilder spannableStringBuilder = this.f2501m;
                spannableStringBuilder.replace(spannableStringBuilder.length() - spannableString.length(), this.f2501m.length(), (CharSequence) spannableString);
                return;
            }
        }
        this.f2501m.append((CharSequence) ELLIPSIS_STRING);
    }

    private void r() {
        if (this.f2502n == null || !getExpendable()) {
            return;
        }
        int length = this.f2501m.length();
        if (this.w) {
            this.f2501m.append((CharSequence) ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        this.f2501m.append((CharSequence) this.f2502n);
        SpannableStringBuilder spannableStringBuilder = this.f2501m;
        spannableStringBuilder.setSpan(this.f2502n, length, spannableStringBuilder.length(), 33);
        v();
    }

    private void v() {
        if (this.p != null) {
            String str = "img" + System.currentTimeMillis();
            int length = this.f2501m.length();
            this.f2501m.append((CharSequence) str);
            ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.jd.dynamic.lib.views.RichTextView.3
                @Override // android.text.style.ClickableSpan
                public void onClick(@NonNull View view) {
                    List<String> i2 = com.jd.dynamic.lib.utils.s.i(RichTextView.this.z);
                    for (int i3 = 0; i3 < i2.size(); i3++) {
                        RichTextView richTextView = RichTextView.this;
                        com.jd.dynamic.lib.utils.s.b(i2.get(i3), richTextView, richTextView.x, RichTextView.this);
                    }
                    RichTextView.this.z();
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(@NonNull TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setUnderlineText(false);
                }
            };
            SpannableStringBuilder spannableStringBuilder = this.f2501m;
            spannableStringBuilder.setSpan(this.p, length, spannableStringBuilder.length(), 33);
            SpannableStringBuilder spannableStringBuilder2 = this.f2501m;
            spannableStringBuilder2.setSpan(clickableSpan, length, spannableStringBuilder2.length(), 33);
            setText(this.f2501m);
        }
    }

    private void x() {
        if (this.o == null || !getExpendable()) {
            return;
        }
        int length = this.f2500l.length();
        if (this.w) {
            this.f2500l.append((CharSequence) ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        this.f2500l.append((CharSequence) this.o);
        SpannableStringBuilder spannableStringBuilder = this.f2500l;
        spannableStringBuilder.setSpan(this.o, length, spannableStringBuilder.length(), 33);
        String str = "img" + System.currentTimeMillis();
        this.r = str;
        this.f2500l.append((CharSequence) str);
        B();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        if (getExpendable()) {
            setRichExpand(!isRichExpand());
            if (isRichExpand()) {
                C();
            } else {
                A();
            }
        }
    }

    public boolean getExpendable() {
        return this.v;
    }

    public int getRichTextColor() {
        return this.f2497i;
    }

    public float getRichTextSize() {
        return this.f2498j;
    }

    public Typeface getRichTextStyle() {
        return this.s;
    }

    public String getSuffixText() {
        return this.y;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public boolean isRichExpand() {
        return this.u;
    }

    public void parseAttribute() {
        String charSequence;
        String str;
        String str2;
        List<SpanView> spanList = getParent() instanceof RichTextViewContainer ? ((RichTextViewContainer) getParent()).getSpanList() : null;
        if (!com.jd.dynamic.lib.utils.m.I(spanList) || this.x == null) {
            com.jd.dynamic.lib.utils.t.g(C, "parseAttribute: attrData or engine is null! so return");
            return;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("");
        for (SpanView spanView : spanList) {
            if ("text".equals(spanView.type) && !TextUtils.isEmpty(spanView.getText()) && (charSequence = spanView.getText().toString()) != null) {
                SpannableString spannableString = new SpannableString(charSequence);
                int length = spannableString.length();
                l(spanView, spannableString, length);
                if (TextUtils.isEmpty(spanView.subtype) || (str = spanView.subtype) == null) {
                    str = "";
                }
                if (!TextUtils.isEmpty(spanView.newLine) && (str2 = spanView.newLine) != null) {
                    this.w = str2.equals("1");
                }
                if (str.equals("stick")) {
                    setExpandSuffixSpan(spannableString);
                    SpannableString spannableString2 = this.o;
                    if (spannableString2 == null) {
                        setCloseSuffixSpan(spannableString);
                    } else {
                        l(spanView, spannableString2, spannableString2.length());
                        setCloseSuffixSpan(spannableString2);
                    }
                } else {
                    int length2 = spannableStringBuilder.length();
                    spannableStringBuilder.append((CharSequence) spannableString);
                    spannableStringBuilder.setSpan(spannableString, length2, length + length2, 33);
                }
            }
            if ("image".equals(spanView.type) && !TextUtils.isEmpty(spanView.src)) {
                String str3 = spanView.src;
                if (!TextUtils.isEmpty(spanView.selectedSrc) && spanView.selectedSrc != null) {
                    j(spanView);
                }
                if (str3 == null || !str3.startsWith("http")) {
                    DynamicTemplateEngine dynamicTemplateEngine = this.x;
                    Context context = getContext();
                    DynamicTemplateEngine dynamicTemplateEngine2 = this.x;
                    int c2 = com.jd.dynamic.lib.viewparse.d.c(dynamicTemplateEngine, str3, context, dynamicTemplateEngine2 == null ? null : dynamicTemplateEngine2.mPackageName);
                    if (c2 != 0) {
                        Drawable drawable = getResources().getDrawable(c2);
                        k(spanView, drawable);
                        if (TextUtils.equals(spanView.subtype, "stick")) {
                            this.p = new ImageSpan(drawable);
                        } else {
                            Object imageSpan = new ImageSpan(drawable);
                            int indexOf = spannableStringBuilder.toString().indexOf("");
                            spannableStringBuilder.setSpan(imageSpan, indexOf, indexOf + 0, 33);
                        }
                    }
                } else {
                    f(spannableStringBuilder, spanView, str3);
                }
            }
            if (ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE.equals(spanView.type)) {
                spannableStringBuilder.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
        this.f2499k = spannableStringBuilder;
        CharSequence charSequence2 = this.A;
        if (charSequence2 == null && (charSequence2 = this.B) == null) {
            setText(spannableStringBuilder);
        } else {
            setText(charSequence2);
        }
        post(new Runnable() { // from class: com.jd.dynamic.lib.views.p
            @Override // java.lang.Runnable
            public final void run() {
                RichTextView.this.E();
            }
        });
    }

    public void setCloseSuffixSpan(SpannableString spannableString) {
        this.o = spannableString;
    }

    public void setData(DynamicTemplateEngine dynamicTemplateEngine) {
        this.x = dynamicTemplateEngine;
    }

    public void setExpandSuffixSpan(SpannableString spannableString) {
        this.f2502n = spannableString;
    }

    public void setExpendable(boolean z) {
        this.v = z;
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i2) {
        com.jd.dynamic.lib.utils.t.g(C, "setMaxLines: maxLines: " + i2);
        if (i2 <= 0) {
            i2 = Integer.MAX_VALUE;
        }
        this.f2495g = i2;
        super.setMaxLines(i2);
    }

    public void setRichExpand(boolean z) {
        this.u = z;
    }

    public void setRichTextColor(int i2) {
        this.f2497i = i2;
    }

    public void setRichTextSize(float f2) {
        this.f2498j = f2;
    }

    public void setRichTextStyle(Typeface typeface) {
        this.s = typeface;
    }

    public void setSuffixText(String str) {
        this.y = str;
        this.o = new SpannableString(str);
    }
}
