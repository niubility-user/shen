package com.jd.dynamic.lib.viewparse.c.e;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IImageLoader;
import com.jd.dynamic.lib.utils.DPIUtil;

/* loaded from: classes13.dex */
public class z extends p0<View> {

    /* loaded from: classes13.dex */
    class a implements IImageLoader.ImageRequestListener<Bitmap> {
        final /* synthetic */ View a;

        a(z zVar, View view) {
            this.a = view;
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(Bitmap bitmap) {
            if (bitmap != null) {
                this.a.setBackgroundDrawable(new BitmapDrawable(this.a.getContext().getResources(), bitmap));
            }
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onCancel() {
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onFailure(Throwable th) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class b implements IImageLoader.ImageRequestListener<Drawable> {
        final /* synthetic */ View a;

        b(z zVar, View view) {
            this.a = view;
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(Drawable drawable) {
            if (drawable != null) {
                this.a.setBackgroundDrawable(drawable);
            }
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onCancel() {
            this.a.setBackgroundDrawable(null);
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onFailure(Throwable th) {
            this.a.setBackgroundDrawable(null);
        }
    }

    private float f(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0.0f;
        }
        try {
            return DPIUtil.dip2px(Float.parseFloat(str));
        } catch (Exception unused) {
            return 0.0f;
        }
    }

    private String g(View view, int i2) {
        if (view == null) {
            return null;
        }
        Object tag = view.getTag(i2);
        if (tag instanceof String) {
            return (String) tag;
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00a6, code lost:
        if (r9.equals(com.jd.dynamic.DYConstants.DY_I_45) == false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void h(android.graphics.drawable.GradientDrawable r7, java.lang.String r8, java.lang.String r9) {
        /*
            r6 = this;
            if (r8 != 0) goto L3
            return
        L3:
            java.lang.String r0 = ","
            java.lang.String[] r8 = r8.split(r0)
            if (r8 == 0) goto Lca
            int r0 = r8.length
            if (r0 > 0) goto L10
            goto Lca
        L10:
            java.util.ArrayList r0 = new java.util.ArrayList
            java.util.List r8 = java.util.Arrays.asList(r8)
            r0.<init>(r8)
            int r8 = r0.size()
            r1 = 0
            r2 = 1
            if (r8 != r2) goto L28
            java.lang.Object r8 = r0.get(r1)
            r0.add(r8)
        L28:
            int r8 = r0.size()
            int[] r8 = new int[r8]
            java.util.Iterator r0 = r0.iterator()
            r3 = 0
        L33:
            boolean r4 = r0.hasNext()
            r5 = -1
            if (r4 == 0) goto L4c
            java.lang.Object r4 = r0.next()
            java.lang.String r4 = (java.lang.String) r4
            int r4 = android.graphics.Color.parseColor(r4)     // Catch: java.lang.Exception -> L49
            r8[r3] = r4     // Catch: java.lang.Exception -> L49
            int r3 = r3 + 1
            goto L33
        L49:
            r8[r3] = r5
            goto L33
        L4c:
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            if (r0 != 0) goto Lc2
            r9.hashCode()
            int r0 = r9.hashCode()
            switch(r0) {
                case 1665: goto La0;
                case 1815: goto L95;
                case 48723: goto L8a;
                case 48873: goto L7f;
                case 49653: goto L74;
                case 49803: goto L69;
                case 50583: goto L5e;
                default: goto L5c;
            }
        L5c:
            r1 = -1
            goto La9
        L5e:
            java.lang.String r0 = "315"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L67
            goto L5c
        L67:
            r1 = 6
            goto La9
        L69:
            java.lang.String r0 = "270"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L72
            goto L5c
        L72:
            r1 = 5
            goto La9
        L74:
            java.lang.String r0 = "225"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L7d
            goto L5c
        L7d:
            r1 = 4
            goto La9
        L7f:
            java.lang.String r0 = "180"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L88
            goto L5c
        L88:
            r1 = 3
            goto La9
        L8a:
            java.lang.String r0 = "135"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L93
            goto L5c
        L93:
            r1 = 2
            goto La9
        L95:
            java.lang.String r0 = "90"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L9e
            goto L5c
        L9e:
            r1 = 1
            goto La9
        La0:
            java.lang.String r0 = "45"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto La9
            goto L5c
        La9:
            switch(r1) {
                case 0: goto Lbf;
                case 1: goto Lbc;
                case 2: goto Lb9;
                case 3: goto Lb6;
                case 4: goto Lb3;
                case 5: goto Lb0;
                case 6: goto Lad;
                default: goto Lac;
            }
        Lac:
            goto Lc2
        Lad:
            android.graphics.drawable.GradientDrawable$Orientation r9 = android.graphics.drawable.GradientDrawable.Orientation.TL_BR
            goto Lc4
        Lb0:
            android.graphics.drawable.GradientDrawable$Orientation r9 = android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM
            goto Lc4
        Lb3:
            android.graphics.drawable.GradientDrawable$Orientation r9 = android.graphics.drawable.GradientDrawable.Orientation.TR_BL
            goto Lc4
        Lb6:
            android.graphics.drawable.GradientDrawable$Orientation r9 = android.graphics.drawable.GradientDrawable.Orientation.RIGHT_LEFT
            goto Lc4
        Lb9:
            android.graphics.drawable.GradientDrawable$Orientation r9 = android.graphics.drawable.GradientDrawable.Orientation.BR_TL
            goto Lc4
        Lbc:
            android.graphics.drawable.GradientDrawable$Orientation r9 = android.graphics.drawable.GradientDrawable.Orientation.BOTTOM_TOP
            goto Lc4
        Lbf:
            android.graphics.drawable.GradientDrawable$Orientation r9 = android.graphics.drawable.GradientDrawable.Orientation.BL_TR
            goto Lc4
        Lc2:
            android.graphics.drawable.GradientDrawable$Orientation r9 = android.graphics.drawable.GradientDrawable.Orientation.LEFT_RIGHT
        Lc4:
            r7.setOrientation(r9)
            r7.setColors(r8)
        Lca:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.viewparse.c.e.z.h(android.graphics.drawable.GradientDrawable, java.lang.String, java.lang.String):void");
    }

    private void i(View view, int i2, Object obj) {
        if (view == null) {
            return;
        }
        view.setTag(i2, obj);
    }

    private void j(String str, View view) {
        DynamicSdk.getEngine().getImageLoader().loadNineImage(str, new b(this, view));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01ae A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01af  */
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.util.HashMap<java.lang.String, java.lang.String> r21, android.view.View r22) {
        /*
            Method dump skipped, instructions count: 1076
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.viewparse.c.e.z.a(java.util.HashMap, android.view.View):void");
    }
}
