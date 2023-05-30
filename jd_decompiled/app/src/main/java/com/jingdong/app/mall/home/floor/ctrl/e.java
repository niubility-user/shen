package com.jingdong.app.mall.home.floor.ctrl;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.FloatRange;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.JdImageToolKit;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.assist.JDFailType;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.app.util.image.listener.JDImageLoadingProgressListener;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.remoteimage.CalorieImageUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public class e {
    public static int a = -2367258;
    public static Drawable b = new ColorDrawable(0);

    /* renamed from: c */
    public static Drawable f9398c = new ColorDrawable(-1);
    public static final int d = R.id.image_last_url;

    /* renamed from: e */
    private static final JDFailReason f9399e = new JDFailReason(JDFailType.UNKNOWN, new Exception());

    /* renamed from: f */
    private static final JDDisplayImageOptions f9400f = f.a().resetViewBeforeLoading(true);

    /* renamed from: g */
    public static final JDDisplayImageOptions f9401g = f.a().resetViewBeforeLoading(false).showImageOnFail((Drawable) null).showImageOnLoading((Drawable) null).showImageForEmptyUri((Drawable) null);

    /* renamed from: h */
    public static final JDDisplayImageOptions f9402h = f.a().resetViewBeforeLoading(true).showImageOnFail(b).showImageOnLoading(b).showImageForEmptyUri(b);

    /* loaded from: classes4.dex */
    public class a extends JDSimpleImageLoadingListener {

        /* renamed from: g */
        final /* synthetic */ b f9403g;

        a(b bVar) {
            this.f9403g = bVar;
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            b bVar = this.f9403g;
            if (bVar != null) {
                bVar.onSuccess(str, view);
            }
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
            b bVar = this.f9403g;
            if (bVar != null) {
                bVar.onFailed(str, view);
            }
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingStarted(String str, View view) {
            b bVar = this.f9403g;
            if (bVar != null) {
                bVar.onStart(str, view);
            }
        }
    }

    /* loaded from: classes4.dex */
    public interface b {
        void onFailed(String str, View view);

        void onStart(String str, View view);

        void onSuccess(String str, View view);
    }

    public static boolean A(ImageView imageView, String str) {
        if (imageView == null || TextUtils.isEmpty(str)) {
            return true;
        }
        Object tag = imageView.getTag(d);
        return (tag == null || TextUtils.equals(tag.toString(), str)) ? false : true;
    }

    public static void a(View view, String str) {
        c(view, str, f9402h, null);
    }

    public static void b(View view, String str, ImageRequestListener<ImageInfo> imageRequestListener) {
        c(view, str, f9402h, imageRequestListener);
    }

    public static void c(View view, String str, JDDisplayImageOptions jDDisplayImageOptions, ImageRequestListener<ImageInfo> imageRequestListener) {
        JDImageLoader.display(CalorieImageUtil.getRemoteImageUriSync("30", str), view, jDDisplayImageOptions, imageRequestListener);
    }

    public static void d(ImageView imageView, String str) {
        q(imageView, str, true, null);
    }

    public static void e(ImageView imageView, String str, boolean z) {
        q(imageView, str, z, null);
    }

    public static void f(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions) {
        h(str, imageView, jDDisplayImageOptions, null, null);
    }

    public static void g(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener) {
        h(str, imageView, jDDisplayImageOptions, jDImageLoadingListener, null);
    }

    public static void h(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener, JDImageLoadingProgressListener jDImageLoadingProgressListener) {
        j(str, imageView, jDDisplayImageOptions, true, jDImageLoadingListener, jDImageLoadingProgressListener);
    }

    public static void i(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, boolean z) {
        j(str, imageView, jDDisplayImageOptions, z, null, null);
    }

    public static void j(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, boolean z, JDImageLoadingListener jDImageLoadingListener, JDImageLoadingProgressListener jDImageLoadingProgressListener) {
        if (jDDisplayImageOptions == null) {
            jDDisplayImageOptions = f9401g;
        }
        JDDisplayImageOptions jDDisplayImageOptions2 = jDDisplayImageOptions;
        String h2 = com.jingdong.app.mall.home.o.a.f.h(str);
        com.jingdong.app.mall.home.floor.common.i.m.c(imageView, h2);
        jDDisplayImageOptions2.bitmapConfig(Bitmap.Config.ARGB_8888);
        imageView.setTag(d, h2);
        try {
            if (!TextUtils.isEmpty(h2) && !TextUtils.equals(h2, "https://emptyUrl")) {
                f.h(h2, imageView, jDDisplayImageOptions2, z, jDImageLoadingListener, jDImageLoadingProgressListener);
                return;
            }
            Drawable imageForEmptyUri = jDDisplayImageOptions2.getImageForEmptyUri(JdImageToolKit.getEngine().getApplicationContext().getResources());
            if (imageForEmptyUri != null) {
                imageView.setImageDrawable(imageForEmptyUri);
            }
            imageView.setTag(JDImageUtils.STATUS_TAG, 3);
            if (jDImageLoadingListener != null) {
                jDImageLoadingListener.onLoadingFailed(h2, imageView, f9399e);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void k(String str, ImageView imageView, JDImageLoadingListener jDImageLoadingListener) {
        h(str, imageView, null, jDImageLoadingListener, null);
    }

    public static void l(String str, SimpleDraweeView simpleDraweeView) {
        h(str, simpleDraweeView, null, null, null);
    }

    public static void m(ImageView imageView, String str, Drawable drawable) {
        o(imageView, str, drawable, Bitmap.Config.ARGB_8888, null);
    }

    public static void n(ImageView imageView, String str, Drawable drawable, Bitmap.Config config) {
        o(imageView, str, drawable, config, null);
    }

    public static void o(ImageView imageView, String str, Drawable drawable, Bitmap.Config config, b bVar) {
        if (imageView == null) {
            return;
        }
        int i2 = d;
        Object tag = imageView.getTag(i2);
        if (str != null && str.equals(tag)) {
            Object tag2 = imageView.getTag(JDImageUtils.STATUS_TAG);
            if (tag2 != null && tag2.equals(1)) {
                return;
            }
            if (tag2 != null && tag2.equals(2)) {
                if (bVar != null) {
                    bVar.onSuccess(str, imageView);
                    return;
                }
                return;
            }
        }
        imageView.setTag(i2, str);
        JDDisplayImageOptions a2 = f.a();
        if (config == null) {
            a2.bitmapConfig(Bitmap.Config.ARGB_8888);
        } else {
            a2.bitmapConfig(config);
        }
        g(str, imageView, a2.resetViewBeforeLoading(false).isScale(false).showImageForEmptyUri(drawable).showImageOnFail(drawable).showImageOnLoading(drawable), new a(bVar));
    }

    public static void p(ImageView imageView, String str, Drawable drawable, b bVar) {
        o(imageView, str, drawable, Bitmap.Config.ARGB_8888, bVar);
    }

    public static void q(ImageView imageView, String str, boolean z, JDImageLoadingListener jDImageLoadingListener) {
        if (imageView == null) {
            return;
        }
        String h2 = com.jingdong.app.mall.home.o.a.f.h(str);
        com.jingdong.app.mall.home.floor.common.i.m.c(imageView, h2);
        JDDisplayImageOptions jDDisplayImageOptions = f9400f;
        jDDisplayImageOptions.bitmapConfig(Bitmap.Config.ARGB_8888);
        int i2 = d;
        if (imageView.getTag(i2) != null && h2 != null && h2.equals(imageView.getTag(i2))) {
            Object tag = imageView.getTag(JDImageUtils.STATUS_TAG);
            if (tag != null && tag.equals(1)) {
                return;
            }
            if (tag != null && tag.equals(2)) {
                if (jDImageLoadingListener != null) {
                    jDImageLoadingListener.onLoadingComplete(h2, imageView, null);
                    return;
                }
                return;
            }
        }
        if (imageView.getTag(i2) == null && z) {
            j(h2, imageView, jDDisplayImageOptions, true, jDImageLoadingListener, null);
        } else {
            s(imageView, h2, jDImageLoadingListener);
        }
        imageView.setTag(i2, h2);
    }

    public static void r(ImageView imageView, String str) {
        t(imageView, str, null, null);
    }

    public static boolean s(ImageView imageView, String str, JDImageLoadingListener jDImageLoadingListener) {
        return t(imageView, str, jDImageLoadingListener, null);
    }

    public static boolean t(ImageView imageView, String str, JDImageLoadingListener jDImageLoadingListener, JDImageLoadingProgressListener jDImageLoadingProgressListener) {
        if (imageView == null) {
            return false;
        }
        String h2 = com.jingdong.app.mall.home.o.a.f.h(str);
        com.jingdong.app.mall.home.floor.common.i.m.c(imageView, h2);
        JDDisplayImageOptions jDDisplayImageOptions = f9401g;
        jDDisplayImageOptions.bitmapConfig(Bitmap.Config.ARGB_8888);
        int i2 = d;
        Object tag = imageView.getTag(i2);
        if (h2 != null && h2.equals(tag)) {
            Object tag2 = imageView.getTag(JDImageUtils.STATUS_TAG);
            if (tag2 != null && tag2.equals(1)) {
                return false;
            }
            if (tag2 != null && tag2.equals(2)) {
                return true;
            }
        }
        j(h2, imageView, jDDisplayImageOptions, false, jDImageLoadingListener, jDImageLoadingProgressListener);
        imageView.setTag(i2, h2);
        return false;
    }

    public static void u(ImageView imageView, String str) {
        o(imageView, str, b, Bitmap.Config.ARGB_8888, null);
    }

    public static JDDisplayImageOptions v() {
        return f9400f;
    }

    public static Drawable w() {
        return new ColorDrawable(a);
    }

    public static Bitmap x(Bitmap bitmap, float f2) {
        if (!com.jingdong.app.mall.home.o.a.o.a(bitmap) || bitmap.getHeight() == f2) {
            return bitmap;
        }
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            float f3 = width;
            float f4 = height;
            matrix.setRectToRect(new RectF(0.0f, 0.0f, f3, f4), new RectF(0.0f, 0.0f, (f3 * f2) / f4, f2), Matrix.ScaleToFit.FILL);
            return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static byte[] y(Bitmap bitmap, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        if (bitmap == null) {
            return null;
        }
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i2 = (int) (width * f2);
            if (width >= 10 && height >= 10 && i2 < width) {
                ByteBuffer order = ByteBuffer.allocate(84).order(ByteOrder.nativeOrder());
                Integer num = 1;
                order.put(num.byteValue());
                Integer num2 = 2;
                order.put(num2.byteValue());
                Integer num3 = 2;
                order.put(num3.byteValue());
                Integer num4 = 9;
                order.put(num4.byteValue());
                order.putInt(0);
                order.putInt(0);
                order.putInt(0);
                order.putInt(0);
                order.putInt(0);
                order.putInt(0);
                order.putInt(0);
                order.putInt(i2);
                order.putInt(i2 + 1);
                order.putInt(0);
                order.putInt(height);
                for (int i3 = 0; i3 < 9; i3++) {
                    order.putInt(1);
                }
                byte[] array = order.array();
                if (NinePatch.isNinePatchChunk(array)) {
                    return array;
                }
                return null;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static void z(String str, JDImageLoadingListener jDImageLoadingListener) {
        try {
            JDImageUtils.loadImageToDiskCache(str, jDImageLoadingListener);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
