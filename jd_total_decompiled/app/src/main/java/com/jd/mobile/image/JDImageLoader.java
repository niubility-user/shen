package com.jd.mobile.image;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.logging.FLog;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.mobile.image.a.a;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import java.util.concurrent.Executor;

/* loaded from: classes17.dex */
public class JDImageLoader {
    private static final String TAG = "JDImageLoader";

    private JDImageLoader() {
    }

    public static void display(Uri uri, View view, JDDisplayImageOptions jDDisplayImageOptions, ImageRequestListener<ImageInfo> imageRequestListener) {
        a.h(view, uri, jDDisplayImageOptions, imageRequestListener);
    }

    public static void display(String str, View view) {
        display(str, view, null);
    }

    public static void display(String str, View view, JDDisplayImageOptions jDDisplayImageOptions) {
        display(str, view, jDDisplayImageOptions, (ImageRequestListener<ImageInfo>) null);
    }

    public static void display(String str, View view, JDDisplayImageOptions jDDisplayImageOptions, ImageRequestListener<ImageInfo> imageRequestListener) {
        display(str != null ? Uri.parse(str) : null, view, jDDisplayImageOptions, imageRequestListener);
    }

    public static void download(String str, String str2, ImageDownloadListener imageDownloadListener) {
        Uri parse = str != null ? Uri.parse(str) : null;
        if (UriUtil.isNetworkUri(parse)) {
            a.g(parse, str2, imageDownloadListener);
        } else {
            FLog.wtf(TAG, "The image is not a valid network resource!");
        }
    }

    public static void getBitmap(String str, JDDisplayImageOptions jDDisplayImageOptions, ImageRequestListener<Bitmap> imageRequestListener) {
        getBitmap(str, jDDisplayImageOptions, imageRequestListener, CallerThreadExecutor.getInstance());
    }

    public static void getBitmap(String str, JDDisplayImageOptions jDDisplayImageOptions, ImageRequestListener<Bitmap> imageRequestListener, Executor executor) {
        String str2;
        Uri parse = str != null ? Uri.parse(str) : null;
        if (parse == null) {
            FLog.wtf(TAG, "The imageSource of the image is inValid!");
            return;
        }
        FLog.d(TAG, "getBitmap:" + str);
        if (jDDisplayImageOptions == null) {
            str2 = "getBitmap:" + JDDisplayImageOptions.createSimple().getReferer();
        } else {
            str2 = "getBitmap:" + jDDisplayImageOptions.getReferer();
        }
        FLog.d(TAG, str2);
        a.f(parse, jDDisplayImageOptions, imageRequestListener, executor);
    }

    public static void getEncodedImage(String str, JDDisplayImageOptions jDDisplayImageOptions, ImageRequestListener<EncodedImage> imageRequestListener) {
        getEncodedImage(str, jDDisplayImageOptions, imageRequestListener, CallerThreadExecutor.getInstance());
    }

    public static void getEncodedImage(String str, JDDisplayImageOptions jDDisplayImageOptions, ImageRequestListener<EncodedImage> imageRequestListener, Executor executor) {
        Uri parse = str != null ? Uri.parse(str) : null;
        if (parse == null) {
            FLog.wtf(TAG, "The imageSource of the image is inValid!");
        } else {
            a.l(parse, jDDisplayImageOptions, imageRequestListener, executor);
        }
    }

    public static void prefetchToDiskCache(String str, ImageRequestListener<String> imageRequestListener) {
        JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
        FLog.d(TAG, "prefetchToDiskCache:" + str);
        FLog.d(TAG, "prefetchToDiskCache:" + createSimple.getReferer());
        a.i(str, imageRequestListener);
    }
}
