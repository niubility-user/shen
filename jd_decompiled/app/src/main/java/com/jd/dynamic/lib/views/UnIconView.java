package com.jd.dynamic.lib.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.lib.utils.DPIUtil;
import com.jd.dynamic.lib.viewparse.c.e.j0;
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

    /* JADX WARN: Code restructure failed: missing block: B:122:0x0176, code lost:
        if (r9 != null) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0178, code lost:
        r9.setVisibility(8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0099, code lost:
        if (r9 != null) goto L123;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onSetResFinish(java.util.HashMap<java.lang.String, java.lang.String> r9) {
        /*
            Method dump skipped, instructions count: 388
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.views.UnIconView.onSetResFinish(java.util.HashMap):void");
    }

    public void preSetValues() {
        this.showName = null;
        this.resCode = null;
        this.trackId = null;
    }
}
