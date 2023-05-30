package com.jingdong.pdj.libcore.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.FloatRange;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.EncodedImage;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.app.util.image.listener.JDImageLoadingProgressListener;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.pdj.libcore.R;
import com.jingdong.remoteimage.CalorieImageUtil;
import com.jingdong.remoteimage.RemoteImageManager;
import com.jingdong.sdk.oklog.OKLog;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes7.dex */
public class HourlyGoImageLoadUtil {
    public static JDDisplayImageOptions sHourGoOptions;
    public static Drawable sTransparentDrawable = new ColorDrawable(0);
    public static Drawable sWhiteDrawable = new ColorDrawable(-1);
    public static final int LAST_URL = R.id.hourly_go_image_last_url;
    public static final JDDisplayImageOptions imageNullOptions = new JDDisplayImageOptions().showImageOnFail((Drawable) null).showImageOnLoading((Drawable) null).showImageForEmptyUri((Drawable) null);
    public static JDDisplayImageOptions sTransparentOptions = new JDDisplayImageOptions().showImageOnFail(sTransparentDrawable).showImageOnLoading(sTransparentDrawable).showImageForEmptyUri(sTransparentDrawable);
    public static JDDisplayImageOptions sWhiteOptions = new JDDisplayImageOptions().showImageOnFail(sWhiteDrawable).showImageOnLoading(sWhiteDrawable).showImageForEmptyUri(sWhiteDrawable);

    static {
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        int i2 = R.drawable.hourly_go_default_icon;
        sHourGoOptions = jDDisplayImageOptions.showImageForEmptyUri(i2).showImageOnFail(i2).showImageOnLoading(i2);
    }

    public static String checkGifUrl(String str) {
        try {
            return (TextUtils.isEmpty(str) || str.length() < 6 || !str.toLowerCase().endsWith(WareBusinessMagicHeadPicInfoEntity.IMAGE_TYPE_GIF)) ? str : str.substring(0, str.length() - 3).concat(WareBusinessMagicHeadPicInfoEntity.IMAGE_TYPE_GIF);
        } catch (Exception e2) {
            e2.printStackTrace();
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
            return str;
        }
    }

    public static void checkGifView(View view, String str) {
        if (HourlyGoMethodSwitchUtil.clearGifCache() && view != null && !TextUtils.isEmpty(str) && (str.toLowerCase().endsWith(".gif") || str.toLowerCase().endsWith(".gif.webp"))) {
            view.setTag(LAST_URL, null);
        }
    }

    public static void display9RemoteImage(View view, String str, JDDisplayImageOptions jDDisplayImageOptions) {
        display9RemoteImage(view, str, jDDisplayImageOptions, null);
    }

    public static void displayImageUseSuper(String str, SimpleDraweeView simpleDraweeView) {
        displayImageUseSuper(str, simpleDraweeView, null, null, null);
    }

    public static void displayRemoteImage(String str, String str2, JDImageLoadingListener jDImageLoadingListener) {
        String imageUrlById = RemoteImageManager.getImageUrlById(str, str2);
        if (TextUtils.isEmpty(imageUrlById)) {
            return;
        }
        JDImageUtils.loadImage(imageUrlById, jDImageLoadingListener);
    }

