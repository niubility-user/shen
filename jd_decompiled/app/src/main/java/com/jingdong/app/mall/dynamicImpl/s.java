package com.jingdong.app.mall.dynamicImpl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.widget.ImageView;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IImageLoader;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.mall.R;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.app.util.image.placeholder.JDPlaceholderDrawable;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.JdSdk;
import java.io.File;

/* loaded from: classes3.dex */
public class s implements IImageLoader {

    /* loaded from: classes3.dex */
    class a implements ImageRequestListener<ImageInfo> {
        final /* synthetic */ IImageLoader.ImageDisplayListener a;

        a(s sVar, IImageLoader.ImageDisplayListener imageDisplayListener) {
            this.a = imageDisplayListener;
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(ImageInfo imageInfo) {
            if (this.a != null) {
                IImageLoader.ImageInfos imageInfos = new IImageLoader.ImageInfos();
                imageInfos.width = imageInfo.getWidth();
                imageInfos.height = imageInfo.getHeight();
                this.a.onSuccess(imageInfos);
            }
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onCancel() {
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onFailure(Throwable th) {
        }
    }

    /* loaded from: classes3.dex */
    class b implements ImageRequestListener<EncodedImage> {
        final /* synthetic */ IImageLoader.ImageRequestListener a;

        b(s sVar, IImageLoader.ImageRequestListener imageRequestListener) {
            this.a = imageRequestListener;
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(EncodedImage encodedImage) {
            Object bitmapDrawable;
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(encodedImage.getInputStream());
                byte[] ninePatchChunk = decodeStream.getNinePatchChunk();
                if (ninePatchChunk != null && NinePatch.isNinePatchChunk(ninePatchChunk)) {
                    bitmapDrawable = new NinePatchDrawable(DynamicSdk.getEngine().getContext().getResources(), new NinePatch(decodeStream, ninePatchChunk, null));
                } else {
                    bitmapDrawable = new BitmapDrawable(DynamicSdk.getEngine().getContext().getResources(), decodeStream);
                }
                IImageLoader.ImageRequestListener imageRequestListener = this.a;
                if (imageRequestListener != null) {
                    imageRequestListener.onSuccess(bitmapDrawable);
                }
            } catch (Exception unused) {
            }
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onCancel() {
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onFailure(Throwable th) {
        }
    }

    /* loaded from: classes3.dex */
    class c implements ImageRequestListener<Bitmap> {
        final /* synthetic */ IImageLoader.ImageRequestListener a;

        c(s sVar, IImageLoader.ImageRequestListener imageRequestListener) {
            this.a = imageRequestListener;
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(Bitmap bitmap) {
            IImageLoader.ImageRequestListener imageRequestListener = this.a;
            if (imageRequestListener != null) {
                imageRequestListener.onSuccess(bitmap);
            }
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onCancel() {
            IImageLoader.ImageRequestListener imageRequestListener = this.a;
            if (imageRequestListener != null) {
                imageRequestListener.onCancel();
            }
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onFailure(Throwable th) {
            IImageLoader.ImageRequestListener imageRequestListener = this.a;
            if (imageRequestListener != null) {
                imageRequestListener.onFailure(th);
            }
        }
    }

    @Override // com.jd.dynamic.base.interfaces.IImageLoader
    public void displayImage(ImageView imageView, String str, int i2, IImageLoader.ImageDisplayListener imageDisplayListener) {
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        if (i2 != -1) {
            jDDisplayImageOptions.setDisplayer(new JDRoundedBitmapDisplayer(i2));
        }
        JDImageLoader.display(str, imageView, jDDisplayImageOptions, new a(this, imageDisplayListener));
    }

    @Override // com.jd.dynamic.base.interfaces.IImageLoader
    public File getCacheImageFile(String str) {
        return JDImageUtils.getImageDiskCacheFile(str);
    }

    @Override // com.jd.dynamic.base.interfaces.IImageLoader
    public Drawable getDefaultPlaceHolder() {
        if (JdSdk.getInstance().getApplicationContext() != null) {
            return JdSdk.getInstance().getApplicationContext().getResources().getDrawable(R.drawable.dyn_default_placeholder);
        }
        return new JDPlaceholderDrawable(7);
    }

    @Override // com.jd.dynamic.base.interfaces.IImageLoader
    public void loadImage(String str, IImageLoader.ImageRequestListener imageRequestListener) {
        JDImageLoader.getBitmap(str, new JDDisplayImageOptions(), new c(this, imageRequestListener), UiThreadImmediateExecutorService.getInstance());
    }

    @Override // com.jd.dynamic.base.interfaces.IImageLoader
    public void loadNineImage(String str, IImageLoader.ImageRequestListener<Drawable> imageRequestListener) {
        JDImageLoader.getEncodedImage(str, null, new b(this, imageRequestListener), UiThreadImmediateExecutorService.getInstance());
    }
}
