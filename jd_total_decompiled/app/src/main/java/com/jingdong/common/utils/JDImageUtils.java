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
import com.facebook.imagepipeline.decoder.DecodeException;
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

            /* JADX WARN: Removed duplicated region for block: B:105:0x00a6  */
            /* JADX WARN: Removed duplicated region for block: B:107:? A[RETURN, SYNTHETIC] */
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                int lastIndexOf;
                if (!TextUtils.isEmpty(str2) && str2.endsWith(".avif") && AvifDecoderUtil.isAVIFRetry() && jDFailReason != null && (jDFailReason.getCause() instanceof DecodeException)) {
                    JdImageToolKit.getEngine().getExceptionReportHandlerImpl().reportBitmapException(str2, jDFailReason, jDDisplayImageOptions2.getReferer(), -1);
                    lastIndexOf = str2.lastIndexOf(".avif");
                } else if (TextUtils.isEmpty(str2) || !str2.endsWith(".gif.webp") || !str2.startsWith("http") || !c.d() || jDFailReason == null || !(jDFailReason.getCause() instanceof Exception)) {
                    JDImageLoadingListener jDImageLoadingListener3 = jDImageLoadingListener;
                    if (jDImageLoadingListener3 != null) {
                        jDImageLoadingListener3.onLoadingFailed(str2, view, jDFailReason);
                    }
                    if (view != null && (view instanceof ImageView)) {
                        ((ImageView) view).setTag(JDImageUtils.STATUS_TAG, 3);
                    }
                    if (jDFailReason == null) {
                        FLog.e(JDImageUtils.TAG, "onLoadingFailed = " + str2);
                        FLog.e(JDImageUtils.TAG, "onLoadingFailed = " + jDFailReason.getType() + jDFailReason);
                        return;
                    }
                    return;
                } else {
                    JdImageToolKit.getEngine().getExceptionReportHandlerImpl().reportBitmapException(str2, jDFailReason, jDDisplayImageOptions2.getReferer(), -1);
                    lastIndexOf = str2.lastIndexOf(".webp");
                }
                String substring = str2.substring(0, lastIndexOf);
                jDDisplayImageOptions2.setInShowRetry(true);
                JDImageUtils.displayImage(substring, imageView, jDDisplayImageOptions2, z, jDImageLoadingListener, jDImageLoadingProgressListener);
                if (jDFailReason == null) {
                }
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