    public static Bitmap getScaleBitmap(Bitmap bitmap, float f2) {
        if (bitmap == null || bitmap.getHeight() == f2) {
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
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(th));
            return null;
        }
    }

    public static byte[] getXNinePatchChunk(Bitmap bitmap, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        if (bitmap == null) {
            return null;
        }
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i2 = (int) (width * f2);
            if (width >= 10 && height >= 10 && i2 < width) {
                ByteBuffer order = ByteBuffer.allocate(84).order(ByteOrder.nativeOrder());
                order.put(HourlyGoNumberUtil.parseInt(1).byteValue());
                order.put(HourlyGoNumberUtil.parseInt(2).byteValue());
                order.put(HourlyGoNumberUtil.parseInt(2).byteValue());
                order.put(HourlyGoNumberUtil.parseInt(9).byteValue());
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
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(th));
            return null;
        }
    }

    public static void display9RemoteImage(final View view, String str, JDDisplayImageOptions jDDisplayImageOptions, final ImageRequestListener<EncodedImage> imageRequestListener) {
        try {
            String imageUrlSync = CalorieImageUtil.getImageUrlSync(str);
            if (TextUtils.isEmpty(imageUrlSync)) {
                return;
            }
            JDImageLoader.getEncodedImage(imageUrlSync, jDDisplayImageOptions, new ImageRequestListener<EncodedImage>() { // from class: com.jingdong.pdj.libcore.utils.HourlyGoImageLoadUtil.2
                @Override // com.jd.mobile.image.ImageRequestListener
                public void onCancel() {
                    ImageRequestListener imageRequestListener2 = imageRequestListener;
                    if (imageRequestListener2 != null) {
                        imageRequestListener2.onCancel();
                    }
                }

                @Override // com.jd.mobile.image.ImageRequestListener
                public void onFailure(Throwable th) {
                    th.printStackTrace();
                    ImageRequestListener imageRequestListener2 = imageRequestListener;
                    if (imageRequestListener2 != null) {
                        imageRequestListener2.onFailure(th);
                    }
                }

                @Override // com.jd.mobile.image.ImageRequestListener
                public void onSuccess(EncodedImage encodedImage) {
                    Drawable bitmapDrawable;
                    Bitmap decodeStream = BitmapFactory.decodeStream(encodedImage.getInputStream());
                    byte[] ninePatchChunk = decodeStream.getNinePatchChunk();
                    if (ninePatchChunk != null && NinePatch.isNinePatchChunk(ninePatchChunk)) {
                        bitmapDrawable = new NinePatchDrawable(JdSdk.getInstance().getApplicationContext().getResources(), new NinePatch(decodeStream, ninePatchChunk, null));
                    } else {
                        bitmapDrawable = new BitmapDrawable(JdSdk.getInstance().getApplicationContext().getResources(), decodeStream);
                    }
                    view.setBackground(bitmapDrawable);
                    ImageRequestListener imageRequestListener2 = imageRequestListener;
                    if (imageRequestListener2 != null) {
                        imageRequestListener2.onSuccess(encodedImage);
                    }
                }
            }, UiThreadImmediateExecutorService.getInstance());
        } catch (Exception e2) {
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
        }
    }

    public static void displayImageUseSuper(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions) {
        displayImageUseSuper(str, imageView, jDDisplayImageOptions, null, null);
    }

    public static void displayImageUseSuper(String str, ImageView imageView, JDImageLoadingListener jDImageLoadingListener) {
        displayImageUseSuper(str, imageView, null, jDImageLoadingListener, null);
    }

    public static void displayImageUseSuper(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener) {
        displayImageUseSuper(str, imageView, jDDisplayImageOptions, jDImageLoadingListener, null);
    }

    public static void displayRemoteImage(SimpleDraweeView simpleDraweeView, String str, String str2) {
        String imageUrlById = RemoteImageManager.getImageUrlById(str, str2);
        if (TextUtils.isEmpty(imageUrlById)) {
            return;
        }
        displayImageUseSuper(imageUrlById, simpleDraweeView);
    }

    public static void displayImageUseSuper(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, boolean z) {
        displayImageUseSuper(str, imageView, jDDisplayImageOptions, z, null, null);
    }

    public static void displayImageUseSuper(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener, JDImageLoadingProgressListener jDImageLoadingProgressListener) {
        displayImageUseSuper(str, imageView, jDDisplayImageOptions, true, jDImageLoadingListener, jDImageLoadingProgressListener);
    }

    public static void displayImageUseSuper(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, boolean z, JDImageLoadingListener jDImageLoadingListener, JDImageLoadingProgressListener jDImageLoadingProgressListener) {
        if (jDDisplayImageOptions == null) {
            jDDisplayImageOptions = imageNullOptions;
        }
        JDDisplayImageOptions jDDisplayImageOptions2 = jDDisplayImageOptions;
        String checkGifUrl = checkGifUrl(str);
        checkGifView(imageView, checkGifUrl);
        jDDisplayImageOptions2.bitmapConfig(Bitmap.Config.ARGB_8888);
        imageView.setTag(LAST_URL, checkGifUrl);
        try {
            JDImageUtils.displayImage(checkGifUrl, imageView, jDDisplayImageOptions2, z, jDImageLoadingListener, jDImageLoadingProgressListener);
        } catch (Exception e2) {
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
        }
    }

    public static void displayRemoteImage(String str, final SimpleDraweeView simpleDraweeView, final String str2, final String str3) {
        if (TextUtils.isEmpty(str)) {
            displayRemoteImage(simpleDraweeView, str2, str3);
        } else {
            displayImageUseSuper(str, simpleDraweeView, new JDSimpleImageLoadingListener() { // from class: com.jingdong.pdj.libcore.utils.HourlyGoImageLoadUtil.1
                @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str4, View view, JDFailReason jDFailReason) {
                    HourlyGoImageLoadUtil.displayRemoteImage(SimpleDraweeView.this, str2, str3);
                }
            });
        }
    }
}
