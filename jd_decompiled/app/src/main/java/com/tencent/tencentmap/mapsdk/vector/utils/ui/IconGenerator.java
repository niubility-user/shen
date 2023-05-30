package com.tencent.tencentmap.mapsdk.vector.utils.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes9.dex */
public class IconGenerator {
    public static final int SQUARE_TEXT_VIEW_ID = new AtomicInteger(1).get();
    public static final int STYLE_BLUE = 4;
    public static final int STYLE_DEFAULT = 1;
    public static final int STYLE_GREEN = 5;
    public static final int STYLE_ORANGE = 7;
    public static final int STYLE_PURPLE = 6;
    public static final int STYLE_RED = 3;
    public static final int STYLE_WHITE = 2;
    public final Context a;
    public ViewGroup b;

    /* renamed from: c  reason: collision with root package name */
    public RotationLayout f18060c;
    public TextView d;

    /* renamed from: e  reason: collision with root package name */
    public View f18061e;

    /* renamed from: f  reason: collision with root package name */
    public int f18062f;

    /* renamed from: g  reason: collision with root package name */
    public float f18063g = 0.5f;

    /* renamed from: h  reason: collision with root package name */
    public float f18064h = 1.0f;

    public IconGenerator(Context context) {
        this.a = context;
        ViewGroup a = a();
        this.b = a;
        this.f18060c = (RotationLayout) a.getChildAt(0);
        this.f18061e = this.d;
        setStyle(1);
    }

    public static int a(int i2) {
        return (i2 == 3 || i2 == 4 || i2 == 5 || i2 == 6 || i2 == 7) ? 1 : 0;
    }

    public final ViewGroup a() {
        LinearLayout linearLayout = new LinearLayout(this.a);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        RotationLayout rotationLayout = new RotationLayout(this.a);
        this.f18060c = rotationLayout;
        rotationLayout.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.d = new TextView(this.a);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        this.d.setLayoutParams(layoutParams);
        this.d.setPadding(10, 5, 10, 5);
        this.d.setId(SQUARE_TEXT_VIEW_ID);
        this.f18060c.addView(this.d);
        linearLayout.addView(this.f18060c);
        return linearLayout;
    }

    public float getAnchorU() {
        return a(this.f18063g, this.f18064h);
    }

    public float getAnchorV() {
        return a(this.f18064h, this.f18063g);
    }

    public Bitmap makeIcon(CharSequence charSequence) {
        TextView textView = this.d;
        if (textView != null) {
            textView.setText(charSequence);
        }
        return makeIcon();
    }

    public void setBackground(Drawable drawable) {
        this.b.setBackgroundDrawable(drawable);
        if (drawable != null) {
            Rect rect = new Rect();
            drawable.getPadding(rect);
            this.b.setPadding(rect.left, rect.top, rect.right, rect.bottom);
            return;
        }
        this.b.setPadding(0, 0, 0, 0);
    }

    public void setContentPadding(int i2, int i3, int i4, int i5) {
        this.f18061e.setPadding(i2, i3, i4, i5);
    }

    public void setContentRotation(int i2) {
        this.f18060c.setViewRotation(i2);
    }

    public void setContentView(View view) {
        this.f18060c.removeAllViews();
        this.f18060c.addView(view);
        this.f18061e = view;
        View findViewById = this.f18060c.findViewById(SQUARE_TEXT_VIEW_ID);
        this.d = findViewById instanceof TextView ? (TextView) findViewById : null;
    }

    public void setRotation(int i2) {
        this.f18062f = ((i2 + R2.attr.additionBottom) % R2.attr.additionBottom) / 90;
    }

    public void setStyle(int i2) {
        setTextAppearance(this.a, a(i2));
        if (i2 == 0) {
            setTextAppearance(16973892, -8421505, 14.0f, 0);
        } else if (i2 == 1) {
            setTextAppearance(16973892, -1118482, 14.0f, 0);
        }
    }

    public void setTextAppearance(Context context, int i2) {
        TextView textView = this.d;
        if (textView != null) {
            textView.setTextAppearance(context, i2);
        }
    }

    public void setTextAppearance(int i2, int i3, float f2, int i4) {
        setTextAppearance(this.a, i2);
        this.d.setTextColor(i3);
        this.d.setTextSize(f2);
        TextView textView = this.d;
        textView.setTypeface(textView.getTypeface(), i4);
    }

    public Bitmap makeIcon() {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.b.measure(makeMeasureSpec, makeMeasureSpec);
        int measuredWidth = this.b.getMeasuredWidth();
        int measuredHeight = this.b.getMeasuredHeight();
        this.b.layout(0, 0, measuredWidth, measuredHeight);
        int i2 = this.f18062f;
        if (i2 == 1 || i2 == 3) {
            measuredHeight = this.b.getMeasuredWidth();
            measuredWidth = this.b.getMeasuredHeight();
        }
        Bitmap createBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        createBitmap.eraseColor(0);
        Canvas canvas = new Canvas(createBitmap);
        int i3 = this.f18062f;
        if (i3 != 0) {
            if (i3 == 1) {
                canvas.translate(measuredWidth, 0.0f);
                canvas.rotate(90.0f);
            } else if (i3 == 2) {
                canvas.rotate(180.0f, measuredWidth / 2, measuredHeight / 2);
            } else {
                canvas.translate(0.0f, measuredHeight);
                canvas.rotate(270.0f);
            }
        }
        this.b.draw(canvas);
        return createBitmap;
    }

    public final float a(float f2, float f3) {
        int i2 = this.f18062f;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        return f3;
                    }
                    throw new IllegalStateException();
                }
                return 1.0f - f2;
            }
            return 1.0f - f3;
        }
        return f2;
    }
}
