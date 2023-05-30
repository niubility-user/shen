package com.jd.dynamic.lib.viewparse.c.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.IImageLoader;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.lib.views.CornerSimpleDraweeView;
import java.util.HashMap;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class j0 extends p0<CornerSimpleDraweeView> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class a implements IImageLoader.ImageRequestListener<Drawable> {
        final /* synthetic */ CornerSimpleDraweeView a;
        final /* synthetic */ String b;

        a(CornerSimpleDraweeView cornerSimpleDraweeView, String str) {
            this.a = cornerSimpleDraweeView;
            this.b = str;
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(Drawable drawable) {
            try {
                CornerSimpleDraweeView cornerSimpleDraweeView = this.a;
                int i2 = R.id.dynamic_image_src_tag;
                boolean z = (cornerSimpleDraweeView.getTag(i2) instanceof String) && TextUtils.equals(this.b, (String) this.a.getTag(i2));
                com.jd.dynamic.lib.utils.t.e("loadImage", "url is equal " + z);
                if (z) {
                    this.a.setImageDrawable(drawable);
                    j0.this.s(this.a);
                    return;
                }
                com.jd.dynamic.lib.utils.t.e("loadImage", "url is " + this.b, "tag is " + this.a.getTag(i2));
            } catch (Exception unused) {
            }
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onCancel() {
            this.a.setTag(R.id.dynamic_image_src_tag, null);
            j0.this.g(this.a);
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onFailure(Throwable th) {
            this.a.setTag(R.id.dynamic_image_src_tag, null);
            j0.this.g(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class b implements IImageLoader.ImageRequestListener<Bitmap> {
        final /* synthetic */ CornerSimpleDraweeView a;
        final /* synthetic */ String b;

        b(CornerSimpleDraweeView cornerSimpleDraweeView, String str) {
            this.a = cornerSimpleDraweeView;
            this.b = str;
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(Bitmap bitmap) {
            CornerSimpleDraweeView cornerSimpleDraweeView = this.a;
            int i2 = R.id.dynamic_image_src_tag;
            boolean z = (cornerSimpleDraweeView.getTag(i2) instanceof String) && TextUtils.equals(this.b, (String) this.a.getTag(i2));
            com.jd.dynamic.lib.utils.t.e("loadImage", "url is equal " + z);
            if (!z) {
                com.jd.dynamic.lib.utils.t.e("loadImage", "url is " + this.b, "tag is " + this.a.getTag(i2));
                return;
            }
            if (bitmap != null) {
                this.a.setImageBitmap(bitmap);
                j0.this.s(this.a);
            }
            if (TextUtils.isEmpty(this.b) || !this.b.toLowerCase().endsWith(".gif")) {
                return;
            }
            if (bitmap != null) {
                bitmap.recycle();
            }
            DynamicSdk.getEngine().getImageLoader().displayImage(this.a, this.b, 0, null);
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onCancel() {
            this.a.setTag(R.id.dynamic_image_src_tag, null);
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onFailure(Throwable th) {
            this.a.setTag(R.id.dynamic_image_src_tag, null);
            j0.this.u(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class c implements IImageLoader.ImageRequestListener<Bitmap> {
        final /* synthetic */ CornerSimpleDraweeView a;
        final /* synthetic */ String b;

        c(CornerSimpleDraweeView cornerSimpleDraweeView, String str) {
            this.a = cornerSimpleDraweeView;
            this.b = str;
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(Bitmap bitmap) {
            CornerSimpleDraweeView cornerSimpleDraweeView = this.a;
            int i2 = R.id.dynamic_image_src_tag;
            boolean z = (cornerSimpleDraweeView.getTag(i2) instanceof String) && TextUtils.equals(this.b, (String) this.a.getTag(i2));
            com.jd.dynamic.lib.utils.t.e("loadImage", "url is equal " + z);
            if (!z) {
                com.jd.dynamic.lib.utils.t.e("loadImage", "url is " + this.b, "tag is " + this.a.getTag(i2));
                return;
            }
            if (bitmap != null) {
                this.a.setImageBitmap(bitmap);
            }
            if (TextUtils.isEmpty(this.b) || !this.b.toLowerCase().endsWith(".gif")) {
                return;
            }
            if (bitmap != null) {
                bitmap.recycle();
            }
            DynamicSdk.getEngine().getImageLoader().displayImage(this.a, this.b, 0, null);
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onCancel() {
            this.a.setTag(R.id.dynamic_image_src_tag, null);
            if (TextUtils.isEmpty(this.b) || !this.b.startsWith("http")) {
                return;
            }
            j0.this.g(this.a);
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onFailure(Throwable th) {
            this.a.setTag(R.id.dynamic_image_src_tag, null);
            if (TextUtils.isEmpty(this.b) || !this.b.startsWith("http")) {
                return;
            }
            j0.this.g(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class d implements IImageLoader.ImageRequestListener<Drawable> {
        final /* synthetic */ CornerSimpleDraweeView a;
        final /* synthetic */ String b;

        d(CornerSimpleDraweeView cornerSimpleDraweeView, String str) {
            this.a = cornerSimpleDraweeView;
            this.b = str;
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(Drawable drawable) {
            CornerSimpleDraweeView cornerSimpleDraweeView = this.a;
            int i2 = R.id.dynamic_image_src_tag;
            boolean z = (cornerSimpleDraweeView.getTag(i2) instanceof String) && TextUtils.equals(this.b, (String) this.a.getTag(i2));
            com.jd.dynamic.lib.utils.t.e("loadImage", "url is equal " + z);
            if (z) {
                if (drawable != null) {
                    this.a.setImageDrawable(drawable);
                }
                if (TextUtils.isEmpty(this.b) || !this.b.toLowerCase().endsWith(".gif")) {
                    return;
                }
                DynamicSdk.getEngine().getImageLoader().displayImage(this.a, this.b, 0, null);
                return;
            }
            com.jd.dynamic.lib.utils.t.e("loadImage", "url is " + this.b, "tag is " + this.a.getTag(i2));
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onCancel() {
            this.a.setTag(R.id.dynamic_image_src_tag, null);
            if (TextUtils.isEmpty(this.b) || !this.b.startsWith("http")) {
                return;
            }
            j0.this.g(this.a);
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onFailure(Throwable th) {
            this.a.setTag(R.id.dynamic_image_src_tag, null);
            if (TextUtils.isEmpty(this.b) || !this.b.startsWith("http")) {
                return;
            }
            j0.this.g(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(CornerSimpleDraweeView cornerSimpleDraweeView) {
        i(cornerSimpleDraweeView, true, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(CornerSimpleDraweeView cornerSimpleDraweeView, String str) {
        com.jd.dynamic.lib.utils.s.b(str, cornerSimpleDraweeView, this.a, cornerSimpleDraweeView);
    }

    private void i(CornerSimpleDraweeView cornerSimpleDraweeView, boolean z, boolean z2) {
        ResultEntity resultEntity;
        Object tag = cornerSimpleDraweeView.getTag(R.id.dynamic_imageview_local_src);
        Object tag2 = cornerSimpleDraweeView.getTag(R.id.dynamic_imageview_default_holder);
        String str = tag instanceof String ? (String) tag : null;
        if (tag2 != null) {
            if (DynamicSdk.getEngine().getImageLoader().getDefaultPlaceHolder() != null) {
                cornerSimpleDraweeView.setImageDrawable(DynamicSdk.getEngine().getImageLoader().getDefaultPlaceHolder());
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(str)) {
            if (!z) {
                return;
            }
            cornerSimpleDraweeView.setImageBitmap(null);
            if (!z2) {
                return;
            }
        } else if (!str.startsWith(DYConstants.DY_ASSETS)) {
            DynamicTemplateEngine dynamicTemplateEngine = this.a;
            Context context = cornerSimpleDraweeView.getContext();
            DynamicTemplateEngine dynamicTemplateEngine2 = this.a;
            int c2 = com.jd.dynamic.lib.viewparse.d.c(dynamicTemplateEngine, str, context, dynamicTemplateEngine2 != null ? dynamicTemplateEngine2.mPackageName : null);
            if (c2 > 0) {
                cornerSimpleDraweeView.setImageResource(c2);
                return;
            }
            return;
        } else {
            DynamicTemplateEngine dynamicTemplateEngine3 = this.a;
            if (dynamicTemplateEngine3 != null && (resultEntity = dynamicTemplateEngine3.entity) != null && !TextUtils.isEmpty(resultEntity.zipDir)) {
                String str2 = "file://" + this.a.entity.zipDir + "/" + str;
                cornerSimpleDraweeView.setTag(R.id.dynamic_image_src_tag, str2);
                if (cornerSimpleDraweeView.getTag(R.id.dynamic_imageview_nine) == null) {
                    p(str2, cornerSimpleDraweeView);
                    return;
                } else {
                    t(str2, cornerSimpleDraweeView);
                    return;
                }
            } else if (!z) {
                return;
            } else {
                cornerSimpleDraweeView.setImageBitmap(null);
                if (!z2) {
                    return;
                }
            }
        }
        s(cornerSimpleDraweeView);
    }

    private void j(String str, CornerSimpleDraweeView cornerSimpleDraweeView) {
        DynamicSdk.getEngine().getImageLoader().loadImage(str, new b(cornerSimpleDraweeView, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void k(Throwable th) {
    }

    private void n(CornerSimpleDraweeView cornerSimpleDraweeView) {
        i(cornerSimpleDraweeView, true, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(CornerSimpleDraweeView cornerSimpleDraweeView, String str) {
        com.jd.dynamic.lib.utils.s.b(str, cornerSimpleDraweeView, this.a, cornerSimpleDraweeView);
    }

    private void p(String str, CornerSimpleDraweeView cornerSimpleDraweeView) {
        DynamicSdk.getEngine().getImageLoader().loadImage(str, new c(cornerSimpleDraweeView, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void q(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(final CornerSimpleDraweeView cornerSimpleDraweeView) {
        if (cornerSimpleDraweeView == null || TextUtils.isEmpty(cornerSimpleDraweeView.getLoadSuccessFun())) {
            return;
        }
        com.jd.dynamic.lib.utils.t.e("callLoadSuccessFun", "loadSuccessFun \uff1a  " + cornerSimpleDraweeView.getLoadSuccessFun());
        Observable.from(com.jd.dynamic.lib.utils.s.i(cornerSimpleDraweeView.getLoadSuccessFun())).forEach(new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.e.s
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                j0.this.o(cornerSimpleDraweeView, (String) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.e.v
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                j0.q((Throwable) obj);
            }
        });
    }

    private void t(String str, CornerSimpleDraweeView cornerSimpleDraweeView) {
        DynamicSdk.getEngine().getImageLoader().loadNineImage(str, new d(cornerSimpleDraweeView, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(final CornerSimpleDraweeView cornerSimpleDraweeView) {
        if (cornerSimpleDraweeView == null || TextUtils.isEmpty(cornerSimpleDraweeView.getLoadFailedFun())) {
            return;
        }
        com.jd.dynamic.lib.utils.t.e("callLoadFailedFun", "loadFailedFun \uff1a  " + cornerSimpleDraweeView.getLoadFailedFun());
        Observable.from(com.jd.dynamic.lib.utils.s.i(cornerSimpleDraweeView.getLoadFailedFun())).forEach(new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.e.t
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                j0.this.h(cornerSimpleDraweeView, (String) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.e.u
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                j0.k((Throwable) obj);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0199  */
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(HashMap<String, String> hashMap, CornerSimpleDraweeView cornerSimpleDraweeView) {
        String str;
        ResultEntity resultEntity;
        int a2;
        int a3;
        int a4;
        int a5;
        ImageView.ScaleType scaleType;
        int i2 = R.id.dynamic_image_src_tag;
        boolean z = false;
        boolean z2 = (cornerSimpleDraweeView.getTag(i2) instanceof String) && TextUtils.equals(hashMap.get("src"), (String) cornerSimpleDraweeView.getTag(i2));
        if (!z2) {
            i(cornerSimpleDraweeView, false, false);
        }
        String str2 = hashMap.get(DYConstants.DY_IMAGE_TINT_COLOR);
        if (!TextUtils.isEmpty(str2)) {
            try {
                cornerSimpleDraweeView.setColorFilter(Color.parseColor(str2));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (hashMap.containsKey(DYConstants.DY_USE_ASPECTRATIO)) {
            cornerSimpleDraweeView.setUseAspectRatio(hashMap.get(DYConstants.DY_USE_ASPECTRATIO));
        }
        if (hashMap.containsKey("scaleType")) {
            String str3 = hashMap.get("scaleType");
            if (DYConstants.DY_STRETCH.equals(str3)) {
                scaleType = ImageView.ScaleType.FIT_XY;
            } else if (DYConstants.DY_FIT.equals(str3)) {
                scaleType = ImageView.ScaleType.FIT_CENTER;
            } else if (DYConstants.DY_FILL.equals(str3)) {
                scaleType = ImageView.ScaleType.CENTER_CROP;
            }
            cornerSimpleDraweeView.setScaleType(scaleType);
        }
        if (hashMap.containsKey("leftCapWidth") || hashMap.containsKey("topCapHeight")) {
            cornerSimpleDraweeView.setTag(R.id.dynamic_imageview_nine, 1);
        }
        String str4 = "image";
        if (!hashMap.containsKey("image")) {
            if (hashMap.containsKey("placeholder")) {
                str = hashMap.get("placeholder");
                z = TextUtils.equals(str, DYConstants.DY_DEFAULT_PLACEHOLDER);
            } else {
                str4 = DYConstants.DY_LOCAL_IMAGE;
                if (!hashMap.containsKey(DYConstants.DY_LOCAL_IMAGE)) {
                    str = null;
                }
            }
            if (z) {
                cornerSimpleDraweeView.setTag(R.id.dynamic_imageview_default_holder, com.jd.dynamic.b.c.a.b);
            }
            if (!TextUtils.isEmpty(str)) {
                cornerSimpleDraweeView.setTag(R.id.dynamic_imageview_local_src, str);
            }
            if (z) {
                if (!TextUtils.isEmpty(str)) {
                    if (str.startsWith(DYConstants.DY_ASSETS)) {
                        DynamicTemplateEngine dynamicTemplateEngine = this.a;
                        if (dynamicTemplateEngine == null || (resultEntity = dynamicTemplateEngine.entity) == null || TextUtils.isEmpty(resultEntity.zipDir)) {
                            cornerSimpleDraweeView.setImageBitmap(null);
                        } else {
                            String str5 = "file://" + this.a.entity.zipDir + "/" + str;
                            cornerSimpleDraweeView.setTag(R.id.dynamic_image_src_tag, str5);
                            if (cornerSimpleDraweeView.getTag(R.id.dynamic_imageview_nine) == null) {
                                p(str5, cornerSimpleDraweeView);
                            } else {
                                t(str5, cornerSimpleDraweeView);
                            }
                        }
                    } else if (!z2) {
                        DynamicTemplateEngine dynamicTemplateEngine2 = this.a;
                        Context context = cornerSimpleDraweeView.getContext();
                        DynamicTemplateEngine dynamicTemplateEngine3 = this.a;
                        cornerSimpleDraweeView.setImageResource(com.jd.dynamic.lib.viewparse.d.c(dynamicTemplateEngine2, str, context, dynamicTemplateEngine3 == null ? null : dynamicTemplateEngine3.mPackageName));
                    }
                }
            } else if (DynamicSdk.getEngine().getImageLoader().getDefaultPlaceHolder() != null && !z2) {
                cornerSimpleDraweeView.setImageDrawable(DynamicSdk.getEngine().getImageLoader().getDefaultPlaceHolder());
            }
            if (hashMap.containsKey(DYConstants.DY_ON_LOAD_SUCCESS)) {
                cornerSimpleDraweeView.setLoadSuccessFun(hashMap.get(DYConstants.DY_ON_LOAD_SUCCESS));
            }
            if (hashMap.containsKey(DYConstants.DY_ON_LOAD_FAILED)) {
                cornerSimpleDraweeView.setLoadFailedFun(hashMap.get(DYConstants.DY_ON_LOAD_FAILED));
            }
            if (hashMap.containsKey("src")) {
                if (TextUtils.isEmpty(hashMap.get("src"))) {
                    n(cornerSimpleDraweeView);
                } else {
                    String str6 = hashMap.get("src");
                    int i3 = R.id.dynamic_image_src_tag;
                    cornerSimpleDraweeView.setTag(i3, str6);
                    if (str6 == null || !str6.startsWith("http")) {
                        DynamicTemplateEngine dynamicTemplateEngine4 = this.a;
                        Context context2 = cornerSimpleDraweeView.getContext();
                        DynamicTemplateEngine dynamicTemplateEngine5 = this.a;
                        int c2 = com.jd.dynamic.lib.viewparse.d.c(dynamicTemplateEngine4, str6, context2, dynamicTemplateEngine5 == null ? null : dynamicTemplateEngine5.mPackageName);
                        if (c2 > 0) {
                            try {
                                cornerSimpleDraweeView.setImageResource(c2);
                            } catch (Exception unused) {
                            }
                        } else {
                            cornerSimpleDraweeView.setTag(i3, null);
                        }
                    } else if (DynamicSdk.getEngine().getImageLoader() != null) {
                        if (cornerSimpleDraweeView.getTag(R.id.dynamic_imageview_nine) == null) {
                            j(str6, cornerSimpleDraweeView);
                        } else {
                            DynamicSdk.getEngine().getImageLoader().loadNineImage(str6, new a(cornerSimpleDraweeView, str6));
                        }
                    }
                }
            }
            a2 = (hashMap.containsKey(DYConstants.DY_BORDER_RADIUS_T_L) || hashMap.get(DYConstants.DY_BORDER_RADIUS_T_L) == null) ? -1 : (int) com.jd.dynamic.lib.viewparse.d.a(hashMap.get(DYConstants.DY_BORDER_RADIUS_T_L), cornerSimpleDraweeView.getContext());
            a3 = (hashMap.containsKey(DYConstants.DY_BORDER_RADIUS_T_R) || hashMap.get(DYConstants.DY_BORDER_RADIUS_T_R) == null) ? -1 : (int) com.jd.dynamic.lib.viewparse.d.a(hashMap.get(DYConstants.DY_BORDER_RADIUS_T_R), cornerSimpleDraweeView.getContext());
            a4 = (hashMap.containsKey(DYConstants.DY_BORDER_RADIUS_B_L) || hashMap.get(DYConstants.DY_BORDER_RADIUS_B_L) == null) ? -1 : (int) com.jd.dynamic.lib.viewparse.d.a(hashMap.get(DYConstants.DY_BORDER_RADIUS_B_L), cornerSimpleDraweeView.getContext());
            a5 = (hashMap.containsKey(DYConstants.DY_BORDER_RADIUS_B_R) || hashMap.get(DYConstants.DY_BORDER_RADIUS_B_R) == null) ? -1 : (int) com.jd.dynamic.lib.viewparse.d.a(hashMap.get(DYConstants.DY_BORDER_RADIUS_B_R), cornerSimpleDraweeView.getContext());
            if (a2 == -1 || a3 != -1 || a4 != -1 || a5 != -1) {
                cornerSimpleDraweeView.setAllCorner(a2, a3, a4, a5);
            }
            if (hashMap.containsKey(DYConstants.DY_BORDER_RADIUS_T) && hashMap.get(DYConstants.DY_BORDER_RADIUS_T) != null) {
                cornerSimpleDraweeView.setTopCorner((int) com.jd.dynamic.lib.viewparse.d.a(hashMap.get(DYConstants.DY_BORDER_RADIUS_T), cornerSimpleDraweeView.getContext()));
            }
            if (hashMap.containsKey(DYConstants.DY_BORDER_RADIUS_B) && hashMap.get(DYConstants.DY_BORDER_RADIUS_B) != null) {
                cornerSimpleDraweeView.setBottomCorner((int) com.jd.dynamic.lib.viewparse.d.a(hashMap.get(DYConstants.DY_BORDER_RADIUS_B), cornerSimpleDraweeView.getContext()));
            }
            if (!hashMap.containsKey("borderRadius") || hashMap.get("borderRadius") == null) {
                return;
            }
            cornerSimpleDraweeView.setCorner((int) com.jd.dynamic.lib.viewparse.d.a(hashMap.get("borderRadius"), cornerSimpleDraweeView.getContext()));
            return;
        }
        str = hashMap.get(str4);
        if (z) {
        }
        if (!TextUtils.isEmpty(str)) {
        }
        if (z) {
        }
        if (hashMap.containsKey(DYConstants.DY_ON_LOAD_SUCCESS)) {
        }
        if (hashMap.containsKey(DYConstants.DY_ON_LOAD_FAILED)) {
        }
        if (hashMap.containsKey("src")) {
        }
        if (hashMap.containsKey(DYConstants.DY_BORDER_RADIUS_T_L)) {
        }
        if (hashMap.containsKey(DYConstants.DY_BORDER_RADIUS_T_R)) {
        }
        if (hashMap.containsKey(DYConstants.DY_BORDER_RADIUS_B_L)) {
        }
        if (hashMap.containsKey(DYConstants.DY_BORDER_RADIUS_B_R)) {
        }
        if (a2 == -1) {
        }
        cornerSimpleDraweeView.setAllCorner(a2, a3, a4, a5);
    }
}
