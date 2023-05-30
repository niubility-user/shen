package com.jingdong.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.mobile.image.utils.AvifDecoderUtil;
import com.jd.mobile.image.utils.c;
import com.jingdong.JdImageToolKit;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.app.util.image.listener.JDImageLoadingProgressListener;
import com.jingdong.jdsdk.image.JDFrescoUtils;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import com.jingdong.jdsdk.widget.ExceptionDrawable;
import java.io.File;

/* loaded from: classes6.dex */
public class JDImageUtils {
    public static final String FAKE_URI_EMPTY = "This string represent the uri is empty";
    public static final int STATUS_FAILED = 3;
    public static final int STATUS_LOADING = 1;
    public static final int STATUS_SUCCESS = 2;
    public static final int STATUS_TAG = R.id.jdImageLoader_status_tag;
    private static final String TAG = "JDImageUtils";
    private static Handler mHandler;

    public static void cancelDisplayTask(ImageView imageView) {
        JDFrescoUtils.cancelDisplayTask(imageView);
    }

    public static void displayImage(String str, ImageView imageView) {
        displayImage(str, imageView, null, null, null);
    }

    public static void displayImage(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions) {
        displayImage(str, imageView, jDDisplayImageOptions, null, null);
    }

    public static void displayImage(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener) {
        displayImage(str, imageView, jDDisplayImageOptions, jDImageLoadingListener, null);
    }

    public static void displayImage(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener, JDImageLoadingProgressListener jDImageLoadingProgressListener) {
        displayImage(str, imageView, jDDisplayImageOptions, true, jDImageLoadingListener, null);
    }

    public static void displayImage(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, boolean z) {
        displayImage(str, imageView, jDDisplayImageOptions, z, null, null);
    }

