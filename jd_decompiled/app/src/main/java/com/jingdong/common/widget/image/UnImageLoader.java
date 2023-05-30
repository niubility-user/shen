package com.jingdong.common.widget.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes12.dex */
public class UnImageLoader {
    public static final int IMAGE_LOADER_FRESCO = 2;
    public static final int IMAGE_LOADER_GLIDE = 1;
    public static final int IMAGE_LOADER_JDIMAGE = 3;
    public static final int IMAGE_LOADER_OTHER = 4;
    private static UnImageLoader unImageLoader;
    private FrescoLoaderListener frescoListener;
    public int imageType = 3;
    private ImageLoaderListener loaderListener;

    private UnImageLoader() {
    }

    public static UnImageLoader getUnImageLoader() {
        UnImageLoader unImageLoader2;
        UnImageLoader unImageLoader3 = unImageLoader;
        if (unImageLoader3 != null) {
            return unImageLoader3;
        }
        synchronized (UnImageLoader.class) {
            if (unImageLoader == null) {
                unImageLoader = new UnImageLoader();
            }
            unImageLoader2 = unImageLoader;
        }
        return unImageLoader2;
    }

    private JDDisplayImageOptions initImageOptions() {
        JDDisplayImageOptions considerExifParams = JDDisplayImageOptions.createSimple().considerExifParams(true);
        considerExifParams.cacheInMemory(true);
        considerExifParams.cacheOnDisk(false);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = 16;
        considerExifParams.decodingOptions(options);
        return considerExifParams;
    }

    public <T> void displayImage(Context context, T t, String str) {
        int i2 = this.imageType;
        if (i2 == 1) {
            ImageLoaderListener imageLoaderListener = this.loaderListener;
            if (imageLoaderListener != null) {
                imageLoaderListener.onLoad(context, (ImageView) t, str);
            } else {
                Glide.with(context).load(str).into((ImageView) t);
            }
        } else if (i2 == 2) {
            FrescoLoaderListener frescoLoaderListener = this.frescoListener;
            if (frescoLoaderListener != null) {
                frescoLoaderListener.onload(context, (SimpleDraweeView) t, str);
            } else {
                ((SimpleDraweeView) t).setImageURI(Uri.parse(str));
            }
        } else if (i2 != 3) {
            ImageLoaderListener imageLoaderListener2 = this.loaderListener;
            if (imageLoaderListener2 != null) {
                imageLoaderListener2.onLoad(context, (ImageView) t, str);
            }
        } else {
            FrescoLoaderListener frescoLoaderListener2 = this.frescoListener;
            if (frescoLoaderListener2 != null) {
                frescoLoaderListener2.onload(context, (SimpleDraweeView) t, str);
            } else {
                JDImageUtils.displayImage(str, (SimpleDraweeView) t, initImageOptions());
            }
        }
    }

    public UnImageLoader init(int i2) {
        this.imageType = i2;
        return unImageLoader;
    }

    public boolean isImageView() {
        int i2 = this.imageType;
        return (i2 == 2 || i2 == 3) ? false : true;
    }

    public UnImageLoader setFrescoLoaderListener(FrescoLoaderListener frescoLoaderListener) {
        this.frescoListener = frescoLoaderListener;
        return unImageLoader;
    }

    public UnImageLoader setImageLoaderListener(ImageLoaderListener imageLoaderListener) {
        this.loaderListener = imageLoaderListener;
        return unImageLoader;
    }
}
