package com.jd.dynamic.lib.viewparse.c.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.IImageLoader;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.lib.views.CornerSimpleDraweeView;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.util.HashMap<java.lang.String, java.lang.String> r8, com.jd.dynamic.lib.views.CornerSimpleDraweeView r9) {
        /*
            Method dump skipped, instructions count: 749
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.viewparse.c.e.j0.a(java.util.HashMap, com.jd.dynamic.lib.views.CornerSimpleDraweeView):void");
    }
}