    public static void displayImage(String str, final ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, final boolean z, final JDImageLoadingListener jDImageLoadingListener, final JDImageLoadingProgressListener jDImageLoadingProgressListener) {
        Drawable imageForEmptyUri;
        if (imageView == null) {
            return;
        }
        if (jDDisplayImageOptions == null) {
            jDDisplayImageOptions = JDDisplayImageOptions.createSimple();
        }
        String a = c.a(AvifDecoderUtil.getTransformImageUri(str, jDDisplayImageOptions.isInShowRetry(), jDDisplayImageOptions.getReferer()), jDDisplayImageOptions.isInShowRetry(), jDDisplayImageOptions.getReferer());
        if (!TextUtils.isEmpty(a) && a.endsWith(".avif") && (!AvifDecoderUtil.isAVIFEnable() || !AvifDecoderUtil.isHasAddAVIFDecoder())) {
            a = a.substring(0, a.lastIndexOf(".avif"));
        }
        if (TextUtils.isEmpty(a) && (imageForEmptyUri = jDDisplayImageOptions.getImageForEmptyUri(JdImageToolKit.getEngine().getApplicationContext().getResources())) != null) {
            imageView.setImageDrawable(imageForEmptyUri);
            return;
        }
        imageView.setTag(STATUS_TAG, 1);
        StringBuilder sb = new StringBuilder();
        sb.append("is SimpleDraweeView:");
        boolean z2 = imageView instanceof SimpleDraweeView;
        sb.append(z2);
        FLog.d(TAG, sb.toString());
        final JDDisplayImageOptions jDDisplayImageOptions2 = jDDisplayImageOptions;
        JDImageLoadingListener jDImageLoadingListener2 = new JDImageLoadingListener() { // from class: com.jingdong.common.utils.JDImageUtils.1
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str2, View view) {
                JDImageLoadingListener jDImageLoadingListener3 = jDImageLoadingListener;
                if (jDImageLoadingListener3 != null) {
                    jDImageLoadingListener3.onLoadingCancelled(str2, view);
                }
                FLog.i(JDImageUtils.TAG, "onLoadingCancelled = " + str2);
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                JDImageLoadingListener jDImageLoadingListener3 = jDImageLoadingListener;
                if (jDImageLoadingListener3 != null) {
                    jDImageLoadingListener3.onLoadingComplete(str2, view, bitmap);
                }
                if (view != null && (view instanceof ImageView)) {
                    ImageView imageView2 = (ImageView) view;
                    imageView2.setTag(JDImageUtils.STATUS_TAG, 2);
                    if (JdImageToolKit.getEngine().getImageControllerImpl().needNoImage()) {
                        imageView2.setOnLongClickListener(null);
                        imageView2.setLongClickable(false);
                    }
                }
                FLog.i(JDImageUtils.TAG, "onLoadingComplete = " + str2);
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                if (str2.endsWith(".dpg") || str2.endsWith(".dpg.webp")) {
                    JdImageToolKit.getEngine().getExceptionReportHandlerImpl().reportDpgPicMta(str2);
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:69:0x00a6  */
            /* JADX WARN: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onLoadingFailed(java.lang.String r10, android.view.View r11, com.jingdong.app.util.image.assist.JDFailReason r12) {
                /*
                    r9 = this;
                    boolean r0 = android.text.TextUtils.isEmpty(r10)
                    r1 = 1
                    r2 = 0
                    r3 = -1
                    if (r0 != 0) goto L4d
                    java.lang.String r0 = ".avif"
                    boolean r4 = r10.endsWith(r0)
                    if (r4 == 0) goto L4d
                    boolean r4 = com.jd.mobile.image.utils.AvifDecoderUtil.isAVIFRetry()
                    if (r4 == 0) goto L4d
                    if (r12 == 0) goto L4d
                    java.lang.Throwable r4 = r12.getCause()
                    boolean r4 = r4 instanceof com.facebook.imagepipeline.decoder.DecodeException
                    if (r4 == 0) goto L4d
                    com.jingdong.JdImageToolKit$ImageConfigEngine r11 = com.jingdong.JdImageToolKit.getEngine()
                    com.jd.mobile.image.config.IExceptionReportHandler r11 = r11.getExceptionReportHandlerImpl()
                    com.jingdong.app.util.image.JDDisplayImageOptions r4 = r2
                    java.lang.String r4 = r4.getReferer()
                    r11.reportBitmapException(r10, r12, r4, r3)
                    int r11 = r10.lastIndexOf(r0)
                L36:
                    java.lang.String r3 = r10.substring(r2, r11)
                    com.jingdong.app.util.image.JDDisplayImageOptions r11 = r2
                    r11.setInShowRetry(r1)
                    android.widget.ImageView r4 = r3
                    com.jingdong.app.util.image.JDDisplayImageOptions r5 = r2
                    boolean r6 = r4
                    com.jingdong.app.util.image.listener.JDImageLoadingListener r7 = r1
                    com.jingdong.app.util.image.listener.JDImageLoadingProgressListener r8 = r5
                    com.jingdong.common.utils.JDImageUtils.displayImage(r3, r4, r5, r6, r7, r8)
                    goto La4
                L4d:
                    boolean r0 = android.text.TextUtils.isEmpty(r10)
                    if (r0 != 0) goto L8b
                    java.lang.String r0 = ".gif.webp"
                    boolean r0 = r10.endsWith(r0)
                    if (r0 == 0) goto L8b
                    java.lang.String r0 = "http"
                    boolean r0 = r10.startsWith(r0)
                    if (r0 == 0) goto L8b
                    boolean r0 = com.jd.mobile.image.utils.c.d()
                    if (r0 == 0) goto L8b
                    if (r12 == 0) goto L8b
                    java.lang.Throwable r0 = r12.getCause()
                    boolean r0 = r0 instanceof java.lang.Exception
                    if (r0 == 0) goto L8b
                    com.jingdong.JdImageToolKit$ImageConfigEngine r11 = com.jingdong.JdImageToolKit.getEngine()
                    com.jd.mobile.image.config.IExceptionReportHandler r11 = r11.getExceptionReportHandlerImpl()
                    com.jingdong.app.util.image.JDDisplayImageOptions r0 = r2
                    java.lang.String r0 = r0.getReferer()
                    r11.reportBitmapException(r10, r12, r0, r3)
                    java.lang.String r11 = ".webp"
                    int r11 = r10.lastIndexOf(r11)
                    goto L36
                L8b:
                    com.jingdong.app.util.image.listener.JDImageLoadingListener r0 = r1
                    if (r0 == 0) goto L92
                    r0.onLoadingFailed(r10, r11, r12)
                L92:
                    if (r11 == 0) goto La4
                    boolean r0 = r11 instanceof android.widget.ImageView
                    if (r0 == 0) goto La4
                    android.widget.ImageView r11 = (android.widget.ImageView) r11
                    int r0 = com.jingdong.common.utils.JDImageUtils.STATUS_TAG
                    r1 = 3
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                    r11.setTag(r0, r1)
                La4:
                    if (r12 == 0) goto Ld5
                    java.lang.StringBuilder r11 = new java.lang.StringBuilder
                    r11.<init>()
                    java.lang.String r0 = "onLoadingFailed = "
                    r11.append(r0)
                    r11.append(r10)
                    java.lang.String r10 = r11.toString()
                    java.lang.String r11 = "JDImageUtils"
                    com.facebook.common.logging.FLog.e(r11, r10)
                    java.lang.StringBuilder r10 = new java.lang.StringBuilder
                    r10.<init>()
                    r10.append(r0)
                    com.jingdong.app.util.image.assist.JDFailType r0 = r12.getType()
                    r10.append(r0)
                    r10.append(r12)
                    java.lang.String r10 = r10.toString()
                    com.facebook.common.logging.FLog.e(r11, r10)
                Ld5:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.JDImageUtils.AnonymousClass1.onLoadingFailed(java.lang.String, android.view.View, com.jingdong.app.util.image.assist.JDFailReason):void");
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str2, View view) {
                JDImageLoadingListener jDImageLoadingListener3 = jDImageLoadingListener;
                if (jDImageLoadingListener3 != null) {
                    jDImageLoadingListener3.onLoadingStarted(str2, view);
                }
                FLog.i(JDImageUtils.TAG, "onLoadingStarted = " + str2);
            }
        };
        JDDisplayImageOptions cloneFrom = JDDisplayImageOptions.createSimple().cloneFrom(jDDisplayImageOptions);
        if (z2) {
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) imageView;
            if (TextUtils.isEmpty(a)) {
                a = FAKE_URI_EMPTY;
            }
            JDFrescoUtils.disPlayImage(a, simpleDraweeView, cloneFrom, z, jDImageLoadingListener2);
        }
    }

    public static void displayImage(String str, ImageView imageView, JDImageLoadingListener jDImageLoadingListener) {
        displayImage(str, imageView, null, jDImageLoadingListener, null);
    }

    public static void displayImageOnlyCache(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener) {
        if (!TextUtils.isEmpty(str) && (imageView instanceof SimpleDraweeView)) {
            JDFrescoUtils.displayImageOnlyCache(str, (SimpleDraweeView) imageView, jDDisplayImageOptions, jDImageLoadingListener);
        }
    }

    public static Bitmap getBitmap(Context context, int i2) {
        if (context == null || i2 < 1) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeResource(context.getResources(), i2, options);
        } catch (Throwable th) {
            FLog.e(TAG, th.getMessage());
            GlobalImageCache.getLruBitmapCache().clean();
            return BitmapFactory.decodeResource(context.getResources(), i2, options);
        }
    }

    public static Bitmap getBitmapWithJpg16(Context context, int i2) {
        if (context == null || i2 < 1) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeResource(context.getResources(), i2, options);
    }

    private static Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }

    public static File getImageDiskCacheFile(String str) {
        return JDFrescoUtils.getImageDirCacheFile(str);
    }

    public static Bitmap imageCrop(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i2 = width > height ? height : width;
        return Bitmap.createBitmap(bitmap, width > height ? (width - height) / 2 : 0, width > height ? 0 : (height - width) / 2, i2, i2, (Matrix) null, false);
    }

    public static void imageToGray(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            drawable.mutate();
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0.0f);
            drawable.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            drawable.invalidateSelf();
        }
    }

    @Deprecated
    public static void loadImage(String str, int i2, int i3, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener) {
        loadImage(str, i2, i3, jDDisplayImageOptions, jDImageLoadingListener, null);
    }

    @Deprecated
    public static void loadImage(String str, int i2, int i3, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener, JDImageLoadingProgressListener jDImageLoadingProgressListener) {
        loadImage(str, null, i2, i3, jDDisplayImageOptions, jDImageLoadingListener, jDImageLoadingProgressListener);
    }

    @Deprecated
    public static void loadImage(String str, int i2, int i3, JDImageLoadingListener jDImageLoadingListener) {
        loadImage(str, i2, i3, null, jDImageLoadingListener, null);
    }

    @Deprecated
    public static void loadImage(String str, ImageView imageView, int i2, int i3, final JDDisplayImageOptions jDDisplayImageOptions, final JDImageLoadingListener jDImageLoadingListener, JDImageLoadingProgressListener jDImageLoadingProgressListener) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (jDDisplayImageOptions == null) {
            jDDisplayImageOptions = new JDDisplayImageOptions();
        }
        JDFrescoUtils.loadImage(str, imageView, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.utils.JDImageUtils.2
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str2, View view) {
                JDImageLoadingListener jDImageLoadingListener2 = jDImageLoadingListener;
                if (jDImageLoadingListener2 != null) {
                    jDImageLoadingListener2.onLoadingCancelled(str2, view);
                }
                FLog.i(JDImageUtils.TAG, "onLoadingCancelled = " + str2);
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                JDImageLoadingListener jDImageLoadingListener2 = jDImageLoadingListener;
                if (jDImageLoadingListener2 != null) {
                    jDImageLoadingListener2.onLoadingComplete(str2, view, bitmap);
                }
                FLog.i(JDImageUtils.TAG, "onLoadingComplete = " + str2);
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                JDImageLoadingListener jDImageLoadingListener2 = jDImageLoadingListener;
                if (jDImageLoadingListener2 != null) {
                    jDImageLoadingListener2.onLoadingFailed(str2, view, jDFailReason);
                }
                JdImageToolKit.getEngine().getExceptionReportHandlerImpl().reportBitmapException(str2, jDFailReason, jDDisplayImageOptions.getReferer(), -1);
                FLog.i(JDImageUtils.TAG, "onLoadingFailed = " + jDFailReason.getType() + jDFailReason.toString());
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str2, View view) {
                JDImageLoadingListener jDImageLoadingListener2 = jDImageLoadingListener;
                if (jDImageLoadingListener2 != null) {
                    jDImageLoadingListener2.onLoadingStarted(str2, view);
                }
                FLog.i(JDImageUtils.TAG, "onLoadingStarted = ");
            }
        });
    }

    @Deprecated
    public static void loadImage(String str, ImageView imageView, JDImageLoadingListener jDImageLoadingListener) {
        loadImage(str, imageView, 0, 0, null, jDImageLoadingListener, null);
    }

    @Deprecated
    public static void loadImage(String str, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener) {
        loadImage(str, 0, 0, jDDisplayImageOptions, jDImageLoadingListener, null);
    }

    @Deprecated
    public static void loadImage(String str, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener, JDImageLoadingProgressListener jDImageLoadingProgressListener) {
        loadImage(str, 0, 0, jDDisplayImageOptions, jDImageLoadingListener, null);
    }

    @Deprecated
    public static void loadImage(String str, JDImageLoadingListener jDImageLoadingListener) {
        loadImage(str, 0, 0, null, jDImageLoadingListener, null);
    }

    public static void loadImageToDiskCache(String str, final JDImageLoadingListener jDImageLoadingListener) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        final JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        FLog.d(TAG, "loadImageToDiskCache:" + str);
        FLog.d(TAG, "loadImageToDiskCache_refer:" + jDDisplayImageOptions.getReferer());
        JDFrescoUtils.loadImageToDiskCache(str, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.utils.JDImageUtils.3
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str2, View view) {
                JDImageLoadingListener jDImageLoadingListener2 = jDImageLoadingListener;
                if (jDImageLoadingListener2 != null) {
                    jDImageLoadingListener2.onLoadingCancelled(str2, view);
                }
                FLog.i(JDImageUtils.TAG, "onLoadingCancelled = " + str2);
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                JDImageLoadingListener jDImageLoadingListener2 = jDImageLoadingListener;
                if (jDImageLoadingListener2 != null) {
                    jDImageLoadingListener2.onLoadingComplete(str2, view, bitmap);
                }
                FLog.i(JDImageUtils.TAG, "onLoadingComplete = " + str2);
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                JDImageLoadingListener jDImageLoadingListener2 = jDImageLoadingListener;
                if (jDImageLoadingListener2 != null) {
                    jDImageLoadingListener2.onLoadingFailed(str2, view, jDFailReason);
                }
                JdImageToolKit.getEngine().getExceptionReportHandlerImpl().reportBitmapException(str2, jDFailReason, jDDisplayImageOptions.getReferer(), -1);
                FLog.i(JDImageUtils.TAG, "onLoadingFailed = " + jDFailReason.getType() + jDFailReason.toString());
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str2, View view) {
                JDImageLoadingListener jDImageLoadingListener2 = jDImageLoadingListener;
                if (jDImageLoadingListener2 != null) {
                    jDImageLoadingListener2.onLoadingStarted(str2, view);
                }
                FLog.i(JDImageUtils.TAG, "onLoadingStarted = ");
            }
        });
    }

    private static void setImageForEmptyUri(ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, boolean z) {
        if (imageView != null) {
            Drawable imageForEmptyUri = jDDisplayImageOptions != null ? jDDisplayImageOptions.getImageForEmptyUri(JdImageToolKit.getEngine().getApplicationContext().getResources()) : null;
            if (imageForEmptyUri == null && z) {
                imageForEmptyUri = new ExceptionDrawable(JdImageToolKit.getEngine().getApplicationContext(), JdImageToolKit.getEngine().getApplicationContext().getString(R.string.image_app_name));
            }
            if (imageForEmptyUri != null) {
                imageView.setImageDrawable(imageForEmptyUri);
            }
        }
    }
}
