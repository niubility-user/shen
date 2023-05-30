package com.jd.dynamic.lib.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicUtils;
import com.jd.dynamic.base.interfaces.IUniConfig;
import com.jd.dynamic.lib.utils.DPIUtil;
import com.jd.dynamic.lib.viewparse.c.e.j0;
import com.jd.dynamic.yoga.android.YogaLayout;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class UnIconView extends FrameLayout {

    /* renamed from: g */
    private CornerSimpleDraweeView f2525g;

    /* renamed from: h */
    private TextView f2526h;
    public String height;
    public String resCode;
    public String showName;
    public String trackId;
    public String width;

    public UnIconView(Context context) {
        this(context, null);
    }

    public UnIconView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UnIconView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    private int a(String str) {
        if (TextUtils.isEmpty(str) || str == null || DYConstants.DY_WRAP_CONTENT.equals(str)) {
            return -2;
        }
        if (str.contains("%")) {
            return -1;
        }
        try {
            return DPIUtil.dip2px(Float.parseFloat(str));
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    private void b(Bitmap bitmap, HashMap<String, String> hashMap) {
        if (this.f2525g == null) {
            this.f2525g = new CornerSimpleDraweeView(getContext());
        }
        new j0().a(hashMap, this.f2525g);
        if (bitmap != null) {
            this.f2525g.setImageBitmap(bitmap);
            return;
        }
        this.f2525g.setImageBitmap(null);
        this.f2525g.setTag(R.id.dynamic_icon_rescode, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:154:0x0099, code lost:
        if (r9 != null) goto L186;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x0176, code lost:
        if (r9 != null) goto L186;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x0178, code lost:
        r9.setVisibility(8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onSetResFinish(HashMap<String, String> hashMap) {
        CornerSimpleDraweeView cornerSimpleDraweeView;
        FrameLayout.LayoutParams layoutParams;
        FrameLayout.LayoutParams layoutParams2;
        IUniConfig uniConfig = DynamicSdk.getEngine().getUniConfig();
        if (uniConfig == null) {
            return;
        }
        if (DynamicUtils.isElOrKnownSymbol(this.showName)) {
            this.showName = "";
        }
        if (DynamicUtils.isElOrKnownSymbol(this.resCode)) {
            this.resCode = "";
        }
        Bitmap bitmap = null;
        if (!TextUtils.isEmpty(this.showName)) {
            TextView textView = this.f2526h;
            if (textView == null) {
                this.f2526h = uniConfig.getTextViewOrNull(this.resCode, this.showName);
            } else {
                textView.setText(this.showName);
                uniConfig.setTextViewProperties(this.resCode, this.f2526h);
            }
            TextView textView2 = this.f2526h;
            if (textView2 != null) {
                textView2.setGravity(16);
                this.f2526h.setSingleLine(true);
                this.f2526h.setIncludeFontPadding(false);
                if (this.f2526h.getParent() == null) {
                    if (com.jd.dynamic.b.a.b.o().S()) {
                        layoutParams2 = new FrameLayout.LayoutParams(a(this.width), a(this.height));
                        layoutParams2.gravity = 17;
                    } else {
                        layoutParams2 = new FrameLayout.LayoutParams(-2, -1);
                        layoutParams2.gravity = 16;
                    }
                    addView(this.f2526h, layoutParams2);
                }
                this.f2526h.setVisibility(0);
            }
            cornerSimpleDraweeView = this.f2525g;
        } else if (TextUtils.isEmpty(this.resCode)) {
            TextView textView3 = this.f2526h;
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
            cornerSimpleDraweeView = this.f2525g;
        } else {
            CornerSimpleDraweeView cornerSimpleDraweeView2 = this.f2525g;
            if (cornerSimpleDraweeView2 == null || !TextUtils.equals((String) cornerSimpleDraweeView2.getTag(R.id.dynamic_icon_rescode), this.resCode)) {
                bitmap = uniConfig.getBitmap(this.resCode);
                b(bitmap, hashMap);
                this.f2525g.setTag(R.id.dynamic_icon_rescode, this.resCode);
            }
            if (bitmap != null) {
                if (com.jd.dynamic.b.a.b.o().S()) {
                    int a = a(this.height);
                    int a2 = a(this.width);
                    if (a > 0) {
                        a2 = (int) (bitmap.getWidth() * (a / bitmap.getHeight()));
                        layoutParams = new FrameLayout.LayoutParams(a2, a(this.height));
                    } else {
                        layoutParams = new FrameLayout.LayoutParams(a(this.width), a(this.height));
                    }
                    if (this.f2525g.getParent() == null) {
                        layoutParams.gravity = 16;
                        addView(this.f2525g, layoutParams);
                    } else {
                        this.f2525g.getLayoutParams().width = a2;
                    }
                } else {
                    int width = (int) (bitmap.getWidth() * (com.jd.dynamic.lib.viewparse.d.b(getContext(), 13.0f) / bitmap.getHeight()));
                    if (this.f2525g.getParent() == null) {
                        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(width, -1);
                        layoutParams3.gravity = 16;
                        addView(this.f2525g, layoutParams3);
                    } else {
                        this.f2525g.getLayoutParams().width = width;
                    }
                }
            }
            this.f2525g.setVisibility(0);
            TextView textView4 = this.f2526h;
            if (textView4 != null) {
                textView4.setVisibility(8);
            }
        }
        post(new Runnable() { // from class: com.jd.dynamic.lib.views.UnIconView.1
            {
                UnIconView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (UnIconView.this.getParent() instanceof YogaLayout) {
                    ((YogaLayout) UnIconView.this.getParent()).invalidate(UnIconView.this);
                }
            }
        });
    }

    public void preSetValues() {
        this.showName = null;
        this.resCode = null;
        this.trackId = null;
    }
}
